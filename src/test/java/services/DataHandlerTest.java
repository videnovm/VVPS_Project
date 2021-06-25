package services;

import base.Base;
import constants.Constants;
import entities.Entity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.DataHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataHandlerTest {

    DataHandler dataHandler = new DataHandler();
    @Test
    void calculateRelativeFrequency_whenNullParam_shouldThrow() {
        Assertions.assertThrows(Exception.class, () -> {
            DataHandler.calculateRelativeFrequency(null, "Example Name");
        });
    }

    @Test
    void calculateRelativeFrequency_whenNullInput_shouldThrow() {
        Assertions.assertThrows(Exception.class, () -> {
            DataHandler.calculateRelativeFrequency(new ArrayList<>(), null);
        });
    }
    @Test
    void calculateAbsFrequency_whenGivenData_shouldCalculate() throws Exception {
        List<Entity> entities = new ArrayList<>();
        for(int i=0; i<5; i++){
            entities.add(Base.createDefaultEntity());;
        }
        Map<String, Long> result = DataHandler.calculateAbsoluteFrequency(entities, "Wiki page updated");
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(5, result.get("8407"));
    }
    @Test
    void calculateAbsFrequency_whenNullInput_shouldThrow() {
        Assertions.assertThrows(Exception.class, () -> {
            DataHandler.calculateAbsoluteFrequency(null, "Example Name");
        });
    }
    @Test
    void findMode_whenGivenEntityWithEvent_shouldFind() throws Exception {
        List<Entity> entity = new ArrayList<>();
        for(int i=0; i<5; i++){
            entity.add(Base.createDefaultEntity());
        }
        for(int i=0; i<7; i++){
            entity.add(Base.createEntityWithData("Course viewed", "The user with id '8408' updated the page with id '285' for the wiki with course module id '5135'.",
                    "Wiki",
                    "Context",
                    "8408",
                    LocalDateTime.MIN));
        }
        int mode = DataHandler.findMode(entity, Constants.EVENT_WIKI);
        Assertions.assertEquals(8407, mode);
    }
    @Test
    void findMode_whenInputIsNull_shouldThrow() {
        Assertions.assertThrows(Exception.class, () -> {
            DataHandler.findMode(null, "Example Name");
        });
    }

    @Test
    void findScopeForUserTestEntitiesParamIsNull() {
        Assertions.assertThrows(Exception.class, () -> {
            dataHandler.calculateVariance(null);
        });
    }

}
