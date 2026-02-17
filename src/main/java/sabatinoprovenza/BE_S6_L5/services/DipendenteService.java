package sabatinoprovenza.BE_S6_L5.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sabatinoprovenza.BE_S6_L5.entities.Dipendente;
import sabatinoprovenza.BE_S6_L5.exceptions.BadRequestException;
import sabatinoprovenza.BE_S6_L5.exceptions.NotFoundException;
import sabatinoprovenza.BE_S6_L5.payloads.DipendenteDTO;
import sabatinoprovenza.BE_S6_L5.repositories.DipendenteRepository;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class DipendenteService {
    private final DipendenteRepository dipendenteRepository;
    private final Cloudinary cloudinary;
    private final PasswordEncoder passwordEncoder;

    public DipendenteService(DipendenteRepository dipendenteRepository, Cloudinary cloudinary, PasswordEncoder passwordEncoder) {
        this.dipendenteRepository = dipendenteRepository;
        this.cloudinary = cloudinary;
        this.passwordEncoder = passwordEncoder;
    }

    public Dipendente findByEmail(String email) {
        return this.dipendenteRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("L'utente con email " + email + " non è stato trovato!"));
    }

    public Dipendente findById(UUID id) {
        return this.dipendenteRepository.findById(id).orElseThrow(() -> new NotFoundException("Il dipendente con id: " + id + " non è stato trovato"));
    }

    public Dipendente create(DipendenteDTO d) {

        if (dipendenteRepository.existsByUsername(d.username())) {
            throw new BadRequestException("Usename già in uso: " + d.username());
        }

        if (dipendenteRepository.existsByEmail(d.email())) {
            throw new BadRequestException("Email già in uso: " + d.email());
        }

        Dipendente dipendente = new Dipendente(d.username(), d.nome(), d.cognome(), d.email(), passwordEncoder.encode(d.password()));

        Dipendente dipendenteSalvato = this.dipendenteRepository.save(dipendente);
        System.out.println("Il dipendente con id: " + dipendenteSalvato.getId() + " è stato salvato correttamente!");
        return this.dipendenteRepository.save(dipendenteSalvato);
    }


    public Dipendente uploadAvatar(MultipartFile file, UUID dipendenteId) {
        Dipendente dipendente = dipendenteRepository.findById(dipendenteId).orElseThrow(() -> new NotFoundException("Il dipendente con id: " + dipendenteId + " non è stato trovato"));

        try {
            Map result = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String imageUrl = (String) result.get("secure_url");
            dipendente.setAvatar(imageUrl);
            return dipendenteRepository.save(dipendente);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Dipendente> findAll() {
        return dipendenteRepository.findAll();
    }
}
