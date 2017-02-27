package Capstone.Table;
import java.sql.SQLException;

import net.proteanit.sql.DbUtils;
import Capstone.Database.Connect;
import Capstone.Model.CounterEventRoomPlan;
import Capstone.Model.CounterNormalRoomPlan;
import Capstone.View.FloorPlan;
import Capstone.View.RoomPlanForm;
import Capstone.View.Sizes;

public class RoomTable {
	Connect con;
	FloorPlan f;
	RoomPlanForm fpf;
	public RoomTable(FloorPlan f){
		this.f = f;
	}
	public RoomTable(RoomPlanForm fpf) {
		this.fpf = fpf;
		CounterNormalRoomPlan();
		CounterEventRoomPlan();
	}
	
	public void CounterEventRoomPlan(){
		CounterEventRoomPlan ct = new CounterEventRoomPlan();
		ct.getAccountNumber();
		if(!ct.getAccountNumber().isEmpty()){
			fpf.tfFunctionRoomId.setText(ct.getAccountNumber());
		}
	}
	
	public void CounterNormalRoomPlan(){
		CounterNormalRoomPlan ct = new CounterNormalRoomPlan();
		ct.getAccountNumber();
		if(!ct.getAccountNumber().isEmpty()){
			fpf.tfStandardRoomId.setText(ct.getAccountNumber());
		}
	}
}
