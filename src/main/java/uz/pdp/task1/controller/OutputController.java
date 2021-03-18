package uz.pdp.task1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task1.entity.Output;
import uz.pdp.task1.payload.OutputDto;
import uz.pdp.task1.payload.Result;
import uz.pdp.task1.service.OutputService;

import java.util.List;

@RestController
@RequestMapping("api/output")
public class OutputController {
    @Autowired
    OutputService outputService;

    @PostMapping
    public Result add(@RequestBody OutputDto dto){
       return outputService.add(dto);
    }

    @GetMapping
    public List<Output> getAll(){
        return outputService.getAll();
    }
    @GetMapping("/{id}")
    public Output getOne(@PathVariable Integer id){
        return outputService.getOne(id);
    }
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        return outputService.delete(id);
    }
    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody OutputDto dto){
        return outputService.edit(dto, id);
    }



}
