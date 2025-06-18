package org.example.atlassian.fileprocessing.dto;

public class FileInfo {
    private String fileName;
    private long fileSize;

    public FileInfo(String fileName, long fileSize) {
        this.fileName = fileName;
        this.fileSize = fileSize;
    }

    public String getFileName() {
        return fileName;
    }

    public long getFileSize() {
        return fileSize;
    }
}
