package org.ucbdev.Core.Application.Response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CreateSingleCustomerResponse {
    private Long id;
    private String name;
    private byte age;
    private String email;
}
