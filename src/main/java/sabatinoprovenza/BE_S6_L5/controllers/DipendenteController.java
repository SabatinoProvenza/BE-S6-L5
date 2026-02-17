package sabatinoprovenza.BE_S6_L5.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sabatinoprovenza.BE_S6_L5.entities.Dipendente;
import sabatinoprovenza.BE_S6_L5.exceptions.ValidationExceptions;
import sabatinoprovenza.BE_S6_L5.payloads.DipendenteDTO;
import sabatinoprovenza.BE_S6_L5.services.DipendenteService;

import java.util.List;
import java.util.UUID;

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

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Dipendente> getAll() {
        return dipendenteService.findAll();
    }

    @PatchMapping("/{dipendenteId}/avatar")
    public Dipendente uploadAvatar(@PathVariable UUID dipendenteId,
                                   @RequestParam("profile_picture") MultipartFile file) {
        return dipendenteService.uploadAvatar(file, dipendenteId);
    }
}
