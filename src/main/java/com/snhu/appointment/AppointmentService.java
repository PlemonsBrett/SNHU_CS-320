package com.snhu.appointment;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AppointmentService {
    private final Map<String, Appointment> appointments = new HashMap<>();

    public void addAppointment(Appointment appointment) {
        if (appointment == null) {
            throw new IllegalArgumentException("Appointment cannot be null");
        }

        if (appointments.containsKey(appointment.getAppointmentId())) {
            throw new IllegalArgumentException("Appointment ID already exists");
        }

        if (hasConflict(appointment)) {
            throw new IllegalArgumentException("Appointment conflicts with an existing appointment");
        }

        appointments.put(appointment.getAppointmentId(), appointment);
    }

    public void deleteAppointment(String appointmentId) {
        if (!appointments.containsKey(appointmentId)) {
            throw new IllegalArgumentException("Appointment ID does not exist");
        }

        appointments.remove(appointmentId);
    }

    public void updateAppointment(String appointmentId, Date appointmentDate, String description) {
        Appointment appointment = appointments.get(appointmentId);

        if (appointment == null) {
            throw new IllegalArgumentException("Appointment ID does not exist");
        }

        if (appointmentDate != null) {
            Appointment tempAppointment = new Appointment(appointmentId, appointmentDate, description);
            if (hasConflict(tempAppointment)) {
                throw new IllegalArgumentException("Appointment conflicts with an existing appointment");
            }
            appointment.setAppointmentDate(appointmentDate);
        }
        if (description != null && !description.trim().isEmpty()) {
            appointment.setDescription(description);
        }
    }

    public Appointment getAppointment(String appointmentId) {
        return appointments.get(appointmentId);
    }

    private boolean hasConflict(Appointment newAppointment) {
        for (Appointment existingAppointment : appointments.values()) {
            if (existingAppointment.getAppointmentDate().equals(newAppointment.getAppointmentDate())) {
                return true;
            }
        }
        return false;
    }
}