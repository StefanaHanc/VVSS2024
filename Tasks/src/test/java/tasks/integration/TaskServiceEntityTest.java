package tasks.integration;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tasks.model.ArrayTaskList;
import tasks.model.Task;
import tasks.services.TasksService;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskServiceEntityTest {
    private TasksService tasksService;
    private ArrayTaskList taskList;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @BeforeEach
    void setUp() {
        taskList = new ArrayTaskList();
        tasksService = new TasksService(taskList);
    }

    @Test
    void testGetObservableList() throws ParseException {
        Task t1 = new Task("t1", sdf.parse("2024-04-01 00:00"));
        Task t2 = new Task("t2", sdf.parse("2024-04-02 00:00"));
        Task t3 = new Task("t3", sdf.parse("2024-04-03 00:00"));

        taskList.add(t1);
        taskList.add(t2);
        taskList.add(t3);
        ObservableList<Task> observableTasks = tasksService.getObservableList();
        assertEquals(observableTasks.get(0), t1);
        assertEquals(observableTasks.get(1), t2);
        assertEquals(observableTasks.get(2), t3);
    }

    @Test
    void testGetObservableNullList() {
        assertEquals(tasksService.getObservableList().size(), 0);
    }
}
