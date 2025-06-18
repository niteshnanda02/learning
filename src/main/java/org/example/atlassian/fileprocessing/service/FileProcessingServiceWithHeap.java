package org.example.atlassian.fileprocessing.service;

import org.example.atlassian.fileprocessing.dto.FileCollection;
import org.example.atlassian.fileprocessing.dto.FileInfo;

import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.SortedSet;
import java.util.stream.Collectors;

public class FileProcessingServiceWithHeap implements FileProcessingService {

    private final Map<String, FileCollection> collectionMap;
    private final PriorityQueue<FileCollection> maxHeap;
    private final int topKElementSize;
    private long totalFileSizeProcessed;

    public FileProcessingServiceWithHeap(int k) {
        collectionMap = new java.util.HashMap<>();
        maxHeap = new PriorityQueue<>();
        topKElementSize = k; // Default value, can be changed as needed
        totalFileSizeProcessed = 0;
    }


    @Override
    public void addFile(String fileName, int fileSize, List<String> collections) {
        FileInfo fileInfo = new FileInfo(fileName, fileSize);
        totalFileSizeProcessed+=fileSize;
        if (collections == null || collections.isEmpty()) {
            String collectionName = "default";
            updateFileInCollection(collectionName, fileInfo);
        } else {
            for (String collectionName : collections) {
                updateFileInCollection(collectionName, fileInfo);
            }
        }

    }

    private void updateFileInCollection(String collectionName, FileInfo fileInfo) {

        FileCollection fileCollection = collectionMap.get(collectionName);
        if (fileCollection == null) {
            fileCollection = new FileCollection(collectionName);
        }
        fileCollection.addFile(fileInfo);
        collectionMap.put(collectionName, fileCollection);
    }


    @Override
    public long getTotalFileSizeProcessed() {
        return totalFileSizeProcessed;
    }

    @Override
    public List<String> getTopKCollections() {
        for(FileCollection collection : collectionMap.values()) {
            maxHeap.add(collection);
            if (maxHeap.size() > topKElementSize) {
                maxHeap.poll();
            }
        }
        return maxHeap.stream()
                .map((fileCollection -> fileCollection.getName()+" "+fileCollection.getTotalSize()))
                .collect(Collectors.toList());
    }
}
