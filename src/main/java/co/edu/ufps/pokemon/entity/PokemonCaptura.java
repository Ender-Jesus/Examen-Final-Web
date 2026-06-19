package co.edu.ufps.pokemon.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "pokemon_captura")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PokemonCaptura {

    @Id
    private Integer id;

    @ManyToOne
    @MapsId
    @JoinColumn(name = "id")
    private Pokemon pokemon;

    @ManyToOne
    @JoinColumn(name = "entrenador_id", nullable = false)
    private Entrenador entrenador;

    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;

    @Column(name = "apellido", length = 50, nullable = false)
    private String apellido;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "fecha_vinculacion")
    private LocalDate fechaVinculacion;

    @ManyToOne
    @JoinColumn(name = "pueblo_id")
    private Pueblo pueblo;
}
