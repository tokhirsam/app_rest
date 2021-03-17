package uz.pdp.task1.projection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.rest.core.config.Projection;
import uz.pdp.task1.entity.Users;
import uz.pdp.task1.entity.WareHouse;

import javax.persistence.*;
import java.util.Set;

@Projection(types = Users.class)
public class CustomUsers {
    private Integer getId;
    private String getFirstName;
    private String getLastName;
    private String getPhoneNumber;
    private String getCode;
    private String getPassword;
    private boolean getActive;
    private Set<WareHouse> getWareHouses;
}
