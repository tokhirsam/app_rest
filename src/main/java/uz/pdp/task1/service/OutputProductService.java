package uz.pdp.task1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.task1.entity.*;
import uz.pdp.task1.payload.OutputProductDto;
import uz.pdp.task1.payload.Result;
import uz.pdp.task1.repository.*;

import java.util.List;
import java.util.Optional;

@Service
public class OutputProductService {
    @Autowired
    OutputRepository outputRepository;
    @Autowired
    OutputProductRepository repository;
    @Autowired
    ProductRepository productRepository;

    public Result add(OutputProductDto dto) {

        Optional<Output> optionalOutput = outputRepository.findById(dto.getOutputId());
        if (!optionalOutput.isPresent()) return new Result("Bunday raqamli chiqim mavjud emas", false);
        Optional<Product> optionalProduct = productRepository.findById(dto.getProductId());
        if (!optionalProduct.isPresent()) return new Result("Bunday product mavjud emas", false);


        OutputProduct outputProduct = new OutputProduct();
        outputProduct.setOutput(optionalOutput.get());
        outputProduct.setProduct(optionalProduct.get());
        outputProduct.setAmount(dto.getAmount());
        outputProduct.setPrice(dto.getPrice());
        repository.save(outputProduct);
        return new Result("Maxsulot chiqim qilindi", true);

    }

    public List<OutputProduct> getAll() {
        return repository.findAll();
    }

    public OutputProduct getOne(Integer id) {
        Optional<OutputProduct> optional = repository.findById(id);
        return optional.orElseGet(OutputProduct::new);
    }

    public Result delete(Integer id) {
        Optional<OutputProduct> optional = repository.findById(id);
        if (!optional.isPresent()) return new Result("OutputProduct Id not found", false);
        repository.deleteById(id);
        return new Result("Output Product deleted", true);
    }

    public Result edit(OutputProductDto dto, Integer id) {
        Optional<OutputProduct> optional = repository.findById(id);
        if (!optional.isPresent()) return new Result("OutputProduct Id not found", false);
        Optional<Output> optionalOutput = outputRepository.findById(dto.getOutputId());
        if (!optionalOutput.isPresent()) return new Result("Bunday raqamli chiqim mavjud emas", false);
        Optional<Product> optionalProduct = productRepository.findById(dto.getProductId());
        if (!optionalProduct.isPresent()) return new Result("Bunday product mavjud emas", false);


        OutputProduct outputProduct = optional.get();
        outputProduct.setOutput(optionalOutput.get());
        outputProduct.setProduct(optionalProduct.get());
        outputProduct.setAmount(dto.getAmount());
        outputProduct.setPrice(dto.getPrice());
        repository.save(outputProduct);
        return new Result("Maxsulot chiqimi o'zgartirildi", true);

    }
}
