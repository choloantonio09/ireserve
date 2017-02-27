package Capstone.Model;

public class ModelReserve {
	String name;
	String id;
	String contact;
	String email;
	String date;
	String time;
	String status;
	String type;
	int guest;
	String meridem;
	
	public ModelReserve(){
		getId();
		getName();
		getContact();
		getEmail();
		getDate();
		getTime();
		getStatus();
		getType();
		getGuest();
		getMeridem();
	}
	
	public void setId(String id){
		this.id = id;
	}

	public void setName(String name){
		this.name = name;
	}

	public void setContact(String contact){
		this.contact = contact;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public void setDate(String date){
		this.date = date;
	}
	
	public void setTime(String time){
		this.time= time;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public void setType(String type){
		this.type = type;
	}

	public void setGuest(int i){
		this.guest = i;
	}

	public void setMeridem(String meridem){
		this.meridem = meridem;
	}

	
	public String getId(){
		return id;
	}

	public String getName(){
		return name;
	}

	public String getContact(){
		return contact;
	}

	public String getEmail(){
		return email;
	}
	public String getDate(){
		return date;
	}

	public String getTime(){
		return time;
	}

	public String getStatus(){
		return status;
	}
	
	public String getType(){
		return type;
	}
	public int getGuest(){
		return guest;
	}
	public String getMeridem() {
		return meridem;
		
	}
}