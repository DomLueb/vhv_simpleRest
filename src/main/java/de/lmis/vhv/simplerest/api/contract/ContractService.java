package de.lmis.vhv.simplerest.api.contract;

import de.lmis.vhv.simplerest.HasLogger;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Service
@Validated
public class ContractService implements HasLogger {
    public void addNewContract(@NotBlank String id, String content) {
        this.logger().info("ID:"+id);
    }

}
