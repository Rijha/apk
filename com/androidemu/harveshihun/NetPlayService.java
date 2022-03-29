package com.androidemu.harveshihun;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.os.Handler;
import android.os.Message;
import com.androidemu.Emulator;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ProtocolException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.util.UUID;
/* loaded from: classes.dex */
public class NetPlayService {
    private static final String BT_SERVICE_NAME = "Nesoid";
    private static final UUID BT_SERVICE_UUID = UUID.fromString("8f996e39-374d-466c-bd0c-e0ced64b4e54");
    private static final short CMD_FRAME_UPDATE = 2;
    private static final short CMD_HELLO = 1;
    private static final short CMD_POWER_ROM = 3;
    private static final short CMD_RESET_ROM = 4;
    private static final short CMD_SAVED_STATE = 5;
    public static final int E_CONNECTION_CLOSED = 3;
    public static final int E_CONNECT_FAILED = 1;
    public static final int E_PROTOCOL_INCOMPATIBLE = 2;
    private static final int MAX_SAVED_STATE_SIZE = 2097152;
    public static final int MESSAGE_CONNECTED = 1;
    public static final int MESSAGE_DISCONNECTED = 2;
    public static final int MESSAGE_POWER_ROM = 3;
    public static final int MESSAGE_RESET_ROM = 4;
    public static final int MESSAGE_SAVED_STATE = 5;
    private static final short PROTO_VERSION = 1;
    private Object frameLock = new Object();
    private Handler handler;
    private PacketInputStream inputStream;
    private boolean isServer;
    private int localFrameCount;
    private int maxFramesAhead;
    private NetThread netThread;
    private PacketOutputStream outputStream;
    private int remoteFrameCount;
    private int remoteKeys;
    private boolean waitOnMessage;

    public NetPlayService(Handler h) {
        this.handler = h;
    }

    public int tcpListen(InetAddress addr, int port) throws IOException {
        this.isServer = true;
        TCPServerThread t = new TCPServerThread(addr, port);
        int port2 = t.getLocalPort();
        start(t);
        return port2;
    }

    public void tcpConnect(InetAddress addr, int port) {
        this.isServer = false;
        start(new TCPClientThread(addr, port));
    }

    public void bluetoothListen() throws IOException {
        this.isServer = true;
        start(new BluetoothServerThread());
    }

    public void bluetoothConnect(String address) throws IOException {
        this.isServer = false;
        start(new BluetoothClientThread(address));
    }

    public final boolean isServer() {
        return this.isServer;
    }

    public final void setMaxFramesAhead(int max) {
        synchronized (this.frameLock) {
            this.maxFramesAhead = max;
            this.frameLock.notify();
        }
    }

    public void disconnect() {
        if (this.netThread != null) {
            this.netThread.interrupt();
            this.netThread.cancel();
            try {
                this.netThread.join();
            } catch (InterruptedException e) {
            }
            this.netThread = null;
            this.outputStream = null;
        }
    }

    private void sendHello() throws IOException {
        this.outputStream.writePacket(createPacket(1, 2).putShort(1));
    }

    public int sendFrameUpdate(int keys) throws IOException, InterruptedException {
        int i;
        ByteBuffer p = createPacket(CMD_FRAME_UPDATE, 8);
        p.putInt(this.localFrameCount);
        p.putInt(keys);
        this.outputStream.writePacket(p);
        synchronized (this.frameLock) {
            while (this.localFrameCount - this.remoteFrameCount > this.maxFramesAhead - 1) {
                this.frameLock.wait();
            }
            this.localFrameCount++;
            i = this.remoteKeys;
        }
        return i;
    }

    public void sendPowerROM() throws IOException {
        resetFrame();
        this.outputStream.writePacket(createPacket(CMD_POWER_ROM));
    }

    public void sendResetROM() throws IOException {
        resetFrame();
        this.outputStream.writePacket(createPacket(CMD_RESET_ROM));
    }

    public void sendSavedState(byte[] state) throws IOException {
        resetFrame();
        this.outputStream.writePacket(createPacket(CMD_SAVED_STATE, 4).putInt(state.length));
        this.outputStream.writeBytes(state);
    }

    private void start(NetThread t) {
        if (this.netThread != null) {
            throw new IllegalStateException();
        }
        this.netThread = t;
        this.netThread.start();
    }

    private void resetFrame() {
        this.remoteFrameCount = 0;
        this.localFrameCount = 0;
        this.remoteKeys = 0;
    }

    public void sendMessageReply() {
        resetFrame();
        synchronized (this) {
            if (this.waitOnMessage) {
                this.waitOnMessage = false;
                notify();
            }
        }
    }

