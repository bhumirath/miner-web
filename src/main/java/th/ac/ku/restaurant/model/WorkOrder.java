package th.ac.ku.restaurant.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class WorkOrder {
    @Id
    @GeneratedValue
    private int id;

    private String workStatus;

    private String workRole;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    //@FutureOrPresent(message = "date must be present or future")
    //@FutureOrPresent(message = "startDate Must be today or future")
    @NotNull(message = "ระบุวันเริ่มงาน")
    private Date startDate;

    //@Future(message = "date must be future")
    @NotNull(message = "ระบุวันจบงาน")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date finishDate;

    @ManyToOne(cascade = CascadeType.MERGE,fetch= FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name="order_id")
    private Order order;

    @ManyToOne(cascade = CascadeType.MERGE,fetch= FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name="user_id")
    private User user;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(String workStatus) {
        this.workStatus = workStatus;
    }

    public String getWorkRole() {
        return workRole;
    }

    public void setWorkRole(String workRole) {
        this.workRole = workRole;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }


    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
