package uz.pdp.task1.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.task1.entity.Input;
import uz.pdp.task1.entity.InputProduct;
import uz.pdp.task1.entity.Product;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.util.Date;
@Projection(types = InputProduct.class)
public class CustomInputProduct {
   Integer getId;
   Product getProduct;
   Double getAmount;
   Double getPrice;
   Date getExpireDate;
   Input getInput;
}
