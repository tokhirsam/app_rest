package uz.pdp.task1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputDto {
    private Timestamp date;
    private Integer wareHouseId;
    private Integer supplierId;
    private Integer currencyId;
    private String factureNumber;
    private String code;
}
