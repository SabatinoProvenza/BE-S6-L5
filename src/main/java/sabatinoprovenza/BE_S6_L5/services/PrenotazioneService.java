package sabatinoprovenza.BE_S6_L5.services;

import org.springframework.stereotype.Service;
import sabatinoprovenza.BE_S6_L5.entities.Dipendente;
import sabatinoprovenza.BE_S6_L5.entities.Prenotazione;
import sabatinoprovenza.BE_S6_L5.entities.Viaggio;
import sabatinoprovenza.BE_S6_L5.exceptions.BadRequestException;
import sabatinoprovenza.BE_S6_L5.exceptions.NotFoundException;
import sabatinoprovenza.BE_S6_L5.payloads.PrenotazioneDTO;
import sabatinoprovenza.BE_S6_L5.repositories.DipendenteRepository;
import sabatinoprovenza.BE_S6_L5.repositories.PrenotazioneRepository;
import sabatinoprovenza.BE_S6_L5.repositories.ViaggioRepository;

@Service
public class PrenotazioneService {
    private final PrenotazioneRepository prenotazioneRepository;
    private final DipendenteRepository dipendenteRepository;
    private final ViaggioRepository viaggioRepository;

    public PrenotazioneService(PrenotazioneRepository prenotazioneRepository, DipendenteRepository dipendenteRepository, ViaggioRepository viaggioRepository) {
        this.prenotazioneRepository = prenotazioneRepository;
        this.dipendenteRepository = dipendenteRepository;
        this.viaggioRepository = viaggioRepository;
    }

    public Prenotazione create(PrenotazioneDTO p) {
        Dipendente dipendente = dipendenteRepository.findById(p.dipendenteId()).orElseThrow(() -> new NotFoundException("Il dipendente con id: " + p.dipendenteId() + " non è stato trovato"));
        Viaggio viaggio = viaggioRepository.findById(p.viaggioId()).orElseThrow(() -> new NotFoundException("Il viaggio con id: " + p.viaggioId() + " non è stato trovato"));
        if (prenotazioneRepository.existsByDipendenteIdAndDataPartenza(p.dipendenteId(), p.dataPartenza()))
            throw new BadRequestException("Il dipendente ha già una prenotazione per la data richiesta: " + p.dataPartenza());

        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setDipendente(dipendente);
        prenotazione.setViaggio(viaggio);
        prenotazione.setDataPartenza(p.dataPartenza());
        prenotazione.setPreferenze(p.preferenze());

        return this.prenotazioneRepository.save(prenotazione);

    }
}
