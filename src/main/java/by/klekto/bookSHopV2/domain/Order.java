package by.klekto.bookSHopV2.domain;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Zakaz")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @OneToMany(mappedBy = "order") //В одном Заказе может быть множество Вещей
    private List<BookInOrder> bookInOrders;


    @OneToOne//Человека в заказе содержит только один заказ
    private User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<BookInOrder> getBookInOrders() {
        return bookInOrders;
    }

    public void setBookInOrders(List<BookInOrder> bookInOrders) {
        this.bookInOrders = bookInOrders;
    }
}
