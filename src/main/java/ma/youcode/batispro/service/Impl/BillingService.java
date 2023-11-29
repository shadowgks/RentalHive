package ma.youcode.batispro.service.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.youcode.batispro.domain.entity.Bill;
import ma.youcode.batispro.domain.entity.BillDetails;
import ma.youcode.batispro.domain.entity.DossierLocation;
import ma.youcode.batispro.domain.enums.BillStatus;
import ma.youcode.batispro.domain.enums.Location.LocationStatus;
import ma.youcode.batispro.domain.enums.PaymentStatus;
import ma.youcode.batispro.dto.BillingDTO.BillDetailsDto;
import ma.youcode.batispro.dto.BillingDTO.BillDto;
import ma.youcode.batispro.dto.mapper.BillDtoMapper;
import ma.youcode.batispro.exception.BillNotFoundException;
import ma.youcode.batispro.exception.DossierNotFoundException;
import ma.youcode.batispro.exception.FolderNotFoundException;
import ma.youcode.batispro.mapper.Billing.BillingResponseDtoMapper;
import ma.youcode.batispro.repository.BillingDetailsRepository;
import ma.youcode.batispro.repository.BillingRepository;
import ma.youcode.batispro.repository.LocationFolderRepository;
import ma.youcode.batispro.repository.LocationRepository;
import ma.youcode.batispro.service.IBillingService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
@Slf4j
@RequiredArgsConstructor
public class BillingService implements IBillingService {

    private final BillingRepository billingRepository;
    private final BillingDetailsRepository billingDetailsRepository;
    private final BillDtoMapper billDtoMapper;
    private final LocationFolderRepository locationFolderRepository;



    @Override
    public BillDetailsDto createBilling(BillDto billRequest) {
        Optional<DossierLocation> dossierLocation = Optional.ofNullable(locationFolderRepository.findByDossierNumber(billRequest.dossierNumber())
                .orElseThrow(() -> new IllegalArgumentException("This dossier not found")));
        DossierLocation dossierLocationGet = dossierLocation.get();

        //Generate number for billing
        String uniqueNumberBilling = "B" + (LocalDateTime.now()).getNano();

        //Create object billDetail
        BillDetails billDetails1 = BillDetails.builder()
                .totalPrice(billRequest.billTotal())
                .build();

        //Create object bill
        Bill bill = Bill.builder()
                .billNumber(uniqueNumberBilling)
                .dateCreation(LocalDateTime.now())
                .status(BillStatus.CREATED)
                .paymentStatus(PaymentStatus.PENDING)
                .client(dossierLocationGet.getClient())
                .comment(billRequest.comment())
                .dossierLocation(dossierLocationGet)
                .build();
        billingRepository.save(bill);

        //Create object billDetail
        BillDetails billDetails = BillDetails.builder()
                .totalPrice(billRequest.billTotal())
                .bill(bill)
                .build();
        billingDetailsRepository.save(billDetails);

        return BillingResponseDtoMapper.mapToDto(billDetails);
    }
}
