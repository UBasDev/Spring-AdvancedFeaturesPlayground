package org.ucbdev.Core.Domain.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long orderId;
    private Integer totalPrice;
    @ManyToOne
    @JoinColumn(
            name= "customerId",

            updatable= true,

            nullable= false,

            unique= true
    )
    @JsonIgnoreProperties(
            value= "orders",

            ignoreUnknown= false,

            allowGetters= false,

            allowSetters= false
    )
    private Customer customer;
}
