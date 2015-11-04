package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import FileIO.Open;
import FileIO.Save;
import GUI.Panel.AddArtistPanel;
import GUI.Panel.AddPerformancePanel;
import GUI.Panel.AddStagePanel;
import GUI.Panel.EditPerformancePanel;
import GUI.Panel.EditStagePanel;
import Objecten.Festival;
import Objecten.Stage;

public class MainFrame extends JFrame{

	private static final long serialVersionUID = 3019899736785213555L;
	
	protected int xMouse;
    protected int yMouse;
    
    public Festival festival;
	public JPanel contentPanel;
	public JPanel pageBar;
	public Table table;
	public StageTable stageTable;
	public ArtistTable artistTable;
	public Boolean selected;
    
	public MainFrame(Festival festival)
	{
		super("Festival Planner");
		this.festival = festival;
		
		initializeFrame();
	}
	
	public void initializeFrame()
	{
		ImageIcon icon = new ImageIcon("src/images/mainIcon.png");
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	    setUndecorated(true);
	    setLocationRelativeTo(null);
	    setSize(1024,864);
	    setIconImage(icon.getImage());
	    
	    
	    JPanel mainPanel = new JPanel();
	    mainPanel.setBorder(BorderFactory.createLineBorder(Color.black));
	    mainPanel.setLayout(null);
	   
	    JPanel menuBar = new JPanel();
        menuBar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        menuBar.setLayout(null);
        menuBar.setBounds(1,1,1022,45);
        
        menuBar.addMouseMotionListener(new MouseMotionAdapter() 
        {
            public void mouseDragged(MouseEvent evt) 
            {
                int x = evt.getXOnScreen();
                int y = evt.getYOnScreen();
                
                setLocation(x - xMouse,y - yMouse);
            }
        });
        
        menuBar.addMouseListener(new MouseAdapter() 
        {
            public void mousePressed(MouseEvent evt) 
            {
              xMouse = evt.getX();
              yMouse = evt.getY();
            }
        });            
        
        JButton openBTN = menuBarBTN("Open");
        JButton saveBTN = menuBarBTN("Save"); 
        JButton exitBTN = menuBarBTN("Exit");
	    
        openBTN.setBounds(15, 6, 30, 30);
        saveBTN.setBounds(50, 6, 30, 30);
        exitBTN.setBounds(990, 6, 30, 30);
        
        menuBar.add(openBTN);
        menuBar.add(saveBTN);
        menuBar.add(exitBTN);
        
        pageBar = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
		pageBar.setName("pageBar");
        pageBar.setBounds(1, 45, 1022, 60);
        pageBar.setOpaque(false);

        loadPageBar();
        
        JPanel contentBackgroundPanel = new JPanel();
        contentBackgroundPanel.setBounds(1,45,1022,818);
        contentBackgroundPanel.setLayout(new GridLayout(1,1));
        
	    JLabel backgroundLBL = new JLabel();
	    backgroundLBL.setIcon(new ImageIcon("src/Images/background.png"));
	    
	    contentBackgroundPanel.add(backgroundLBL);
        
      //Initialize ContentPanel
        contentPanel = new JPanel();
        contentPanel.setBounds(1,105,1022,712);
        contentPanel.setLayout(new GridLayout(1,1));
        contentPanel.setOpaque(false);
        
        //FOOTER
        
        JPanel footerBar = new JPanel(new FlowLayout(FlowLayout.RIGHT,0,0));
        footerBar.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));
        footerBar.setBounds(1,818,1022,45);
        
        JButton stageBTN = addBTN("Stage");
        JButton artistBTN = addBTN("Artist");
        JButton addArtistBTN = addBTN("Add Artist");
        JButton addStageBTN = addBTN("Add Stage");
        JButton addPerfBTN = addBTN("Add Performance");
        JButton editBTN = menuBarBTN("Edit"); 
        JButton delBTN = menuBarBTN("Delete");
       
        footerBar.add(stageBTN);
        footerBar.add(artistBTN);
        
        footerBar.add(addStageBTN);
        footerBar.add(addArtistBTN);
        footerBar.add(addPerfBTN);
        footerBar.add(editBTN);
        footerBar.add(delBTN);
        
        
        mainPanel.add(pageBar);
        mainPanel.add(menuBar);
        mainPanel.add(footerBar);
        mainPanel.add(contentPanel);
        mainPanel.add(contentBackgroundPanel);
        
	    setContentPane(mainPanel);
	
	}
	
	public JButton menuBarBTN(String name)
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
	            public void mouseEntered(MouseEvent evt) {
	            	button.setIcon(hover);
	            }

	            public void mouseExited(MouseEvent evt) {
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
	                		                		
	                	case "Save":
	                		new Save().saveFile(festival);
	                		break;
	                		
	                	case "Open":
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
	    						e.printStackTrace();
	    					}
	                		break;
	                	case "Delete":
	                		if(table != null)
	                		{
	                			table.removeRow();  
	                		}
	                		else if(stageTable != null)
	                		{
	                			stageTable.removeRow();
	                		}
	                		else if(artistTable != null)
	                		{
	                			artistTable.removeRow();
	                		}
	                		
	                		break;
	                	case "Edit": 
	                		if(table != null)
	                		{
	                			if (table.getTable().getSelectedRow() != -1 || table.getTable().getSelectedColumn() != -1)
	                			{
	                				loadContent(new EditPerformancePanel(festival, (MainFrame)SwingUtilities.getWindowAncestor(button), table).getPanel());
	                				table = null;
	                			}
	                		}
	                		else if(stageTable != null)
	                		{
	                			if (stageTable.getTable().getSelectedRow() != -1 || stageTable.getTable().getSelectedColumn() != -1)
	                			{
	                				loadContent(new EditStagePanel(festival, (MainFrame)SwingUtilities.getWindowAncestor(button), stageTable).getPanel());
	                				stageTable = null;
	                			}
	                		}	                		
	                		break;
	                	case "Add":
	    	            	loadContent(new AddArtistPanel(festival, (MainFrame)SwingUtilities.getWindowAncestor(button)).getPanel());
	                		break;
	            	}
	            }
	        });
		    
	        
		return button;
	}
	
	public JButton pageBarBTN(String name, Border border)
	{
		
        JButton button = new JButton();
		button.setName(name);
		button.setText(name);
		button.setBackground(new Color(238,238,238));
		
		button.setBorder(border);
		button.setPreferredSize(new Dimension(100,60));
	    button.setFocusPainted(false);
	    button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		    
	        button.addMouseListener(new MouseAdapter() 
	        {
	            public void mouseEntered(MouseEvent evt) {
	            	button.setBackground(new Color(250,250,250));
	            }

	            public void mouseExited(MouseEvent evt) {
	            	button.setBackground(new Color(238,238,238));	
	            }
	            
	            public void mouseClicked(MouseEvent event)
	            {
	            	  for (Stage s : festival.getStage())
	            		{
	            			if(button.getText() == s.getName())
		            		{
	            				stageTable = null;
	            				artistTable = null;
	            				table = new Table(festival, s);
	            				loadContent(table.tablePanel());
		            		}
	            		}  
	            }
	            
	        });
	        	      
	     
		return button;
	}
	
	public JButton addBTN(String name)
	{
        JButton button = new JButton();
		button.setName(name);
		button.setText(name);
		button.setBackground(new Color(238,238,238));
	    button.setFocusPainted(false);
	    button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		    
	        button.addMouseListener(new MouseAdapter() 
	        {
	            public void mouseEntered(MouseEvent evt) {
	            	
	            }

	            public void mouseExited(MouseEvent evt) {
	            	
	            }
	        });
	      
	     button.addActionListener(new ActionListener()
	        {
	            public void actionPerformed(ActionEvent event)
	            {      
	            	switch(((JComponent) event.getSource()).getName())
	            	{
	            		case "Artist":
	            			table = null;
	            			stageTable = null;
	            			artistTable = new ArtistTable(festival);
		            		loadContent(artistTable.tablePanel());
	        			break;    		
	        			
	            		case "Stage":
	            		  table = null;
	            		  artistTable = null;
	  	            	  stageTable = new StageTable(festival);
		            	  loadContent(stageTable.tablePanel());  
	        			break;
	            		case "Add Artist":
	            			loadContent(new AddArtistPanel(festival, (MainFrame)SwingUtilities.getWindowAncestor(button)).getPanel());
            			break;    		
            			
	            		case "Add Stage":
	            			loadContent(new AddStagePanel(festival, (MainFrame)SwingUtilities.getWindowAncestor(button)).getPanel());
            			break;
            			
	            		case "Add Performance":
	            			loadContent(new AddPerformancePanel(festival, (MainFrame)SwingUtilities.getWindowAncestor(button)).getPanel());
            			break;
	            	}
            			
	            }
	        });
		    
	        
		return button;
	}
	
	public void loadPageBar()
	{		       
			pageBar.removeAll();
			
			ArrayList<Stage> stages = festival.getStage();
			
	        
		    if(!stages.isEmpty())
		    {
		    	int i = 0;
		    	
		    	
		    	for(Stage stage : stages)
				{
		    		i++;
		    		if(i != stages.size())
		    		{
		    			pageBar.add(pageBarBTN(stage.getName(), BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black)));
		    		}
		    		else
		    		{
		    			pageBar.add(pageBarBTN(stage.getName(), BorderFactory.createMatteBorder(0, 0, 1, 1, Color.black)));
		    		}
				}
		    }
			
			pageBar.validate();
			pageBar.repaint();			    
	}
	
	public void loadContent(JPanel newContent)
	{
		contentPanel.removeAll();
		contentPanel.add( newContent );
		
				
		contentPanel.revalidate();
		contentPanel.repaint();
	}
	
	public void clearContent()
	{
		contentPanel.removeAll();	
		contentPanel.revalidate();
		contentPanel.repaint();
	}	
}
