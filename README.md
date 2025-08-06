# scheduled-distributed-locks
Scheduled Distributed locking utility to execute scheduled tasks in distributed environment.

## Usage
`@ScheduleDistributedLock(lockKey = "ngpos-isa-batch", expiresAfterMs = 50000)`

`lockkey - unique to identify scheduled tasks` 
`expiresAfterMs - lock expiry after in milliseconds.`


## Required env vars

REDIS_HOST - Redis cluster host <br>
REDIS_PORT - Redis cluster port <br>
REDIS_KEY - Redis cluster key to connect

## Property to enable

app.schedule.distributed-lock.enable = true 