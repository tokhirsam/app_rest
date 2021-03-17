package uz.pdp.task1.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.task1.entity.Currency;
import uz.pdp.task1.entity.Input;
import uz.pdp.task1.entity.Supplier;
import uz.pdp.task1.entity.WareHouse;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@Projection(types = Input.class)
public class CustomInput {
     Integer getId;
     Timestamp getDate;
     WareHouse getWareHouse;
     Supplier getSupplier;
     Currency getCurrency;
     String getFactureNumber;
     String getCode;
}
