package product.repository.image;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Component
@PropertySource(value = "classpath:/project.properties")
public class ImageHandlerImpl implements ImageHandler {

    private static final Logger logger = LoggerFactory.getLogger(ImageHandlerImpl.class);

    @Value("${product_image_save_location}")
    private String SAVE_LOCATION;

    @Override
    public void save(MultipartFile imageFile) {
        String imageName = getAvailableImageName(imageFile.getOriginalFilename());
        try {
            imageFile.transferTo(new File(SAVE_LOCATION + imageName));
        } catch (IOException e) {
            logger.error("Error creating new file", e);
        }
    }

    @Override
    public void delete(String imageName) {
        File deletePathFile = new File(SAVE_LOCATION + imageName);
        deletePathFile.delete();
    }

    @Override
    public String getAvailableImageName(String imageName) {
        File imagePath = new File(SAVE_LOCATION + imageName);
        for (int i = 1; imagePath.exists(); i++) {
            imageName = "(" + i + ")" + imageName;
            imagePath = new File(SAVE_LOCATION + imageName);
        }
        return imageName;
    }

}