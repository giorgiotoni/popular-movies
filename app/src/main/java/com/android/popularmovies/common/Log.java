package com.android.popularmovies.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.android.popularmovies.BuildConfig;

public final class Log {

    private static final String TAG = "PopularMovies";

    private static final StackTraceElement NOT_FOUND = new StackTraceElement("", "", "", 0);

    private Log() {
    }

    private static StackTraceElement determineCaller() {
        for (final StackTraceElement element : new RuntimeException().getStackTrace()) {
            if (element.getClassName().equals(Log.class.getName())) {
                continue;
            }
            return element;
        }
        return NOT_FOUND;
    }

    private static String getClassNameOnly(final String classNameWithPackage) {
        final int lastDotPos = classNameWithPackage.lastIndexOf('.');
        if (lastDotPos == -1) {
            return classNameWithPackage;
        }
        return classNameWithPackage.substring(lastDotPos + 1);
    }

    private static String enhanced(final String message) {
        if (BuildConfig.LOG_MODE) {
            StackTraceElement caller = determineCaller();
            String classNameOnly = getClassNameOnly(caller.getClassName());
            String methodName = caller.getMethodName();
            int lineNumber = caller.getLineNumber();
            Thread thread = Thread.currentThread();
            return String.format("%s [%s:%s:%s] %s", message, classNameOnly, methodName, lineNumber, thread);
        }
        return message;
    }

    public static void v(String msg) {
        if (BuildConfig.LOG_MODE) {
            android.util.Log.v(TAG, msg);
        }
    }

    public static void pin() {
        if (BuildConfig.LOG_MODE) {
            android.util.Log.v(TAG, enhanced("==>"));
        }
    }

    public static void pin(String msg) {
        if (BuildConfig.LOG_MODE) {
            android.util.Log.v(TAG, enhanced("==> " + msg));
        }
    }

    public static void dump(Object o) {
        if (BuildConfig.LOG_MODE) {
            try {
                if (o == null) {
                    android.util.Log.v(TAG, enhanced("Object is null"));
                } else {
                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    android.util.Log.v(TAG, enhanced("" + gson.toJson(o)));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void e(String msg, Exception e) {
        android.util.Log.e(TAG, enhanced(msg), e);
    }

    public static void e(Exception e) {
        e("", e);
    }

    public static void w(String msg) {
        android.util.Log.w(TAG, enhanced(msg));
    }
}