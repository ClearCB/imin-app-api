package clearcb.imin.BusinessApi.image.infrastructure.dto;

import clearcb.imin.BusinessApi.common.infrastructure.validation.UUIDConstraint;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageDTO {

    @UUIDConstraint
    private String id;

    @NotBlank
    private byte[] bytes;

    private boolean isMain;
}
