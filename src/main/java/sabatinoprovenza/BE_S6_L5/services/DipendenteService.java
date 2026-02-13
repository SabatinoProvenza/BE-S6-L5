package sabatinoprovenza.BE_S6_L5.services;

import org.springframework.stereotype.Service;
import sabatinoprovenza.BE_S6_L5.entities.Dipendente;
import sabatinoprovenza.BE_S6_L5.exceptions.BadRequestException;
import sabatinoprovenza.BE_S6_L5.payloads.DipendenteDTO;
import sabatinoprovenza.BE_S6_L5.repositories.DipendenteRepository;

@Service
public class DipendenteService {
    private final DipendenteRepository dipendenteRepository;

    public DipendenteService(DipendenteRepository dipendenteRepository) {
        this.dipendenteRepository = dipendenteRepository;
    }

    public Dipendente create(DipendenteDTO d) {

        if (dipendenteRepository.existsByUsername(d.username())) {
            throw new BadRequestException("Usename già in uso: " + d.username());
        }

        if (dipendenteRepository.existsByEmail(d.email())) {
            throw new BadRequestException("Email già in uso: " + d.email());
        }

        Dipendente dipendente = new Dipendente(d.username(), d.nome(), d.cognome(), d.email());

        Dipendente dipendenteSalvato = this.dipendenteRepository.save(dipendente);
        System.out.println("Il dipendente con id: " + dipendenteSalvato.getId() + " è stato salvato correttamente!");
        return this.dipendenteRepository.save(dipendenteSalvato);
    }
}
