package com.balestech.b3web.entity.vstock_indicator;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockIndicatorDataCompileRepository extends JpaRepository<StockIndicatorDataCompile, String> {

    List<StockIndicatorDataCompile> findByName(String stockName);
}