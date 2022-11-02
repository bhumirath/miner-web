package th.ac.ku.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import th.ac.ku.restaurant.model.Menu;

import java.util.List;
import java.util.UUID;

public interface MenuRepository extends JpaRepository<Menu, UUID> {
    Menu findByName (String Name);
    List<Menu> findByCategory(String category);
}
