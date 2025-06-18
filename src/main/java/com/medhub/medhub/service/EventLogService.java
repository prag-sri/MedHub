package com.medhub.medhub.service;

import com.medhub.medhub.entity.EventLog;
import com.medhub.medhub.repository.EventLogRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EventLogService {

    private final EventLogRepository eventLogRepository;

    public EventLogService(EventLogRepository eventLogRepository){
        this.eventLogRepository = eventLogRepository;
    }

    public void log(String eventType, String eventData) {
        EventLog log = new EventLog();
        log.setEventType(eventType);
        log.setEventData(eventData);
        log.setTimestamp(LocalDateTime.now());
        eventLogRepository.save(log);
    }
}
