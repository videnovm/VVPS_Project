package entities;

import util.UserIdParser;

import java.time.LocalDateTime;

public class Entity {
    private String eventContext;
    private String component;
    private String eventName;
    private String description;
    private String derivedUserId;
    private LocalDateTime time;

    public String getEventContext() {
        return eventContext;
    }

    public void setEventContext(String eventContext) {
        this.eventContext = eventContext;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDerivedUserId() {
        if (derivedUserId == null) {
            deriveUserId();
        }
        return derivedUserId;
    }

    public Entity setDerivedUserId(String derivedUserId) {
        this.derivedUserId = derivedUserId;
        return this;
    }

    public Entity deriveUserId() {
        derivedUserId = UserIdParser.deriveUserIdFromEventDescription(getDescription());
        return this;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}