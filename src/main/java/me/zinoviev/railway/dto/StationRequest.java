package me.zinoviev.railway.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record StationRequest(

        @NotBlank
        @Size(max = 20)
        String code,

        @NotBlank
        String name,

        @NotBlank
        String city

) {
}
