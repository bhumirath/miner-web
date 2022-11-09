package th.ac.ku.restaurant.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Menu {
    @Id
    @GeneratedValue
    private int  id;

    @NotBlank(message = "Product Name must Not Null")
    private String name;
    @Min(value = 1, message = "Price must have at least 1")
    @Max(value = 20000,message = "Price limit is 20000")
    private double price;
    private String category;

    @Min(value = 1, message = "Stock must have at least 1")
    @Max(value = 20000,message = "Stock limit is 20000")
    private double stock;

    public int  getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }
}
