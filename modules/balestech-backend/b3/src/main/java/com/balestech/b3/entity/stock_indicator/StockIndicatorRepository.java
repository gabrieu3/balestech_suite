package com.balestech.b3.entity.stock_indicator;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface StockIndicatorRepository extends JpaRepository<StockIndicator, BigInteger> {
}
