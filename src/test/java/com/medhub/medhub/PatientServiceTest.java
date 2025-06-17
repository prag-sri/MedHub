package com.medhub.medhub;

import com.medhub.medhub.dto.PatientDTO;
import com.medhub.medhub.entity.AppUser;
import com.medhub.medhub.entity.Patient;
import com.medhub.medhub.exception.PatientNotFoundException;
import com.medhub.medhub.repository.AppUserRepository;
import com.medhub.medhub.repository.PatientRepository;
import com.medhub.medhub.service.PatientService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PatientServiceTest {

    @InjectMocks
    private PatientService patientService;

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private AppUserRepository appUserRepository;

    private PatientDTO dto;
    private Patient patient;
    private AppUser user;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

        user = new AppUser();
        user.setId(1L);
        user.setUsername("docuser");

        dto = new PatientDTO();
        dto.setId(10L);
        dto.setUserId(1L);
        dto.setMedicalHistory("Fracture");

        patient = new Patient();
        patient.setId(10L);
        patient.setMedicalHistory("Fracture");
        patient.setUser(user);
    }

    @Test
    void createPatient_success(){
        when(appUserRepository.findById(1L)).thenReturn(Optional.of(user));
        when(patientRepository.save(any(Patient.class))).thenReturn(patient);

        PatientDTO result = patientService.createPatient(dto);

        assertNotNull(result);
        assertEquals("Fracture",result.getMedicalHistory());
        verify(patientRepository).save(any(Patient.class));
    }

    @Test
    void getPatientById_success(){
        when(patientRepository.findById(10L)).thenReturn(Optional.of(patient));

        PatientDTO result = patientService.getPatientById(10L);

        assertNotNull(result);
        assertEquals("Fracture",result.getMedicalHistory());
    }

    @Test
    void getPatientById_notFound(){
        when(patientRepository.findById(10L)).thenReturn(Optional.empty());

        assertThrows(PatientNotFoundException.class, () -> patientService.getPatientById(99L));
    }

    @Test
    void updatePatient_success(){
        Patient updated = new Patient();
        updated.setId(10L);
        updated.setMedicalHistory("Back Pain");
        updated.setUser(user);

        dto.setMedicalHistory("Back Pain");

        when(patientRepository.findById(10L)).thenReturn(Optional.of(patient));
        when(appUserRepository.findById(1L)).thenReturn(Optional.of(user));
        when(patientRepository.save(any(Patient.class))).thenReturn(updated);

        PatientDTO result = patientService.updatePatient(10L,dto);

        assertNotNull(result);
        assertEquals("Back Pain", result.getMedicalHistory());
    }

    @Test
    void deletePatient_success(){
        when(patientRepository.findById(10L)).thenReturn(Optional.of(patient));

        patientService.deletePatient(10L);

        verify(patientRepository).deleteById(10L);
    }

    @Test
    void getAllPatients_success(){
        when(patientRepository.findAll()).thenReturn(List.of(patient));

        List<PatientDTO> list = patientService.getAllPatients();

        assertEquals(1,list.size());
    }
}
