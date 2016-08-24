/***********************************************************************
Program Name: orderstab.java
Programmer's Name: Pablo Muñoz
Program Description: displays the saved orders
***********************************************************************/
package CourseProject;

import java.awt.Desktop.Action;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.mysql.*;

public class OrdersTab extends BaseTab {
	JTextArea textarea;
	JButton displayButton;
	
	public OrdersTab(GridBagConstraints gbc)
	{
		super(gbc);
	}
	
	
	public void main() {
		textarea = new JTextArea();
		textarea.setEditable(false);
		textarea.setPreferredSize(new Dimension(350, 150));
		JScrollPane scrollPane = new JScrollPane(textarea);
		
		
		displayButton  = new JButton("Display orders");
		displayButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				try {
					showOrders();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		add(displayButton, gbc);
		
		nextY();
		add(scrollPane, gbc);
	}
	
	public void showOrders() throws SQLException
	{
		String tempString;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
		} 
		catch (ClassNotFoundException e) {
			System.out.println("MySQL JDBC Driver not found !!");
			return;
		}
		Connection con = null;
		con = DriverManager.getConnection("jdbc:mysql://Devry.edupe.net:4300/CIS355A_3415", "3415", "Hitokory12");
		
		PreparedStatement statement = null;
		statement = con.prepareStatement("select * from flooring");
		ResultSet result = statement.executeQuery();
		textarea.setText("Orders:");
		while(result.next())
		{
			tempString = result.getString(1) + "   " + result.getString(2) + "   " + result.getString(3) + "   " + result.getDouble(4) + "   " + result.getDouble(5);
			textarea.append("\n" + tempString);
		}
	}

}
