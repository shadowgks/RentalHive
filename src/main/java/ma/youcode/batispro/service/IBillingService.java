package ma.youcode.batispro.service;

import ma.youcode.batispro.domain.enums.BillStatus;
import ma.youcode.batispro.dto.BillingDTO.BillDto;
import ma.youcode.batispro.exception.DossierNotFoundException;
import ma.youcode.batispro.mapper.Billing.BillingResponseDtoMapper;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IBillingService {

    BillDto createBilling(BillDto billRequest);

}
