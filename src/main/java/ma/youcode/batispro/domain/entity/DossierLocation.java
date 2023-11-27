package ma.youcode.batispro.domain.entity;

import lombok.*;
import ma.youcode.batispro.domain.enums.Location.LocationFolderStatus;
import ma.youcode.batispro.domain.enums.Location.LocationStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor @NoArgsConstructor
@Entity
public class DossierLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dossierNumber ;
    @Column(name = "date_creation",columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime dateCreation ;
    @ManyToOne(cascade = CascadeType.ALL)
    private Client client ;
    @Enumerated(EnumType.STRING)
    private LocationStatus status ;

    @ManyToOne
    private Location Location;
}
