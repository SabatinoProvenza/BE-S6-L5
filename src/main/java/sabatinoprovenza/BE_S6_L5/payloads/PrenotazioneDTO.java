package sabatinoprovenza.BE_S6_L5.payloads;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record PrenotazioneDTO(
        @NotNull(message = "L' id del viaggio è obbligatorio")
        UUID viaggioId,

        @NotNull(message = "L' id del dipendente è obbligatorio")
        UUID dipendenteId,

        @NotNull(message = "data partenza obbligatoria")
        LocalDate dataPartenza,

        String preferenze
) {
}
