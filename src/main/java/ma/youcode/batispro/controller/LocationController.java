package ma.youcode.batispro.controller;

import lombok.RequiredArgsConstructor;
import ma.youcode.batispro.dto.locationDTO.LocationCreationRequestDto;
import ma.youcode.batispro.dto.locationDTO.LocationFolderRequestDto;
import ma.youcode.batispro.dto.locationDTO.LocationRequestDto;
import ma.youcode.batispro.service.ILocationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/locations")
@RequiredArgsConstructor
public class LocationController {
    private final ILocationService locationService;

    @PostMapping()
    public ResponseEntity<List<LocationRequestDto>> createLocation(@RequestBody LocationCreationRequestDto locationCreationRequestDto){
        List<LocationRequestDto> locationFolder = locationService.createLocation(locationCreationRequestDto);
        return ResponseEntity.ok().body(locationFolder);
    }

    @GetMapping("/{dossier_number}")
    public ResponseEntity<List<LocationFolderRequestDto>> getLocationFolderByNumber(@PathVariable("dossier_number") String dossier_number){
        return ResponseEntity.ok(locationService.getLocationFolderByNumber(dossier_number));
    }

}
