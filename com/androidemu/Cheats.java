package com.androidemu;

import android.util.Xml;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;
/* loaded from: classes.dex */
public class Cheats {
    private static final String XML_ENCODING = "UTF-8";
    private File file;
    private ArrayList<Item> items = new ArrayList<>();
    private boolean modified;

    public native boolean nativeAdd(String str);

    public native void nativeRemove(String str);

    /* loaded from: classes.dex */
    public class Item {
        public String code;
        public boolean enabled;
        public String name;

        public Item() {
        }

        public String toString() {
            if (this.name == null) {
                return this.code;
            }
            return String.valueOf(this.name) + "\n" + this.code;
        }
    }

    public static File getCheatsFile(File rom) {
        String name = rom.getName();
        int dot = name.lastIndexOf(46);
        if (dot >= 0) {
            name = name.substring(0, dot);
        }
        return new File(rom.getParentFile(), String.valueOf(name) + ".cht");
    }

    public Cheats(String romFile) {
        this.file = getCheatsFile(new File(romFile));
        load();
    }

    public final List<Item> getAll() {
        return this.items;
    }

    public void setModified() {
        this.modified = true;
    }

    public Item add(String code, String name) {
        Item c = add(code, name, true);
        if (c != null) {
            this.modified = true;
        }
        return c;
    }

    private Item add(String code, String name, boolean enabled) {
        if (code == null || code.length() == 0) {
            return null;
        }
        if (enabled && !nativeAdd(code)) {
            return null;
        }
        if ("".equals(name)) {
            name = null;
        }
        Item c = new Item();
        c.enabled = enabled;
        c.code = code;
        c.name = name;
        this.items.add(c);
        return c;
    }

    public void remove(int i) {
        Item c = this.items.get(i);
        if (c.enabled) {
            nativeRemove(c.code);
        }
        this.items.remove(i);
        this.modified = true;
    }

    public void enable(int i, boolean enabled) {
        Item c = this.items.get(i);
        if (c.enabled != enabled) {
            c.enabled = enabled;
            if (enabled) {
                nativeAdd(c.code);
            } else {
                nativeRemove(c.code);
            }
            this.modified = true;
        }
    }

    private void load() {
        Exception e;
        Throwable th;
        boolean enabled;
        BufferedInputStream in = null;
        try {
            BufferedInputStream in2 = new BufferedInputStream(new FileInputStream(this.file));
            try {
                XmlPullParser parser = Xml.newPullParser();
                parser.setInput(in2, XML_ENCODING);
                for (int event = parser.getEventType(); event != 1; event = parser.next()) {
                    if (event == 2) {
                        if (parser.getName().equals("item")) {
                            String code = parser.getAttributeValue(null, "code");
                            String name = parser.getAttributeValue(null, "name");
                            if ("true".equals(parser.getAttributeValue(null, "disabled"))) {
                                enabled = false;
                            } else {
                                enabled = true;
                            }
                            add(code, name, enabled);
                        }
                    }
                }
                if (in2 != null) {
                    try {
                        in2.close();
                    } catch (FileNotFoundException e2) {
                    } catch (Exception e3) {
                        e = e3;
                        e.printStackTrace();
                    }
                }
                this.modified = false;
            } catch (Throwable th2) {
                th = th2;
                in = in2;
                if (in != null) {
                    try {
                        in.close();
                    } catch (FileNotFoundException e4) {
                    } catch (Exception e5) {
                        e = e5;
                        e.printStackTrace();
                        this.modified = false;
                    }
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    public void save() {
        BufferedOutputStream out;
        if (this.modified) {
            if (this.items.size() != 0 || !this.file.delete()) {
                BufferedOutputStream out2 = null;
                try {
                    out = new BufferedOutputStream(new FileOutputStream(this.file));
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    XmlSerializer serializer = Xml.newSerializer();
                    serializer.setOutput(out, XML_ENCODING);
                    serializer.startDocument(null, null);
                    serializer.startTag(null, "cheats");
                    Iterator<Item> it = this.items.iterator();
                    while (it.hasNext()) {
                        Item c = it.next();
                        serializer.startTag(null, "item");
                        if (!c.enabled) {
                            serializer.attribute(null, "disabled", "true");
                        }
                        serializer.attribute(null, "code", c.code);
                        if (c.name != null) {
                            serializer.attribute(null, "name", c.name);
                        }
                        serializer.endTag(null, "item");
                    }
                    serializer.endTag(null, "cheats");
                    serializer.endDocument();
                    if (out != null) {
                        try {
                            out.close();
                        } catch (Exception e) {
                        }
                    }
                    this.modified = false;
                } catch (Throwable th2) {
                    th = th2;
                    out2 = out;
                    if (out2 != null) {
                        try {
                            out2.close();
                        } catch (Exception e2) {
                        }
                    }
                    throw th;
                }
            }
        }
    }

    public void destroy() {
        save();
        int i = this.items.size();
        while (true) {
            i--;
            if (i < 0) {
                this.items.clear();
                return;
            }
            Item c = this.items.get(i);
            if (c.enabled) {
                nativeRemove(c.code);
            }
        }
    }
}
