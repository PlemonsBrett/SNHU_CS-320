import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

class AppointmentTest {

    private Date getFutureDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1); // Add one day to current date
        return cal.getTime();
    }

    @Test
    void testValidAppointment() {
        Appointment appointment = new Appointment("A123", getFutureDate(), "Test appointment");
        assertEquals("A123", appointment.getAppointmentId());
        assertEquals("Test appointment", appointment.getDescription());
    }

    @Test
    void testInvalidAppointmentId() {
        // When appointmentId is null
        IllegalArgumentException nullAppointmentIdException = assertThrows(IllegalArgumentException.class, () -> {
            new Appointment(null, getFutureDate(), "Test appointment");
        });
        assertEquals("Appointment ID must not be null and must be 10 characters or less",
                nullAppointmentIdException.getMessage());

        // When appointmentId is too long
        IllegalArgumentException appointmentIdTooLongException = assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("12345678901", getFutureDate(), "Test appointment");
        });
        assertEquals("Appointment ID must not be null and must be 10 characters or less",
                appointmentIdTooLongException.getMessage());
    }

    @Test
    void testInvalidAppointmentDate() {
        // When date is null
        IllegalArgumentException nullAppointmentDateException = assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("A123", null, "Test appointment");
        });
        assertEquals("Appointment date must not be null and must be in the future",
                nullAppointmentDateException.getMessage());

        // When date is in the past
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1); // Subtract one day
        Date pastDate = cal.getTime();

        IllegalArgumentException appointmentDateInPastException = assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("A123", pastDate, "Test appointment");
        });
        assertEquals("Appointment date must not be null and must be in the future",
                appointmentDateInPastException.getMessage());
    }

    @Test
    void testInvalidDescription() {
        // When description is null
        IllegalArgumentException nullDescriptionException = assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("A123", getFutureDate(), null);
        });
        assertEquals("Description must not be null and must be 50 characters or less",
                nullDescriptionException.getMessage());

        // When description is too long
        String longDescription = "This description is way too long and should cause an error because it exceeds fifty characters";
        IllegalArgumentException descriptionTooLongException = assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("A123", getFutureDate(), longDescription);
        });
        assertEquals("Description must not be null and must be 50 characters or less",
                descriptionTooLongException.getMessage());
    }
}