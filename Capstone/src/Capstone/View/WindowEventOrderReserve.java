package Capstone.View;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

import javax.swing.JScrollPane;

import Capstone.Controller.AddController;
import Capstone.Controller.RemoveController;
import Capstone.Model.ModelSales;
import Capstone.Model.ModelSalesEventOrder;
import Capstone.Table.EventSalesReserveTable;

public class WindowEventOrderReserve {

	private JPanel contentPane;
	JPanel pnlSales;
	EventOrderReserve eor;
	EventSalesReserveTable sls;
	public static JFrame jf;
	String type;
	/**
	 * Create the frame.
	 */
	public WindowEventOrderReserve(String rtype, String rid, String type, String rname) {
		this.type = type;
		jf = new JFrame();
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jf.setExtendedState(JFrame.MAXIMIZED_BOTH);
		jf.setUndecorated(true);
		jf.setBounds(0, 0, 1390, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		jf.setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 0 0,grow");
		
		pnlSales = new JPanel();
		scrollPane.setViewportView(pnlSales);
		pnlSales.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		eor = new EventOrderReserve();
		ModelSalesEventOrder ms = new ModelSalesEventOrder();
		sls = new EventSalesReserveTable(eor);
		new RemoveController(eor, ms, sls);
		new AddController(eor, ms, sls);
		sls.ViewTableOrders(rid);
		eor.tfId.setText(rid);
		pnlSales.add(eor, "cell 0 0,grow");
		eor.tfName.setText(rname);
		eor.tfType.setText(rtype);
		pnlSales.repaint();
		pnlSales.revalidate();
	}

}
