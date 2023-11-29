package ma.youcode.batispro.dto.BillingDTO;

import lombok.Builder;
import ma.youcode.batispro.domain.enums.BillStatus;
import ma.youcode.batispro.domain.enums.PaymentStatus;
import ma.youcode.batispro.dto.BillingDTO.BillDetailsDto;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Builder
public record BillDto(
        @NotNull String dossierNumber,
        @NotNull String billNumber,
        @NotNull LocalDateTime creationDate,
        @NotNull BillStatus billStatus,
        @NotNull PaymentStatus billPaymentStatus,
        LocalDateTime dateConfirmation,
        String comment,
        Double billTotal,
        BillDetailsDto billDetails
) {
}
