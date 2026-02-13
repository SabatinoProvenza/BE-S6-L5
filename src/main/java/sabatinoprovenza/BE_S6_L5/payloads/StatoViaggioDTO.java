package sabatinoprovenza.BE_S6_L5.payloads;

import jakarta.validation.constraints.NotBlank;

public record StatoViaggioDTO(

        @NotBlank(message = "Stato obbligatorio")
        String stato
) {
}