package product.domain;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "price")
	private double price;
	
	@Column(name = "image")
	private String image;
	
	public Product() {
	}
	
	public Product(String title, double price, String image) {
		this.title = title;
		this.price = price;
		this.image = image;
	}
		
	public int getId() {
	    	return id;
	}
	
        public void setId(int id) {
            	this.id = id;
        }
    
        public String getTitle() {
            	return title;
        }
    
        public void setTitle(String title) {
            	this.title = title;
        }
    
        public double getPrice() {
            	return price;
        }
    
        public void setPrice(double price) {
            	this.price = price;
        }
    
        public String getImage() {
        	return image;
        }
    
        public void setImage(String image) {
        	this.image = image;
        }
  
}
