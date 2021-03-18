package uz.pdp.task1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.task1.entity.*;
import uz.pdp.task1.payload.InputDto;
import uz.pdp.task1.payload.Result;
import uz.pdp.task1.repository.*;

import java.util.List;
import java.util.Optional;

@Service
public class InputService {
    @Autowired
    InputRepository inputRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    CurrencyRepository currencyRepository;
    @Autowired
    SupplierRepository supplierRepository;

    public Result add(InputDto inputDto){
        Optional<WareHouse> optionalWareHouse = warehouseRepository.findById(inputDto.getWareHouseId());
        if (!optionalWareHouse.isPresent()) return new Result("Bunday ombor mavjud emas",false);
        Optional<Currency> currencyOptional = currencyRepository.findById(inputDto.getCurrencyId());
        if (!currencyOptional.isPresent()) return new Result("Bunday Valyuta mavjud emas", false);
        Optional<Supplier> supplierOptional = supplierRepository.findById(inputDto.getSupplierId());
        if (!supplierOptional.isPresent()) return new Result("Bunday taminotchi mavjud emas", false);



        Input input = new Input();
        input.setDate(inputDto.getDate());
        input.setCode("ICD"+(codeGenerator()+1));
        input.setFactureNumber(inputDto.getFactureNumber());
        input.setCurrency(currencyOptional.get());
        input.setSupplier(supplierOptional.get());
        input.setWareHouse(optionalWareHouse.get());
        inputRepository.save(input);
        return new Result("Kirim saqlandi",true);

    }
    public List<Input> getAll(){
       return inputRepository.findAll();
    }
    public Input getOne(Integer id){
        Optional<Input> optionalInput = inputRepository.findById(id);
        return optionalInput.orElseGet(Input::new);
    }
    public Result delete(Integer id){
        Optional<Input> optionalInput = inputRepository.findById(id);
        if (!optionalInput.isPresent()) return new Result("Input Id not found", false);
        inputRepository.deleteById(id);
        return new Result("Input deleted", true);
    }
    public Result edit(InputDto inputDto, Integer id){
        Optional<Input> optionalInput = inputRepository.findById(id);
        if (!optionalInput.isPresent()) return new Result("Input Id not found", false);
        Optional<WareHouse> optionalWareHouse = warehouseRepository.findById(inputDto.getWareHouseId());
        if (!optionalWareHouse.isPresent()) return new Result("Bunday ombor mavjud emas",false);
        Optional<Currency> currencyOptional = currencyRepository.findById(inputDto.getCurrencyId());
        if (!currencyOptional.isPresent()) return new Result("Bunday Valyuta mavjud emas", false);
        Optional<Supplier> supplierOptional = supplierRepository.findById(inputDto.getSupplierId());
        if (!supplierOptional.isPresent()) return new Result("Bunday taminotchi mavjud emas", false);

        Input input =optionalInput.get();
        input.setDate(inputDto.getDate());
        input.setCode("ICD"+(codeGenerator()+1));
        input.setFactureNumber(inputDto.getFactureNumber());
        input.setCurrency(currencyOptional.get());
        input.setSupplier(supplierOptional.get());
        input.setWareHouse(optionalWareHouse.get());
        inputRepository.save(input);
        return new Result("Kirim o'zgartirildi",true);

    }
    public Integer codeGenerator(){
        List<Input> all = inputRepository.findAll();
        if (all.size()==0){
            return 1;
        }else{
            Input last = all.get(all.size()-1);
            return Integer.valueOf(last.getCode().substring(3));
        }
    }

}
