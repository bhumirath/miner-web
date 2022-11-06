package th.ac.ku.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import th.ac.ku.restaurant.model.Order;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    Order findByName (String Name);
    List<Order> findByCategory (String category);
}
