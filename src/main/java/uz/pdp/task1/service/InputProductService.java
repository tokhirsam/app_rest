package uz.pdp.task1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.task1.entity.*;
import uz.pdp.task1.payload.InputProductDto;
import uz.pdp.task1.payload.Result;
import uz.pdp.task1.repository.*;

import java.util.List;
import java.util.Optional;

@Service
public class InputProductService {
    @Autowired
    InputRepository inputRepository;
    @Autowired
    InputProductRepository repository;
    @Autowired
    ProductRepository productRepository;

    public Result add(InputProductDto dto){

        Optional<Input> optionalInput = inputRepository.findById(dto.getInputId());
        if (!optionalInput.isPresent()) return new Result("Bunday raqamli kirim mavjud emas", false);
        Optional<Product> optionalProduct = productRepository.findById(dto.getProductId());
        if (!optionalProduct.isPresent()) return new Result("Bunday product mavjud emas", false);


        InputProduct input = new InputProduct();
        input.setInput(optionalInput.get());
        input.setProduct(optionalProduct.get());
        input.setAmount(dto.getAmount());
        input.setPrice(dto.getPrice());
        input.setExpireDate(dto.getExpireDate());
        repository.save(input);
        return new Result("Maxsulot kirim qilindi",true);

    }
    public List<InputProduct> getAll(){
       return repository.findAll();
    }
    public InputProduct getOne(Integer id){
        Optional<InputProduct> optional = repository.findById(id);
        return optional.orElseGet(InputProduct::new);
    }
    public Result delete(Integer id){
        Optional<InputProduct> optional = repository.findById(id);
        if (!optional.isPresent()) return new Result("Input product Id not found", false);
        repository.deleteById(id);
        return new Result("Input Product deleted", true);
    }
    public Result edit(InputProductDto dto, Integer id){
        Optional<InputProduct> optional = repository.findById(id);
        if (!optional.isPresent()) return new Result("Input product Id not found", false);
        Optional<Input> optionalInput = inputRepository.findById(dto.getInputId());
        if (!optionalInput.isPresent()) return new Result("Bunday raqamli kirim mavjud emas", false);
        Optional<Product> optionalProduct = productRepository.findById(dto.getProductId());
        if (!optionalProduct.isPresent()) return new Result("Bunday product mavjud emas", false);


        InputProduct input = optional.get();
        input.setInput(optionalInput.get());
        input.setProduct(optionalProduct.get());
        input.setAmount(dto.getAmount());
        input.setPrice(dto.getPrice());
        input.setExpireDate(dto.getExpireDate());
        repository.save(input);
        return new Result("Maxsulot kirimi o'zgartirildi",true);

    }
}
