package com.zdd.classloader;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

/**
 * -Xbootclasspath/a:D:/tmp/clz
 * -XBootClasspath 指定 说明判断类是否存在，从app classloader开始
 *
 * @author Administrator
 */
public class InitClass {
    public static void main(String args[]) throws Exception {
        ClassLoader cl = InitClass.class.getClassLoader();
//        byte[] bHelloLoader = loadClassBytes("com.zdd.classloader.HelloLoader");
//        Method md_defineClass = ClassLoader.class.getDeclaredMethod("defineClass", byte[].class, int.class, int.class);
//        md_defineClass.setAccessible(true);
//        md_defineClass.invoke(cl, bHelloLoader, 0, bHelloLoader.length);
//        md_defineClass.setAccessible(false);
        Object loader = cl.loadClass("com.zdd.classloader.HelloLoader").newInstance();
        Field a = loader.getClass().getField("a");
        Field b = loader.getClass().getField("b");
        Field c = loader.getClass().getField("c");
        System.out.println(a.getInt(loader));
        System.out.println(b.getInt(loader));
        System.out.println(c.getInt(loader));


//        Object loader = cl.getParent().loadClass("com.zdd.classloader.HelloLoader").newInstance();
//        System.out.println(loader.getClass().getClassLoader());//null
//        Method m = loader.getClass().getMethod("print", null);
//        m.invoke(loader, null);
//        HelloLoader helloLoader = new HelloLoader();
//        helloLoader.print();
//        System.out.println(helloLoader.getClass().getClassLoader());//sun.misc.Launcher$AppClassLoader

    }

    private static String getClassFile(String name) {
        StringBuffer sb = new StringBuffer("./target/classes");
        name = name.replace('.', File.separatorChar) + ".class";
        sb.append(File.separator + name);
        return sb.toString();
    }

    private static byte[] loadClassBytes(String className) throws ClassNotFoundException {
        try {
            String classFile = getClassFile(className);
            FileInputStream fis = new FileInputStream(classFile);
            FileChannel fileC = fis.getChannel();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            WritableByteChannel outC = Channels.newChannel(baos);
            ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
            while (true) {
                int i = fileC.read(buffer);
                if (i == 0 || i == -1) {
                    break;
                }
                buffer.flip();
                outC.write(buffer);
                buffer.clear();
            }
            fis.close();
            return baos.toByteArray();
        } catch (IOException fnfe) {
            throw new ClassNotFoundException(className);
        }
    }
}
