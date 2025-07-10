package br.com.monee.api.entity;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false, length = 150)
    private String name;
    @Column(nullable = false, length = 200)
    private String email;
    @Column(nullable = false, length = 50)
    private String password;
    @Column(nullable = false, length = 50)
    private String phone;
    private String profilePhotoUrl;

    public UserEntity(String name, String email, String password, String phone, String profilePhotoUrl) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.profilePhotoUrl = profilePhotoUrl;
    }

    @Override
    public String toString(){
        return "UserEntity [id= "+ this.id + ", name= " + this.name+"]";
    }
}
