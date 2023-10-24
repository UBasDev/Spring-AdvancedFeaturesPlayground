package org.ucbdev.Core.Application.Request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateSingleCustomerRequest {
    private String name;
    private byte age;
    private String email;
}
