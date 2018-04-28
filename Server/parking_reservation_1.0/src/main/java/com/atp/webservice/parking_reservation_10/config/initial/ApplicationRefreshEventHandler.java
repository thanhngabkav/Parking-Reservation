package com.atp.webservice.parking_reservation_10.config.initial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class ApplicationRefreshEventHandler implements ApplicationListener<ContextRefreshedEvent> {


    @Autowired
    private DatabaseInitial databaseInitial;

    @Autowired
    private WorkerInitial workerInitial;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        //init sample data
        databaseInitial.doImport();
        workerInitial.startDaemondThreads();
    }
}
