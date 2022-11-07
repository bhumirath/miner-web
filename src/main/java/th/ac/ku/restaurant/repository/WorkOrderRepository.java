package th.ac.ku.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import th.ac.ku.restaurant.model.WorkOrder;

import java.util.List;
import java.util.UUID;

@Repository
public interface WorkOrderRepository extends JpaRepository<WorkOrder, Integer> {

    //WorkOrder findByOrderId(UUID id);

    //@Query( "SELECT wo FROM Order od join od. wo WHERE od.orderId = :orderId")
    //List<WorkOrder> findWorkOrderByOrderStatusAndOrderId(String orderStatus, int id);

    //List<WorkOrder> deleteByOrderId(int id);

}
