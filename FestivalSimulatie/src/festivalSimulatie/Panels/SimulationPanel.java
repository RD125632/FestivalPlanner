package festivalSimulatie.Panels;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import festivalSimulatie.Object.DigitalClock;

public class SimulationPanel extends JPanel implements ChangeListener {
	private static final long serialVersionUID = 1L;

	private JButton simulationButton;
	private JLabel amountOfVisitorsLabel;
	private JTextField amountOfVisitorsInput;
	private boolean simulationStatus = true; // if true the simulation is on, if
												// false the simulation is off
	private DigitalClock clock;
	private JSlider slider;
	private JLabel framesPerSecond;
	private int fps;
	
	private ViewPanel panel;

	public SimulationPanel(int width, DigitalClock clock, ViewPanel panel) {
		super(null);
		this.panel = panel;
		fps = 60;
		setBounds(10, 520, width / 2, 180);
		setBackground(Color.LIGHT_GRAY);
		this.clock = clock;
		makeFields();

	}

	// make the fields
	private void makeFields() {
		simulationButton = new JButton("Simulation");
		amountOfVisitorsLabel = new JLabel("Amount of visitors");
		amountOfVisitorsInput = new JTextField(4);
		slider = new JSlider(JSlider.HORIZONTAL, 0, 300, 60);
		slider.setMajorTickSpacing(30);
		slider.setMinorTickSpacing(30);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setBackground(Color.LIGHT_GRAY);
		framesPerSecond = new JLabel("Frames Per Second");
		setFields();
	}

	// set the position and size of the fields
	private void setFields() {
		simulationButton.setBounds(
				this.getWidth() / 20, 10,
				(int) simulationButton.getPreferredSize().getWidth(),
				(int) simulationButton.getPreferredSize().getHeight());
		amountOfVisitorsLabel.setBounds(simulationButton.getX(),
								 (int) (simulationButton.getY() + simulationButton.getPreferredSize().getHeight()),
							     (int) amountOfVisitorsLabel.getPreferredSize().getWidth(),
								 (int) amountOfVisitorsLabel.getPreferredSize().getHeight());
		amountOfVisitorsInput.setBounds(
								 (int) (amountOfVisitorsLabel.getX() + amountOfVisitorsLabel.getPreferredSize().getWidth()), 
								        amountOfVisitorsLabel.getY(), 
								  (int) amountOfVisitorsInput.getPreferredSize().getWidth(), 
								  (int) amountOfVisitorsInput.getPreferredSize().getHeight());
		clock.setBounds(
				amountOfVisitorsLabel.getX(),
				amountOfVisitorsLabel.getY()
						+ amountOfVisitorsLabel.getHeight(), (int) clock
						.getPreferredSize().getWidth(), (int) clock
						.getPreferredSize().getHeight());
		slider.setBounds(100, 80, 300, 40);
		framesPerSecond.setBounds(200, 30,
				framesPerSecond.getText().length() * 10, 70);

		addFields();
		addAction();
	}

	// if needed add an action on a field
	private void addAction() {
		simulationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(clock.getTimer().isRunning())
					simulationStatus = true;
				else
					simulationStatus = false;
				
				if (!simulationStatus) {
					clock.getTimer().start();
				} else {
					clock.getTimer().stop();
				}
			}
		});
		slider.addChangeListener(this);
		amountOfVisitorsInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setAmountOfVisitors(Integer.parseInt(amountOfVisitorsInput.getText()));
			}
		});
	}

	// add the fields on the panel
	private void addFields() {
		add(simulationButton);
		add(amountOfVisitorsLabel);
		add(amountOfVisitorsInput);
		add(clock);
		add(slider);
		add(framesPerSecond);
	}

	public boolean isSimulationStatus() {
		return simulationStatus;
	}

	public void setSimulationStatus(boolean simulationStatus) {
		this.simulationStatus = simulationStatus;
	}

	public void stateChanged(ChangeEvent arg0) {
		fps = slider.getValue();
		if (fps <= 0) {
			fps = 1;
		}
		clock.getTimer().setDelay(1000 / fps);
	}

	public int getspeed() {
		return fps;
	}

	public DigitalClock getClock(){
		return clock;
	} 
}
