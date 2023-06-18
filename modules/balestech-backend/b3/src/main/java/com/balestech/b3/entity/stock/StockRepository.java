package com.balestech.b3.entity.stock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, BigInteger> {

    List<Stock> findByName(String name);
    List<Stock> findByStatus (String status);

}
