package com.examen.stock_ms.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="stock")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "productid")
    private Integer productId;

    @Column(name = "warehouseid")
    private Integer wareHouseId;

    @Column
    private Integer quantity;
}
