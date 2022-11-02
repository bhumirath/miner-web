package th.ac.ku.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import th.ac.ku.restaurant.service.MenuService;

@Controller
public class InboxController {
    @Autowired
    private MenuService service;


    @GetMapping("/inbox")
    public String getInboxPage(Model model) {
        model.addAttribute("menus", service.getAll());
        return "inbox";
    }

}
