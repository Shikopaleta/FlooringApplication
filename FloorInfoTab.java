/***********************************************************************
Program Name: FloorInfoTab.java
Programmer's Name: Pablo Muñoz
Program Description: Stores the area and flooring type.
***********************************************************************/
package CourseProject;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class FloorInfoTab extends BaseTab {
	JLabel AreaLabel, FloorTypeLabel, LengthLabel, WidthLabel, CostLabel;
	JRadioButton WoodRadioButton, CarpetRadioButton;
	JTextField AreaTextField, LengthField, WidthField, CostField;
	JButton AreaCalcButton;
	ButtonGroup bg;
	double area;
	
	public FloorInfoTab(GridBagConstraints gbc)
	{
		super(gbc);
	}
	public void main() {
		area = 0.f;
		AreaLabel = new JLabel("Area:");
		FloorTypeLabel = new JLabel("Floor type:");
		LengthLabel = new JLabel("Length:");
		WidthLabel = new JLabel("Width:");
		CostLabel = new JLabel("Cost:");
		
		AreaTextField = new JTextField(8);
		AreaTextField.setEditable(false);
		CostField = new JTextField(8);
		CostField.setEditable(false);
		LengthField = new JTextField(8);
		WidthField = new JTextField(8);
		
		AreaCalcButton = new JButton("Calculate");
		AreaCalcButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				setArea();
				AreaTextField.setText(Double.toString(area));
				CostField.setText(String.format("$%10.2f", getCost()));
			}
		});
		
		WoodRadioButton = new JRadioButton("Wood", true);
		CarpetRadioButton = new JRadioButton("Carpet", false);
		
		bg = new ButtonGroup();
		bg.add(WoodRadioButton);
		bg.add(CarpetRadioButton);
		
		add(LengthLabel, gbc);
		
		nextY();
		add(LengthField, gbc);
		
		nextY();
		
		gbc.gridwidth = 2;
		add(AreaLabel, gbc);
		
		nextY();
		add(AreaTextField, gbc);
		
		nextY();
		add(AreaCalcButton, gbc);
		
		nextY();
		add(CostLabel, gbc);
		
		nextY();
		add(CostField, gbc);
		gbc.gridwidth = 1;
		
		
		nextX();
		resetY();
		add(WidthLabel, gbc);
		
		nextY();
		add(WidthField, gbc);
		
		nextX();
		resetY();
		add(FloorTypeLabel, gbc);
		
		nextY();
		add(WoodRadioButton, gbc);
		
		nextY();
		add(CarpetRadioButton, gbc);
	}
	
	public void setArea()
	{
		double length, width;
		try
		{
			length = Double.parseDouble(LengthField.getText());
			width = Double.parseDouble(WidthField.getText());
			area = length * width;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Please type valid numbers for length and width.", "Error", JOptionPane.PLAIN_MESSAGE);
			area = 0;
		}
		
	}
	
	public double getCost()
	{
		double floorcost = 0;

		if (area > 0)
		{
			
			if (WoodRadioButton.isSelected())
			{
				floorcost = 20.f;
			}
			else if (CarpetRadioButton.isSelected())
			{
				floorcost = 10.f;
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Please select a valid floor type.", "Error", JOptionPane.PLAIN_MESSAGE);
				return 0;
			}
			return area * floorcost;
		}
		else
		{
			return 0;
		}
		
	}

}
