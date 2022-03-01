package de.lmis.vhv.simplerest.api.contract;

import de.lmis.vhv.simplerest.HasLogger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.Validator;

@RestController
@RequestMapping("/contracts")
public class ContractController implements HasLogger {
    private final Validator validator;
    private final ContractService contractService;

    public ContractController(Validator validator, ContractService contractService) {
        this.validator = validator;
        this.contractService = contractService;
    }

    @PostMapping
    public void submitNewContract(@RequestBody @Valid ContractDTO contractDTO) {
        this.logger().info(contractDTO.toString());
    }

    @GetMapping
    public String validationTester() {
        this.contractService.addNewContract("","qwe");
        return "";
    }
}