package pl.fissst.lbd.lotto.model;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "coupon")
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private OffsetDateTime creationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Bet bet;

    @Column(columnDefinition = "integer[]")
    @Type(type = "com.vladmihalcea.hibernate.type.array.IntArrayType")
    private Integer[] numbers;
}
