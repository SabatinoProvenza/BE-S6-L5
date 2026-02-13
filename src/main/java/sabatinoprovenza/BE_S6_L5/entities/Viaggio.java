package sabatinoprovenza.BE_S6_L5.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "viaggi")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Viaggio {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String destinazione;

    @Column(nullable = false)
    private LocalDate dataPartenza;

    @Column(nullable = false)
    private String stato;


    public Viaggio(String destinazione, LocalDate dataPartenza, String stato) {
        this.destinazione = destinazione;
        this.dataPartenza = dataPartenza;
        this.stato = stato;
    }
}