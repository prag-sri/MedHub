package com.medhub.medhub;

import com.medhub.medhub.dto.DoctorDTO;
import com.medhub.medhub.entity.AppUser;
import com.medhub.medhub.entity.Doctor;
import com.medhub.medhub.exception.DoctorNotFoundException;
import com.medhub.medhub.repository.AppUserRepository;
import com.medhub.medhub.repository.DoctorRepository;
import com.medhub.medhub.service.DoctorService;
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

public class DoctorServiceTest {

    @InjectMocks
    private DoctorService doctorService;

    @Mock
    private DoctorRepository doctorRepository;

    @Mock
    private AppUserRepository appUserRepository;

    private DoctorDTO dto;
    private AppUser user;
    private Doctor doctor;

    @BeforeEach
    void setUp(){
        // Below tells to:
        // Scan your test class (this) for annotations like @Mock and @InjectMocks.
        // Automatically initialize those mocks.
        // Inject the mocks into your DoctorService (or whatever class is under test).
        // Without this, your mocks won’t be initialized, and your tests will throw NullPointerException.
        MockitoAnnotations.openMocks(this);

        user = new AppUser();
        user.setId(1L);
        user.setUsername("docuser");

        dto = new DoctorDTO();
        dto.setId(10L);
        dto.setUserId(1L);
        dto.setSpecialization("Cardiology");

        doctor = new Doctor();
        doctor.setId(10L);
        doctor.setSpecialization("Cardiology");
        doctor.setUser(user);
    }

    @Test
    void createDoctor_success(){
        when(appUserRepository.findById(1L)).thenReturn(Optional.of(user));
        when(doctorRepository.save(any(Doctor.class))).thenReturn(doctor);

        DoctorDTO result = doctorService.createDoctor(dto);

        assertNotNull(result);
        assertEquals("Cardiology",result.getSpecialization());
        verify(doctorRepository).save(any(Doctor.class));
    }

    @Test
    void getDoctorById_success(){
        when(doctorRepository.findById(10L)).thenReturn(Optional.of(doctor));

        DoctorDTO result = doctorService.getDoctorById(10L);

        assertNotNull(result);
        assertEquals("Cardiology", result.getSpecialization());
    }

    @Test
    void getDoctorById_notFound(){
        when(doctorRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(DoctorNotFoundException.class, () -> doctorService.getDoctorById(99L));
    }

    @Test
    void updateDoctor_success(){
        Doctor updated = new Doctor();
        updated.setId(10L);
        updated.setSpecialization("Neurology");
        updated.setUser(user);

        dto.setSpecialization("Neurology");

        when(doctorRepository.findById(10L)).thenReturn(Optional.of(doctor));
        when(appUserRepository.findById(1L)).thenReturn(Optional.of(user));
        when(doctorRepository.save(any(Doctor.class))).thenReturn(updated);

        DoctorDTO result = doctorService.updateDoctor(10L,dto);

        assertNotNull(result);
        assertEquals("Neurology",result.getSpecialization());
    }

    @Test
    void deleteDoctor_success(){
        when(doctorRepository.findById(10L)).thenReturn(Optional.of(doctor));

        doctorService.deleteDoctor(10L);

        verify(doctorRepository).deleteById(10L);
    }

    @Test
    void getAllDoctors_success(){
        when(doctorRepository.findAll()).thenReturn(List.of(doctor));

        List<DoctorDTO> list = doctorService.getAllDoctors();

        assertEquals(1,list.size());
    }
}
