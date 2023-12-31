package project_pet_backEnd.productMall.order.dto;


import lombok.Data;
import org.springframework.data.relational.core.sql.In;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ChangeOrderStatusDTO {
    @NotNull
    private Integer ordNo;
    @Min(0)
    @Max(6)
    private Integer ordStatus;
    private String recipientName;
    private String recipientAddress;
    private String recipientPh;
}
