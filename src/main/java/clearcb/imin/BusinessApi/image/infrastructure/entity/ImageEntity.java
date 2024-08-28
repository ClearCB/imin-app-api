package clearcb.imin.BusinessApi.image.infrastructure.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "event_image", schema = "dbo")
public class ImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    byte[] bytes;

    @Column(name = "is_main")
    boolean isMain;

    @Column(name = "event_id")
    UUID eventId;

    public ImageEntity(UUID id, byte[] bytes, boolean isMain) {
        this.id = id;
        this.bytes = bytes;
        this.isMain = isMain;
    }
}