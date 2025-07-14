package br.com.monee.api.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@EntityListeners(AuditingEntityListener.class)
public class UserEntity implements UserDetails {

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
    @Column(nullable = false)
    private UserRole userRole;

    //variáveis de auditoria
    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.userRole == UserRole.ADMINISTRATOR){
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        }else{
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}