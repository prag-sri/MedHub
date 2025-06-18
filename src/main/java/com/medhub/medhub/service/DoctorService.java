package com.medhub.medhub.service;

import com.medhub.medhub.dto.DoctorDTO;
import com.medhub.medhub.entity.AppUser;
import com.medhub.medhub.entity.Doctor;
import com.medhub.medhub.exception.DoctorNotFoundException;
import com.medhub.medhub.exception.UserNotFoundException;
import com.medhub.medhub.mapper.DoctorMapper;
import com.medhub.medhub.repository.AppUserRepository;
import com.medhub.medhub.repository.DoctorRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    private DoctorRepository doctorRepository;
    private AppUserRepository appUserRepository;

    public DoctorService(DoctorRepository doctorRepository, AppUserRepository appUserRepository) {
        this.doctorRepository = doctorRepository;
        this.appUserRepository = appUserRepository;
    }

    public DoctorDTO createDoctor(DoctorDTO dto) {
        AppUser user = appUserRepository.findById(dto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User with ID: " + dto.getUserId() + " not found!"));
        Doctor doctor = DoctorMapper.toEntity(dto,user);
        return DoctorMapper.toDTO(doctorRepository.save(doctor));
    }

    @Cacheable(value = "doctors", key = "#id")
    public DoctorDTO getDoctorById(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new DoctorNotFoundException("Doctor with ID: " + id + " not found!"));
        return DoctorMapper.toDTO(doctor);
    }

    @CachePut(value = "doctors", key = "#id")
    public DoctorDTO updateDoctor(Long id, DoctorDTO dto) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new DoctorNotFoundException("Doctor with ID: " + id + " not found!"));
        AppUser user = appUserRepository.findById(dto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User with ID: " + dto.getUserId() + " not found!"));
        doctor.setSpecialization(dto.getSpecialization());
        doctor.setUser(user);

        return DoctorMapper.toDTO(doctorRepository.save(doctor));
    }

    @CacheEvict(value = "doctors", key = "#id")
    public void deleteDoctor(Long id) {
        doctorRepository.findById(id)
                .orElseThrow(() -> new DoctorNotFoundException("Doctor with ID: " + id + " not found!"));
        doctorRepository.deleteById(id);
    }

    public List<DoctorDTO> getAllDoctors() {
        return doctorRepository.findAll()
                .stream()
                .map(DoctorMapper::toDTO)
                .toList();
    }
}
