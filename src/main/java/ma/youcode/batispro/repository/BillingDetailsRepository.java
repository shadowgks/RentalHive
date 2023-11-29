package ma.youcode.batispro.repository;

import ma.youcode.batispro.domain.entity.BillDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingDetailsRepository extends JpaRepository<BillDetails, Long> {
}
