package org.ucbdev.Presentation.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/v1/customerapi")
public class CustomerController {
    private final RestTemplate restTemplate;
    @Autowired
    public CustomerController(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }
    @GetMapping(path = "get-single-customer")
    public String getSingleCustomer(){
        return this.restTemplate.getForObject(
                "http://PRODUCTAPI/api/v1/productapi/get-single-product",
                String.class
        );
    }
}
