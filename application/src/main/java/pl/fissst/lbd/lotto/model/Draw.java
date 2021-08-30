package pl.fissst.lbd.lotto.model;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "draw")
public class Draw {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private OffsetDateTime creationDate;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "draw")
    private Set<Bet> bets;

    @Column(columnDefinition = "integer[]")
    @Type(type = "com.vladmihalcea.hibernate.type.array.IntArrayType")
    private Integer[] numbers;
}