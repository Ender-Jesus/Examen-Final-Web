package co.edu.ufps.pokemon.repository;

import co.edu.ufps.pokemon.entity.Pokemon;
import co.edu.ufps.pokemon.entity.PokemonCaptura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PokemonCapturaRepository extends JpaRepository<PokemonCaptura, Integer> {

    @Query("SELECT pc.pokemon FROM PokemonCaptura pc WHERE pc.entrenador.id = :entrenadorId AND (pc.pokemon.tipoPokemon.descripcion = :tipo OR CAST(pc.pokemon.tipoPokemon.id AS string) = :tipo)")
    List<Pokemon> findPokemonsByEntrenadorAndTipo(@Param("entrenadorId") Integer entrenadorId, @Param("tipo") String tipo);

    @Query("SELECT pc.pokemon FROM PokemonCaptura pc WHERE pc.entrenador.uuid = :uuid")
    List<Pokemon> findPokemonsByEntrenadorUuid(@Param("uuid") String uuid);
	
	@Query("SELECT COUNT(pc) > 0 FROM PokemonCaptura pc WHERE pc.entrenador.uuid = :uuidEntrenador AND pc.pokemon.uuid = :uuidPokemon")
    boolean existsByEntrenadorUuidAndPokemonUuid(@Param("uuidEntrenador") String uuidEntrenador, @Param("uuidPokemon") String uuidPokemon);
}
