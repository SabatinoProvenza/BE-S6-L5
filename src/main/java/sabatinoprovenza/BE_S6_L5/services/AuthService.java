package sabatinoprovenza.BE_S6_L5.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sabatinoprovenza.BE_S6_L5.entities.Dipendente;
import sabatinoprovenza.BE_S6_L5.exceptions.UnauthorizedException;
import sabatinoprovenza.BE_S6_L5.payloads.LoginRequestDTO;
import sabatinoprovenza.BE_S6_L5.security.JWTTools;

@Service
public class AuthService {
    private final DipendenteService dipendenteService;
    private final JWTTools jwtTools;
    private final PasswordEncoder passwordEncoder;

    public AuthService(DipendenteService dipendenteService, JWTTools jwtTools, PasswordEncoder passwordEncoder) {
        this.dipendenteService = dipendenteService;
        this.jwtTools = jwtTools;
        this.passwordEncoder = passwordEncoder;
    }

    public String checkCredentialsAndGenerateToken(LoginRequestDTO body) {

        Dipendente dipendente = this.dipendenteService.findByEmail(body.email());

        if (passwordEncoder.matches(body.password(), dipendente.getPassword())) {
            return jwtTools.generateToken(dipendente);

        } else {
            throw new UnauthorizedException("Credenziali errate!");
        }


    }
}
