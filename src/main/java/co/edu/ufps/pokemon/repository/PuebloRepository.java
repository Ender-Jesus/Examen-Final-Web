package co.edu.ufps.pokemon.repository;

import co.edu.ufps.pokemon.entity.Pueblo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PuebloRepository extends JpaRepository<Pueblo, Integer> {
}
