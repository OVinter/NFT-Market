package com.Market.NFT.UserPackage;

import com.Market.NFT.NftPackage.Nft;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {

    @Getter @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Getter @Setter
    @NonNull
    private String userName;
    @Getter @Setter
    @NonNull
    private String email;
    @Getter @Setter
    @ElementCollection
    private List<Nft> nfts;
    @Getter @Setter
    @NonNull
    private String password;

    public User() {
    }

    public User(@NonNull String userName, @NonNull String email, List<Nft> nfts, String password) {
        this.userName = userName;
        this.email = email;
        this.nfts = nfts;
        this.password = password;
    }

    public User(Long id, @NonNull String userName, @NonNull String email, List<Nft> nfts, String password) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.nfts = nfts;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", nfts=" + nfts.toString() +
                ", password='" + password + '\'' +
                '}';
    }
}
