package uz.pdp.task1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.task1.entity.Users;
import uz.pdp.task1.entity.WareHouse;
import uz.pdp.task1.payload.Result;
import uz.pdp.task1.payload.UserDto;
import uz.pdp.task1.repository.UserRepository;
import uz.pdp.task1.repository.WarehouseRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    WarehouseRepository warehouseRepository;


    public Result add(@RequestBody UserDto dto) {
        if (userRepository.existsByPhoneNumber(dto.getPhoneNumber())) return new Result("Bu telefon raqamli user mavjud", false);
        Users user = new Users();
        user.setFirstName(dto.getFirstName());
        user.setPassword(dto.getPassword());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setLastName(dto.getLastName());
        user.setCode("UCD"+(codeGenerator()+1));
        Set<Integer> warehouseIds = dto.getWareHousesIds();
        Set<WareHouse> wareHouses = new HashSet<>();
        for (Integer i : warehouseIds) {
            Optional<WareHouse> optionalWareHouse = warehouseRepository.findById(i);
            if (!optionalWareHouse.isPresent()) return new Result("Warehouse not found", false);
            wareHouses.add(optionalWareHouse.get());
        }
        user.setWareHouses(wareHouses);
        userRepository.save(user);
        return new Result("New User added", true);

    }
    public List<Users> getAll(){
        return userRepository.findAll();
    }
    public Users getOne(Integer id){
        Optional<Users> optionalUsers = userRepository.findById(id);
        return optionalUsers.orElseGet(Users::new);
    }
    public Result delete(Integer id){
        Optional<Users> optionalUsers = userRepository.findById(id);
        if (!optionalUsers.isPresent()) return new Result("User not found", false);
        userRepository.deleteById(id);
        return new Result("User deleted", true);
    }
    public Result edit(Integer id, UserDto dto){
        Optional<Users> optionalUsers = userRepository.findById(id);
        if (!optionalUsers.isPresent()) return new Result("User not found", false);
        Users user = optionalUsers.get();
        user.setFirstName(dto.getFirstName());
        user.setPassword(dto.getPassword());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setLastName(dto.getLastName());
        Set<Integer> warehouseIds = dto.getWareHousesIds();
        Set<WareHouse> wareHouses = new HashSet<>();
        for (Integer i : warehouseIds) {
            Optional<WareHouse> optionalWareHouse = warehouseRepository.findById(i);
            if (!optionalWareHouse.isPresent()) return new Result("Warehouse not found", false);
            wareHouses.add(optionalWareHouse.get());
        }
        user.setWareHouses(wareHouses);
        return new Result("User edited", true);

    }


    public Integer codeGenerator(){
        List<Users> all = userRepository.findAll();
        if (all.size()==0){
            return 1;
        }else{
            Users last = all.get(all.size()-1);
            return Integer.valueOf(last.getCode().substring(3));
        }
    }

}
