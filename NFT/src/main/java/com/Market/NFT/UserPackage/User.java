package com.Market.NFT.UserPackage;

import com.Market.NFT.NftPackage.Nft;
import com.Market.NFT.security.model.Authority;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "USER")
public class User {

    @Id
    //    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ")
    //    @SequenceGenerator(name = "USER_SEQ", sequenceName = "USER_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    protected Long id;
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
    @JsonIgnore
    @NotNull
    private boolean activated;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
      name = "USER_AUTHORITY",
      joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
      inverseJoinColumns = {@JoinColumn(name = "AUTHORITY_NAME", referencedColumnName = "NAME")})
    @BatchSize(size = 20)
    private Set<Authority> authorities = new HashSet<>();


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
