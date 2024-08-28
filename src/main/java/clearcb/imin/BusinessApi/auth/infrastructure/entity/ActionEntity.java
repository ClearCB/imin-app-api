package clearcb.imin.BusinessApi.auth.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "action", schema = "auth")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;

    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "action_permission",
            schema = "auth",
            joinColumns = @JoinColumn(name = "action_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private Set<PermissionEntity> permissionsNeeded = new HashSet<>();
}
