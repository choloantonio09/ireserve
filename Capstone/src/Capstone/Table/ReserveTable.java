package Capstone.Table;

import Capstone.Database.Connect;
import Capstone.Model.*;
import Capstone.View.*;

public class ReserveTable {
	Connect con;
	ReservePanel r;
	public ReserveTable(ReservePanel r){
		this.r = r;
		CounterReserve();
	}
	public ReserveTable(POSDesign r2) {
		// TODO 
	}
	public void CounterReserve(){
		CounterReserve cc = new CounterReserve();
		cc.getAccountNumber();
		if(!cc.getAccountNumber().isEmpty()){
			r.tfReserveId.setText(cc.getAccountNumber());
		}
	}
}
