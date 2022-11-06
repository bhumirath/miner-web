package th.ac.ku.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.util.DateUtils;
import th.ac.ku.restaurant.model.Order;
import th.ac.ku.restaurant.model.WorkOrder;
import th.ac.ku.restaurant.repository.OrderRepository;
import th.ac.ku.restaurant.repository.WorkOrderRepository;
import th.ac.ku.restaurant.service.OrderService;
import th.ac.ku.restaurant.service.SignupService;
import th.ac.ku.restaurant.service.WorkOrderService;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.Date;
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
    private WorkOrderRepository workOrderRepository;

    @Autowired
    private WorkOrderService workOrderService;

    @GetMapping("/delegate/{orderId}")
    public String getDelegate(Model model,@PathVariable(value = "orderId") int id) {

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
        
        Date startDate = workOrder.getStartDate();
        Date finishDate = workOrder.getFinishDate();

        Calendar c = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        c.setTime(startDate);
        c2.setTime(finishDate);

        c.add(Calendar.YEAR, 543);

        c2.add(Calendar.YEAR, 543);

        Date startDate2 = c.getTime();
        Date finishDate2 = c2.getTime();
        workOrder.setStartDate(startDate2);
        workOrder.setFinishDate(finishDate2);

        workOrder.setWorkStatus("Not finish");
        workOrderService.create(workOrder);
        return "redirect:/inbox"; //กลับหน้า inbox
    }

    @GetMapping("/delete/{orderId}")
    public String deleteOrder(Model model,@PathVariable(value = "orderId") int id) {
        Order order = orderRepository.getById(id);
        //workOrderRepository.findByOrderId(id);

        orderRepository.delete(order);
        //workOrderRepository.deleteById(id);
        return "redirect:/inbox";
    }

}
