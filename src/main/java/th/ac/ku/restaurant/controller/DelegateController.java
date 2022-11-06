package th.ac.ku.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import th.ac.ku.restaurant.model.Order;
import th.ac.ku.restaurant.model.WorkOrder;
import th.ac.ku.restaurant.repository.OrderRepository;
import th.ac.ku.restaurant.service.OrderService;
import th.ac.ku.restaurant.service.SignupService;
import th.ac.ku.restaurant.service.WorkOrderService;

import javax.validation.Valid;
import java.util.UUID;

@Controller
public class DelegateController {

    @Autowired
    private SignupService userService;
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private WorkOrderService workOrderService;

    @GetMapping("/delegate/{orderId}")
    public String getDelegate(Model model,@PathVariable(value = "orderId") UUID id) {

        Order order = orderRepository.getById(id);
        model.addAttribute("orders", order);
        model.addAttribute("newWorkOrder", new WorkOrder());
        model.addAttribute("userSet",userService.getAll());
        return "delegate";
    }

    @PostMapping("/delegate")
    public String delegateOrder(Model model, @Valid @ModelAttribute("newWorkOrder") WorkOrder workOrder, BindingResult result) {
        if (result.hasErrors())
            return "delegate";
        //เก็บworkOrder
        workOrder.setWorkStatus("Not finish");
        workOrderService.create(workOrder);
        return "redirect:/inbox"; //กลับหน้า inbox
    }
}
