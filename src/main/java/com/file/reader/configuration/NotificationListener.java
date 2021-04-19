package com.file.reader.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.file.reader.repository.EmployeeRepository;

@Component
public class NotificationListener extends JobExecutionListenerSupport{

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationListener.class);

    
    @Autowired
    EmployeeRepository repository;

    @Override
    public void afterJob(final JobExecution jobExecution) {
    	
    	if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            LOGGER.info("!!! JOB FINISHED! Time to verify the results");
            
            repository.findAll().forEach(employee -> LOGGER.info("Found <" + employee + "> in the database."));;
            
        }
    }
}