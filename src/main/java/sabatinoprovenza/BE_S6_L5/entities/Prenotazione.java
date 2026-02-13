package sabatinoprovenza.BE_S6_L5.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "prenotazioni")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Prenotazione {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "viaggio_id", nullable = false)
    private Viaggio viaggio;

    @ManyToOne(optional = false)
    @JoinColumn(name = "dipendente_id", nullable = false)
    private Dipendente dipendente;

    @Column(name = "data_richiesta", nullable = false)
    private LocalDate dataPartenza;

    @Column(columnDefinition = "TEXT")
    private String preferenze;


    public Prenotazione(Viaggio viaggio, Dipendente dipendente, LocalDate dataPartenza, String preferenze) {
        this.viaggio = viaggio;
        this.dipendente = dipendente;
        this.dataPartenza = dataPartenza;
        this.preferenze = preferenze;
    }
}
