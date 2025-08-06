package com.kroger.ngpos.instore.accounting.distributed.locking.config.annotation;

import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.locks.Lock;

@Aspect
@Component
@Slf4j
public class ScheduleDistributedLockProcessor {

    private final RedisConnectionFactory redisConnectionFactory;


    public ScheduleDistributedLockProcessor(RedisConnectionFactory redisConnectionFactory) {
        this.redisConnectionFactory = redisConnectionFactory;
    }


    @Around("@annotation(com.kroger.ngpos.instore.accounting.distributed.locking.config.annotation.ScheduleDistributedLock)")
    public Object executeTask(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Lock lockObj = extractLockObject(proceedingJoinPoint);
        Object result = null;
        try {
            if (lockObj.tryLock()) {
                result = proceedingJoinPoint.proceed();
                lockObj.unlock();
            }
        } catch (Exception ex){
            log.error("Exception in schedule job execution : {}", ex.getMessage());
            lockObj.unlock();
        }
        return result;
    }

    private Lock extractLockObject(ProceedingJoinPoint proceedingJoinPoint) {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = methodSignature.getMethod();
        ScheduleDistributedLock scheduleDistributedLock = method.getAnnotation(ScheduleDistributedLock.class);
        return new RedisLockRegistry(redisConnectionFactory, scheduleDistributedLock.lockKey(), scheduleDistributedLock.expiresAfterMs()).obtain(scheduleDistributedLock.lockKey());
    }
}
