package uz.pdp.task1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.task1.entity.WareHouse;
import uz.pdp.task1.projection.CustomWareHouse;

@RepositoryRestResource(path = "wareHouse", collectionResourceRel = "list", excerptProjection = CustomWareHouse.class)
public interface WarehouseRepository extends JpaRepository<WareHouse, Integer> {
    boolean existsByName(String name);
}
