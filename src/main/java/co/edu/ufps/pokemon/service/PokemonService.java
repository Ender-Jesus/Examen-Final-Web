package co.edu.ufps.pokemon.service;

import co.edu.ufps.pokemon.dto.PokemonRequestDTO;
import co.edu.ufps.pokemon.dto.PokemonResponseDTO;
import co.edu.ufps.pokemon.dto.TipoPokemonResponseDTO;
import co.edu.ufps.pokemon.entity.Pokemon;
import co.edu.ufps.pokemon.repository.PokemonCapturaRepository;
import co.edu.ufps.pokemon.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonService {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    @Autowired
    private PokemonCapturaRepository pokemonCapturaRepository;

    @Autowired
    private PokemonRepository pokemonRepository;

    public List<PokemonResponseDTO> listarPorTipoYEntrenador(String tipo, Integer entrenadorId) {
        List<Pokemon> pokemons = pokemonCapturaRepository
                .findPokemonsByEntrenadorAndTipo(entrenadorId, tipo);

        return pokemons.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public PokemonResponseDTO registrar(PokemonRequestDTO requestDTO) {
        LocalDate fecha = null;
        if (requestDTO.getFechaDescubrimiento() != null && !requestDTO.getFechaDescubrimiento().isBlank()) {
            fecha = LocalDate.parse(requestDTO.getFechaDescubrimiento(), DATE_FORMATTER);
        }

        Pokemon pokemon = Pokemon.builder()
                .nombre(requestDTO.getNombre())
                .descripcion(requestDTO.getDescripcion())
                .fechaDescubrimiento(fecha)
                .build();

        Pokemon saved = pokemonRepository.save(pokemon);
        return toResponseDTO(saved);
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