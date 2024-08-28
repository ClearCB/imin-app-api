package clearcb.imin.BusinessApi.image.infrastructure.controller;

import clearcb.imin.BusinessApi.common.domain.criteria.ApiResponse;
import clearcb.imin.BusinessApi.common.domain.port.output.Logger;
import clearcb.imin.BusinessApi.common.infrastructure.validation.UUIDConstraint;
import clearcb.imin.BusinessApi.event.domain.exceptions.EventNotFoundError;
import clearcb.imin.BusinessApi.image.application.service.ImageService;
import clearcb.imin.BusinessApi.image.domain.model.Image;
import clearcb.imin.BusinessApi.image.infrastructure.mapper.ImageMapper;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.ast.tree.expression.Collation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/api/v1/image")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;
    private final ImageMapper imageMapper;
    private final Logger logger;

    @PostMapping("/upload")
    public ResponseEntity<ApiResponse> uploadImages(@RequestParam("mainImage") MultipartFile mainImage, @RequestParam("eventId") @UUIDConstraint String eventId) throws IOException {

        if (mainImage != null) {
            Image mainImageModel = new Image(mainImage.getBytes(), true);
            imageService.uploadImage(Set.of(mainImageModel), UUID.fromString(eventId));
        }

        ApiResponse apiResponse = new ApiResponse(
                "Image uploaded success",
                "",
                HttpStatus.OK.value()
        );
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/delete/{imageId}")
    public void deleteImage(@PathVariable UUID imageId) {
        imageService.deleteImage(imageId);
    }


    @GetMapping(value = "/get/{eventId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImagesByEventId(@PathVariable UUID eventId) {

        Optional<Image> image = imageService.getImagesFromObject(eventId).stream().filter(Image::isMain).findFirst();
        return image.map(Image::getBytes).orElse(null);

    }

//@GetMapping(value = "/get/{eventId}", produces = MediaType.APPLICATION_JSON_VALUE)
//public @ResponseBody List<String> getImagesByEventId(@PathVariable UUID eventId) {
//    return imageService.getImagesFromObject(eventId).stream()
//            .map(image -> Base64.getEncoder().encodeToString(image.getBytes()))
//            .collect(Collectors.toList());
//}
}