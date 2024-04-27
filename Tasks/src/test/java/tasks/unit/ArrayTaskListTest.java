package tasks.unit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tasks.model.ArrayTaskList;
import tasks.model.Task;
import static org.mockito.Mockito.when;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayTaskListTest {
    @Mock
    private Task task1, task2, task3;
    private ArrayTaskList taskList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        taskList = new ArrayTaskList();
    }

    @Test
    void testGetAll() {
        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);
        List<Task> tasks = taskList.getAll();

        assertEquals(tasks.size(), 3);
        assertEquals(tasks.get(0), task1);
        assertEquals(tasks.get(1), task2);
        assertEquals(tasks.get(2), task3);
    }

    @Test
    void testGetAllEmptyList() {
        assertEquals(taskList.getAll().size(), 0);
    }
}
