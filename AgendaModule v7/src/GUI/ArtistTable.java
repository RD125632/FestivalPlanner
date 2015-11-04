package GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import Objecten.Artist;
import Objecten.Festival;

public class ArtistTable
{
	
	private JTable table;
	private ArrayList<Artist> p;
	
	public ArtistTable(Festival festival)
	{
		setModel();
		p = festival.getArtists();
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
					return p.get(row).getName();
				} 
				else if (col == 1)
				{
					return p.get(row).getPopularity();
				}
				else if (col == 2)
				{
					return p.get(row).getGenre();
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
					return "Popularity";
				}
				else if (col == 2)
				{
					return "Genre";
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
				Artist localArtist = p.get(row);
				return localArtist.getName() + "," + localArtist.getPopularity() + "," + localArtist.getGenre();
			} 
			else if(col == 1)
			{
				return "" + p.get(row).getPopularity();
			}
			else if( col == 2)
			{
				return p.get(row).getGenre();
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
			else if(col == 1)
			{
				try
        		{
					String splitSpace = text.replace(" ","");
					int popularityInt = Integer.parseInt(splitSpace);
					p.get(row).setPopularity(popularityInt);
        		}
        		catch(NumberFormatException e) 
        		{ 
        	    	JOptionPane.showMessageDialog(null,"Popularity is not a number","Foutmelding",JOptionPane.WARNING_MESSAGE);
        	    }
			}
			else if( col == 2)
			{
				p.get(row).setGenre(text);
			}
		}
	}

	public JTable getTable()
	{
		return table;
	}
	
	
}
