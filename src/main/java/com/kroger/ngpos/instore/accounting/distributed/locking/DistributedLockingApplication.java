package com.kroger.ngpos.instore.accounting.distributed.locking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DistributedLockingApplication {

	public static void main(String[] args) {
		SpringApplication.run(DistributedLockingApplication.class, args);
	}

}
