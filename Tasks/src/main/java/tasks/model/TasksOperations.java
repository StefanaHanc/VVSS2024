package tasks.model;

import javafx.collections.ObservableList;

import java.util.*;

public class TasksOperations {
    public ArrayList<Task> tasks;

    public TasksOperations(ObservableList<Task> tasksList){
        tasks=new ArrayList<>();
        tasks.addAll(tasksList);
    }
    public Iterable<Task> incoming(Date start, Date end) {
        if (end.before(start))
            return new ArrayList<>();
        ArrayList<Task> incomingTasks = new ArrayList<>();
        for (Task t : tasks)
            if (t != null) {
                Date nextTime = t.nextTimeAfter(start);
                if (nextTime != null && (nextTime.before(end) || nextTime.equals(end))) {
                    incomingTasks.add(t);
                }
        }
        return incomingTasks;
    }
    public SortedMap<Date, Set<Task>> calendar( Date start, Date end){
        Iterable<Task> incomingTasks = incoming(start, end);
        TreeMap<Date, Set<Task>> calendar = new TreeMap<>();

        for (Task t : incomingTasks){
            Date nextTimeAfter = t.nextTimeAfter(start);
            while (nextTimeAfter!= null && (nextTimeAfter.before(end) || nextTimeAfter.equals(end))){
                if (calendar.containsKey(nextTimeAfter)){
                    calendar.get(nextTimeAfter).add(t);
                }
                else {
                    HashSet<Task> oneDateTasks = new HashSet<>();
                    oneDateTasks.add(t);
                    calendar.put(nextTimeAfter,oneDateTasks);
                }
                nextTimeAfter = t.nextTimeAfter(nextTimeAfter);
            }
        }
        return calendar;
    }
}