    private synchronized void sendMessage(Message msg) {
        msg.sendToTarget();
        this.waitOnMessage = true;
        while (this.waitOnMessage) {
            try {
                wait();
            } catch (InterruptedException e) {
                this.waitOnMessage = false;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void manageConnection(InputStream in, OutputStream out) throws IOException {
        this.inputStream = new PacketInputStream(in);
        this.outputStream = new PacketOutputStream(out);
        if (this.isServer) {
            ByteBuffer p = this.inputStream.readPacket();
            if (p.getShort() != 1) {
                throw new ProtocolException();
            }
            handleHello(p);
        } else {
            sendHello();
            ByteBuffer p2 = this.inputStream.readPacket();
            if (p2.getShort() != 5) {
                throw new ProtocolException();
            }
            handleSavedState(p2);
        }
        sendMessage(this.handler.obtainMessage(1));
        while (true) {
            ByteBuffer p3 = this.inputStream.readPacket();
            if (p3 != null) {
                switch (p3.getShort()) {
                    case 2:
                        handleFrameUpdate(p3);
                        break;
                    case 3:
                        handlePowerROM(p3);
                        break;
                    case 4:
                        handleResetROM(p3);
                        break;
                    case MESSAGE_SAVED_STATE /* 5 */:
                        handleSavedState(p3);
                        break;
                    default:
                        throw new ProtocolException();
                }
            } else {
                return;
            }
        }
    }

    private ByteBuffer createPacket(short cmd, int len) {
        return PacketOutputStream.createPacket(len + 2).putShort(cmd);
    }

    private ByteBuffer createPacket(short cmd) {
        return createPacket(cmd, 0);
    }

    private void handleHello(ByteBuffer p) throws IOException {
        if (p.getShort() != 1) {
            throw new ProtocolException();
        }
    }

    private void handleFrameUpdate(ByteBuffer p) {
        int frameCount = p.getInt();
        int keys = p.getInt();
        synchronized (this.frameLock) {
            if (this.remoteFrameCount == frameCount) {
                this.remoteKeys = keys;
                int i = this.remoteFrameCount + 1;
                this.remoteFrameCount = i;
                if (i == this.localFrameCount + 1) {
                    this.frameLock.notify();
                }
            }
        }
    }

    private void handlePowerROM(ByteBuffer p) {
        sendMessage(this.handler.obtainMessage(3));
    }

    private void handleResetROM(ByteBuffer p) {
        sendMessage(this.handler.obtainMessage(4));
    }

    private void handleSavedState(ByteBuffer p) throws IOException {
        int len = p.getInt();
        if (len <= 0 || len > MAX_SAVED_STATE_SIZE) {
            throw new IOException();
        }
        byte[] buffer = new byte[len];
        this.inputStream.readBytes(buffer);
        sendMessage(this.handler.obtainMessage(5, buffer));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public abstract class NetThread extends Thread {
        public abstract void cancel();

        protected abstract void runIO() throws IOException;

        private NetThread() {
        }

        /* synthetic */ NetThread(NetPlayService netPlayService, NetThread netThread) {
            this();
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            int error = 3;
            try {
                runIO();
            } catch (ProtocolException e) {
                error = 2;
            } catch (IOException e2) {
                if (NetPlayService.this.outputStream == null) {
                    error = 1;
                }
            }
            NetPlayService.this.handler.obtainMessage(2, error, 0).sendToTarget();
        }
    }

    /* loaded from: classes.dex */
    private class BluetoothServerThread extends NetThread {
        private BluetoothServerSocket serverSocket = BluetoothAdapter.getDefaultAdapter().listenUsingRfcommWithServiceRecord(NetPlayService.BT_SERVICE_NAME, NetPlayService.BT_SERVICE_UUID);
        private BluetoothSocket socket;

        public BluetoothServerThread() throws IOException {
            super(NetPlayService.this, null);
        }

        @Override // com.androidemu.harveshihun.NetPlayService.NetThread
        protected void runIO() throws IOException {
            this.socket = this.serverSocket.accept();
            this.serverSocket.close();
            NetPlayService.this.manageConnection(this.socket.getInputStream(), this.socket.getOutputStream());
        }

        @Override // com.androidemu.harveshihun.NetPlayService.NetThread
        public void cancel() {
            try {
                this.serverSocket.close();
            } catch (IOException e) {
            }
            try {
                if (this.socket != null) {
                    this.socket.close();
                }
            } catch (IOException e2) {
            }
        }
    }

    /* loaded from: classes.dex */
    private class BluetoothClientThread extends NetThread {
        private BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        private BluetoothSocket socket;

        public BluetoothClientThread(String address) throws IOException {
            super(NetPlayService.this, null);
            this.socket = this.adapter.getRemoteDevice(address).createRfcommSocketToServiceRecord(NetPlayService.BT_SERVICE_UUID);
        }

        @Override // com.androidemu.harveshihun.NetPlayService.NetThread
        protected void runIO() throws IOException {
            this.adapter.cancelDiscovery();
            this.socket.connect();
            NetPlayService.this.manageConnection(this.socket.getInputStream(), this.socket.getOutputStream());
        }

        @Override // com.androidemu.harveshihun.NetPlayService.NetThread
        public void cancel() {
            try {
                this.socket.close();
            } catch (IOException e) {
            }
        }
    }

    /* loaded from: classes.dex */
    private class TCPServerThread extends NetThread {
        private ServerSocket serverSocket;
        private Socket socket;

        public TCPServerThread(InetAddress addr, int port) throws IOException {
            super(NetPlayService.this, null);
            try {
                this.serverSocket = new ServerSocket(port, 1, addr);
            } catch (IOException e) {
                if (port == 0) {
                    throw e;
                }
            }
            if (this.serverSocket == null) {
                this.serverSocket = new ServerSocket(0, 1, addr);
            }
        }

        public int getLocalPort() {
            return this.serverSocket.getLocalPort();
        }

        @Override // com.androidemu.harveshihun.NetPlayService.NetThread
        protected void runIO() throws IOException {
            this.socket = this.serverSocket.accept();
            this.socket.setSoTimeout(1000);
            this.serverSocket.close();
            NetPlayService.this.manageConnection(this.socket.getInputStream(), this.socket.getOutputStream());
        }

        @Override // com.androidemu.harveshihun.NetPlayService.NetThread
        public void cancel() {
            try {
                this.serverSocket.close();
            } catch (IOException e) {
            }
            try {
                if (this.socket != null) {
                    this.socket.close();
                }
            } catch (IOException e2) {
            }
        }
    }

    /* loaded from: classes.dex */
    private class TCPClientThread extends NetThread {
        private Socket socket = new Socket();
        private InetSocketAddress socketAddr;

        public TCPClientThread(InetAddress addr, int port) {
            super(NetPlayService.this, null);
            this.socketAddr = new InetSocketAddress(addr, port);
        }

        @Override // com.androidemu.harveshihun.NetPlayService.NetThread
        protected void runIO() throws IOException {
            this.socket.connect(this.socketAddr);
            this.socket.setSoTimeout(1000);
            this.socketAddr = null;
            NetPlayService.this.manageConnection(this.socket.getInputStream(), this.socket.getOutputStream());
        }

        @Override // com.androidemu.harveshihun.NetPlayService.NetThread
        public void cancel() {
            try {
                this.socket.close();
            } catch (IOException e) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class PacketInputStream {
        private final InputStream stream;
        private final byte[] twoBytes = new byte[2];

        public PacketInputStream(InputStream s) {
            this.stream = s;
        }

        public ByteBuffer readPacket() throws IOException {
            readBytes(this.twoBytes);
            return ByteBuffer.wrap(readBytes(new byte[((this.twoBytes[0] << 8) & 65280) | this.twoBytes[1]]));
        }

        public byte[] readBytes(byte[] buffer) throws IOException {
            int n;
            int bytes = 0;
            while (bytes < buffer.length) {
                try {
                    n = this.stream.read(buffer, bytes, buffer.length - bytes);
                } catch (SocketTimeoutException e) {
                }
                if (n < 0) {
                    throw new IOException();
                    break;
                }
                bytes += n;
            }
            return buffer;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class PacketOutputStream {
        private OutputStream stream;

        public static ByteBuffer createPacket(int len) {
            return ByteBuffer.allocate(len + 2).putShort((short) len);
        }

        public PacketOutputStream(OutputStream out) {
            this.stream = out;
        }

        public void writePacket(ByteBuffer buffer) throws IOException {
            this.stream.write(buffer.array());
            this.stream.flush();
        }

        public void writeBytes(byte[] buffer) throws IOException {
            int bytes = 0;
            while (bytes < buffer.length) {
                int n = buffer.length - bytes;
                if (n > 8192) {
                    n = Emulator.GAMEPAD_SELECT;
                }
                this.stream.write(buffer, bytes, n);
                bytes += n;
            }
            this.stream.flush();
        }
    }
}
