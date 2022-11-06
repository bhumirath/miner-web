package th.ac.ku.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import th.ac.ku.restaurant.service.WorkOrderService;

@Controller
public class WorkController {

    @Autowired
    private WorkOrderService workOrderService;

    @GetMapping("/work")
    public String getInboxPage(Model model) {
        //model.addAttribute("workOrder",new WorkOrder());
        model.addAttribute("works", workOrderService.getAll());
        return "work";
    }
}
