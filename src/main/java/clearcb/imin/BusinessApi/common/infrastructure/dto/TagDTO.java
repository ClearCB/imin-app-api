package clearcb.imin.BusinessApi.common.infrastructure.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagDTO {

    public Long id;

    @NotBlank(message = "Name must not be blank")
    public String name;

}
