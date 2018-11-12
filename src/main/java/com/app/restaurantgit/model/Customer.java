package com.app.restaurantgit.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {

    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "addressId")
    private Address address;
    @OneToOne(cascade = CascadeType.PERSIST, mappedBy = "customer")
    private PaymentDetail paymentDetail;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "customer", fetch = FetchType.EAGER)
    private List<Order> orders = new ArrayList<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) &&
                Objects.equals(firstName, customer.firstName) &&
                Objects.equals(lastName, customer.lastName) &&
                Objects.equals(email, customer.email) &&
                Objects.equals(paymentDetail, customer.paymentDetail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, paymentDetail);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", paymentDetail=" + paymentDetail +
                '}';
    }
}
