package ma.youcode.batispro.mapper.Billing;

import lombok.Builder;
import ma.youcode.batispro.domain.entity.Bill;
import ma.youcode.batispro.dto.BillingDTO.BillDto;

@Builder
public record BillingResponseDtoMapper() {
    public static BillDto mapToDto(Bill bill){
        return BillDto.builder()
                .dossierNumber(bill.getDossierLocation().getDossierNumber())
                .billNumber(bill.getBillNumber())
                .creationDate(bill.getDateCreation())
                .billStatus(bill.getStatus())
                .billPaymentStatus(bill.getPaymentStatus())
                .comment(bill.getComment())
                .build();
    }
}
