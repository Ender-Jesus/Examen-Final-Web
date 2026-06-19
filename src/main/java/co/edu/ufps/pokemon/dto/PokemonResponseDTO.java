package co.edu.ufps.pokemon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PokemonResponseDTO {

    private String id;
    private String nombre;
    private String descripcion;

    @JsonProperty("tipo_pokemon")
    private TipoPokemonResponseDTO tipoPokemon;

    private String uuid;
    private String generacion;

    @JsonProperty("fecha_descubrimiento")
    private String fechaDescubrimiento;
}
