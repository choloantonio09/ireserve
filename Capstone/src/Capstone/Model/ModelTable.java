package Capstone.Model;

public class ModelTable {
	String id;
	String desc;
	String description[] = new String[100];
	int width[]= new int[100];
	int height[]= new int[100];
	int x[]= new int[100];
	int y[]= new int[100];
	int i;
	int count;
	String status;
	String floor;
	private String symbol;
	private int capacity;
	
	public ModelTable(){
	}
	
	public void setId(String id){
		this.id = id;
	}
	
	public void setDesc(String desc){
		this.desc = desc;
	}

	public void setSymbol(String symbol){
		this.symbol = symbol;
	}
	public void setDescription(String description, int i){
		this.i = i;
		this.description[i] = description;
	}
	
	public void setStatus(String status){
		this.status = status;
	}
	
	public void setCapacity(int capacity){
		this.capacity = capacity;
	}

	public void setFloor(String floor){
		this.floor = floor;
	}

	public void setWidth(int width, int i){
		this.i = i;
		this.width[i] = width;
	}
	public void setHeight(int height, int i){
		this.i = i;
		this.height[i] = height;
	}
	public void setX(int x, int i){
		this.i = i;
		this.x[i] = x;
	}
	public void setY(int y, int i){
		this.i = i;
		this.y[i] = y;
	}
	
	public void setI(int i){
		this.i = i;
		this.count = i+1;
	}
	
	public String getId(){
		return id;
	}

	public String getDesc(){
		return desc;
	}
	public String getSymbol(){
		return symbol;
	}

	public String getDescription(){
		return description[i];
	}

	public String getStatus(){
		return status;
	}

	public int getWidth(){
		return width[i];
	}

	public int getHeight(){
		return height[i];
	}

	public int getX(){
		return x[i];
	}

	public int getY(){
		return y[i];
	}
	
	public int getCount(){
		return count;
	}

	public int getCapacity() {
		return capacity;
	}

	public String getFloor() {
		return floor;
	}
}
