package com.zdd.classloader;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

public class CopyFileUsingFileChannelTransferTo {
    public static void main(String[] args) {
        Path copy_from = Paths.get("/Users/zrh", "1.txt");
        Path copy_to = Paths.get("/Users/zrh/Copy/2.txt");
        System.out.println("使用 FileChannel.transferTo 方法复制文件 ...");
        try (FileChannel fileChannel_from = (FileChannel.open(copy_from, EnumSet.of(StandardOpenOption.READ)));
             FileChannel fileChannel_to = (FileChannel.open(copy_to, EnumSet.of(StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE)))) {
            fileChannel_from.transferTo(0L, fileChannel_from.size(), fileChannel_to);
            System.out.println("使用 FileChannel.transferTo 方法复制文件成功");
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}
