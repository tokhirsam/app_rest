package uz.pdp.task1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.task1.entity.Users;


public interface UserRepository extends JpaRepository<Users, Integer> {
    boolean existsByPhoneNumber(String firstName);
}
