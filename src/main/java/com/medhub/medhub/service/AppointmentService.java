package com.medhub.medhub.service;

import com.medhub.medhub.dto.AppointmentDTO;
import com.medhub.medhub.entity.Appointment;
import com.medhub.medhub.entity.Doctor;
import com.medhub.medhub.entity.Patient;
import com.medhub.medhub.exception.AppointmentNotFoundException;
import com.medhub.medhub.exception.DoctorNotFoundException;
import com.medhub.medhub.exception.PatientNotFoundException;
import com.medhub.medhub.mapper.AppointmentMapper;
import com.medhub.medhub.repository.AppointmentRepository;
import com.medhub.medhub.repository.DoctorRepository;
import com.medhub.medhub.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    private AppointmentRepository appointmentRepository;
    private DoctorRepository doctorRepository;
    private PatientRepository patientRepository;

    public AppointmentService(AppointmentRepository appointmentRepository, DoctorRepository doctorRepository, PatientRepository patientRepository) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    public AppointmentDTO createAppointment(AppointmentDTO dto) {
        Doctor doctor = doctorRepository.findById(dto.getDoctorId())
                .orElseThrow(() -> new DoctorNotFoundException("Doctor with ID: " + dto.getDoctorId() + " not found!"));

        Patient patient = patientRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new PatientNotFoundException("Patient with ID: " + dto.getPatientId() + " not found!"));

        Appointment appointment = AppointmentMapper.toEntity(dto,doctor,patient);
        return AppointmentMapper.toDTO(appointmentRepository.save(appointment));
    }

    public AppointmentDTO getAppointmentById(Long id) {
        Appointment appointment= appointmentRepository.findById(id)
                .orElseThrow(() -> new AppointmentNotFoundException("Appointment with ID: " + id + " not found!"));
        return AppointmentMapper.toDTO(appointment);
    }

    public AppointmentDTO updateAppointment(Long id, AppointmentDTO dto) {
        Appointment appointment= appointmentRepository.findById(id)
                .orElseThrow(() -> new AppointmentNotFoundException("Appointment with ID: " + id + " not found!"));

        Doctor doctor = doctorRepository.findById(dto.getDoctorId())
                .orElseThrow(() -> new DoctorNotFoundException("Doctor with ID: " + dto.getDoctorId() + " not found!"));

        Patient patient = patientRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new PatientNotFoundException("Patient with ID: " + dto.getPatientId() + " not found!"));

        appointment.setAppointmentTime(dto.getAppointmentTime());
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);

        return AppointmentMapper.toDTO(appointmentRepository.save(appointment));
    }

    public void deleteAppointment(Long id) {
        appointmentRepository.findById(id)
                .orElseThrow(() -> new AppointmentNotFoundException("Appointment with ID: " + id + " not found!"));
        appointmentRepository.deleteById(id);
    }

    public List<AppointmentDTO> getAllAppointments() {
        return appointmentRepository.findAll()
                .stream()
                .map(AppointmentMapper::toDTO)
                .toList();
    }
}
