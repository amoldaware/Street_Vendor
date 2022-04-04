package com.nbfc.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.nbfc.service.DisbursementDetailsService;

@Component
@Configuration
@EnableScheduling
public class ScheduledMasterUpdation 
{
	final static Logger logger = Logger.getLogger(ClaimUploadController.class.getName());
	
	@Autowired
	DisbursementDetailsService disbursementDetailsService;
	
    //@Scheduled(cron = "*/5 * * * * ?")
    @Scheduled(cron ="0 19 * * * *")
	//@Scheduled(cron="0 * * * 0/30 ?")
    public void updateMasterTable() 
    {
        try
        {
        	disbursementDetailsService.saveUnmatcherMasterTableData();
        }
        catch(Exception e){
        	e.printStackTrace();
        	System.out.println(e.getMessage());
        }
    }
}
