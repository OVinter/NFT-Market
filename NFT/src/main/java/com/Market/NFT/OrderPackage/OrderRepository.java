package com.Market.NFT.OrderPackage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

  @Query("SELECT max(o.updated) FROM Order o")
  Date lastUpdate();
}
