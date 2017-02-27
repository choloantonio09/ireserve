package Capstone.Model;


public class ModelWaiting {
	String name;
	String id;
	String no;
	String status;
	
	public ModelWaiting(){
		getId();
		getName();
		getNo();
		getStatus();
	}
	
	public void setId(String id){
		this.id = id;
	}

	public void setName(String name){
		this.name = name;
	}

	public void setNo(String no){
		this.no = no;
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

	public String getNo(){
		return no;
	}

	public String getStatus(){
		return status;
	}
}