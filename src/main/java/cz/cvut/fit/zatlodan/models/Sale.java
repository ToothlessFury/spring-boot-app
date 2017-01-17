package cz.cvut.fit.zatlodan.models;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by jack on 19.12.16.
 */
@Entity
@Table(name = "sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String info;
    @Column(nullable = false)
    private Timestamp date;
    @Column(nullable = false)
    private char done;
    @Column(nullable = false, name = "customers_id")
    private Long customersId;

    public Sale(String info, Timestamp date, char done, Long customersId) {
        this.info = info;
        this.date = date;
        this.done = done;
        this.customersId = customersId;
    }

    public Sale() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public char getDone() {
        return done;
    }

    public void setDone(char done) {
        this.done = done;
    }

    public Long getCustomersId() {
        return customersId;
    }

    public void setCustomersId(Long customersId) {
        this.customersId = customersId;
    }
    
    
}
