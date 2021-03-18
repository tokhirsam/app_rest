package uz.pdp.task1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String code;
    private String password;
    private boolean active ;
    private Set<Integer> wareHousesIds;
}
