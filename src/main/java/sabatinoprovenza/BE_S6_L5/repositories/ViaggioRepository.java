package sabatinoprovenza.BE_S6_L5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sabatinoprovenza.BE_S6_L5.entities.Viaggio;

import java.util.UUID;

public interface ViaggioRepository extends JpaRepository<Viaggio, UUID> {
}
