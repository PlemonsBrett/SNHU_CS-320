import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AppointmentServiceTest {

    private AppointmentService appointmentService;
    private Appointment testAppointment;

    private Date getFutureDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1); // Add one day to current date
        return cal.getTime();
    }

    @BeforeEach
    void setUp() {
        appointmentService = new AppointmentService();
        testAppointment = new Appointment("A123", getFutureDate(), "Test appointment");
    }

    @Test
    void testAddAppointment() {
        appointmentService.addAppointment(testAppointment);
        assertEquals(testAppointment, appointmentService.getAppointment("A123"));
    }

    @Test
    void testAddDuplicateAppointment() {
        appointmentService.addAppointment(testAppointment);
        Appointment duplicateAppointment = new Appointment("A123", getFutureDate(), "Duplicate appointment");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            appointmentService.addAppointment(duplicateAppointment);
        });
        assertEquals("Appointment ID already exists", exception.getMessage());
    }

    @Test
    void testAddNullAppointment() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            appointmentService.addAppointment(null);
        });
        assertEquals("Appointment cannot be null", exception.getMessage());
    }

    @Test
    void testDeleteAppointment() {
        appointmentService.addAppointment(testAppointment);
        appointmentService.deleteAppointment("A123");
        assertNull(appointmentService.getAppointment("A123"));
    }

    @Test
    void testDeleteNonexistentAppointment() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            appointmentService.deleteAppointment("nonexistent");
        });
        assertEquals("Appointment ID does not exist", exception.getMessage());
    }
}