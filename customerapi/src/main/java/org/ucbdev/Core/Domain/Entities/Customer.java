package org.ucbdev.Core.Domain.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
/*
@Table(name = "customers", indexes = {
        @Index(name = "index1", columnList = "name ASC, lastname DESC"),
        @Index(name = "database1.index1", columnList = "name, lastname"),
        @Index(name = "index1", columnList = "name", unique = true)
})
*/
@Table(name = "customers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long customerId;
    private String name = "";
    private String lastname = "";
    private Integer age = 0;
    @OneToMany(
            mappedBy= "customer",

            cascade= CascadeType.ALL,

            fetch = FetchType.LAZY
    )
    @JsonIgnoreProperties(
            value= "customer",

            ignoreUnknown= false,

            allowGetters= false,

            allowSetters= false
    )
    private Set<Order> orders = new HashSet<>();
}
