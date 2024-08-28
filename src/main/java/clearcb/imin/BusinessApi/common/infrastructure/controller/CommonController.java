package clearcb.imin.BusinessApi.common.infrastructure.controller;

import clearcb.imin.BusinessApi.common.domain.criteria.ApiResponse;
import clearcb.imin.BusinessApi.common.domain.model.Category;
import clearcb.imin.BusinessApi.common.domain.model.Tag;
import clearcb.imin.BusinessApi.common.domain.port.input.GetAllCategoriesUseCase;
import clearcb.imin.BusinessApi.common.domain.port.input.GetAllTagsUseCase;
import clearcb.imin.BusinessApi.common.infrastructure.dto.CategoryDTO;
import clearcb.imin.BusinessApi.common.infrastructure.dto.TagDTO;
import clearcb.imin.BusinessApi.common.infrastructure.mapper.CategoryMapper;
import clearcb.imin.BusinessApi.common.infrastructure.mapper.TagMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/common")
public class CommonController {


    private final TagMapper tagMapper;
    private final CategoryMapper categoryMapper;
    private final GetAllCategoriesUseCase getAllCategoriesUseCase;
    private final GetAllTagsUseCase getAllTagsUseCase;

    public CommonController(
            TagMapper tagMapper,
            CategoryMapper categoryMapper,
            GetAllCategoriesUseCase getAllCategoriesUseCase,
            GetAllTagsUseCase getAllTagsUseCase
    ) {
        this.tagMapper = tagMapper;
        this.categoryMapper = categoryMapper;
        this.getAllCategoriesUseCase = getAllCategoriesUseCase;
        this.getAllTagsUseCase = getAllTagsUseCase;
    }

    @GetMapping(path = "/categories",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> getAllCategories() {

        Set<Category> categories = getAllCategoriesUseCase.getAllCategories();

        Set<CategoryDTO> categoryDTOS = categories.stream().map(categoryMapper::toDto).collect(Collectors.toSet());

        ApiResponse apiResponse = new ApiResponse(
                categoryDTOS,
                "",
                HttpStatus.OK.value()
        );
        return ResponseEntity.ok(apiResponse);
    }


    @GetMapping(path = "/tags",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> getAllTags() {

        Set<Tag> tags = getAllTagsUseCase.getAllTags();

        Set<TagDTO> tagDTOS = tags.stream().map(tagMapper::toDto).collect(Collectors.toSet());

        ApiResponse apiResponse = new ApiResponse(
                tagDTOS,
                "",
                HttpStatus.OK.value()
        );
        return ResponseEntity.ok(apiResponse);
    }

//    @PostMapping("/upload")
//    public String uploadImage(@RequestParam("file") MultipartFile file) {
//        return "Image uploaded successfully";
//    }
//
//    @GetMapping("/{id}")
//    public byte[] getImage(@PathVariable Long id) {
//        return imageService.getImageById(id);
//    }

}
