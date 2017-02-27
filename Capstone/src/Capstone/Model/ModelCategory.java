package Capstone.Model;

public class ModelCategory {
	String id;
	String name;
	String status;
	
	public ModelCategory(){
		getId();
		getName();
		getStatus();
	}
	
	public void setId(String id){
		this.id = id;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setStatus(String status){
		this.status = status;
	}
	
	public String getId(){
		return id;
	}

	public String getName(){
		return name;
	}

	public String getStatus(){
		return status;
	}
	
}
