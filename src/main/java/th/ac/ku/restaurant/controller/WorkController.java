package th.ac.ku.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import th.ac.ku.restaurant.model.WorkOrder;
import th.ac.ku.restaurant.repository.WorkOrderRepository;
import th.ac.ku.restaurant.service.WorkOrderService;

import java.util.List;

@Controller
public class WorkController {

    @Autowired
    private WorkOrderService workOrderService;

    @Autowired
    private WorkOrderRepository workOrderRepository;

    @GetMapping("/work")
    public String getInboxPage(Model model) {
        //model.addAttribute("workOrder",new WorkOrder());
        model.addAttribute("works", workOrderService.getAll());
        return "work";
    }

    @GetMapping("/update/{workId}")
    public String updateWork1(Model model,@PathVariable(value = "workId") int id) {
        WorkOrder workOrder = workOrderRepository.findById(id).get();
        workOrder.setWorkStatus("Finish");
        workOrderRepository.save(workOrder);

        //List<WorkOrder> workOrderList;
        //workOrderList = workOrderRepository.findWorkOrderByFkOrderWorkOrderStatus("Finish");
        //workOrderList.
        return "redirect:/work";
    }

    @GetMapping("/update2/{workId}")
    public String updateWork2(Model model,@PathVariable(value = "workId") int id) {
        WorkOrder workOrder = workOrderRepository.findById(id).get();
        workOrder.setWorkStatus("Not Finish");
        workOrderRepository.save(workOrder);

        return "redirect:/work";
    }
}
