package com.medhub.medhub.repository;

import com.medhub.medhub.entity.EventLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventLogRepository extends JpaRepository<EventLog,Long> {
}
