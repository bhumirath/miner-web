package th.ac.ku.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import th.ac.ku.restaurant.model.WorkOrder;
import th.ac.ku.restaurant.repository.WorkOrderRepository;

import java.util.List;
import java.util.UUID;

@Service
public class WorkOrderService {
    @Autowired
    private WorkOrderRepository workOrderRepository;

    public List<WorkOrder> getAll(){
        return workOrderRepository.findAll();
    }

    public WorkOrder create(WorkOrder workOrder){
        workOrderRepository.save(workOrder);
        return workOrder;
    }

    public WorkOrder getWorkOrderById(int id){return  workOrderRepository.findById(id).get(); }
}
