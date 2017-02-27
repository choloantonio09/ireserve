package Capstone.Model;

public class ModelEventPackage {
	String name;
	String foodname;
	String servesize;
	String id;
	String size;
	Double total;
	Double price;
	String photos;
	
	
	
	public ModelEventPackage(){
		getName();
		getFoodName();
		getServingSize();
		getId();
		getSize();
		getTotal();
		getPrice();
		getPhotos();
	}


	public void setPhotos(String photos){
		this.photos = photos;
	}
	
	public void setName(String name){
		this.name = name;
	}

	public void setFoodName(String foodname){
		this.foodname = foodname;
	}

	public void setServingSize(String servesize){
		this.servesize = servesize;
	}

	public void setId(String id){
		this.id = id;
	}

	public void setSize(String size){
		this.size = size;
	}
	
	public void setTotal(Double total){
		this.total = total;
	}

	public void setPrice(Double price){
		this.price = price;
	}

	public String getName(){
		return name;
	}
	
	public String getFoodName(){
		return foodname;
	}
	
	public String getServingSize(){
		return servesize;
	}
	
	public String getId(){
		return id;
	}
	
	public String getSize(){
		return size;
	}
	
	public Double getTotal(){
		return total;
	}
	
	public Double getPrice(){
		return price;
	}

	public String getPhotos(){
		return photos;
	}
}