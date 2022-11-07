package th.ac.ku.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import th.ac.ku.restaurant.model.Menu;
import th.ac.ku.restaurant.model.Order;
import th.ac.ku.restaurant.repository.MenuRepository;
import th.ac.ku.restaurant.repository.OrderRepository;
import th.ac.ku.restaurant.repository.UserRepository;
import th.ac.ku.restaurant.service.OrderService;
import th.ac.ku.restaurant.service.SignupService;
import th.ac.ku.restaurant.service.UserDetailsServiceImp;

import javax.validation.Valid;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService service;

    @Autowired
    private SignupService signupService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserDetailsServiceImp userDetailsServiceImp;

    @GetMapping("/add/{name}")
    public String getMenuForm(Model model, @PathVariable(value = "name") int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName().toString();
        Menu menu = menuRepository.getById(id);
        model.addAttribute("newOrder", new Order());
        model.addAttribute("orderName", menu);
        model.addAttribute("userIdd", userRepository.findByUsername(username));
        return "order-add";
    }

    @PostMapping("/add")
    public String addMenu(@Valid @ModelAttribute("newOrder") Order order, BindingResult result, Model model) {
        if (result.hasErrors())
            return "order-add";

        order.setOrderStatus("Not Finish");
        service.create(order);
        return "redirect:/status";
    }

}
