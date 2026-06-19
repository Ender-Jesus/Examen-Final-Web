package co.edu.ufps.pokemon.controller;

import co.edu.ufps.pokemon.dto.EntrenadorRequestDTO;
import co.edu.ufps.pokemon.dto.EntrenadorResponseDTO;
import co.edu.ufps.pokemon.dto.PokemonResponseDTO;
import co.edu.ufps.pokemon.service.EntrenadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/entrenador")
public class EntrenadorController {

    @Autowired
    private EntrenadorService entrenadorService;

    @PostMapping("/login")
    public ResponseEntity<EntrenadorResponseDTO> login(@RequestBody EntrenadorRequestDTO requestDTO) {
        String uuid = entrenadorService.registrar(requestDTO.getEmail());
        EntrenadorResponseDTO responseDTO = EntrenadorResponseDTO.builder()
                .uuid(uuid)
                .build();
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/{uuid}/pokemons")
    public ResponseEntity<List<PokemonResponseDTO>> listarPokemons(@PathVariable String uuid) {
        List<PokemonResponseDTO> pokemons = entrenadorService.listarPokemons(uuid);
        return ResponseEntity.ok(pokemons);
    }
	
	@PostMapping("/{uuid}/pokemons/{uuidPokemon}")
    public ResponseEntity<Object> agregarPokemon(
            @PathVariable String uuid,
            @PathVariable String uuidPokemon) {
        Object resultado = entrenadorService.agregarPokemon(uuid, uuidPokemon);
        return ResponseEntity.ok(resultado);
    }
}

