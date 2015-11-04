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
import javax.swing.JPanel;
import javax.swing.JTextField;

import GUI.MainFrame;
import GUI.StageTable;
import Objecten.Festival;

public class EditStagePanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private MainFrame mainFrame;
	private StageTable table;
	
	public EditStagePanel(Festival festival, MainFrame mainFrame, StageTable table)
	{
		this.mainFrame = mainFrame;
		this.table = table;
		panel = initializePanel();
	}

	
	public JPanel initializePanel()
	{
		JPanel artistPanel = new JPanel();
		artistPanel.setLayout(null);
		artistPanel.setOpaque(false);
		
		JPanel panelPanel = new JPanel();
		panelPanel.setBounds(175, 20, 650, 350);
		panelPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		
		JPanel alignPanel = new JPanel();
		alignPanel.setBounds(200, 25, 600, 300);
		
		GridLayout layout = new GridLayout(6,2);
		layout.setHgap(20);
		layout.setVgap(20);
		alignPanel.setLayout(layout);
		
		JLabel titleLabel = new JLabel("Edit");
		titleLabel.setFont(new Font("Sans", Font.BOLD, 20));
				
		JLabel invis = new JLabel();
		invis.setVisible(false);
		

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
		
        
        alignPanel.add(titleLabel);
        alignPanel.add(back);
        
        String text = table.edit();
        
		JTextField setNameText;

			
				JLabel nameLabel = new JLabel("Name");
				setNameText = new JTextField(text);
				setNameText.setBorder(BorderFactory.createLineBorder(Color.black));
				alignPanel.add(nameLabel);
		        alignPanel.add(setNameText);
	
		
	    
	    JButton submit = new JButton();		    
        submit.setText("Edit");
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
            	String newText = setNameText.getText();
            	
            	table.newText(newText);
            	mainFrame.clearContent();
            	mainFrame.loadPageBar();
            	
            
            }
        });
        
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
