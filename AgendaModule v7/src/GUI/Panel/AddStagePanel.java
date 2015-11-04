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
import Objecten.Festival;
import Objecten.Stage;

public class AddStagePanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private Festival festival;
	private MainFrame mainFrame;
	
	public AddStagePanel(Festival festival, MainFrame mainFrame)
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
		panelPanel.setBounds(175, 20, 650, 150);
		panelPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		
		JPanel alignPanel = new JPanel();
		alignPanel.setBounds(200, 25, 600, 120);
		
		JLabel titleLabel = new JLabel("Stage");
		titleLabel.setFont(new Font("Sans", Font.BOLD, 20));
		
		JLabel nameLabel = new JLabel("Name");
		
		JLabel invis = new JLabel();
		invis.setVisible(false);
		
		GridLayout layout = new GridLayout(3,2);
		layout.setHgap(20);
		layout.setVgap(20);
		alignPanel.setLayout(layout);
		
		JTextField setNameText = new JTextField();

		setNameText.setBorder(BorderFactory.createLineBorder(Color.black));
		
		
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

            	if(setNameText.getText().isEmpty())
            	{
            		JOptionPane.showMessageDialog(null," Name is empty!","Foutmelding",JOptionPane.WARNING_MESSAGE);	
            	}
            	else if(festival.getStage().size() >= 10)
            	{
            		JOptionPane.showMessageDialog(null,"Maximum amount of stages is 10","Foutmelding",JOptionPane.WARNING_MESSAGE);
            	}
            	else
            	{
            		try
            		{ 
            			// Dit is waar de stage aangemaakt wordt.
            			Stage stage = new Stage(setNameText.getText());
            			festival.addStage(stage);

            			mainFrame.loadPageBar();
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
