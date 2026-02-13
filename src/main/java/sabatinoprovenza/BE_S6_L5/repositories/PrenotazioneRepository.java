package sabatinoprovenza.BE_S6_L5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sabatinoprovenza.BE_S6_L5.entities.Prenotazione;

import java.time.LocalDate;
import java.util.UUID;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, UUID> {
    boolean existsByDipendenteIdAndDataPartenza(UUID dipendenteId, LocalDate dataPartenza);
}
