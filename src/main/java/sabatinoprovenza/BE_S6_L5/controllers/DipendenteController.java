package sabatinoprovenza.BE_S6_L5.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sabatinoprovenza.BE_S6_L5.entities.Dipendente;
import sabatinoprovenza.BE_S6_L5.exceptions.ValidationExceptions;
import sabatinoprovenza.BE_S6_L5.payloads.DipendenteDTO;
import sabatinoprovenza.BE_S6_L5.services.DipendenteService;

import java.util.List;

@RestController
@RequestMapping("/dipendenti")
public class DipendenteController {
    private final DipendenteService dipendenteService;

    public DipendenteController(DipendenteService dipendenteService) {
        this.dipendenteService = dipendenteService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Dipendente create(@RequestBody @Validated DipendenteDTO payload, BindingResult validationResult) {

        if (validationResult.hasErrors()) {

            List<String> errorsList = validationResult.getFieldErrors()
                    .stream()
                    .map(fieldError -> fieldError.getDefaultMessage())
                    .toList();

            throw new ValidationExceptions(errorsList);
        } else {
            return this.dipendenteService.create(payload);
        }

    }
}
