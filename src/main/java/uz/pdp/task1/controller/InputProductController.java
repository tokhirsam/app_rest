package uz.pdp.task1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task1.entity.InputProduct;
import uz.pdp.task1.payload.InputProductDto;
import uz.pdp.task1.payload.Result;
import uz.pdp.task1.service.InputProductService;

import java.util.List;

@RestController
@RequestMapping("api/inputProduct")
public class InputProductController {
    @Autowired
    InputProductService service;

    @PostMapping
    public Result add(@RequestBody InputProductDto dto){
       return service.add(dto);
    }

    @GetMapping
    public List<InputProduct> getAll(){
        return service.getAll();
    }
    @GetMapping("/{id}")
    public InputProduct getOne(@PathVariable Integer id){
        return service.getOne(id);
    }
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        return service.delete(id);
    }
    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody InputProductDto dto){
        return service.edit(dto, id);
    }



}
