package org.ucbdev.Presentation.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customerapi")
public class CustomerController {
    @GetMapping(path = "get-single-customer")
    public String GetSingleCustomer(){
        return "Hello world! test2";
    }
}
