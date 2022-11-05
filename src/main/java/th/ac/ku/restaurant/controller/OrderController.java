package th.ac.ku.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import th.ac.ku.restaurant.model.Menu;
import th.ac.ku.restaurant.model.Order;
import th.ac.ku.restaurant.repository.UserRepository;
import th.ac.ku.restaurant.service.MenuService;
import th.ac.ku.restaurant.service.OrderService;
import th.ac.ku.restaurant.service.SignupService;

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

    @GetMapping("/add")
    public String getMenuForm(Model model) {
        model.addAttribute("newOrder", new Order());
        model.addAttribute("userSet", signupService.getAll());
        return "order-add";
    }

    @PostMapping("/add")
    public String addMenu(@Valid @ModelAttribute("newOrder") Order order, BindingResult result, Model model) {
        if (result.hasErrors())
            return "order-add";

        service.create(order);
        return "redirect:/menu";
    }

}
