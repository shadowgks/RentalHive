package ma.youcode.batispro.service;

import ma.youcode.batispro.dto.locationDTO.LocationCreationRequestDto;
import ma.youcode.batispro.dto.locationDTO.LocationRequestDto;

import java.util.List;

public interface ILocationService {
    public List<LocationRequestDto> createLocation(LocationCreationRequestDto locationRequest);
//    public LocationFolderDetailsDto consultLocationFolder(String dossierNumber) throws DossierNotFoundException;
//
//    public LocationFolderDetailsDto acceptLocationFolder(String dossierNumber) throws DossierNotFoundException;
//
//    public LocationFolderDetailsDto resolveLocationFolder(String locationFolderNumber,  List<LocationStatusUpdateDto> statusUpdates) throws DossierNotFoundException;
//
//    public List<LocationFolderDetailsDto> findAllLocationFolders(Pageable pageable);
//
//    public List<LocationRequestDto> findAllLocations(Pageable pageable);
//
//    public List<LocationRequestDto> findAllLocationsByStatus(LocationStatus status);
//
//    DossierLocation findLocationFolderByNumber(String dossierNumber) throws DossierNotFoundException;
}
