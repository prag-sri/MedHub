package com.medhub.medhub.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "event_log")
@Data
public class EventLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String eventType;

    @Column(length = 1000)
    private String eventData;

    private LocalDateTime timestamp = LocalDateTime.now();
}
