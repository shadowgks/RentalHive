package ma.youcode.batispro.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter @Getter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class BillDetails {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Long id;
    private Double totalPrice;

    @ManyToOne
    @JoinColumn(name = "bill_id")
    private Bill bill;

}
