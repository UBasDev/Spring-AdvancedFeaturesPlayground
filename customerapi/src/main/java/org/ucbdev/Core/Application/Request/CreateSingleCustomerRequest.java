package org.ucbdev.Core.Application.Request;

import lombok.*;

@Builder
@Getter
@Setter
public class CreateSingleCustomerRequest {
    private String name;
    private byte age;
    private String email;
}
