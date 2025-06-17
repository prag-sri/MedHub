package com.medhub.medhub.controller;

import com.medhub.medhub.dto.AppointmentDTO;
import com.medhub.medhub.service.AppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    private AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    @PostMapping
    public ResponseEntity<AppointmentDTO> createAppointment(@RequestBody AppointmentDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentService.createAppointment(dto));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDTO> getAppointmentById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(appointmentService.getAppointmentById(id));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    @PutMapping("/{id}")
    public ResponseEntity<AppointmentDTO> updateAppointment(@PathVariable Long id, @RequestBody AppointmentDTO dto){
        return ResponseEntity.status(HttpStatus.OK).body(appointmentService.updateAppointment(id,dto));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAppointment(@PathVariable Long id){
        appointmentService.deleteAppointment(id);
        return ResponseEntity.status(HttpStatus.OK).body("Appointment deleted successfully!");
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    @GetMapping
    public ResponseEntity<List<AppointmentDTO>> getAllAppointments(){
        return ResponseEntity.status(HttpStatus.OK).body(appointmentService.getAllAppointments());
    }
}
