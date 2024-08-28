package clearcb.imin.BusinessApi.auth.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "permission", schema = "auth")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermissionEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;

    private String name;

}
