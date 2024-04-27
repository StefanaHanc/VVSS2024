package tasks.unit;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import tasks.model.ArrayTaskList;
import tasks.model.Task;
import tasks.services.TasksService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TaskServiceTest {

    @Mock
    private Task t1, t2, t3;

    @Mock
    private ArrayTaskList taskList;

    private TasksService tasksService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        tasksService = new TasksService(taskList);
    }

    @Test
    void testGetObservableList() {
        List<Task> tasks = List.of(t1, t2, t3);
        when(taskList.getAll()).thenReturn(tasks);
        ObservableList<Task> observableTasks = tasksService.getObservableList();
        assertEquals(observableTasks.get(0), t1);
        assertEquals(observableTasks.get(1), t2);
        assertEquals(observableTasks.get(2), t3);
        Mockito.verify(taskList, times(1)).getAll();
    }

    @Test
    void testGetObservableNullList() {
        List<Task> tasks = new ArrayList<>();
        when(taskList.getAll()).thenReturn(tasks);
        ObservableList<Task> observableTasks = tasksService.getObservableList();
        assertEquals(observableTasks.size(), 0);
    }

}
