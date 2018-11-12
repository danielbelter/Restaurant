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
public class Address {

    @Id
    @GeneratedValue
    private Long id;
    private String street;
    private String postCode;
    private String city;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "address", fetch = FetchType.EAGER)
    private List<Customer> customers = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(id, address.id) &&
                Objects.equals(street, address.street) &&
                Objects.equals(postCode, address.postCode) &&
                Objects.equals(city, address.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, street, postCode, city);
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", postCode='" + postCode + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
