package com.incetutku.transactionaloverview.service;

import com.incetutku.transactionaloverview.entity.Log;
import com.incetutku.transactionaloverview.repository.LogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoggingService {

    private final LogRepository logRepository;

    public LoggingService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void  logAMessage(String logMessage) {
        Log log = new Log(logMessage);
        logRepository.save(log);
    }
}
