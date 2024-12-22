import java.util.Date;

public class Appointment {
    private final String appointmentId;
    private Date appointmentDate;
    private String description;

    public Appointment(String appointmentId, Date appointmentDate, String description) {
        // Validate appointmentId
        validateAppointmentId(appointmentId);

        // Validate appointmentDate
        validateAppointmentDate(appointmentDate);

        // Validate description
        validateDescription(description);

        this.appointmentId = appointmentId;
        this.appointmentDate = appointmentDate;
        this.description = description;
    }

    // Getters
    public String getAppointmentId() {
        return appointmentId;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public String getDescription() {
        return description;
    }

    // Setters for mutable fields
    // Setters for mutable fields
    public void setAppointmentDate(Date appointmentDate) {
        validateAppointmentDate(appointmentDate);
        this.appointmentDate = appointmentDate;
    }

    public void setDescription(String description) {
        validateDescription(description);
        this.description = description;
    }

    // Validators
    private void validateAppointmentId(String appointmentId) {
        if (appointmentId == null || appointmentId.length() > 10) {
            throw new IllegalArgumentException("Appointment ID must not be null and must be 10 characters or less");
        }
    }

    private void validateAppointmentDate(Date appointmentDate) {
        if (appointmentDate == null || appointmentDate.before(new Date())) {
            throw new IllegalArgumentException("Appointment date must not be null and must be in the future");
        }
    }

    private void validateDescription(String description) {
        if (description == null || description.length() > 50) {
            throw new IllegalArgumentException("Description must not be null and must be 50 characters or less");
        }
    }

}
