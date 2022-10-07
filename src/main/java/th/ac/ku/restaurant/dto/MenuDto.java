package th.ac.ku.restaurant.dto;

import lombok.Data;

import java.util.UUID;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class MenuDto {
    private UUID id;
    @NotBlank
    private String name;

    @NotNull
    @Min(value = 0)
    private double price;

    @NotBlank
    private String category;
}
