package com.Market.NFT.security.repository;


import com.Market.NFT.security.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Spring Data JPA repository for the {@link Authority} entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
