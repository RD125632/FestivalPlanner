package festivalSimulatie.Panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Objecten.Performance;
import Objecten.Time;
import festivalSimulatie.ImageIO.ImageLoader;
import festivalSimulatie.Object.Building;
import festivalSimulatie.Object.DigitalClock;
import festivalSimulatie.Object.Gate;
import festivalSimulatie.Object.StageS;

public class OptionsPanel extends JPanel {
	private static final long serialVersionUID = -7396013325254780505L;

	private static ArrayList<JLabel> labels = new ArrayList<JLabel>();
	private static ImagePanel imagePanel;

	private JPanel buildingInfoPanel;
	protected Font font = new Font("Geen Foto", Font.BOLD, 20);

	private static DigitalClock clock;

	public OptionsPanel(int width, DigitalClock clock) {
		setBounds(width / 2 + 20, 520, width / 2 - 40, 180);
		setLayout(new BorderLayout());

		imagePanel = new ImagePanel();
		setLabels();

		add(labels.get(0), BorderLayout.NORTH);
		add(imagePanel);
		add(setBuildingInfoPanel(), BorderLayout.WEST);

		setBackground(Color.LIGHT_GRAY);

		buildingInfoPanel.setBackground(this.getBackground());
		OptionsPanel.clock = clock;

	}

	private void setLabels() {
		String[] labelNames = { "naam", "populariteit", "beginTime", "endTime" };
		for (int t = 0; t < labelNames.length; t++) {
			JLabel l = new JLabel();
			l.setFont(new Font("", Font.BOLD, 18));
			l.setName(labelNames[t]);
			labels.add(l);
		}
	}

	private JPanel setBuildingInfoPanel() {
		buildingInfoPanel = new JPanel();
		buildingInfoPanel.setLayout(new BoxLayout(buildingInfoPanel,BoxLayout.PAGE_AXIS));
		for (int t = 1; t < 4; t++) {
			buildingInfoPanel.add(labels.get(t));
			buildingInfoPanel.add(Box.createVerticalGlue());
		}
		setLabel(labels.get(0), "geen geselecteerd bestand");
		return buildingInfoPanel;
	}

	private static void setLabel(JLabel label, String text) {
		label.setText(label.getName() + ": " + text);
	}

	public static void setPanel(Building ob) {
		setLabel(labels.get(0), ob.getName());
		imagePanel.setImage(ImageLoader.loadImage(ob.getPath()));
		if (ob.getClass().equals(new StageS().getClass())) {
			StageS stage = (StageS) ob;
			setStageInfo(stage);
		} else if (ob.getClass().equals(new Gate().getClass())) {
			imagePanel.setImage(null);
			setLabel(labels.get(1), "");
			setLabel(labels.get(2), "");
			setLabel(labels.get(3), "");
		}
	}
	
	private static void setStageInfo(StageS stage) {
		Performance currentPerformance = getCurrentPerformance(stage, clock.getHours(), clock.getMinutes());
		try {
			setLabel(labels.get(1), currentPerformance.getArtist().getPopularity() + "");
			setLabel(labels.get(2), currentPerformance.getTime().getBeginTime());
			setLabel(labels.get(3), currentPerformance.getTime().getEndTime());
		} catch (NullPointerException e) {
			setLabel(labels.get(1), "");
			setLabel(labels.get(2), "");
			setLabel(labels.get(3), "");
		}

	}
	
	private static Performance getCurrentPerformance(StageS stage,int currentHour, int currentMinute) {
		for (Performance performance : stage.getAgendaStage().getLineUp()) {
			String[] endTime = performance.getTime().getEndTime().split(":");// first hours, second minutes
			int endHour = Integer.parseInt(endTime[0]);
			int endMinute = Integer.parseInt(endTime[1]);
			boolean boven = false;

			if (currentHour > endHour) {
				boven = true;
			} else if (currentHour == endHour) {
				if (currentMinute > endMinute) {
					boven = true;
				}
			}

			if (!boven) {
				return performance;
			}
		}
		return null;
	}
	
	class ImagePanel extends JPanel {
		private static final long serialVersionUID = -1082565740868604867L;
		private BufferedImage image = null;
		private int x = this.getX() + 10, y = this.getX() + 10;

		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			
			this.setBackground(OptionsPanel.this.getBackground());
			
			if (image != null) {
				g2.drawImage(image, x + 50, y, image.getWidth(),image.getHeight(), null);
			} else {
				g2.setFont(font);
				g2.drawString("Geen Foto", x + 200, y + 30);
			}
		}

		public void setImage(BufferedImage image) {
			this.image = image;
		}
	}
}
