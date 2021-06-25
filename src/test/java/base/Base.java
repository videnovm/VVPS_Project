package base;

import entities.Entity;

import java.time.LocalDateTime;

public class Base {
    public static Entity createDefaultEntity() {
        Entity entity = new Entity();
        entity.setEventName("Wiki page updated");
        entity.setDescription("The user with id '8407' updated the page with id '359' for the wiki with course module id '5135'.");
        entity.setComponent("Wiki");
        entity.setEventContext("Context");
        entity.setDerivedUserId("8407");
        entity.setTime(LocalDateTime.MIN);
        return entity;
    }
    public static Entity createEntityWithData(String eventName, String description, String component,
                                              String eventContext, String derivedId, LocalDateTime time) {
        Entity entity = new Entity();
        entity.setEventName(eventName);
        entity.setDescription(description);
        entity.setComponent(component);
        entity.setEventContext(eventContext);
        entity.setDerivedUserId(derivedId);
        entity.setTime(LocalDateTime.MIN);
        return entity;
    }
}
