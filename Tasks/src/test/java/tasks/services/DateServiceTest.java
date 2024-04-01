package tasks.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.model.ArrayTaskList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DateServiceTest {

    private DateService dateService;

    @BeforeEach
    void setUp() {
        TasksService tasksService = new TasksService(new ArrayTaskList());
        dateService = new DateService(tasksService);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testGetDateMergedWithTime() throws ParseException {

        /// arrange
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String time = "23:59";
        Date noTimeDate = formatter.parse("2023-5-32");

        // act
        Date date = dateService.getDateMergedWithTime(time, noTimeDate);

        // assert
        System.out.println(date.getTime());
    }

}
