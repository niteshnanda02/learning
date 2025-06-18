package org.example.atlassian.fileprocessing.dto;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileCollection implements Comparable<FileCollection> {
    private String name;
    private long totalSize;
    private List<FileInfo> files;

    public FileCollection(String name) {
        this.name = name;
        this.totalSize = 0;
        this.files = new ArrayList<>();
    }

    public void addFile(FileInfo file) {
        this.files.add(file);
        this.totalSize += file.getFileSize(); // Assuming Files has a size() method
    }


    @Override
    public int compareTo(FileCollection o) {
        return Long.compare(o.totalSize,this.totalSize);
    }

    public String getName() {
        return name;
    }

    public long getTotalSize() {
        return totalSize;
    }

    public List<FileInfo> getFiles() {
        return files;
    }
}
