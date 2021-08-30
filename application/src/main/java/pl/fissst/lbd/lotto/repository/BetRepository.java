package pl.fissst.lbd.lotto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.fissst.lbd.lotto.model.Bet;

import java.util.Set;

@Repository
public interface BetRepository extends JpaRepository<Bet, Long> {

    Set<Bet> findByDrawIsNull();
}