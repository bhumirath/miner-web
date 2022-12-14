package th.ac.ku.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import th.ac.ku.restaurant.model.Order;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    Order findByName (String Name);
    List<Order> findByCategory (String category);
    Optional<Order> findById(Integer id);
    List<Order> findOrderByUserId(int id);
}
