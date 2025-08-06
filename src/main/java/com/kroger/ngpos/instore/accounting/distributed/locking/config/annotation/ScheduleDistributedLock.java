package com.kroger.ngpos.instore.accounting.distributed.locking.config.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface ScheduleDistributedLock {

    String lockKey() default "sched-dist-lock-key";

    int expiresAfterMs() default 60000;

}
