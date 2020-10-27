package product.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import product.domain.Product;
import product.repository.ProductRepository;
import product.repository.image.ImageHandler;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ImageHandler imageHandler;

    @Override
    @Transactional
    public void save(String title, double price, MultipartFile imageFile) {
        Product product = new Product(title, price, imageHandler.getAvailableImageName(imageFile.getOriginalFilename()));
        productRepository.save(product);
        imageHandler.save(imageFile);
    }

    @Override
    @Transactional
    public void edit(int id, String title, double price, MultipartFile imageFile) {
        Product product = getById(id);
        imageHandler.delete(product.getImage());
        product.setTitle(title);
        product.setPrice(price);
        product.setImage(imageHandler.getAvailableImageName(imageFile.getOriginalFilename()));
        productRepository.edit(product);
        imageHandler.save(imageFile);
    }

    @Override
    @Transactional
    public void delete(int id) {
        Product product = getById(id);
        imageHandler.delete(product.getImage());
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

}