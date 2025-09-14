package com.examen.stock_ms.repository;

import com.examen.stock_ms.model.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Integer> {

    @Query("SELECT SUM(s.quantity) FROM Stock s WHERE s.productId = :productId")
    Integer getTotalQuantityByProductId(@Param("productId") Integer productId);

}
