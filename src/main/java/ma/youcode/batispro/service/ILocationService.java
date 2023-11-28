package ma.youcode.batispro.service;

import ma.youcode.batispro.dto.locationDTO.LocationCreationRequestDto;
import ma.youcode.batispro.dto.locationDTO.LocationFolderRequestDto;
import ma.youcode.batispro.dto.locationDTO.LocationRequestDto;

import java.util.List;
import java.util.Optional;

public interface ILocationService {
    List<LocationFolderRequestDto> getLocationFolderByNumber(String numberFolder);
    List<LocationRequestDto> createLocation(LocationCreationRequestDto locationRequest);

}
