package ma.youcode.batispro.repository;

import ma.youcode.batispro.domain.entity.EquipmentUnit;
import ma.youcode.batispro.domain.entity.Location;
import ma.youcode.batispro.domain.enums.Location.LocationStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LocationRepository extends PagingAndSortingRepository<Location, Long> {
//    List<Location> findByStatus(LocationStatus status);

    @Query("SELECT u FROM Location u JOIN FETCH u.equipment WHERE u.equipment.id = :equipment_id")
    List<Location> findByLocationWithEquipment(@Param("equipment_id") Long equipment_id);


//    List<Location> findByStartDateBetweenAndStatusAndEquipmentUnit_EquipmentModelOrEndDateBetweenAndStatusAndEquipmentUnit_EquipmentModel(
//            LocalDate startDate, LocalDate endDate, LocationStatus status, String model,
//            LocalDate startDate1, LocalDate endDate1, LocationStatus status1, String model1
//    );
}
