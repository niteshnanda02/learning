package org.example.atlassian.fileprocessing;

import org.example.atlassian.fileprocessing.service.FileProcessingService;
import org.example.atlassian.fileprocessing.service.FileProcessingServiceWithHeap;
import org.example.atlassian.fileprocessing.service.FileProcessingServiceWithSortedSet;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        FileProcessingService fileProcessingService = new FileProcessingServiceWithSortedSet(2);
        fileProcessingService.addFile("file1",100, null);
        System.out.println("Top K Collections: " + fileProcessingService.getTopKCollections());
        fileProcessingService.addFile("file2",200, Arrays.asList(new String[]{"collection1"}));
        System.out.println("Top K Collections: " + fileProcessingService.getTopKCollections());
        fileProcessingService.addFile("file3",200, Arrays.asList(new String[]{"collection1","collection2"}));
        System.out.println("Top K Collections: " + fileProcessingService.getTopKCollections());
        fileProcessingService.addFile("file4",300, Arrays.asList(new String[]{"collection2"}));
        System.out.println("Top K Collections: " + fileProcessingService.getTopKCollections());
        fileProcessingService.addFile("file5",100, null);
        System.out.println("Total File Size Processed: " + fileProcessingService.getTotalFileSizeProcessed());
        System.out.println("Top K Collections: " + fileProcessingService.getTopKCollections());

        System.out.println("-------------------------******--------------------------");
        FileProcessingService fileProcessingService2 = new FileProcessingServiceWithHeap(2);
        fileProcessingService2.addFile("file1",100, null);
        fileProcessingService2.addFile("file2",200, Arrays.asList(new String[]{"collection1"}));
        fileProcessingService2.addFile("file3",200, Arrays.asList(new String[]{"collection1","collection2"}));
        fileProcessingService2.addFile("file4",300, Arrays.asList(new String[]{"collection2"}));
        fileProcessingService2.addFile("file5",100, null);
        System.out.println("Total File Size Processed: " + fileProcessingService.getTotalFileSizeProcessed());
        System.out.println("Top K Collections: " + fileProcessingService.getTopKCollections());


    }
}
