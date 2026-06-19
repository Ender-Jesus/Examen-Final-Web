package co.edu.ufps.pokemon.repository;

import co.edu.ufps.pokemon.entity.TipoPokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoPokemonRepository extends JpaRepository<TipoPokemon, Integer> {
}
