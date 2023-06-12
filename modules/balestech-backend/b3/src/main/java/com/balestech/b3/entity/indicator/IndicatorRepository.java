package com.balestech.b3.entity.indicator;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface IndicatorRepository extends JpaRepository<Indicator, BigInteger> {
}
