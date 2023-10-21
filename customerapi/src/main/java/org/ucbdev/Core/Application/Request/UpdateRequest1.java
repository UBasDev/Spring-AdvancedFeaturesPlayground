package org.ucbdev.Core.Application.Request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateRequest1 {
    private Long customerId;
    private String customerName = "";
    private String customerLastname = "";
    private Integer customerAge = 0;
    private Integer orderPrice = 0;
}
