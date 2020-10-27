package product.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

import product.domain.Product;
import product.repository.ProductRepository;

@Service
@PropertySource(value = "classpath:/project.properties")
public class ProductServiceImpl implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Value("${product_image_save_location}")
    private String SAVE_LOCATION;

    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional
    public void save(String title, double price, MultipartFile imageFile) {
        Product product = new Product(title, price, getOriginalImageName(imageFile.getOriginalFilename()));
        productRepository.save(product);
        saveImage(imageFile);
    }

    @Override
    @Transactional
    public void edit(int id, String title, double price, MultipartFile imageFile) {
        Product product = getById(id);
        deleteImage(product.getImage());
        product.setTitle(title);
        product.setPrice(price);
        product.setImage(getOriginalImageName(imageFile.getOriginalFilename()));
        productRepository.edit(product);
        saveImage(imageFile);
    }

    @Override
    @Transactional
    public void delete(int id) {
        Product product = getById(id);
        deleteImage(product.getImage());
        productRepository.delete(product);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getAll() {
        return productRepository.getAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Product getById(int id) {
        return productRepository.getById(id);
    }

    private void saveImage(MultipartFile imageFile) {
        String imageName = getOriginalImageName(imageFile.getOriginalFilename());
        try {
            imageFile.transferTo(new File(SAVE_LOCATION + imageName));
        } catch (IOException e) {
            logger.error("Error creating new file", e);
        }
    }

    private void deleteImage(String fileName) {
        File deletePathFile = new File(SAVE_LOCATION + fileName);
        deletePathFile.delete();
    }

    private String getOriginalImageName(String imageName) {
        File imagePath = new File(SAVE_LOCATION + imageName);
        for (int i = 1; imagePath.exists(); i++) {
            imageName = "(" + i + ")" + imageName;
            imagePath = new File(SAVE_LOCATION + imageName);
        }
        return imageName;
    }

}