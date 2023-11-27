package ma.youcode.batispro.dto.RLocationDTO;

import lombok.Builder;
import ma.youcode.batispro.domain.entity.Location;
import ma.youcode.batispro.dto.locationDTO.LocationRequestDto;

import java.util.List;
import java.util.stream.Collectors;

@Builder
public record LocationResponseDTO() {
    public static List<LocationRequestDto> mapToDTO(List<Location> locationList){
        return locationList.stream().map(l -> LocationRequestDto.builder()
                .reference(l.getReference())
                .quantity(l.getQuantity())
                .startDate(l.getStartDate())
                .endDate(l.getEndDate())
                .Equipment(l.getEquipment())
                .build()).collect(Collectors.toList());
    }
}
