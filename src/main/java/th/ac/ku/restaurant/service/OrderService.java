package th.ac.ku.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import th.ac.ku.restaurant.model.Order;
import th.ac.ku.restaurant.repository.OrderRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAll(){
        return orderRepository.findAll();
    }

    public Order create(Order order){
        orderRepository.save(order);
        return order;
    }

    public Order getMenuByName(String name) {
        return orderRepository.findByName(name);
    }

    public Optional<Order> getOrderById(int id){return  orderRepository.findById(id); }

    public List<Order> getMenuByCategory(String category) {
        return orderRepository.findByCategory(category);
    }
}
