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
import th.ac.ku.restaurant.model.User;
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
        User user = userRepository.findByUsername(username);
        Order order = new Order();
        order.setName(menu.getName());
        order.setPrice(menu.getPrice());
        order.setCategory(menu.getCategory());
        //order.setOrderStatus("Not Finish");
        //order.setUser(userRepository.findByUsername(username));
        model.addAttribute("newOrder", order);
        //model.addAttribute("orderName", menu);
        model.addAttribute("userIdd", user);
        return "order-add";
    }

    @PostMapping("/add")
    public String addMenu(@Valid @ModelAttribute("newOrder") Order order,BindingResult result, Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName().toString();
        User user = userRepository.findByUsername(username);

        if (result.hasErrors()){
            model.addAttribute("newOrder",order);
            model.addAttribute("userIdd",user);
            return "order-add";
        }
        //@ModelAttribute("userIdd") User user, @ModelAttribute("orderName") Menu menu ,@Valid @ModelAttribute("userIdd") User user,BindingResult a,@ModelAttribute("orderName") Menu menu
        //order.setUser(user);
        //order.setName(menu.getName());
        //order.setPrice(menu.getPrice());
        //order.setCategory(menu.getCategory());
        //order.setOrderStatus("Not Finish");
        order.setOrderStatus("Not Finish");
        orderRepository.save(order);

        return "redirect:/status";
    }

}
