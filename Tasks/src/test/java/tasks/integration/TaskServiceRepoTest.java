package tasks.integration;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tasks.model.ArrayTaskList;
import tasks.model.Task;
import tasks.services.TasksService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskServiceRepoTest {
    @Mock
    private Task t1, t2, t3;

    private TasksService tasksService;
    private ArrayTaskList taskList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        taskList = new ArrayTaskList();
        tasksService = new TasksService(taskList);
    }

    @Test
    void testGetObservableList() {
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
