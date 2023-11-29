package ma.youcode.batispro.repository;

import ma.youcode.batispro.domain.entity.Bill;
import ma.youcode.batispro.domain.entity.DossierLocation;
import ma.youcode.batispro.domain.enums.BillStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BillingRepository extends PagingAndSortingRepository<Bill, Long> {
//    Iterable<Bill> findByStatus(BillStatus status, Pageable pageable);

    Optional<Bill> findByBillNumber(String billNumber);
    Optional<Bill> findBillByDossierLocation(DossierLocation dossierLocation);

//    Iterable<Object> findByStatus(BillStatus status, Pageable pageable);
}
