package services;

import constants.Constants;
import entities.Entity;
import org.junit.jupiter.api.Assertions;
import service.XslxFileReader;

import java.io.File;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

public class XslxFileReaderTest {
    @Test
    public void extractEntitiesFromFile_shouldReadAllData() {
        XslxFileReader reader = new XslxFileReader();
        List<Entity> extractedData = XslxFileReader.extractEntitiesFromFile(new File(Constants.FILE_PATH));
        Assertions.assertEquals(28089, extractedData.size());
    }

    @Test
    public void extractEntitiesFromFile_shouldGetValidDerivedIds() {
        XslxFileReader reader = new XslxFileReader();
        List<Entity> extractedData = XslxFileReader.extractEntitiesFromFile(new File(Constants.FILE_PATH));
        List<String> userIds = extractedData.stream()
                .map(Entity::getDerivedUserId)
                .collect(Collectors.toList());
        Assertions.assertTrue(userIds.contains("8429"));
        Assertions.assertTrue(userIds.contains("8414"));
        Assertions.assertTrue(userIds.contains("8427"));
    }
    @Test
    public void extractEntitiesFromFile_shouldGetValidEvents() {
        XslxFileReader reader = new XslxFileReader();
        List<Entity> extractedData = XslxFileReader.extractEntitiesFromFile(new File(Constants.FILE_PATH));
        List<String> eventNames = extractedData.stream()
                .map(Entity::getEventName)
                .collect(Collectors.toList());
        Assertions.assertTrue(eventNames.contains("Course viewed"));
        Assertions.assertTrue(eventNames.contains("Wiki page updated"));
    }
}
