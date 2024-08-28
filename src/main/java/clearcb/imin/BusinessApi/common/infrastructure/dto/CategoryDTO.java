package clearcb.imin.BusinessApi.common.infrastructure.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    public Long id;

    @NotBlank(message = "Name must not be blank")
    public String name;

    @NotBlank(message = "Icon must not be blank")
    public String icon;

}
