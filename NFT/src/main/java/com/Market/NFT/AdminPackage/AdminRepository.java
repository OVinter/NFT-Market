package com.Market.NFT.AdminPackage;

import com.Market.NFT.AdminPackage.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
}
