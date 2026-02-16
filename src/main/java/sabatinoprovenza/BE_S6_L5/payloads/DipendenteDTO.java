package sabatinoprovenza.BE_S6_L5.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DipendenteDTO(
        @NotBlank(message = "Username obbligatorio")
        String username,

        @NotBlank(message = "Nome obbligatorio")
        String nome,

        @NotBlank(message = "Cognome obbligatorio")
        String cognome,

        @NotBlank(message = "Email obbligatoria")
        @Email(message = "Email non valida")
        String email,

        @NotBlank(message = "Password obbligatoria")
        String password
) {

}
