package co.edu.ufps.pokemon.service;

import co.edu.ufps.pokemon.dto.PokemonResponseDTO;
import co.edu.ufps.pokemon.dto.TipoPokemonResponseDTO;
import co.edu.ufps.pokemon.entity.Entrenador;
import co.edu.ufps.pokemon.entity.Pokemon;
import co.edu.ufps.pokemon.entity.PokemonCaptura;
import co.edu.ufps.pokemon.repository.EntrenadorRepository;
import co.edu.ufps.pokemon.repository.PokemonCapturaRepository;
import co.edu.ufps.pokemon.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EntrenadorService {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    @Autowired
    private EntrenadorRepository entrenadorRepository;

    @Autowired
    private PokemonRepository pokemonRepository;

    @Autowired
    private PokemonCapturaRepository pokemonCapturaRepository;

    public String registrar(String email) {
        Entrenador entrenador = Entrenador.builder()
                .email(email)
                .build();
        Entrenador savedEntrenador = entrenadorRepository.save(entrenador);
        return savedEntrenador.getUuid();
    }

    public List<PokemonResponseDTO> listarPokemons(String uuid) {
        List<Pokemon> pokemons = pokemonCapturaRepository.findPokemonsByEntrenadorUuid(uuid);
        return pokemons.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public Object agregarPokemon(String uuidEntrenador, String uuidPokemon) {
        boolean yaExiste = pokemonCapturaRepository
                .existsByEntrenadorUuidAndPokemonUuid(uuidEntrenador, uuidPokemon);

        if (yaExiste) {
            return Map.of(
                "error", "true",
                "message", "pokemon ya esta registrado al entrenador"
            );
        }

        Entrenador entrenador = entrenadorRepository.findByUuid(uuidEntrenador)
                .orElseThrow(() -> new RuntimeException("Entrenador no encontrado"));

        Pokemon pokemon = pokemonRepository.findByUuid(uuidPokemon)
                .orElseThrow(() -> new RuntimeException("Pokemon no encontrado"));

        PokemonCaptura captura = PokemonCaptura.builder()
                .pokemon(pokemon)
                .entrenador(entrenador)
                .nombre(entrenador.getEmail())
                .apellido("-")
                .fechaVinculacion(LocalDate.now())
                .build();

        pokemonCapturaRepository.save(captura);

        return listarPokemons(uuidEntrenador);
    }

    private PokemonResponseDTO toResponseDTO(Pokemon pokemon) {
        TipoPokemonResponseDTO tipoDTO = null;
        if (pokemon.getTipoPokemon() != null) {
            tipoDTO = TipoPokemonResponseDTO.builder()
                    .id(String.valueOf(pokemon.getTipoPokemon().getId()))
                    .descripcion(pokemon.getTipoPokemon().getDescripcion())
                    .uuid(pokemon.getTipoPokemon().getUuid())
                    .build();
        }

        return PokemonResponseDTO.builder()
                .id(String.valueOf(pokemon.getId()))
                .nombre(pokemon.getNombre())
                .descripcion(pokemon.getDescripcion())
                .tipoPokemon(tipoDTO)
                .uuid(pokemon.getUuid())
                .generacion(pokemon.getGeneracion() != null ? String.valueOf(pokemon.getGeneracion()) : null)
                .fechaDescubrimiento(
                        pokemon.getFechaDescubrimiento() != null
                                ? pokemon.getFechaDescubrimiento().format(DATE_FORMATTER)
                                : null
                )
                .build();
    }
}