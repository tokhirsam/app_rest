package uz.pdp.task1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.task1.entity.*;
import uz.pdp.task1.payload.OutputDto;
import uz.pdp.task1.payload.Result;
import uz.pdp.task1.repository.*;

import java.util.List;
import java.util.Optional;

@Service
public class OutputService {
    @Autowired
    OutputRepository outputRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    CurrencyRepository currencyRepository;
    @Autowired
    ClientRepository clientRepository;

    public Result add(OutputDto dto){
        Optional<WareHouse> optionalWareHouse = warehouseRepository.findById(dto.getWareHouseId());
        if (!optionalWareHouse.isPresent()) return new Result("Bunday ombor mavjud emas",false);
        Optional<Currency> currencyOptional = currencyRepository.findById(dto.getCurrencyId());
        if (!currencyOptional.isPresent()) return new Result("Bunday Valyuta mavjud emas", false);
        Optional<Client> clientOptional = clientRepository.findById(dto.getClientId());
        if (!clientOptional.isPresent()) return new Result("Bunday klient mavjud emas", false);



        Output output = new Output();
        output.setDate(dto.getDate());
        output.setCode("OCD"+(codeGenerator()+1));
        output.setFactureNumber(dto.getFactureNumber());
        output.setCurrency(currencyOptional.get());
        output.setClient(clientOptional.get());
        output.setWareHouse(optionalWareHouse.get());
        outputRepository.save(output);
        return new Result("Chiqim saqlandi",true);

    }
    public List<Output> getAll(){
       return outputRepository.findAll();
    }
    public Output getOne(Integer id){
        Optional<Output> optionalOutput = outputRepository.findById(id);
        return optionalOutput.orElseGet(Output::new);
    }
    public Result delete(Integer id){
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (!optionalOutput.isPresent()) return new Result("Output Id not found", false);
        outputRepository.deleteById(id);
        return new Result("Output deleted", true);
    }
    public Result edit(OutputDto dto, Integer id){
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (!optionalOutput.isPresent()) return new Result("Output Id not found", false);
        Optional<WareHouse> optionalWareHouse = warehouseRepository.findById(dto.getWareHouseId());
        if (!optionalWareHouse.isPresent()) return new Result("Bunday ombor mavjud emas",false);
        Optional<Currency> currencyOptional = currencyRepository.findById(dto.getCurrencyId());
        if (!currencyOptional.isPresent()) return new Result("Bunday Valyuta mavjud emas", false);
        Optional<Client> clientOptional = clientRepository.findById(dto.getClientId());
        if (!clientOptional.isPresent()) return new Result("Bunday klient mavjud emas", false);



        Output output = optionalOutput.get();
        output.setDate(dto.getDate());
        output.setFactureNumber(dto.getFactureNumber());
        output.setCurrency(currencyOptional.get());
        output.setClient(clientOptional.get());
        output.setWareHouse(optionalWareHouse.get());
        outputRepository.save(output);
        return new Result("Chiqim Ozgartirildi",true);

    }
    public Integer codeGenerator(){
        List<Output> all = outputRepository.findAll();
        if (all.size()==0){
            return 1;
        }else{
            Output last = all.get(all.size()-1);
            return Integer.valueOf(last.getCode().substring(3));
        }
    }

}
