package sabatinoprovenza.BE_S6_L5.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ViaggioDTO(
        @NotBlank(message = "Destinazione obbligatoria")
        String destinazione,

        @NotNull(message = "Data obbligatoria")
        LocalDate data,

        @NotBlank(message = "Stato obbligatorio")
        String stato
) {

}
