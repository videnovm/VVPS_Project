package service;

import constants.Constants;
import entities.Entity;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public class DataHandler<list> {
    public static Map<String, Long> calculateAbsoluteFrequency(List<Entity> entities, String eventName) throws Exception {
        if(entities == null || eventName == null) {
            throw new Exception();
        }
        Map<String, Long> usersMap = new HashMap<>();
        List<Entity> filteredEntities = filterData(entities, eventName);
        for (Entity entity: filteredEntities) {
            String userId = entity.getDerivedUserId();
            if (userId != null && usersMap.putIfAbsent(userId, 1L) != null) {
                usersMap.put(userId, usersMap.get(userId)+1);
            }
        }
        return usersMap;
    }

    public static Map<String, Double> calculateRelativeFrequency(List<Entity> entities, String eventName)
            throws Exception{
        if (entities == null || eventName == null) {
            throw new Exception(Constants.NULL_PARAM);
        }
        Map<String, Double> relativeFrequencies = new HashMap<>();
        int listSize = filterData(entities, eventName).size();
        Map<String, Long> userMap = calculateAbsoluteFrequency(entities, eventName);
        for (Map.Entry<String, Long> entry: userMap.entrySet()) {
            relativeFrequencies.put(entry.getKey(), ((double)entry.getValue() / listSize) * 100);
        }
        return relativeFrequencies;
    }

    public static int findMode(List<Entity> entities, String eventName) throws Exception{
        if(entities == null || eventName == null) {
            throw new Exception(Constants.NULL_PARAM);
        }
        Map<String, Long> users = calculateAbsoluteFrequency(entities, eventName);
        List<Map.Entry<String, Long>> entries = new LinkedList<>(users.entrySet());
        Collections.sort(entries, Comparator.comparing(Map.Entry::getValue));
        int mode = Integer.parseInt(entries.get(entries.size() - 1).getKey());

        return mode;
    }

    public float calculateVariance(List<Entity> eventLogs) {
        Map<String, List<Entity>> eventsByUsers = eventLogs.stream()
                .collect(Collectors.groupingBy(Entity::getDerivedUserId));
        double average = getWikiUpdatedEventsPerUser(eventsByUsers).average().getAsDouble();
        double variance = getWikiUpdatedEventsPerUser(eventsByUsers).map(value -> Math.pow(value - average, 2))
                .average()
                .getAsDouble();
        return (float)variance;
    }

    public static List<Entity> filterData(List<Entity> entities, String eventName) {
        return entities.stream().filter(e -> e.getEventName().equals(eventName)).collect(Collectors.toList());
    }

    private boolean isValidWikiEvent(Entity entity) {
        return entity.getEventName().equals(Constants.EVENT_WIKI);
    }

    private double wikiUpdatedEvents(List<Entity> userEvents) {
        int totalEventsByThisUser = userEvents.size();
        long wikiEditedEventsByThisUser = userEvents.stream().filter(this::isValidWikiEvent).count();
        return (double) wikiEditedEventsByThisUser / totalEventsByThisUser;
    }

    private DoubleStream getWikiUpdatedEventsPerUser(Map<String, List<Entity>> eventsByUsers) {
        return eventsByUsers.values().stream().mapToDouble(this::wikiUpdatedEvents);
    }
}
