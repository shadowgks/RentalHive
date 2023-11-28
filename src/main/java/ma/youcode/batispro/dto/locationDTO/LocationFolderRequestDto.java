package ma.youcode.batispro.dto.locationDTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import ma.youcode.batispro.domain.entity.Client;
import ma.youcode.batispro.domain.entity.Location;
import ma.youcode.batispro.domain.enums.Location.LocationFolderStatus;
import ma.youcode.batispro.domain.enums.Location.LocationStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record LocationFolderRequestDto(
        String dossierNumber,
        LocalDateTime dateCreation,
        Client client,
        LocationStatus status,
        Location location
) {
}
