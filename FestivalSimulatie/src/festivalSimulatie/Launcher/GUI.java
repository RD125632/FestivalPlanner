package festivalSimulatie.Launcher;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import Objecten.Festival;
import festivalSimulatie.ImageIO.OpenFPL;
import festivalSimulatie.ImageIO.OpenSim;
import festivalSimulatie.ImageIO.Save;
import festivalSimulatie.Panels.BuildingPanel;
import festivalSimulatie.Panels.Item;
import festivalSimulatie.Panels.OptionsPanel;
import festivalSimulatie.Panels.SimulationPanel;
import festivalSimulatie.Panels.ViewPanel;

public class GUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private Festival festival;
	private int width;
	private JPanel basePanel = new JPanel();
	private JMenuBar menubar = new JMenuBar();
	private ViewPanel viewPanel;
	private BuildingPanel buildingPanel;
	private SimulationPanel simulationPanel;
	private OptionsPanel optionsPanel;
	private boolean boot;

	// Initialize GUI
	public GUI(String title, int width, int height) {
		super(title);
		this.width = width;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(basePanel);
		setLayout(null);
		boot = true;
		load();
		initPanels();
		makeMenuBar();

		setSize(width, height);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void initPanels() {				
		viewPanel = new ViewPanel(this);
		buildingPanel = new BuildingPanel(festival,viewPanel);
		simulationPanel = new SimulationPanel(width, viewPanel.getClock(), viewPanel);
		optionsPanel = new OptionsPanel(width, viewPanel.getClock());
		
		basePanel.add(simulationPanel);
		basePanel.add(optionsPanel);
		basePanel.add(buildingPanel);
		basePanel.add(viewPanel);
	}

	private void makeMenuBar() {
		setJMenuBar(menubar);

		JMenu menu = new JMenu("Menu");

		// load Sim File
		JMenuItem loadSim = new JMenuItem("Load Sim");
		loadSim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadSim();

			}
		});
		menu.add(loadSim);

		// save Sim File
		JMenuItem saveAs = new JMenuItem("Save Sim");
		saveAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveSim();
			}
		});
		menu.add(saveAs);

		// Load FPL File
		JMenuItem loadFPL = new JMenuItem("Load .FPL");
		loadFPL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				load();
			}
		});
		menu.add(loadFPL);

		menubar.add(menu);
	}

	public void load() {
		OpenFPL openFPL = new OpenFPL();
		try {
			openFPL.openFile();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		festival = openFPL.getFestivalObject();

		if (boot) {
			boot = false;
			return;
		} else {
			buildingPanel.reInit(festival);
		}
	}

	public void loadSim() {
		OpenSim open = new OpenSim();
		try {
			open.openFile();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		ArrayList<Item> itemsForView = open.getItemsForViewObject();
		ArrayList<Item> items = open.getItemsObject();
		buildingPanel.reInit(itemsForView,items);
		viewPanel.setTiles(open.getTilesObject());
		
		viewPanel.repaint();
	}

	public void saveSim() {
		Save save = new Save();
		save.saveFile(BuildingPanel.getViewableItems(),buildingPanel.getItems(),viewPanel.getTiles());
	}
}
