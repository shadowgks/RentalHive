package ma.youcode.batispro.dto.mapper;


import ma.youcode.batispro.domain.entity.Bill;
import ma.youcode.batispro.dto.BillingDTO.BillDetailsDto;
import org.springframework.stereotype.Component;

@Component
public class BillDetailsDtoMapper {

//    public BillDetailsDto mapToDto(Bill bill){
//        return BillDetailsDto.builder()
//                .dossierNumber(bill.getBillDetails().getBill().getDossierLocation().getDossierNumber())
//                .totalPrice(bill.getBillDetails().getTotalPrice().toString())
//                .build();
//    }
}
