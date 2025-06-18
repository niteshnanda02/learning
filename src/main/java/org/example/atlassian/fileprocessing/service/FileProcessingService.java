package org.example.atlassian.fileprocessing.service;

import java.util.List;

public interface FileProcessingService {
    void addFile(String fileName, int fileSize, List<String> collections);
    long getTotalFileSizeProcessed();
    List<String> getTopKCollections();
}
