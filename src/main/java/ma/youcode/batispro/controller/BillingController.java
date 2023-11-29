package ma.youcode.batispro.controller;

import lombok.RequiredArgsConstructor;
import ma.youcode.batispro.dto.BillingDTO.BillDto;
import ma.youcode.batispro.mapper.Billing.BillingResponseDtoMapper;
import ma.youcode.batispro.service.Impl.BillingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/billing")
@RequiredArgsConstructor
public class BillingController {
    private final BillingService billingService;

    @PostMapping
    public ResponseEntity<BillDto> createBilling(@RequestBody BillDto billRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(billingService.createBilling(billRequest));
    }

}
