/***********************************************************************
Program Name: mainFrame.java
Programmer's Name: Pablo Muñoz
Program Description: creates all four tabs for the Pane.
***********************************************************************/
package CourseProject;

import java.awt.*;

import javax.swing.*;

public class mainFrame extends JFrame {
	JTabbedPane jtp;
	CustInfoTab custinfotab;
	FloorInfoTab floorinfotab;
	SummaryTab summarytab;
	OrdersTab orderstab;
	
	Dimension d = new Dimension(400, 300);
	
	GridBagConstraints gbc;
	
	public mainFrame()
	{
		jtp = new JTabbedPane();
		add(jtp);
		
		gbc = new GridBagConstraints();
		custinfotab = new CustInfoTab(gbc);
		floorinfotab = new FloorInfoTab(gbc);
		summarytab = new SummaryTab(gbc, custinfotab, floorinfotab);
		orderstab = new OrdersTab(gbc);
		
		jtp.add("Customer Info", custinfotab);
		jtp.add("Flooring", floorinfotab);
		jtp.add("Summary", summarytab);
		jtp.addTab("Orders", orderstab);
		
		setSize(d);
		setTitle("Flooring Application");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLocationRelativeTo(null);
		setVisible(true);
	}

}
