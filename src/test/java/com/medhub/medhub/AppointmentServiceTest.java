package com.medhub.medhub;

import com.medhub.medhub.dto.AppointmentDTO;
import com.medhub.medhub.entity.AppUser;
import com.medhub.medhub.entity.Appointment;
import com.medhub.medhub.entity.Doctor;
import com.medhub.medhub.entity.Patient;
import com.medhub.medhub.exception.AppointmentNotFoundException;
import com.medhub.medhub.repository.AppointmentRepository;
import com.medhub.medhub.repository.DoctorRepository;
import com.medhub.medhub.repository.PatientRepository;
import com.medhub.medhub.service.AppointmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AppointmentServiceTest {

    @InjectMocks
    private AppointmentService appointmentService;

    @Mock
    private AppointmentRepository appointmentRepository;

    @Mock
    private DoctorRepository doctorRepository;

    @Mock
    private PatientRepository patientRepository;

    private Appointment appointment;
    private AppointmentDTO dto;
    private Doctor doctor;
    private Patient patient;
    private AppUser user;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

        user = new AppUser();
        user.setId(1L);
        user.setUsername("docuser");

        doctor = new Doctor();
        doctor.setId(1L);
        doctor.setSpecialization("Cardiology");
        doctor.setUser(user);

        patient = new Patient();
        patient.setId(1L);
        patient.setMedicalHistory("Fracture");
        patient.setUser(user);

        appointment = new Appointment();
        appointment.setId(10L);
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);

        dto = new AppointmentDTO();
        dto.setId(10L);
        dto.setAppointmentTime(LocalDateTime.now());
        dto.setPatientId(1L);
        dto.setDoctorId(1L);
    }

    @Test
    void createAppointment_success(){
        when(doctorRepository.findById(1L)).thenReturn(Optional.of(doctor));
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
        when(appointmentRepository.save(any(Appointment.class))).thenReturn(appointment);

        AppointmentDTO result = appointmentService.createAppointment(dto);

        assertNotNull(result);
        verify(appointmentRepository).save(any(Appointment.class));
    }

    @Test
    void getAppointmentById_success(){
        when(appointmentRepository.findById(10L)).thenReturn(Optional.of(appointment));

        AppointmentDTO result = appointmentService.getAppointmentById(10L);

        assertNotNull(result);
    }

    @Test
    void getAppointmentById_notFound(){
        when(appointmentRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(AppointmentNotFoundException.class, () -> appointmentService.getAppointmentById(99L));
    }

    @Test
    void updateAppointment_success(){
        Appointment updated = new Appointment();
        updated.setId(10L);
        updated.setPatient(patient);
        updated.setDoctor(doctor);

        when(appointmentRepository.findById(10L)).thenReturn(Optional.of(appointment));
        when(doctorRepository.findById(1L)).thenReturn(Optional.of(doctor));
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
        when(appointmentRepository.save(any(Appointment.class))).thenReturn(updated);

        AppointmentDTO result = appointmentService.updateAppointment(10L,dto);

        assertNotNull(result);
    }

    @Test
    void deleteAppointment_success(){
        when(appointmentRepository.findById(10L)).thenReturn(Optional.of(appointment));

        appointmentService.deleteAppointment(10L);

        verify(appointmentRepository).deleteById(10L);
    }

    @Test
    void getAllAppointments_success(){
        when(appointmentRepository.findAll()).thenReturn(List.of(appointment));

        List<AppointmentDTO> list = appointmentService.getAllAppointments();

        assertEquals(1,list.size());
    }
}
