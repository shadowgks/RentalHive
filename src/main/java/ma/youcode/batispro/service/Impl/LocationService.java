package ma.youcode.batispro.service.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.youcode.batispro.domain.entity.*;
import ma.youcode.batispro.domain.enums.Equipment.EquipmentStatus;
import ma.youcode.batispro.domain.enums.Location.LocationStatus;
import ma.youcode.batispro.dto.locationDTO.LocationFolderRequestDto;
import ma.youcode.batispro.mapper.LocationFolderResponseDTO;
import ma.youcode.batispro.mapper.LocationResponseDTO;
import ma.youcode.batispro.dto.locationDTO.LocationCreationRequestDto;
import ma.youcode.batispro.dto.locationDTO.LocationRequestDto;
import ma.youcode.batispro.exception.EquipmentNotFoundException;
import ma.youcode.batispro.mapper.LocationCreationRequestDtoMapper;
import ma.youcode.batispro.mapper.LocationFolderDetailsDtoMapper;
import ma.youcode.batispro.repository.*;
import ma.youcode.batispro.service.ILocationService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class LocationService implements ILocationService {

    private final LocationRepository locationRepository;
    private final LocationFolderRepository locationFolderRepository;
    private final EquipmentRepository equipmentRepository;
    private final EquipmentUnitRepository equipmentUnitRepository;
    private final LocationCreationRequestDtoMapper locationCreationRequestDtoMapper;
    private final LocationFolderDetailsDtoMapper locationFolderDetailsDtoMapper;
    private final ClientRespository clientRespository;


    @Override
    public LocationFolderRequestDto getLocationFolderByNumber(String numberFolder) {
        Optional<DossierLocation> dossierLocation = Optional.ofNullable(locationFolderRepository.findByDossierNumber(numberFolder)
                .orElseThrow(() -> new IllegalArgumentException("Not found this dossier")));
        return LocationFolderResponseDTO.mapToDto(dossierLocation.get());
    }

    @Override
    public List<LocationRequestDto> createLocation(LocationCreationRequestDto locationRequest) {
        List<LocationRequestDto> locationRequests = locationRequest.locationRequests();

        //Get User Manual
        Client client = clientRespository.findByCin(locationRequest.cinUser())
                .orElseThrow(() -> new IllegalArgumentException("Client Not Found"));

        //Check date location
        boolean anyInvalidDateForLocation = locationRequests.stream()
                .anyMatch(l -> l.startDate().isBefore(LocalDate.now()) ||
                        l.endDate().isBefore(l.startDate()));
        if(anyInvalidDateForLocation){
            throw new IllegalArgumentException("Invalid date for location");
        }

        List<Location> locationList = new ArrayList<>();
        locationRequests.stream().forEach(e -> {
            //Check Equipment If Exist
            Equipment equipment = equipmentRepository.findByModel(e.model())
                    .orElseThrow(() -> new EquipmentNotFoundException("This is Equipment not found"));

            //Check status equipment
            if(!equipment.getEquipmentStatus().equals(EquipmentStatus.valueOf(e.status()))){
                throw new IllegalArgumentException("this is status not valid");
            }
            //Get EquipmentUnit
            List<EquipmentUnit> equipmentUnit =  equipmentUnitRepository.findByEquipmentUnitWithEquipment(equipment.getId());

            //Get Sum Quantity Reserved
            List<Location> locationWithEquipmentList = locationRepository.findByLocationWithEquipment(1L);
            Integer sumQuantityReserved = locationWithEquipmentList.stream()
                    .filter(t -> t.getStartDate().isAfter(e.startDate()) && t.getEndDate().isBefore(e.endDate())
                            || t.getStartDate().equals(e.startDate()) && t.getEndDate().equals(e.endDate()))
                    .map(Location::getQuantity)
                    .reduce(0, Integer::sum);

            //Check Available Quantity Equipment
            Integer quantityEquipment = equipmentUnit.stream()
                    .map(EquipmentUnit::getQuantity)
                    .reduce(0, Integer::sum);

            Integer sumCheck = sumQuantityReserved + e.quantity();
            if(sumCheck > quantityEquipment){
                throw new IllegalArgumentException("Equipment out of stock");
            } else if (e.quantity() > quantityEquipment) {
                throw new IllegalArgumentException("This quantity is not available at equipment");
            }

            Location location = Location.builder()
                    .reference(UUID.randomUUID())
                    .quantity(e.quantity())
                    .startDate(e.startDate())
                    .endDate(e.endDate())
                    .equipment(equipment)
                    .build();
            //Save locations in array
            locationList.add(location);
        });
        //Save All location
        locationRepository.saveAll(locationList);

        //Save All dossierLocations
        locationList.stream().forEach(location -> {
            //Generate UniqueDossier
            String uniqueDossierNumber = "D" + LocalDateTime.now().getNano();

            DossierLocation dossierLocation = DossierLocation.builder()
                    .dossierNumber(uniqueDossierNumber)
                    .dateCreation(LocalDateTime.now())
                    .client(client)
                    .Location(location)
                    .status(LocationStatus.PENDING)
                    .build();
            locationFolderRepository.save(dossierLocation);
        });

        return LocationResponseDTO.mapToDTO(locationList);
    }

    @Override
    public LocationFolderRequestDto acceptedLocationFolder(String numberFolder) {
        Optional<DossierLocation> dossierLocation = Optional.ofNullable(locationFolderRepository.findByDossierNumber(numberFolder)
                .orElseThrow(() -> new IllegalArgumentException("This dossier not found!")));
            dossierLocation.get().setStatus(LocationStatus.ACCEPTED);
        locationFolderRepository.save(dossierLocation.get());
        return LocationFolderResponseDTO.mapToDto(dossierLocation.get());
    }


}
