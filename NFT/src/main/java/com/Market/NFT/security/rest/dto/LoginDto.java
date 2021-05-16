package com.Market.NFT.security.rest.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * DTO for storing a user's credentials.
 */
@Data
public class LoginDto {

   @NotNull
   @Size(min = 1, max = 50)
   private String userName;

   @NotNull
   @Size(min = 4, max = 100)
   private String password;

  @NotNull
  @Size(min = 4, max = 100)
  private String email;
}
