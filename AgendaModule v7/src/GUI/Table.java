package GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import Objecten.Artist;
import Objecten.Festival;
import Objecten.Performance;
import Objecten.Stage;

public class Table
{
	
	private JTable table;
	private ArrayList<Performance> p;
	
	public Table(Festival festival, Stage stage)
	{
		setModel();
		p = stage.getLineUp();
	}
	
	private void setModel()
	{				
		table = new JTable(new AbstractTableModel()
		{
			private static final long serialVersionUID = 1L;

			@Override
			public int getColumnCount() {
				
				return 3;
			}

			@Override
			public int getRowCount() {
				
				return p.size();
			}

			@Override
			public Object getValueAt(int row, int col) {
				if (col == 0)
				{
					return p.get(row).getArtist().getName();
				} 
				else if (col == 1)
				{
					return p.get(row).getTime().getBeginTime();
				}
				else if (col == 2)
				{
					return p.get(row).getTime().getEndTime();
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
							return "Artist";
						} 
						else if (col == 1)
						{
							return "Begin time";
						}
						else if (col == 2)
						{
							return "End time";
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
		if(row == -1)
		{} 
		else 
		{
			p.remove(row);			
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
				Artist localArtist = p.get(row).getArtist();
				return localArtist.getName() + "," + localArtist.getPopularity() + "," + localArtist.getGenre();
			} 
			else if(col == 1)
			{
				return p.get(row).getTime().getBeginTime();
			}
			else if( col == 2)
			{
				return p.get(row).getTime().getEndTime();
			}
			else
			{
				return null;
			}
		}
	}
	
	public Boolean newText(String text)
	{
		int row = table.getSelectedRow();
		int col = table.getSelectedColumn();
		if(row == -1 || col == -1)
		{} 
		else 
		{
			if(col == 0)
			{
				String[] textPart = text.split(",");
				
				try
        		{ 
					String splitSpace = textPart[1].replace(" ","");
					int popularityInt = Integer.parseInt(splitSpace);
				
					p.get(row).getArtist().setName(textPart[0]);
					p.get(row).getArtist().setPopularity(popularityInt);
					p.get(row).getArtist().setGenre(textPart[2]);
        		}
        		catch(NumberFormatException e) 
        		{ 
        	    	JOptionPane.showMessageDialog(null,"Popularity is not a number","Foutmelding",JOptionPane.WARNING_MESSAGE);
        	    	return false;
        	    }
			} 
			else if(col == 1)
			{
				SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");
        	
            		try
            		{
            			Date beginFormat = sdf1.parse(text);
            			Date endFormat = sdf1.parse((String)this.getTable().getModel().getValueAt(row, 2));
            		
            			
            			
            			if(beginFormat.compareTo(endFormat) == 1)
            			{
            				JOptionPane.showMessageDialog(null,"YOU CAN NOT PARTY BACKWARDS","Errormessage",JOptionPane.WARNING_MESSAGE);	
            				return false;
            			}
            			else if(beginFormat.compareTo(endFormat) == 0)
            			{
            				JOptionPane.showMessageDialog(null,"YOU CAN NOT PARTY FOREVER","Errormessage",JOptionPane.WARNING_MESSAGE);	
            				return false;
            			}
            			else
            			{
            				p.get(row).getTime().setBeginTime(text);
            				
            			}            					
            		}
            		catch(ParseException ex)
            		{
            			JOptionPane.showMessageDialog(null,"This field uses HH:MM","Errormessage",JOptionPane.WARNING_MESSAGE);
            			return false;
            		}  
				
			}
			else if( col == 2)
			{
				p.get(row).getTime().setEndTime(text);
			}
		}
		return true;
	}

	public JTable getTable()
	{
		return table;
	}
	
	
}
