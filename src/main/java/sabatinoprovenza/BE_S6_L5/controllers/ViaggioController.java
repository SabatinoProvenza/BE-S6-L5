package sabatinoprovenza.BE_S6_L5.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sabatinoprovenza.BE_S6_L5.entities.Viaggio;
import sabatinoprovenza.BE_S6_L5.exceptions.ValidationExceptions;
import sabatinoprovenza.BE_S6_L5.payloads.ViaggioDTO;
import sabatinoprovenza.BE_S6_L5.services.ViaggioService;

import java.util.List;

@RestController
@RequestMapping("/viaggi")
public class ViaggioController {
    private final ViaggioService viaggioService;

    public ViaggioController(ViaggioService viaggioService) {
        this.viaggioService = viaggioService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Viaggio create(@RequestBody @Validated ViaggioDTO payload, BindingResult validationResult) {
        if (validationResult.hasErrors()) {

            List<String> errorsList = validationResult.getFieldErrors()
                    .stream()
                    .map(fieldError -> fieldError.getDefaultMessage())
                    .toList();

            throw new ValidationExceptions(errorsList);
        } else {
            return this.viaggioService.create(payload);
        }
    }

}
