package th.ac.ku.restaurant.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.UUID;

@Data
public class WorkOrderDto {
    private UUID id;

    @NotBlank
    private String workStatus;

    @NotBlank
    private String workRole;

    @NotBlank
    private Date startDate;

    @NotBlank
    private Date finishDate;
}
