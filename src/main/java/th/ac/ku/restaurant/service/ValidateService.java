package th.ac.ku.restaurant.service;

import org.springframework.stereotype.Service;
import th.ac.ku.restaurant.model.WorkOrder;

@Service
public class ValidateService {
    public String validateUser(WorkOrder order) {
        String message = "";
        if (order.getStartDate()!=null && order.getFinishDate()!=null && order.getStartDate().after(order.getFinishDate())) {
                message = "StartDate Must before Finish Date ";
        }
        /*
        else if(order.getStartDate() == null || order.getFinishDate() == null || (order.getFinishDate() == null && order.getStartDate() == null)){
            message = "โปรดระบุเวลางาน";
        }

         */
        return message;
    }
}
