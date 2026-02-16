package sabatinoprovenza.BE_S6_L5.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sabatinoprovenza.BE_S6_L5.entities.Dipendente;
import sabatinoprovenza.BE_S6_L5.exceptions.BadRequestException;
import sabatinoprovenza.BE_S6_L5.exceptions.NotFoundException;
import sabatinoprovenza.BE_S6_L5.payloads.DipendenteDTO;
import sabatinoprovenza.BE_S6_L5.repositories.DipendenteRepository;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Service
public class DipendenteService {
    private final DipendenteRepository dipendenteRepository;
    private final Cloudinary cloudinary;

    public DipendenteService(DipendenteRepository dipendenteRepository, Cloudinary cloudinary) {
        this.dipendenteRepository = dipendenteRepository;
        this.cloudinary = cloudinary;
    }

    public Dipendente create(DipendenteDTO d) {

        if (dipendenteRepository.existsByUsername(d.username())) {
            throw new BadRequestException("Usename già in uso: " + d.username());
        }

        if (dipendenteRepository.existsByEmail(d.email())) {
            throw new BadRequestException("Email già in uso: " + d.email());
        }

        Dipendente dipendente = new Dipendente(d.username(), d.nome(), d.cognome(), d.email(), d.password());

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
}
