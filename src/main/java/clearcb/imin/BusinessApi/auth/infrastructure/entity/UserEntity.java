package clearcb.imin.BusinessApi.auth.infrastructure.entity;


import clearcb.imin.BusinessApi.auth.domain.model.AppRole;
import clearcb.imin.BusinessApi.event.infrastructure.entity.EventEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user", schema = "auth")
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Basic
    @Column(nullable = false)
    String username;

    @Column(nullable = false)
    String password;

    @Column(nullable = false)
    String email;

    @Enumerated(EnumType.STRING)
    AppRole role;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Set<EventEntity> events = new HashSet<>();

    @Column(name = "verification_code", length = 64)
    private String verificationCode;

    private boolean enabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority((role.name())));
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
        return enabled;
    }
}