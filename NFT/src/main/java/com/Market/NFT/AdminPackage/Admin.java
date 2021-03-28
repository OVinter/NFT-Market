package com.Market.NFT.AdminPackage;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Admin {

    @Getter @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Getter
    @Setter
    @NonNull
    private String userName;
    @Getter @Setter
    @NonNull
    private String email;
    @Getter @Setter
    @NonNull
    private String password;

    public Admin(Long id, @NonNull String userName, @NonNull String email, @NonNull String password) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public Admin(@NonNull String userName, @NonNull String email, @NonNull String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public Admin() {
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
