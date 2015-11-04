package GUI.Panel;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import GUI.MainFrame;
import Objecten.Artist;
import Objecten.Festival;

public class AddArtistPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private Festival festival;
	private MainFrame mainFrame;
	
	public AddArtistPanel(Festival festival, MainFrame mainFrame)
	{
		this.festival = festival;
		this.mainFrame = mainFrame;
		panel = initializePanel();
	}
	
	public JPanel initializePanel()
	{
		JPanel artistPanel = new JPanel();
		artistPanel.setName("stagePanel");
		artistPanel.setLayout(null);
		artistPanel.setOpaque(false);
		
		JPanel panelPanel = new JPanel();
		panelPanel.setBounds(175, 20, 650, 250);
		panelPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		
		JPanel alignPanel = new JPanel();
		alignPanel.setBounds(200, 25, 600, 230);
		
		JLabel titleLabel = new JLabel("Artist");
		titleLabel.setFont(new Font("Sans", Font.BOLD, 20));
		
		JLabel nameLabel = new JLabel("Name");
		JLabel popularityLabel = new JLabel("Popularity");
		JLabel genreLabel = new JLabel("Genre");
		
		JLabel invis = new JLabel();
		invis.setVisible(false);
		
		GridLayout layout = new GridLayout(5,2);
		layout.setHgap(20);
		layout.setVgap(20);
		alignPanel.setLayout(layout);
		
		JTextField setNameText = new JTextField();
		JTextField setPopularText = new JTextField();
		JTextField setGenreText = new JTextField();

		setNameText.setBorder(BorderFactory.createLineBorder(Color.black));
		setPopularText.setBorder(BorderFactory.createLineBorder(Color.black));
		setGenreText.setBorder(BorderFactory.createLineBorder(Color.black));
		
		
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
            	String errorCode = "";
            	if(setNameText.getText().isEmpty())
            	{
            		errorCode = "Name";	
            	}
            	else if(setPopularText.getText().isEmpty())
            	{
            		errorCode = "Popularity";	
            	}
            	else if(setGenreText.getText().isEmpty())
            	{
            		errorCode = "Genre";	
            	}
            	else if(errorCode != "")
            	{
            		JOptionPane.showMessageDialog(null,"Er is geen " + errorCode + " ingevuld!","Foutmelding",JOptionPane.WARNING_MESSAGE);	
            	}
            	else
            	{
            		try
            		{ 
            			String splitSpace = setPopularText.getText().replace(" ","");
            			int popularityInt = Integer.parseInt(splitSpace);
            			Artist artist = new Artist(setNameText.getText(), popularityInt, setGenreText.getText());
            			festival.addArtist(artist);
            			mainFrame.clearContent();
            		}
            		catch(NumberFormatException e) 
            		{ 
            	    	JOptionPane.showMessageDialog(null,"Popularity is not a number","Foutmelding",JOptionPane.WARNING_MESSAGE);
            	    }	
            	}
            	
            }
        });
        alignPanel.add(titleLabel);
        alignPanel.add(back);
        alignPanel.add(nameLabel);
        alignPanel.add(setNameText);
        alignPanel.add(popularityLabel);
        alignPanel.add(setPopularText);
        alignPanel.add(genreLabel);
        alignPanel.add(setGenreText);
        alignPanel.add(submit);
        

		artistPanel.add(alignPanel);
        artistPanel.add(panelPanel);
        
		return artistPanel;
	
	}
	
	public JPanel getPanel()
	{
		return panel;
	
	}
}
