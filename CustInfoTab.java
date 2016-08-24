/***********************************************************************
Program Name: CustInfoTab.java
Programmer's Name: Pablo Muñoz
Program Description: Accepts the name and address of the customer
***********************************************************************/
package CourseProject;

import java.awt.GridBagConstraints;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class CustInfoTab extends BaseTab {
	JLabel NameLabel, AddressLabel;
	JTextField NameTextField, AddressTextField;
	
	public CustInfoTab(GridBagConstraints gbc)
	{
		super(gbc);
	}
	
	public void main()
	{
		NameLabel = new JLabel("Name:");
		AddressLabel = new JLabel("Address:");
		
		NameTextField = new JTextField(15);
		AddressTextField  = new JTextField(15);
		
		add(NameLabel, gbc);

		nextY();
		add(NameTextField, gbc);
		
		nextY();
		add(AddressLabel, gbc);
		
		nextY();
		add(AddressTextField, gbc);
	}
}
