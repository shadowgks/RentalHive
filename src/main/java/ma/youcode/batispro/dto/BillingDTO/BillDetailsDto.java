package ma.youcode.batispro.dto.BillingDTO;

import lombok.Builder;
import ma.youcode.batispro.domain.entity.Bill;

@Builder
public record BillDetailsDto(
        String dossierNumber,
        Double totalPrice,
        Bill bill
) {
}
