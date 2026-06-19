package co.edu.ufps.pokemon.controller;

import co.edu.ufps.pokemon.dto.PokemonRequestDTO;
import co.edu.ufps.pokemon.dto.PokemonResponseDTO;
import co.edu.ufps.pokemon.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pokemons")
public class PokemonController {

    @Autowired
    private PokemonService pokemonService;

    @GetMapping("/{tipo}/{po}")
    public ResponseEntity<List<PokemonResponseDTO>> listarPorTipoYEntrenador(
            @PathVariable String tipo,
            @PathVariable Integer po) {

        List<PokemonResponseDTO> pokemons = pokemonService.listarPorTipoYEntrenador(tipo, po);
        return ResponseEntity.ok(pokemons);
    }

    @PostMapping
    public ResponseEntity<PokemonResponseDTO> registrar(@RequestBody PokemonRequestDTO requestDTO) {
        PokemonResponseDTO response = pokemonService.registrar(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

