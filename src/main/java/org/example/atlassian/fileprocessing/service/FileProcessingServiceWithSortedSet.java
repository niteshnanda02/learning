package org.example.atlassian.fileprocessing.service;

import org.example.atlassian.fileprocessing.dto.FileCollection;
import org.example.atlassian.fileprocessing.dto.FileInfo;

import java.util.*;
import java.util.stream.Collectors;

public class FileProcessingServiceWithSortedSet implements FileProcessingService {
    private final Map<String, FileCollection> collectionMap;
    private final SortedSet<FileCollection> sortedCollections;
    private final int topKElementSize;
    private long totalFileSizeProcessed;

    public FileProcessingServiceWithSortedSet(int k) {
        collectionMap = new HashMap<>();
        sortedCollections = new TreeSet<>();
        topKElementSize = k;
        totalFileSizeProcessed = 0;
    }


    @Override
    public void addFile(String fileName, int fileSize, List<String> collections) {
        FileInfo fileInfo = new FileInfo(fileName, fileSize);
        totalFileSizeProcessed+=fileSize;
        if (collections==null||collections.isEmpty()){
            String collectionName = "default";
            updateFileInCollection(collectionName, fileInfo);
        }
        else {
            for (String collectionName : collections) {
                updateFileInCollection(collectionName, fileInfo);
            }
        }

        if (sortedCollections.size() > topKElementSize) {
            sortedCollections.removeLast();
        }

    }

    private void updateFileInCollection(String collectionName, FileInfo fileInfo) {

        FileCollection fileCollection = collectionMap.get(collectionName);
        if (fileCollection == null) {
            fileCollection = new FileCollection(collectionName);
        } else {
            sortedCollections.remove(fileCollection);
        }
        fileCollection.addFile(fileInfo);
        collectionMap.put(collectionName, fileCollection);
        sortedCollections.add(fileCollection);

    }

    @Override
    public long getTotalFileSizeProcessed() {
        return totalFileSizeProcessed;
    }

    @Override
    public List<String> getTopKCollections() {
    if (sortedCollections.isEmpty()) {
            return Collections.emptyList();
        }
        return sortedCollections.stream()
                .map(fileCollection -> fileCollection.getName()+" "+fileCollection.getTotalSize())
                .collect(Collectors.toList());
    }
}
