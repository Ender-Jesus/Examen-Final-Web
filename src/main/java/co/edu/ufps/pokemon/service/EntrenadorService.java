package co.edu.ufps.pokemon.service;

import co.edu.ufps.pokemon.entity.Entrenador;
import co.edu.ufps.pokemon.repository.EntrenadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntrenadorService {

    @Autowired
    private EntrenadorRepository entrenadorRepository;

    public String registrar(String email) {
        Entrenador entrenador = Entrenador.builder()
                .email(email)
                .build();
        Entrenador savedEntrenador = entrenadorRepository.save(entrenador);
        return savedEntrenador.getUuid();
    }
}
