package pl.fissst.lbd.lotto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.fissst.lbd.lotto.model.Draw;

@Repository
public interface DrawRepository extends JpaRepository<Draw, Long> {
}