package ma.youcode.batispro.mapper;

import ma.youcode.batispro.domain.entity.DossierLocation;
import ma.youcode.batispro.dto.locationDTO.LocationFolderRequestDto;

public class LocationFolderResponseDTO {
    public static LocationFolderRequestDto mapToDto(DossierLocation dossierLocation){
        return LocationFolderRequestDto.builder()
                .dossierNumber(dossierLocation.getDossierNumber())
                .dateCreation(dossierLocation.getDateCreation())
                .client(dossierLocation.getClient())
                .status(dossierLocation.getStatus())
                .location(dossierLocation.getLocation())
                .build();
    }
}
