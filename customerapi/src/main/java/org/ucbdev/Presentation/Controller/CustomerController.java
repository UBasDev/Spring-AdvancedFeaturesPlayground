package org.ucbdev.Presentation.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.ucbdev.Core.Application.Request.CreateSingleCustomerRequest;
import org.ucbdev.Core.Application.Response.CreateSingleCustomerResponse;

import java.text.MessageFormat;
import java.time.*;
import java.util.Date;

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
    @PostMapping(path="create-single-customer")
    public CreateSingleCustomerResponse createSingleCustomer(@RequestBody(required = true)CreateSingleCustomerRequest requestBody){
        return CreateSingleCustomerResponse.builder().id((long)1).name(requestBody.getName()).age(requestBody.getAge()).email(requestBody.getEmail()).build();
    }
    @GetMapping(path = "test1")
    public void Test1(){
        var x1 = "Ahmet1";
        var x2 = "Ali1";
        var x3 = String.format("String values: %s ve %s",x1,x2);
        var x6 = String.format("Boolean values: %b ve %b",x1,x2);
        var x7 = String.format("Char values: %c ve %c", 'a', 'b');
        var x8 = String.format("Integer values: %d ve %d",1,2);
        var x10 = String.format("Integer values: %03d ve %d",1,2);
        var x11 = String.format("Integer values: %05d ve %d",1,2);
        var x9 = String.format("Float values: %f", 12.12345);
        var x4 = new StringBuilder().append("Hello ").append(x1).append(" ").append("and ").append(x2).toString();
        var x5 = MessageFormat.format("Hello {0} and {1} and {1}",x1,x2);

        int x12 = 3;
        String x13 = String.valueOf(x12);
        String x14 = String.valueOf('a');
        long x15 = 5;
        String x16 = String.valueOf(x15);
        float x17 = 12.55F;
        String x18 = String.valueOf(x17);
        double x19 = 12.55d;
        String x20 = String.valueOf(x19);
        boolean x21 = true;
        String x22 = String.valueOf(x21);
        char[] x23 = new char[]{'a', 'h', 'm', 'e', 't', '1'};
        String x24 = String.valueOf(x23, 2, 3);
        var object1 = CreateSingleCustomerResponse.builder().name("ahmet1").email("ahmet1@gmail.com").build();
        String x25 = String.valueOf(object1);

        /*
        var x26 = LocalTime.MAX;
        var x27 = LocalTime.MIN;
        var x28 = LocalTime.now();
        var x29 = LocalTime.now(Clock.systemUTC());
        var x30 = LocalTime.now(ZoneId.of("Europe/Paris"));
        */
    }

    @GetMapping(path="test2")
    public String Test2(){
        char[] x1 = new char[]{'a', 'h', 'm', 'e', 't'};
        var x2 = String.copyValueOf(x1, 2, 3);
        String x3 = String.join(" ** ", "Ahmet1", "Mehmet1", "Ali1");
        return x3;
    }
}
