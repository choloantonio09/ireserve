package Capstone.Model;

public class ModelFloor {
	String id;
	int mainroom;
	int functionroom;
	int totalmainroom;
	String status;
	private int totalfloorcapacity;
	private int remainingcapacity;
	private int remaining;
	private int totalfloor;
	private int main;
	private int function;
	
	public ModelFloor(){
		getId();
		getMainRoom();
		getFunctionRoom();
		getTotalCapacity();
		getStatus();
	}

	public void setId(String id){
		this.id = id;
	}
	
	public void setMainRoom(int mainroom){
		this.mainroom = mainroom;
	}

	public void setFunctionRoom(int functionroom){
		this.functionroom = functionroom;
	}

	public void setTotalCapacity(int totalmainroom){
		this.totalmainroom = totalmainroom;
	}
	
	public void setTotalFloorCapacity(int totalfloorcapacity) {
		this.totalfloorcapacity = totalfloorcapacity;
	}

	public void setRemainingCapacity(int remainingcapacity) {
		this.remainingcapacity = remainingcapacity;
	}
	
	public void setStatus(String status){
		this.status = status;
	}

	public void setTotalFloor(int totalfloor){
		this.totalfloor = totalfloor;
	}
	
	public void setRemaining(int remaining){
		this.remaining = remaining;
	}

	public void setMain(int main){
		this.main = main;
	}
	
	public void setFunction(int function){
		this.function = function;
	}
	
	public String getId(){
		return id;
	}
	
	public int getMainRoom(){
		return mainroom;
	}
	
	public int getTotalFloorCapacity(){
		return totalfloorcapacity;
	}

	public int getFunctionRoom(){
		return functionroom;
	}

	public int getTotalCapacity(){
		return totalmainroom;
	}

	public int getRemainingCapacity() {
		return remainingcapacity;
	}
	
	public String getStatus(){
		return status;
	}
	
	public int getTotalFloor(){
		return totalfloor;
	}

	public int getRemaining(){
		return remaining;
	}

	public int getMain(){
		return main;
	}

	public int getFunction(){
		return function;
	}

}