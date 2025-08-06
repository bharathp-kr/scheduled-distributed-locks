package com.kroger.ngpos.instore.accounting.distributed.locking.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StartupListener {

    private static final Logger log = LoggerFactory.getLogger(StartupListener.class);

    public StartupListener() {

    }

    @EventListener
    public void onApplicationReady(ApplicationReadyEvent event){
        log.info("Application startup event received : time taken {}", event.getTimeTaken());
       // log.info("Lock acquired : {}", distributedLockService.acquireLock());

    }

}
