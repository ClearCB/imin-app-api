package clearcb.imin.BusinessApi.auth.infrastructure.entity;

import clearcb.imin.BusinessApi.community.infrastructure.entity.CommunityEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "membership", schema = "dbo")
public class MembershipEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private UserEntity user;

    @JoinColumn(name = "community_id")
    @ManyToOne
    private CommunityEntity community;

    @JoinColumn(name = "role_id")
    @ManyToOne
    private RoleEntity role;
}
