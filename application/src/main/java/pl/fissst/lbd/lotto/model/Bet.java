package pl.fissst.lbd.lotto.model;

import lombok.*;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "bet")
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private OffsetDateTime creationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Draw draw;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "bet")
    private Set<Coupon> coupons;
}