package me.zinoviev.railway.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import me.zinoviev.railway.train.CarriageType;

import java.util.List;

public record CreateTrainRequest(

        @NotBlank
        String number,

        @NotBlank
        String name,

        @NotEmpty
        List<@Valid CarriageSpec> carriages
) {

    public record CarriageSpec(

            @Min(1)
            int number,

            @NotNull
            CarriageType type

    ){}

}
