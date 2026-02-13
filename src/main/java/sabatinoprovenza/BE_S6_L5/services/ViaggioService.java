package sabatinoprovenza.BE_S6_L5.services;

import org.springframework.stereotype.Service;
import sabatinoprovenza.BE_S6_L5.entities.Viaggio;
import sabatinoprovenza.BE_S6_L5.payloads.ViaggioDTO;
import sabatinoprovenza.BE_S6_L5.repositories.ViaggioRepository;

@Service
public class ViaggioService {
    private final ViaggioRepository viaggioRepository;

    public ViaggioService(ViaggioRepository viaggioRepository) {
        this.viaggioRepository = viaggioRepository;
    }

    public Viaggio create(ViaggioDTO v) {
        Viaggio viaggio = new Viaggio(v.destinazione(), v.data(), v.completato());
        return this.viaggioRepository.save(viaggio);
    }
}
