package com.kroger.ngpos.instore.accounting.distributed.locking.config;

import com.kroger.ngpos.instore.accounting.distributed.locking.config.annotation.ScheduleDistributedLock;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import java.time.LocalDateTime;


@EnableScheduling
@Configuration
@EnableAsync
@Slf4j
public class ScheduleConfiguration {

    @Scheduled(cron = "*/1 * * * * * ")
    @Async
    @ScheduleDistributedLock(lockKey = "ngpos-isa-batch", expiresAfterMs = 50000)
    public void schedule() throws InterruptedException {
        log.info("Executing schedule : {}", LocalDateTime.now());
        Thread.sleep(5000);
    }


    @Scheduled(cron = "*/1 * * * * * ")
    @Async
    @ScheduleDistributedLock(lockKey = "ngpos-isa-batch-2")
    public void schedule2() throws InterruptedException {
        log.info("Executing schedule 2: {}", LocalDateTime.now());
        Thread.sleep(5000);
    }

}
