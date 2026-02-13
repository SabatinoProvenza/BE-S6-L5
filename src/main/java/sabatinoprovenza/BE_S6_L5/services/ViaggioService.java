package sabatinoprovenza.BE_S6_L5.services;

import org.springframework.stereotype.Service;
import sabatinoprovenza.BE_S6_L5.entities.Viaggio;
import sabatinoprovenza.BE_S6_L5.exceptions.NotFoundException;
import sabatinoprovenza.BE_S6_L5.payloads.ViaggioDTO;
import sabatinoprovenza.BE_S6_L5.repositories.ViaggioRepository;

import java.util.UUID;

@Service
public class ViaggioService {
    private final ViaggioRepository viaggioRepository;

    public ViaggioService(ViaggioRepository viaggioRepository) {
        this.viaggioRepository = viaggioRepository;
    }

    public Viaggio create(ViaggioDTO v) {
        Viaggio viaggio = new Viaggio(v.destinazione(), v.data(), v.stato());
        return this.viaggioRepository.save(viaggio);
    }

    public Viaggio cambiaStato(UUID id, String stato) {

        Viaggio viaggio = viaggioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Viaggio con id " + id + " non trovato"));

        viaggio.setStato(stato);

        return viaggioRepository.save(viaggio);
    }
}
