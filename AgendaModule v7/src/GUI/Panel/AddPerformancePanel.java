package GUI.Panel;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

import GUI.MainFrame;
import Objecten.Artist;
import Objecten.Festival;
import Objecten.Performance;
import Objecten.Stage;

public class AddPerformancePanel extends JPanel
{

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private Festival festival;
	private MainFrame mainFrame;
	private Artist currentArtist;
	
	public AddPerformancePanel(Festival festival, MainFrame mainFrame)
	{
		this.festival = festival;
		this.mainFrame = mainFrame;
		panel = initializePanel();
	}
	
	public JPanel initializePanel()
	{
		JPanel mainPanel = new JPanel();
		mainPanel.setName("mainPanel");
		mainPanel.setLayout(null);
		mainPanel.setOpaque(false);
		
		JPanel panelPanel = new JPanel();
		panelPanel.setBounds(175, 20, 650, 350);
		panelPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JLabel titleLabel = new JLabel("Performance");
		titleLabel.setFont(new Font("Sans", Font.BOLD, 20));
		
		
		JPanel alignPanel = new JPanel();
		alignPanel.setBounds(200, 25, 600, 330);
		
		JLabel beginLabel = new JLabel("Begin time");
		JLabel endLabel = new JLabel("End time");
		JLabel artistLabel = new JLabel("Artist");
		JLabel stageLabel = new JLabel("Stage");
		
		JLabel invis = new JLabel();
		invis.setVisible(false);
		
		GridLayout layout = new GridLayout(7,2);
		layout.setHgap(20);
		layout.setVgap(20);
		alignPanel.setLayout(layout);
		
		JSpinner setBeginTimeText = new JSpinner( new SpinnerDateModel() );
		JSpinner.DateEditor beginTimeEditor = new JSpinner.DateEditor(setBeginTimeText, "HH:mm");
		setBeginTimeText.setEditor(beginTimeEditor);
		setBeginTimeText.setValue(new Date());
		
		JSpinner setEndTimeText = new JSpinner( new SpinnerDateModel() );
		JSpinner.DateEditor endTimeEditor = new JSpinner.DateEditor(setEndTimeText, "HH:mm");
		setEndTimeText.setEditor(endTimeEditor);
		setEndTimeText.setValue(new Date());

		JComboBox<String> artistList = new JComboBox<String>();
		for(Artist artist : festival.getArtists())
		{
			artistList.addItem(artist.getName());
		}
		
		JComboBox<String> stageList = new JComboBox<String>();
		for(Stage stage : festival.getStage())
		{
			stageList.addItem(stage.getName());
		}
		
		setBeginTimeText.setBorder(BorderFactory.createLineBorder(Color.black));
		setEndTimeText.setBorder(BorderFactory.createLineBorder(Color.black));
		artistList.setBorder(BorderFactory.createLineBorder(Color.black));
		stageList.setBorder(BorderFactory.createLineBorder(Color.black));
		
		
		JButton back = new JButton();	
		
		Icon normal = new ImageIcon("src/Images/exit.png");
        Icon hover = new ImageIcon("src/Images/exitHover.png");
        
		back.setIcon(normal);
        back.setForeground(Color.white);
        back.setContentAreaFilled(false);
        back.setBorderPainted(false);
        back.setOpaque(false);
        back.setFocusPainted(false);
        back.setCursor(new Cursor(Cursor.HAND_CURSOR));
        back.addMouseListener(new MouseAdapter() 
        {
            public void mouseEntered(MouseEvent evt) {
            	back.setIcon(hover);
            }

            public void mouseExited(MouseEvent evt) {
            	back.setIcon(normal);
            }
        });
        
        back.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            { 
            	mainFrame.clearContent();
            }
        });
		
		
	    JButton submit = new JButton();		    
        submit.setText("Add");
        submit.setBackground(new Color(217,118,39));
        submit.setForeground(Color.white);
        submit.setBorderPainted(false);
        submit.setFocusPainted(false);
        submit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        submit.addMouseListener(new MouseAdapter() 
        {
            public void mouseEntered(MouseEvent evt) {
            	submit.setBackground(new Color(231,163,54));
            }

            public void mouseExited(MouseEvent evt) {
            	submit.setBackground(new Color(217,118,39));
            }
        });

        submit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            { 
        		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        		SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");
        		
        		Date beginDate = (Date)setBeginTimeText.getValue();
        		Date endDate = (Date)setEndTimeText.getValue();
        		
        		String beginFormat = sdf.format(beginDate);
        		String endFormat = sdf.format(endDate);
            		
            		try
            		{
            			Date beginFormat1 = sdf1.parse(beginFormat);
            			Date endFormat1 = sdf1.parse(endFormat);
            		
            			if(beginFormat1.compareTo(endFormat1) == 1)
            			{
            				JOptionPane.showMessageDialog(null,"YOU CAN NOT PARTY BACKWARDS","Errormessage",JOptionPane.WARNING_MESSAGE);	
            			}
            			else if(beginFormat1.compareTo(endFormat1) == 0)
            			{
            				JOptionPane.showMessageDialog(null,"YOU CAN NOT PARTY FOREVER","Errormessage",JOptionPane.WARNING_MESSAGE);	
            			}
            			else
            			{
            				elseStatement(artistList, stageList, beginFormat, endFormat);
            			}            					
            		}
            		catch(ParseException ex)
            		{
            			ex.printStackTrace();
            		}            		               			     	
            }
        });
        
        alignPanel.add(titleLabel);
        alignPanel.add(back);
        alignPanel.add(beginLabel);
        alignPanel.add(setBeginTimeText);
        alignPanel.add(endLabel);
        alignPanel.add(setEndTimeText);
        alignPanel.add(artistLabel);
        alignPanel.add(artistList);
        alignPanel.add(stageLabel);
        alignPanel.add(stageList);
        alignPanel.add(submit);
		
        mainPanel.add(alignPanel);
        mainPanel.add(panelPanel);
        
        
		return mainPanel;
	}
	
	public JPanel getPanel()
	{
		return panel;
	
	}

	
	public void elseStatement(JComboBox<String> artistList, JComboBox<String> stageList, String beginFormat, String endFormat)
	{		
		for(Stage stage : festival.getStage())
		{
			for(Artist artist : festival.getArtists())
			{
				if(artistList.getSelectedItem() == artist.getName())
				{
					currentArtist = artist;
				}
			}
			if(stageList.getSelectedItem() == stage.getName())
			{
				if(stage.getLineUp().isEmpty())
				{
        			Performance performance = new Performance(beginFormat, endFormat);
        			performance.setArtist(currentArtist);
        			
					stage.addPerformance(performance);
					mainFrame.clearContent();
					
				}
				else 
				{
					if(stageList.getSelectedItem() == stage.getName())
					{
		    			Performance performance = new Performance(beginFormat, endFormat);
		                
		    			for(Artist artist : festival.getArtists())
		    			{
		    				if(artistList.getSelectedItem() == artist.getName())
		    				{
		    					performance.setArtist(artist);
		    					currentArtist = artist;
		    				}
		    			}
						stage.addPerformance(performance);
						
		    			//Table table = new Table(festival, stage);
		    			mainFrame.clearContent();
		    			//mainFrame.loadContent(table.tablePanel());
					}
				}
			}					
		}
	}
}
