package Capstone.Model;

public class ModelNormalPackage {
	String name;
	String foodname;
	String servesize;
	String id;
	String size;
	Double total;
	Double price;
	String photos;
	private int discountbypercent;
	private Double discountbyprice;
	private Double originalprice;
	private String discount;
	private String type;
	
	
	public ModelNormalPackage(){
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


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public int getDiscountbypercent() {
		return discountbypercent;
	}


	public void setDiscountbypercent(int discountbypercent) {
		this.discountbypercent = discountbypercent;
	}


	public Double getDiscountbyprice() {
		return discountbyprice;
	}


	public void setDiscountbyprice(Double discountbyprice2) {
		this.discountbyprice = discountbyprice2;
	}


	public Double getOriginalprice() {
		return originalprice;
	}


	public void setOriginalprice(Double originalprice) {
		this.originalprice = originalprice;
	}


	public String getDiscount() {
		return discount;
	}


	public void setDiscount(String discount) {
		this.discount = discount;
	}

}