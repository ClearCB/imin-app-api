package clearcb.imin.BusinessApi.auth.infrastructure.entity;

import clearcb.imin.BusinessApi.event.infrastructure.entity.EventEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "attendance", schema = "dbo")
public class AttendanceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private UserEntity user;

    @JoinColumn(name = "event_id")
    @ManyToOne
    private EventEntity event;

    @JoinColumn(name = "role_id")
    @ManyToOne
    private RoleEntity role;

    public AttendanceEntity(UserEntity user, EventEntity event, RoleEntity role) {
        this.user = user;
        this.event = event;
        this.role = role;
    }
}
