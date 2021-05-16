package com.Market.NFT.security.rest.dto;


import com.Market.NFT.security.rest.AuthenticationRestController;
import lombok.Getter;
import lombok.Setter;

public class AuthResponse {

  @Getter @Setter
  private AuthenticationRestController.JWTToken jwtToken;
  @Getter @Setter
  private Long idUser;

  public AuthResponse(AuthenticationRestController.JWTToken jwtToken, Long idUser) {
    this.jwtToken = jwtToken;
    this.idUser = idUser;
  }
}
