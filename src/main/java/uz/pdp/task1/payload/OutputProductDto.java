package uz.pdp.task1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OutputProductDto {
    private Integer productId;
    private Double amount;
    private Double price;
    private Integer outputId;
}
