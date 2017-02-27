package Capstone.Model;

public class ModelStandardRoom {
	String id;
	String name;
	int capacity;
	int table;
	int chair;
	int totalcapacity;
	String status;
	String desc;
	String type;
	private String floorid;
	
	public ModelStandardRoom(){
		getId();
		getName();
		getDesc();
		getCapacity();
		getTable();
		getChair();
		getTotalCapacity();
		getStatus();
		getType();
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setFloorId(String floorid) {
		this.floorid = floorid;
	}
	
	public void setName(String name){
		this.name = name;
	}

	public void setDesc(String desc){
		this.desc = desc;
	}
	
	public void setCapacity(int capacity){
		this.capacity = capacity;
	}

	public void setTable(int table){
		this.table = table;
	}

	public void setChair(int chair){
		this.chair = chair;
	}

	public void setTotalCapacity(int totalcapacity){
		this.totalcapacity = totalcapacity;
	}
	
	public void setType(String type){
		this.type = type;
	}
	
	public void setStatus(String status){
		this.status = status;
	}

	public String getId(){
		return id;
	}

	public String getFloorId() {
		return floorid;
	}
	
	public String getName(){
		return name;
	}
	
	public String getDesc(){
		return desc;
	}

	public int getCapacity(){
		return capacity;
	}

	public int getTable(){
		return table;
	}

	public int getChair(){
		return chair;
	}

	public int getTotalCapacity(){
		return totalcapacity;
	}

	public String getStatus(){
		return status;
	}

	public String getType() {
		return type;
	}

}