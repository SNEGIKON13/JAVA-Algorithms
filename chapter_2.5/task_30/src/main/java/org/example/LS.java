package org.example;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;

public class LS {
    public static void main(final String[] args) {
        String directoryPath = args[0];
        boolean sortByTimeFlag = false,
                sortBySizeFlag = false,
                descendingFlag = false;
        File[] files;
        for (int i = 1; i < args.length; i++) {
            switch (args[i]) {
                case "-t" -> sortByTimeFlag = true;
                case "-s" -> sortBySizeFlag = true;
                case "-d" -> descendingFlag = true;
                default -> System.err.println("Invalid option: " + args[i]);
            }
        }
        File directory = new File(directoryPath);
        if (directory.isDirectory()) {
             files = directory.listFiles();
        } else {
            return;
        }
        if (sortByTimeFlag && sortBySizeFlag) {
            Arrays.sort(files, new FilesModifiedDateComparator(descendingFlag).
                    thenComparing(new FilesSizeComparator(descendingFlag)));
            for (File file : files) {
                System.out.println(file.getName());
            }
        } else if (sortByTimeFlag) {
            Arrays.sort(files, new FilesModifiedDateComparator(descendingFlag));
            for (File file : files) {
                System.out.println(file.getName());
            }
        } else if (sortBySizeFlag) {
            Arrays.sort(files, new FilesSizeComparator(descendingFlag));
            for (File file : files) {
                System.out.println(file.getName());
            }
        } else if (descendingFlag) {
            Arrays.sort(files, Collections.reverseOrder());
        } else {
            Arrays.sort(files);
            for (File file : files) {
                System.out.println(file.getName());
            }
        }
    }
}
