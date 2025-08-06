package com.kroger.ngpos.instore.accounting.distributed.locking;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @GetMapping
    public String welcome(){
        return "Welcome to Distributed locking demo service";
    }

}
