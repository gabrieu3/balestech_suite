package com.balestech.b3.entity.stock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface StockRepository extends JpaRepository<Stock, BigInteger> {
}
