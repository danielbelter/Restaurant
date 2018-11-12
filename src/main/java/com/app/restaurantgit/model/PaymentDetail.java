package com.app.restaurantgit.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PaymentDetail {
    @Id
    @GeneratedValue
    private Long id;
    private String customerRef;
    private String cardNo;
    private Date cardExpircy;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "customerId")
    private Customer customer;

    @Override
    public String toString() {
        return "PaymentDetail{" +
                "id=" + id +
                ", customerRef='" + customerRef + '\'' +
                ", cardNo='" + cardNo + '\'' +
                ", cardExpircy=" + cardExpircy +
                ", customer=" + customer +
                '}';
    }
}
