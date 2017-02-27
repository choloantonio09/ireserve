package Capstone.Model;

public class ModelSales {
	String name;
	String orderid;
	String type;
	String id;
	String size;
	String foodid;
	int quantity;
	Double total;
	Double price;
	String status;
	
	
	
	public ModelSales(){
		getName();
		getOrderId();
		getType();
		getId();
		getQuantity();
		getSize();
		getFoodId();
		getTotal();
		getPrice();
	}
	
	public void setName(String name){
		this.name = name;
	}

	public void setOrderId(String orderid){
		this.orderid = orderid;
	}

	public void setType(String type){
		this.type = type;
	}

	public void setId(String id){
		this.id = id;
	}

	public void setQuantity(int quantity){
		this.quantity = quantity;
	}

	public void setSize(String size){
		this.size = size;
	}

	public void setFoodId(String foodid){
		this.foodid = foodid;
	}
	
	public void setTotal(Double total){
		this.total = total;
	}

	public void setPrice(Double price){
		this.price = price;
	}
	
	public void setStatus(String status) {
		this.status = status;;
	}

	public String getName(){
		return name;
	}
	
	public String getOrderId(){
		return orderid;
	}
	
	public String getType(){
		return type;
	}
	
	public String getId(){
		return id;
	}
	
	public String getFoodId(){
		return foodid;
	}
	
	public int getQuantity(){
		return quantity;
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
	
	public String getStatus(){
		return status;
	}

}