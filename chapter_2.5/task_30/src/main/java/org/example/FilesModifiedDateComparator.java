package org.example;

import java.io.File;
import java.util.Comparator;

public class FilesModifiedDateComparator implements Comparator<File> {

    private final boolean upOrDown;

    public FilesModifiedDateComparator(final boolean upOrDown) {
        this.upOrDown = upOrDown;
    }
    @Override
    public int compare(final File file1, final File file2) {
        long modifiedTime1 = file1.lastModified();
        long modifiedTime2 = file2.lastModified();
        if (upOrDown) {
            return Long.compare(modifiedTime2, modifiedTime1);
        }
        else {
            return Long.compare(modifiedTime1, modifiedTime2);
        }
    }
}
