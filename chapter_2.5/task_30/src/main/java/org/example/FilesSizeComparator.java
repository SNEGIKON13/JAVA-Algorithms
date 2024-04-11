package org.example;

import java.io.File;
import java.util.Comparator;

public class FilesSizeComparator implements Comparator<File> {

    private final boolean upOrDown;

    public FilesSizeComparator(final boolean upOrDown) {
        this.upOrDown = upOrDown;
    }

    @Override
    public int compare(final File f1, final File f2) {
        long size1;
        long size2;
        if (f1.isDirectory()) {
            size1 = getFolderSize(f1);
        } else {
            size1 = f1.length();
        }
        if (f2.isDirectory()) {
            size2 = getFolderSize(f2);
        } else {
            size2 = f2.length();
        }
        if (upOrDown) {
            return Long.compare(size2, size1);
        } else {
            return Long.compare(size1, size2);
        }
    }

    static long getFolderSize(final File folder) {
        if (!folder.isDirectory()) {
            throw new IllegalArgumentException("Параметр должен быть папкой");
        }

        long size = 0;

        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    size += file.length();
                } else {
                    size += getFolderSize(file);
                }
            }
        }
        return size;
    }
}


