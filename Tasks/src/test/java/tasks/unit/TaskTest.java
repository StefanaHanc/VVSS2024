package tasks.unit;

import org.junit.jupiter.api.Test;
import tasks.model.Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Test
    void testEqualsTrue() throws ParseException {
        String title = "task";
        String date = "2024-04-01 00:00";
        Task t1 = new Task(title, sdf.parse(date));
        Task t2 = new Task(title, sdf.parse(date));
        assertEquals(t1, t2);
        assertEquals(t2, t1);
    }

    @Test
    void testEqualsFalse() throws ParseException {
        String title = "task";
        String date1 = "2024-04-01 00:00";
        String date2 = "2024-04-01 00:01";

        Task t1 = new Task(title, sdf.parse(date1));
        Task t2 = new Task(title, sdf.parse(date2));
        assertNotEquals(t1, t2);
        assertNotEquals(t2, t1);
    }


    /*
    @Test
    void testValidConstructor() throws ParseException {
        String title = "task";
        String date = "2024-04-01 00:00";
        System.out.println(sdf.parse(date).getTime());
        Task t = new Task("task", sdf.parse(date));
        assertEquals(t.getTitle(), title);
        assertEquals(sdf.format(t.getTime()), date);
        assertEquals(sdf.format(t.getStartTime()), date);
        assertEquals(sdf.format(t.getEndTime()), date);
    }

    @Test
    void testWrongConstructor() {
        String title = "task";
        String date = "1969-04-01 00:00";
        assertThrows(IllegalArgumentException.class, () -> new Task(title, sdf.parse(date)));
    }*/
}
