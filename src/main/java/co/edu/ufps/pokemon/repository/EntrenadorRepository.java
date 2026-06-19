package co.edu.ufps.pokemon.repository;

import co.edu.ufps.pokemon.entity.Entrenador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntrenadorRepository extends JpaRepository<Entrenador, Integer> {
	java.util.Optional<Entrenador> findByUuid(String uuid);
}
