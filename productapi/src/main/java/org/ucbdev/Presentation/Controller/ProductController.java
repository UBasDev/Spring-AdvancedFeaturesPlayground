package org.ucbdev.Presentation.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/productapi")
public class ProductController {
    @GetMapping("get-single-product")
    public String getSingleProduct(){
        System.out.println("******************************Product has been sent!******************************");
        return "BMW";
    }
}
