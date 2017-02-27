package Capstone.Model;

public class ModelEventFood {
	String id;
	String name;
	String type;
	String category;
	String photos;
	String status;
	double foodprice;
	double packageprice;
	int discount;
	int personfirst;
	int personlast;
	String size;
	String sizetable;
	private String desc;
	public ModelEventFood(){
		getId();
		getName();
		getType();
		getCategory();
		getPhotos();	
		getSize();
		getFoodPrice();
		getDiscount();
		getPackagePrice();
		getPersonFirst();
		getPersonLast();
		getStatus();	
		getSizeTable();
	}
	
	public void setId(String id){
		this.id = id;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setType(String type){
		this.type = type;
	}
	
	public void setCategory(String category){
		this.category = category;
	}

	public void setPhotos(String photos){
		this.photos = photos;
	}
	
	public void setSize(String size){
		this.size = size;
	}
	public void setDesc(String desc){
		this.desc = desc;
	}
	public void setFoodPrice(double foodprice){
		this.foodprice = foodprice;
	}
	
	public void setDiscount(int discount){
		this.discount= discount;
	}
	
	public void setPackagePrice(double packageprice){
		this.packageprice = packageprice;
	}
	
	public void setPersonFirst(int personfirst){
		this.personfirst= personfirst;
	}
	
	public void setPersonLast(int personlast){
		this.personlast = personlast;
	}
	
	public void setStatus(String status){
		this.status = status;
	}
	
	public void setSizeTable(String sizetable){
		this.sizetable = sizetable;
	}
	
	public String getId(){
		return id;
	}

	public String getName(){
		return name;
	}
	
	public String getType(){
		return type;
	}
	
	public String getCategory(){
		return category;
	}
	
	public String getPhotos(){
		return photos;
	}
	
	public String getSize(){
		return size;
	}
	
	public double getFoodPrice(){
		return foodprice;
	}

	public int getDiscount(){
		return discount;
	}

	public double getPackagePrice(){
		return packageprice;
	}

	public int getPersonFirst(){
		return personfirst;
	}

	public int getPersonLast(){
		return personlast;
	}
	
	public String getStatus(){
		return status;
	}
	public String getDesc(){
		return desc;
	}

	public String getSizeTable(){
		return sizetable;
	}
	
	
}
