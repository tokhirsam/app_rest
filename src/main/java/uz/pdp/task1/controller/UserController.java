package uz.pdp.task1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task1.entity.Users;
import uz.pdp.task1.payload.Result;
import uz.pdp.task1.payload.UserDto;
import uz.pdp.task1.service.UserService;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping
    public Result add(@RequestBody UserDto dto){
       return userService.add(dto);
    }

    @GetMapping
    public List<Users> getAll(){
        return userService.getAll();
    }
    @GetMapping("/{id}")
    public Users getOne(@PathVariable Integer id){
        return userService.getOne(id);
    }
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        return userService.delete(id);
    }
    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody UserDto dto){
        return userService.edit(id, dto);
    }



}
