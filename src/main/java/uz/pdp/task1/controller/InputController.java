package uz.pdp.task1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task1.entity.Input;
import uz.pdp.task1.payload.InputDto;
import uz.pdp.task1.payload.Result;
import uz.pdp.task1.service.InputService;

import java.util.List;

@RestController
@RequestMapping("api/input")
public class InputController {
    @Autowired
    InputService inputService;

    @PostMapping
    public Result add(@RequestBody InputDto inputDto){
       return inputService.add(inputDto);
    }

    @GetMapping
    public List<Input> getAll(){
        return inputService.getAll();
    }
    @GetMapping("/{id}")
    public Input getOne(@PathVariable Integer id){
        return inputService.getOne(id);
    }
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        return inputService.delete(id);
    }
    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody InputDto inputDto){
        return inputService.edit(inputDto, id);
    }



}
