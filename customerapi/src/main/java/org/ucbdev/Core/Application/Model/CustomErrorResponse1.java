package org.ucbdev.Core.Application.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class CustomErrorResponse1 {
    private final int status;
    private final String error;
    private final String message;
    private final Instant timestamp;
}
