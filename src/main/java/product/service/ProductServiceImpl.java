package product.service;

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
	
	@Autowired
	private ProductRepository productRepository;
	
	@Value("${project.basedir}")
	String basedir;
	
	@Override
	@Transactional
	public void save(String title, double price, MultipartFile imageFile) {
		
		final String SAVE_LOCATION = basedir + "/src/main/webapp/productImages/";
		
		String fileName = imageFile.getOriginalFilename();
		String location = SAVE_LOCATION;
        
		File pathFile = new File(location + fileName);
        
		if (!pathFile.exists()) {
		    	try {
		    	    	imageFile.transferTo(pathFile);
		    	} 
		    	catch (IOException e) {
		    	    	e.printStackTrace();
		    	}
		}
        
		else {
        	
		    	String tempName = fileName;
        	
		    	for (int i = 1; pathFile.exists(); i++) {
		    	    	fileName = "(" + i + ")" + tempName;
		    	    	pathFile = new File(location + fileName);
		    	}
		    	
		    	try {
		    	    	imageFile.transferTo(pathFile);
		    	} 
		    	catch (IOException e) {
		    	    	e.printStackTrace();
		    	}
		}
 
		Product product = new Product(title, price, fileName);
		productRepository.save(product);
	}
	
	@Override
	@Transactional
	public void edit(int id, String title, double price, MultipartFile imageFile) {
		
		final String SAVE_LOCATION = basedir + "/src/main/webapp/productImages/";
		
		String location = SAVE_LOCATION;
		
		Product product = getById(id);
		
		String oldFileName = product.getImage();
		File deletePathFile = new File(location + oldFileName);
		deletePathFile.delete();
		
                String fileName = imageFile.getOriginalFilename();
                
                File pathFile = new File(location + fileName);
                
                if (!pathFile.exists()) {
                    
                	try {
                	    	imageFile.transferTo(pathFile);
                	} 
                	catch (IOException e) {
                	    	e.printStackTrace();
                	}
                }
                
                else {
                	
                	String tempName = fileName;
                	
                	for (int i = 1; pathFile.exists(); i++) {
                		fileName = "(" + i + ")" + tempName;
                		pathFile = new File(location + fileName);
                	}
                	
                	try {
                	    	imageFile.transferTo(pathFile);
                  	} 
                  	catch (IOException e) {
                  	    	e.printStackTrace();
                  	}
                }
        		
                product.setTitle(title);
                product.setPrice(price);
                product.setImage(fileName);
        	productRepository.edit(product);
        }
	
	@Override
	@Transactional
	public void delete(int id) {

		final String SAVE_LOCATION = basedir + "/src/main/webapp/productImages/";
		
		String location = SAVE_LOCATION;
		
		Product product = getById(id);
		String fileName = product.getImage();
		File pathFile = new File(location + fileName);
		pathFile.delete();
		productRepository.delete(product);
	}
	
	@Override
	@Transactional
	public List<Product> getAll() {
		return productRepository.getAll();
	}
	
	@Override
	@Transactional
	public Product getById(int id) {
		return productRepository.getById(id);
	}

}
