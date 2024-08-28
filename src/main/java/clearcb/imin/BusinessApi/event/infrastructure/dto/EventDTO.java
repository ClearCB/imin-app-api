package clearcb.imin.BusinessApi.event.infrastructure.dto;

import clearcb.imin.BusinessApi.common.infrastructure.dto.CategoryDTO;
import clearcb.imin.BusinessApi.common.infrastructure.dto.TagDTO;
import clearcb.imin.BusinessApi.image.infrastructure.dto.ImageDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDTO {

    public String id;

    public String userId;

    @NotBlank(message = "Title must not be blank")
    public String title;

    @NotBlank(message = "Small description must not be blank")
    private String smallDescription;

    @NotBlank(message = "Large description must not be blank")
    private String largeDescription;

    @NotBlank(message = "Location name must not be blank")
    private String locationName;

    private Double latitude;

    private Double longitude;

    private LocalDateTime startDate;

    private LocalDateTime finishDate;

    private boolean isOnline;

    private Set<CategoryDTO> categories;

    private Set<TagDTO> tags;
    private Set<ImageDTO> images;

    public EventDTO(String id, String userId, String title, String smallDescription, String largeDescription, String locationName, Double latitude, Double longitude, boolean isOnline, LocalDateTime startDate, LocalDateTime finishDate) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.smallDescription = smallDescription;
        this.largeDescription = largeDescription;
        this.locationName = locationName;
        this.latitude = latitude;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.longitude = longitude;
        this.isOnline = isOnline;
    }
}
