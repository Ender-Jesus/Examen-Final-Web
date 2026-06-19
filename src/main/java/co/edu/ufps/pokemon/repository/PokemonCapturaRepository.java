package co.edu.ufps.pokemon.repository;

import co.edu.ufps.pokemon.entity.PokemonCaptura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonCapturaRepository extends JpaRepository<PokemonCaptura, Integer> {
}
