package co.edu.ufps.pokemon.service;

import co.edu.ufps.pokemon.dto.PokemonResponseDTO;
import co.edu.ufps.pokemon.dto.TipoPokemonResponseDTO;
import co.edu.ufps.pokemon.entity.Pokemon;
import co.edu.ufps.pokemon.repository.PokemonCapturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonService {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    @Autowired
    private PokemonCapturaRepository pokemonCapturaRepository;

    public List<PokemonResponseDTO> listarPorTipoYEntrenador(String tipo, Integer entrenadorId) {
        List<Pokemon> pokemons = pokemonCapturaRepository
                .findPokemonsByEntrenadorAndTipo(entrenadorId, tipo);

        return pokemons.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    private PokemonResponseDTO toResponseDTO(Pokemon pokemon) {
        TipoPokemonResponseDTO tipoDTO = TipoPokemonResponseDTO.builder()
                .id(String.valueOf(pokemon.getTipoPokemon().getId()))
                .descripcion(pokemon.getTipoPokemon().getDescripcion())
                .uuid(pokemon.getTipoPokemon().getUuid())
                .build();

        return PokemonResponseDTO.builder()
                .id(String.valueOf(pokemon.getId()))
                .nombre(pokemon.getNombre())
                .descripcion(pokemon.getDescripcion())
                .tipoPokemon(tipoDTO)
                .uuid(pokemon.getUuid())
                .generacion(String.valueOf(pokemon.getGeneracion()))
                .fechaDescubrimiento(
                        pokemon.getFechaDescubrimiento() != null
                                ? pokemon.getFechaDescubrimiento().format(DATE_FORMATTER)
                                : null
                )
                .build();
    }
}
