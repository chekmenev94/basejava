package com.urise.webapp.util;

public class LazySingleton {
    volatile private static LazySingleton INSTANCE;
    int i;
    double sin = Math.sin(13.);
    private LazySingleton() {
    }

    private static class LazySingletonHolder {
        private static final LazySingleton INSTANCE = new LazySingleton();

    }

    public static LazySingleton getInstance() {
        return LazySingletonHolder.INSTANCE;
    }

//    public static LazySingleton getInstance() {
//        if (INSTANCE == null) {
//            synchronized (LazySingleton.class) {
//                if (INSTANCE == null) {
//                    int i = 13;
//                    INSTANCE = new LazySingleton();
//                }
//            }
//        }
//        return INSTANCE;
//    }
}
