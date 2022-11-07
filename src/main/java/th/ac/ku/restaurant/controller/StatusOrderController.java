package th.ac.ku.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import th.ac.ku.restaurant.model.User;
import th.ac.ku.restaurant.repository.OrderRepository;
import th.ac.ku.restaurant.repository.UserRepository;
import th.ac.ku.restaurant.service.OrderService;
import th.ac.ku.restaurant.service.WorkOrderService;

@Controller
public class StatusOrderController {

    @Autowired
    private WorkOrderService workOrderService;
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/status")
    public String getInboxPage(Model model) {
        //model.addAttribute("workOrder",new WorkOrder());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName().toString();
        User user = userRepository.findByUsername(username);

        model.addAttribute("order",  orderRepository.findOrderByUserId(user.getId()));
        model.addAttribute("orders", workOrderService.getAll());
        return "status";
    }
}
