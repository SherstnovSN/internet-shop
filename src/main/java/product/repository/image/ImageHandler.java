package product.repository.image;

import org.springframework.web.multipart.MultipartFile;

public interface ImageHandler {

    void save(MultipartFile imageFile);

    void delete(String iamgeName);

    String getAvailableImageName(String imageName);

}
