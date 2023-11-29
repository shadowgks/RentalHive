package ma.youcode.batispro.mapper.Billing;

import lombok.Builder;
import ma.youcode.batispro.domain.entity.Bill;
import ma.youcode.batispro.domain.entity.BillDetails;
import ma.youcode.batispro.dto.BillingDTO.BillDetailsDto;
import ma.youcode.batispro.dto.BillingDTO.BillDto;

@Builder
public record BillingResponseDtoMapper() {
    public static BillDetailsDto mapToDto(BillDetails billDetails){
        return BillDetailsDto.builder()
                .dossierNumber(billDetails.getBill().getDossierLocation().getDossierNumber())
                .totalPrice(billDetails.getTotalPrice())
                .bill(billDetails.getBill()).build();

//        return BillDto.builder()
//                .dossierNumber(bill.getDossierLocation().getDossierNumber())
//                .billNumber(bill.getBillNumber())
//                .creationDate(bill.getDateCreation())
//                .billStatus(bill.getStatus())
//                .billPaymentStatus(bill.getPaymentStatus())
//                .comment(bill.getComment())
//                .billTotal(bill.get)
//                .build();
    }
}
