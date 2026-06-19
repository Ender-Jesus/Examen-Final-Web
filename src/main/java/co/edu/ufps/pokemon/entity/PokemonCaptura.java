package co.edu.ufps.pokemon.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "pokemon_captura")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PokemonCaptura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "pokemon_id", nullable = false)
    private Pokemon pokemon;

    @ManyToOne
    @JoinColumn(name = "entrenador_id", nullable = false)
    private Entrenador entrenador;

    @Column(name = "nombre", length = 50)
    private String nombre;

    @Column(name = "apellido", length = 50)
    private String apellido;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "fecha_vinculacion")
    private LocalDate fechaVinculacion;

    @ManyToOne
    @JoinColumn(name = "pueblo_id")
    private Pueblo pueblo;
}