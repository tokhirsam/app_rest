package uz.pdp.task1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.task1.entity.Input;
@RepositoryRestResource(path = "input", collectionResourceRel = "list", excerptProjection = Input.class)
public interface InputRepository extends JpaRepository<Input, Integer> {

}
