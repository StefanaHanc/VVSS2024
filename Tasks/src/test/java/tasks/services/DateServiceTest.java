package tasks.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import tasks.model.ArrayTaskList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DateServiceTest {

    private DateService dateService;
    private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    @BeforeEach
    void setUp() {
        TasksService tasksService = new TasksService(new ArrayTaskList());
        dateService = new DateService(tasksService);
    }

    private LocalDateTime toLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testGetDateMergedWithTime1() throws ParseException {

        /// arrange
        String time = "23:59";
        Date noTimeDate = formatter.parse("2023-05-31");

        // act
        Date date = dateService.getDateMergedWithTime(time, noTimeDate);
        LocalDateTime localDate = toLocalDate(date);
        // assert
        assertEquals(localDate.getYear(), 2023);
        assertEquals(localDate.getMonth(), Month.MAY);
        assertEquals(localDate.getDayOfMonth(), 31);
        assertEquals(localDate.getHour(), 23);
        assertEquals(localDate.getMinute(), 59);
    }
    @Test
    void testGetDateMergedWithTime1ECP() throws ParseException {

        // Ora și data sunt ambele valide
        String time = "12:30";
        Date noTimeDate = formatter.parse("2023-04-15");
        // act
        Date date = dateService.getDateMergedWithTime(time, noTimeDate);
        LocalDateTime localDate = toLocalDate(date);
        // assert
        assertEquals(localDate.getYear(), 2023);
        assertEquals(localDate.getMonth(), Month.APRIL);
        assertEquals(localDate.getDayOfMonth(), 15);
        assertEquals(localDate.getHour(), 12);
        assertEquals(localDate.getMinute(), 30);
    }
    @Test
    void testGetDateMergedWithTime2ECP() throws ParseException {
      //  Ora valida dar data invalida ziua >31
        /// arrange
        String time = "22:00";
        Date noTimeDate = formatter.parse("2023-05-32");
        // act && assert
        assertThrows(IllegalArgumentException.class, () -> dateService.getDateMergedWithTime(time, noTimeDate));
    }

    @Test
    void testGetDateMergedWithTime3ECP() throws ParseException {
        // Ora invalidă, dar data validă ora>24:00
        /// arrange
        String time = "24:01";
        Date noTimeDate = formatter.parse("2023-05-32");

        // act && assert
        assertThrows(IllegalArgumentException.class, () -> dateService.getDateMergedWithTime(time, noTimeDate));
    }




    @Test
    void testGetDateMergedWithTime2() throws ParseException {

        /// arrange
        String time = "00:00";
        Date noTimeDate = formatter.parse("2023-01-01");

        // act
        Date date = dateService.getDateMergedWithTime(time, noTimeDate);
        LocalDateTime localDate = toLocalDate(date);
        // assert
        assertEquals(localDate.getYear(), 2023);
        assertEquals(localDate.getMonth(), Month.JANUARY);
        assertEquals(localDate.getDayOfMonth(), 1);
    }
    @Test
    void testGetDateMergedWithTime3() throws ParseException {
        /// arrange
        String time = "24:00";
        Date noTimeDate = formatter.parse("2023-05-31");
        // act && assert
        assertThrows(IllegalArgumentException.class, () -> dateService.getDateMergedWithTime(time, noTimeDate));
    }

    @Test
    void testGetDateMergedWithTime4() throws ParseException {
        /// arrange
        String time = "22:00";
        Date noTimeDate = formatter.parse("2023-05-32");

        // act && assert
        assertThrows(IllegalArgumentException.class, () -> dateService.getDateMergedWithTime(time, noTimeDate));
    }
}
