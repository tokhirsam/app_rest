package uz.pdp.task1.projection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.rest.core.config.Projection;
import uz.pdp.task1.entity.Output;
import uz.pdp.task1.entity.OutputProduct;
import uz.pdp.task1.entity.Product;

import javax.persistence.*;

@Projection(types = OutputProduct.class)
public class CustomOutputProduct {
    Integer getId;
    Product getProduct;
    Double getAmount;
    Double getPrice;
    Output getOutput;

}
