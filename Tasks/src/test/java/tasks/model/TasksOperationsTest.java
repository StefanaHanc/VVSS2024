package tasks.model;

import javafx.collections.FXCollections;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;



public class TasksOperationsTest {

    private List<Task> tasks;
    private TasksOperations tasksOperations;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");


    @BeforeEach
    void setUp() {
        tasks = new ArrayList<>();
    }

    // test - 0 iteratii
    @Test
    void testEmptyList() throws ParseException {
        tasksOperations = new TasksOperations(FXCollections.observableArrayList(tasks));

        List<Task> filteredTasks = (List<Task>) tasksOperations.incoming(sdf.parse("2024-04-01 00:00"), sdf.parse("2024-04-30 23:59"));
        assertEquals(filteredTasks.size(), 0);
    }

    // test - 1 iteratie
    //      - nextTime = null
    @Test
    void testSize1ListNull() throws ParseException {
        Task t = new Task("task", sdf.parse("2024-04-15 00:00"));
        tasks.add(t);
        tasksOperations = new TasksOperations(FXCollections.observableArrayList(tasks));
        List<Task> filteredTasks = (List<Task>) tasksOperations.incoming(sdf.parse("2024-04-01 00:00"), sdf.parse("2024-04-30 23:59"));
        assertEquals(filteredTasks.size(), 0);
    }

    // test - 1 iteratie
    //      - nextTime < end
    @Test
    void testSize1ListBefore() throws ParseException {
        Task t = new Task("task", sdf.parse("2024-04-15 00:00"));
        t.setActive(true);
        tasks.add(t);
        tasksOperations = new TasksOperations(FXCollections.observableArrayList(tasks));
        List<Task> filteredTasks = (List<Task>) tasksOperations.incoming(sdf.parse("2024-04-01 00:00"), sdf.parse("2024-04-30 23:59"));
        assertEquals(filteredTasks.size(), 1);
    }

    // test - 1 iteratie
    //      - nextTime = end
    @Test
    void testSize1ListEquals() throws ParseException {
        Task t = new Task("task", sdf.parse("2024-04-30 23:59"));
        t.setActive(true);
        tasks.add(t);
        tasksOperations = new TasksOperations(FXCollections.observableArrayList(tasks));
        List<Task> filteredTasks = (List<Task>) tasksOperations.incoming(sdf.parse("2024-04-01 00:00"), sdf.parse("2024-04-30 23:59"));
        assertEquals(filteredTasks.size(), 1);
    }


    // test - 1 iteratie
    //      - nextTime > end
    @Test
    void testSize1ListAfter() throws ParseException {
        Task t = new Task("task", sdf.parse("2024-04-30 23:59"));
        t.setActive(true);
        tasks.add(t);
        tasksOperations = new TasksOperations(FXCollections.observableArrayList(tasks));
        List<Task> filteredTasks = (List<Task>) tasksOperations.incoming(sdf.parse("2024-04-01 00:00"), sdf.parse("2024-04-30 23:58"));
        assertEquals(filteredTasks.size(), 0);
    }

    //test - start > end
    @Test
    void testWrongDates() throws ParseException {
        Task t = new Task("task", sdf.parse("2024-04-30 23:58"));
        t.setActive(true);
        tasks.add(t);
        tasksOperations = new TasksOperations(FXCollections.observableArrayList(tasks));
        List<Task> filteredTasks = (List<Task>) tasksOperations.incoming(sdf.parse("2024-04-30 23:59"), sdf.parse("2024-04-01 00:00"));
        assertEquals(filteredTasks.size(), 0);
    }

    // test - task = null
    @Test
    void testNullTask() throws ParseException {
        tasks.add(null);
        tasksOperations = new TasksOperations(FXCollections.observableArrayList(tasks));
        List<Task> filteredTasks = (List<Task>) tasksOperations.incoming(sdf.parse("2024-04-30 23:59"), sdf.parse("2024-04-01 00:00"));
        assertEquals(filteredTasks.size(), 0);
    }



    @AfterEach
    void tearDown() {

    }
}
