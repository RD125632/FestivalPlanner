package GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;

import Objecten.Festival;
import Objecten.Stage;

public class StageTable
{
	
	private JTable table;
	private ArrayList<Stage> p;
	
	public StageTable(Festival festival)
	{
		setModel();
		p = festival.getStage();
	}
	
	private void setModel()
	{				
		table = new JTable(new AbstractTableModel()
		{
			private static final long serialVersionUID = 1L;

			@Override
			public int getColumnCount() {
				
				return 1;
			}

			@Override
			public int getRowCount() {
				
				return p.size();
			}

			@Override
			public Object getValueAt(int row, int col) {
				if (col == 0)
				{
					return p.get(row).getName();
				} 
				else
				{
					return "-";
				}
			}

			@Override
			public String getColumnName(int col) {
				if (col == 0)
				{
					return "Stage";
				} 
				
				else
				{
					return "-";
				}
			}			
		});
		
		table.setCellSelectionEnabled(true);
	}
	
	public JPanel tablePanel()
	{
		JPanel tableContentPanel = new JPanel();
		tableContentPanel.setLayout(new GridLayout(1,1));
		tableContentPanel.setOpaque(false);

		JPanel tablePanel = new JPanel();
		tablePanel.setOpaque(false);
		tablePanel.setLayout(new GridLayout(1,1));
		tablePanel.add( new JScrollPane(table), BorderLayout.CENTER);
		tableContentPanel.add(tablePanel);

		return tableContentPanel;
	}

	public void removeRow()
	{
		int row = table.getSelectedRow();
		if(row != -1)
		{
			p.remove(row);
			MainFrame main = (MainFrame)SwingUtilities.getWindowAncestor(table);
			main.loadPageBar();
			table.repaint();
		}
	}
	
	public String edit()
	{
		int row = table.getSelectedRow();
		int col = table.getSelectedColumn();
		if(row == -1 || col == -1)
		{return null;} 
		else 
		{
			if(col == 0)
			{
				Stage localStage = p.get(row);
				return localStage.getName();
			} 
			else
			{
				return null;
			}
		}
	}
	
	public void newText(String text)
	{
		int row = table.getSelectedRow();
		int col = table.getSelectedColumn();
		if(row == -1 || col == -1)
		{} 
		else 
		{
			if(col == 0)
			{
				p.get(row).setName(text);
			} 
		}
	}

	public JTable getTable()
	{
		return table;
	}
	
	
}
