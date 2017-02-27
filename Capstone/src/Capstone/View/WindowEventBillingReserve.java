package Capstone.View;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Capstone.Table.EventBillingReserveTable;
import net.miginfocom.swing.MigLayout;

public class WindowEventBillingReserve  {

	private JPanel contentPane;
	EventBillingReserve nb;
	static EventBillingReserveTable nbt;
	static String rid;
	public static JDialog jf;
	/**
	 * Create the frame.
	 */
	public WindowEventBillingReserve(String rid, String rtype, String type, String name) {
		this.rid = rid;
		jf = new JDialog();
		jf.setTitle("Billing");
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jf.setBounds(0, 0, 600, 666);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		jf.setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		nb = new EventBillingReserve(type);
//		ModelSales ms = new ModelSales();
		nbt = new EventBillingReserveTable(nb);
//		new RemoveController(b, mb, bls);
//		new AddController(b, mb, bls);
		nbt.ViewTableOrders(rid);
		contentPane.add(nb, "cell 0 0,grow");
		nb.tfId.setText(rid);
		nb.tfType.setText(rtype);
		nb.tfName.setText(name);
		nb.Downpayment();
//		b.type = type;
	}

}
