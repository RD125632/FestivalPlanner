package GUI;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import FileIO.Open;
import Objecten.Festival;

public class StartFrame extends JFrame {
	
		private static final long serialVersionUID = -6306845250865097939L;
		
		protected int xMouse;
	    protected int yMouse;
	    
	    public Festival festival;
		    
		public StartFrame()
		{
			super("Festival Planner");
			initializeFrame();
		}
		
		public void initializeFrame()
		{
			//Initialize Frame
			ImageIcon icon = new ImageIcon("src/images/mainIcon.png");
			
			setDefaultCloseOperation(EXIT_ON_CLOSE);
		    setUndecorated(true);
		    setLocationRelativeTo(null);
		    setSize(600,600);
		    setIconImage(icon.getImage());
		    
		    //Initialize Main panel
		    JPanel mainPanel = new JPanel();
		    mainPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		    mainPanel.setLayout(null);
		   
		    //MENU
		    //Initialize menu panel
		    JPanel menuPanel = new JPanel();
	        menuPanel.setBorder(BorderFactory.createLineBorder(Color.black));
	        menuPanel.setLayout(null);
	        menuPanel.setBounds(0,0,600,45);
	        
	        //Makes frame dragable
	        menuPanel.addMouseMotionListener(new MouseMotionAdapter() 
	        {
	            public void mouseDragged(MouseEvent evt) 
	            {
	                int x = evt.getXOnScreen();
	                int y = evt.getYOnScreen();
	                
	                setLocation(x - xMouse,y - yMouse);
	            }
	        });
	        
	        //Gets X and Y Coordinate for the listener above
	        menuPanel.addMouseListener(new MouseAdapter() 
	        {
	            public void mousePressed(MouseEvent evt) 
	            {
	              xMouse = evt.getX();
	              yMouse = evt.getY();
	            }
	        });            

	        //Initialize close button
	        JButton exitBTN = BTN("Exit");
	        exitBTN.setBounds(560, 6, 30, 30);
	        menuPanel.add(exitBTN);
	        
	        
	        //CONTENT
	        
	        JPanel contentPanel = new JPanel();
	        contentPanel.setBounds(1,45,598,553);
	        contentPanel.setLayout(new GridLayout(1,1));
	        
		    JLabel backgroundLBL = new JLabel();
		    backgroundLBL.setIcon(new ImageIcon("src/Images/background.png"));
	        
		    JButton openBTN = BTN("openDoor");
		    openBTN.setBounds(30,100,250,350);
		    
		    JButton newBTN = BTN("newDoor");
		    newBTN.setBounds(320,100,250,350);
		    
		    
		    JLabel openLBL = new JLabel("Open Festival", SwingConstants.CENTER);
		    openLBL.setBorder(BorderFactory.createLineBorder(Color.black));
		    openLBL.setBounds(30,410,250,60);
		    
		    JLabel newLBL = new JLabel("New Festival", SwingConstants.CENTER);
		    newLBL.setBorder(BorderFactory.createLineBorder(Color.black));
		    newLBL.setBounds(320,410,250,60);
		    
		    mainPanel.add(openBTN);
	        mainPanel.add(newBTN);
	        mainPanel.add(openLBL);
	        mainPanel.add(newLBL);
	        
	        contentPanel.add(backgroundLBL);
	        	        
	        mainPanel.add(menuPanel);
	        mainPanel.add(contentPanel);
	        	        
		    setContentPane(mainPanel);
		
		}
		
		public JButton BTN(String name)
		{
			Icon normal = new ImageIcon("src/Images/" + name + ".png");
	        Icon hover = new ImageIcon("src/Images/" + name + "Hover.png");

	        JButton button = new JButton();
			button.setName(name);
			button.setIcon(normal);
		    button.setOpaque(false);
		    button.setContentAreaFilled(false);
		    button.setBorderPainted(false);
		    button.setFocusPainted(false);
		    button.setCursor(new Cursor(Cursor.HAND_CURSOR));
			    
		        button.addMouseListener(new MouseAdapter() 
		        {
		            public void mouseEntered(MouseEvent evt) 
		            {
		            	button.setIcon(hover);
		            }

		            public void mouseExited(MouseEvent evt) 
		            {
		            	button.setIcon(normal);
		            }
		        });
		      
		     button.addActionListener(new ActionListener()
		        {
		            public void actionPerformed(ActionEvent event)
		            {
		            	switch(((JComponent) event.getSource()).getName())
		            	{
		                	case "Exit":
		                		System.exit(0);
		                	case "openDoor" :
		                		//Use the Opener.getFestivalObject method to get the retrieved Festival Object from the chosen file for later use
		                		try 
		                		{
		    						Open open = new Open();
		    						open.openFile();
		    						
		    						if(open.getFestivalObject() != null)
		    						{
		    							new MainFrame(open.getFestivalObject()).setVisible(true);
		                    			JFrame frame = (JFrame)SwingUtilities.getWindowAncestor(button);
		                    			frame.dispose();
		    						}
		    					} 
		                		catch (ClassNotFoundException e) 
		                		{
		    						// TODO Auto-generated catch block
		    						e.printStackTrace();
		    					}
		                		break;
		                	case "newDoor" :
		                		Festival festival = new Festival("NEW");

		                		new MainFrame(festival).setVisible(true);
	                    		JFrame frame = (JFrame)SwingUtilities.getWindowAncestor(button);
	                    		frame.dispose();
		                		break;		                		
		            	}
		            }
		        });        
			return button;
		}		
	}

