/***********************************************************************
Program Name: BaseTab.java
Programmer's Name: Pablo Muñoz
Program Description: serves as the base class for the Tabs
***********************************************************************/
package CourseProject;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

public abstract class BaseTab extends JPanel {
	GridBagConstraints gbc;
	
	public BaseTab(GridBagConstraints gbc)
	{
		this.gbc = gbc;
		setLayout(new GridBagLayout());
		ResetGBC();
		main();
	}
	
	public void ResetGBC()
	{
		gbc.ipady = 0;
		gbc.ipadx = 0;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.NONE;
	}
	
	public abstract void main(); 
	
	public void nextY(){gbc.gridy = gbc.gridy + 1;}
	
	public void nextX(){gbc.gridx = gbc.gridx + 1;}
	
	public void resetX(){gbc.gridx = 0;}
	
	public void resetY(){gbc.gridy = 0;}
}
