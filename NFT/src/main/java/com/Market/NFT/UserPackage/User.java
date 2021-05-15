package com.Market.NFT.UserPackage;

import com.Market.NFT.NftPackage.Nft;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "User name is required") // test
    private String userName;
    @NotBlank(message = "Password is required") // test
    private String password;
    @NotBlank(message = "Email is required") // test
    private String email;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Nft> nfts;

    private Instant created;
    private boolean enabled;


    public User(@NonNull String userName, @NonNull String email, List<Nft> nfts, String password) {
        this.userName = userName;
        this.email = email;
        this.nfts = nfts;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + id +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", nfts=" + nfts.toString() +
                ", password='" + password + '\'' +
                '}';
    }
}
