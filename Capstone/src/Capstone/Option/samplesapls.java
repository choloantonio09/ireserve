package Capstone.Option;

import javax.swing.JOptionPane;

public class samplesapls {

	public static void main(String[] args) {
		String name = "123";
		StringUtil su = new StringUtil();
		if(su.isNumber(name)){
//		if(validateName(mc.getName()) == true && validateEmptyString(mc.getName()) == true){	
//			con = new Connect();
//			con.pst = con.con.prepareCall("{call insert_categories(?,?,?)}");
//			mc.setName(mc.getName().substring(0,1).toUpperCase()+mc.getName().substring(1).toLowerCase());
//			con.pst.setString(1, mc.getId());
//			con.pst.setString(2, mc.getName());
//			con.pst.setString(3, mc.getStatus());
//			con.pst.execute();
			
			
//			JOptionPane.showMessageDialog(null, "Successfully Add Categories");
			JOptionPane.showMessageDialog(null, "YES IT IT A NUMBER");
		}else{
			JOptionPane.showMessageDialog(null, "NO ITS NOT");
		}
	}

}
