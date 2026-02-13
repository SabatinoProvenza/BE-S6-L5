package sabatinoprovenza.BE_S6_L5.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Entity
@Table(name = "dipendenti")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Dipendente {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cognome;

    @Column(nullable = false)
    private String email;

    private String avatar;

    public Dipendente(String username, String nome, String cognome, String email) {
        this.username = username;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.avatar = "https://ui-avatars.com/api/?name=" + nome + "+" + cognome;
    }
}