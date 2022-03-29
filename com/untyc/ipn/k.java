package com.untyc.ipn;

import java.lang.reflect.Method;
/* loaded from: classes.dex */
public class k {
    protected Object a(Object obj, Method method, Object[] objArr) {
        if (obj == null || method == null) {
            return null;
        }
        try {
            method.setAccessible(true);
            return method.invoke(obj, objArr);
        } catch (Exception e) {
            return null;
        }
    }

    protected Method a(Object obj, String str) {
        try {
            Class<?> cls = obj.getClass();
            return cls.getMethod(str, a((Class) cls, str));
        } catch (Exception e) {
            return null;
        }
    }

    protected Class[] a(Class cls, String str) {
        Method[] declaredMethods = cls.getDeclaredMethods();
        int length = declaredMethods.length;
        Class<?>[] clsArr = null;
        for (int i = 0; i < length; i++) {
            if (declaredMethods[i].getName().equals(str)) {
                clsArr = declaredMethods[i].getParameterTypes();
            }
        }
        return clsArr;
    }
}
