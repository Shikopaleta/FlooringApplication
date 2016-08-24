/***********************************************************************
Program Name: SummaryTab.java
Programmer's Name: Pablo Muñoz
Program Description: Displays a summary and saves infrmation.
***********************************************************************/
package CourseProject;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class SummaryTab extends BaseTab {
	CustInfoTab custinfotab;
	FloorInfoTab floorinfotab;
	JLabel Label1, Label2, Label3, Label4, Label5;
	JTextField NameField, AddressField, FloorTypeField, AreaField, CostField;
	JButton SummaryDisplayButton, SaveButton;
	
	public SummaryTab(GridBagConstraints gbc, CustInfoTab cust, FloorInfoTab floor)
	{
		super(gbc);
		custinfotab  = cust;
		floorinfotab = floor;
	}
	
	public void main()
	{
		Label1 = new JLabel("Name: ");
		Label2 = new JLabel("Address: ");
		Label3 = new JLabel("Floor type: ");
		Label4 = new JLabel("Floor area:");
		Label5 = new JLabel("Cost: ");
		
		NameField = new JTextField(15);
		NameField.setEditable(false);
		AddressField = new JTextField(15);
		AddressField.setEditable(false);
		FloorTypeField = new JTextField(15);
		FloorTypeField.setEditable(false);
		AreaField = new JTextField(15);
		AreaField.setEditable(false);
		CostField = new JTextField(15);
		CostField.setEditable(false);
		
		SummaryDisplayButton = new JButton("Display Summary");
		SummaryDisplayButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				displaySummary();
			}
		});
		
		SaveButton = new JButton("Save");
		SaveButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				try {
					saveInfo();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		);
		
		
		add(Label1, gbc);
		
		nextY();
		add(Label2, gbc);
		
		nextY();
		add(Label3, gbc);
		
		nextY();
		add(Label4, gbc);
		
		nextY();
		add(Label5, gbc);
		
		nextY();
		add(SummaryDisplayButton, gbc);
		
		resetY();
		nextX();
		add(NameField, gbc);
		
		nextY();
		add(AddressField, gbc);
		
		nextY();
		add(FloorTypeField, gbc);
		
		nextY();
		add(AreaField, gbc);
		
		nextY();
		add(CostField, gbc);
		
		nextY();
		add(SaveButton, gbc);
	}
	
	public void displaySummary()
	{
		String floortype = "Not Selected"; //default value

		//Name
		NameField.setText(custinfotab.NameTextField.getText());
		
		//Address
		AddressField.setText(custinfotab.AddressTextField.getText());
		
		//Floor type
		if(floorinfotab.WoodRadioButton.isSelected())
		{
			floortype = "Wood";
		}
		else if (floorinfotab.CarpetRadioButton.isSelected())
		{
			floortype = "Carpet";
		}
		FloorTypeField.setText(floortype);
		
		//Area
		floorinfotab.setArea();
		if(floorinfotab.area > 0)
		{
			AreaField.setText(Double.toString(floorinfotab.area));
		} else
		{
			AreaField.setText("Not specified.");
		}
		
		//Cost
		CostField.setText(String.format("$%10.2f", floorinfotab.getCost()));
	}
	
	public void saveInfo() throws SQLException
	{
		if(!validateInfo())
		{
			return;
		}
		
		String name, address, type, stringStatement;
		double area, cost;
       
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
				
		name = custinfotab.NameTextField.getText();
		address = custinfotab.AddressTextField.getText();
		type = "";
		if(floorinfotab.WoodRadioButton.isSelected())
		{
			type = "Wood";
		}
		else if (floorinfotab.CarpetRadioButton.isSelected())
		{
			type = "Carpet";
		}
		area = floorinfotab.area;
		cost = floorinfotab.getCost();
				
		stringStatement = "INSERT INTO flooring"
				+ "(CustomerName, CustomerAddress, FlooringType, FloorArea, FloorCost) "
				+ "VALUES"
				+ "(?, ?, ?, ?, ?)";
		
		PreparedStatement statement = null;
		statement = con.prepareStatement(stringStatement);
		statement.setString(1, name);
		statement.setString(2, address);
		statement.setString(3, type);
		statement.setDouble(4, area);
		statement.setDouble(5, cost);
		statement.executeUpdate();
		JOptionPane.showMessageDialog(null, "Customer saved!", "Message", JOptionPane.PLAIN_MESSAGE);
	}
	
	public boolean validateInfo()
	{
		if(custinfotab.NameTextField.getText().isEmpty())
		{
			JOptionPane.showMessageDialog(null, "Please type the name.", "Error", JOptionPane.PLAIN_MESSAGE);
			return false;
		}
		if(custinfotab.AddressTextField.getText().isEmpty())
		{
			JOptionPane.showMessageDialog(null, "Please type the address.", "Error", JOptionPane.PLAIN_MESSAGE);
			return false;
		}
		if(!floorinfotab.WoodRadioButton.isSelected() && !floorinfotab.CarpetRadioButton.isSelected())
		{
			JOptionPane.showMessageDialog(null, "Please select a valid floor type.", "Error", JOptionPane.PLAIN_MESSAGE);
			return false;
		}
		floorinfotab.setArea();
		if(floorinfotab.area <= 0)
		{
			return false;
		}
		
		return true;
	}

}
