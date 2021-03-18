package uz.pdp.task1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task1.entity.OutputProduct;
import uz.pdp.task1.payload.OutputProductDto;
import uz.pdp.task1.payload.Result;
import uz.pdp.task1.service.OutputProductService;

import java.util.List;

@RestController
@RequestMapping("api/outputProduct")
public class OutputProductController {
    @Autowired
    OutputProductService service;

    @PostMapping
    public Result add(@RequestBody OutputProductDto dto){
       return service.add(dto);
    }

    @GetMapping
    public List<OutputProduct> getAll(){
        return service.getAll();
    }
    @GetMapping("/{id}")
    public OutputProduct getOne(@PathVariable Integer id){
        return service.getOne(id);
    }
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        return service.delete(id);
    }
    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody OutputProductDto dto){
        return service.edit(dto, id);
    }



}
