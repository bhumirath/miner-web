package th.ac.ku.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import th.ac.ku.restaurant.model.WorkOrder;
import th.ac.ku.restaurant.service.MenuService;
import th.ac.ku.restaurant.service.OrderService;
import th.ac.ku.restaurant.service.SignupService;

import javax.validation.Valid;

@Controller
public class InboxController {
    @Autowired
    private OrderService service;

    @Autowired
    private SignupService signupService;


    @GetMapping("/inbox")
    public String getInboxPage(Model model) {
        //model.addAttribute("workOrder",new WorkOrder());
        model.addAttribute("orders", service.getAll());
        model.addAttribute("user", signupService.getAll());
        return "inbox";
    }

}
