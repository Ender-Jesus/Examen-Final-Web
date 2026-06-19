package co.edu.ufps.pokemon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PokemonRequestDTO {

    private String nombre;
    private String descripcion;

    @JsonProperty("fecha_descubrimiento")
    private String fechaDescubrimiento;
}
