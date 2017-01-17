package cz.cvut.fit.zatlodan.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by jack on 14.12.16.
 */
@Entity
@Table(name = "customers")
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(name = "email_address")
    private String emailAddress;
    @Column(nullable = false)
    private String address;
    private String contact;

    @OneToMany(mappedBy = "customersId")
    private List<Sale> sales;

    public Customer(String name, String emailAddress, String address, String contact) {
        this.name = name;
        this.emailAddress = emailAddress;
        this.address = address;
        this.contact = contact;
    }
    
    public void addSale(Sale s){
        sales.add(s);
    }

    public Customer() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }
    
    
}
