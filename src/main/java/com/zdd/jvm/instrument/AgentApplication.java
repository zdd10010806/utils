package com.zdd.jvm.instrument;

import java.lang.instrument.Instrumentation;

public class AgentApplication {
    public static void premain(String arg, Instrumentation instrumentation) {
        System.err.println("agent startup , args is " + arg);
    }

    public static void agentmain(String arg, Instrumentation instrumentation) {
        System.err.println("agent startup , args is " + arg);

        Class<?>[] classes = instrumentation.getAllLoadedClasses();
        for (Class<?> cls : classes) {
            System.out.println(cls.getName());
        }
    }
}
