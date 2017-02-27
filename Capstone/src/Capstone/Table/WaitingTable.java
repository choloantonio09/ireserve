package Capstone.Table;

import Capstone.Database.Connect;
import Capstone.Model.*;
import Capstone.View.*;

public class WaitingTable {
	Connect con;
	WaitingForm w;
	public WaitingTable(WaitingForm w){
		this.w = w;
		CounterWaiting();
		CounterWaitingNo();
	}
	public void CounterWaiting(){
		CounterWaiting cc = new CounterWaiting();
		cc.getAccountNumber();
		if(!cc.getAccountNumber().isEmpty()){
			w.tfWaitingId.setText(cc.getAccountNumber());
		}
	}
	public void CounterWaitingNo(){
		CounterWaitingNo cc = new CounterWaitingNo();
		cc.getAccountNumber();
		if(!(cc.getAccountNumber() == 0)){
			w.tfWaitingNo.setText(String.valueOf(cc.getAccountNumber()));
		}
	}
}
