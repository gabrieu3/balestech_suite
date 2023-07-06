package com.balestech.b3scrap.entity.stock_site;

import com.balestech.b3scrap.entity.stock.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface StockSiteRepository extends JpaRepository<StockSite, BigInteger> {
    List<StockSite> findByStockAndSite(Stock stock, String site);
}
