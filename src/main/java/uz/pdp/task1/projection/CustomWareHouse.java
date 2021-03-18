package uz.pdp.task1.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.task1.entity.WareHouse;

@Projection(types = WareHouse.class)
public interface CustomWareHouse {
    Integer getId();
    String getName();
    boolean getActive();

}
