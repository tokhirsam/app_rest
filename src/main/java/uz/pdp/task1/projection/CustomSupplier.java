package uz.pdp.task1.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.task1.entity.Supplier;

@Projection(types = Supplier.class)
public class CustomSupplier {
    String phoneNumber;
    Integer getId;
    String getName;
    boolean getActive;
}
