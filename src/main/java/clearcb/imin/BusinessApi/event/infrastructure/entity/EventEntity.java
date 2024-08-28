package clearcb.imin.BusinessApi.event.infrastructure.entity;

import clearcb.imin.BusinessApi.common.infrastructure.entity.CategoryEntity;
import clearcb.imin.BusinessApi.common.infrastructure.entity.TagEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "event", schema = "dbo")
@AllArgsConstructor
@NoArgsConstructor
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;

    @Column(name = "small_description")
    private String smallDescription;

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "large_description")
    private String largeDescription;

    @Column(name = "location_name")
    private String locationName;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "is_online")
    private boolean isOnline;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "finish_date")
    private LocalDateTime finishDate;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "event_category",
            schema = "dbo",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<CategoryEntity> categories = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "event_tag",
            schema = "dbo",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<TagEntity> tags = new HashSet<>();

    @Column(name = "max_attendance")
    private int maxAttendance;

    public EventEntity(UUID id, UUID userId,String title, String smallDescription, String largeDescription, String locationName, Double latitude, Double longitude, boolean isOnline, LocalDateTime startDate
            , LocalDateTime finishDate) {
        this.id = id;
        this.title = title;
        this.smallDescription = smallDescription;
        this.largeDescription = largeDescription;
        this.userId = userId;
        this.locationName = locationName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.isOnline = isOnline;
        this.startDate = startDate;
        this.finishDate = finishDate;
    }

    @Override
    public String toString() {
        return "EventEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }

}
