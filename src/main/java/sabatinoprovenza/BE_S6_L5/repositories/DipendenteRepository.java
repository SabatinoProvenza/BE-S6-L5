package sabatinoprovenza.BE_S6_L5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sabatinoprovenza.BE_S6_L5.entities.Dipendente;

import java.util.Optional;
import java.util.UUID;

public interface DipendenteRepository extends JpaRepository<Dipendente, UUID> {
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Optional<Dipendente> findByEmail(String email);
}
