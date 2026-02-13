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

    // false = IN_PROGRAMMA, true = COMPLETATO
    @Column(nullable = false)
    private boolean completato;


    public Viaggio(String destinazione, LocalDate dataPartenza, boolean completato) {
        this.destinazione = destinazione;
        this.dataPartenza = dataPartenza;
        this.completato = completato;
    }
}