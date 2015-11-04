package simulator;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class PopUp extends JDialog {
	private static final long serialVersionUID = 1L;
	private JFrame frame;

	private JPanel content;
	private JPanel north;
	private JPanel south;

	private Tile tile;
	private List<Tile> tiles;

	private Table table;
	private JTable jtable;

	public PopUp(JFrame frame, Tile tile, List<Tile> tiles) {
		super(frame, tile.getName());
		this.tile = tile;
		this.tiles = tiles;
		this.frame = frame;
		table = new Table(tile);
		jtable = table.getJTable();
		setModalityType(ModalityType.APPLICATION_MODAL);
		setLocationRelativeTo(null);

		initWindow();
		fillWindow(frame);
		initMisc();

		setSize(400, 400);
		setVisible(true);
	}

	private void initWindow() {
		content = new JPanel(new BorderLayout());
		north = new JPanel(new GridLayout(3, 2));
		setContentPane(content);
		content.add(north, BorderLayout.NORTH);
	}

	private void fillWindow(JFrame frame) {
		JLabel name = new JLabel(" Name:");
		JTextField invlName = new JTextField(tile.getName());
		JButton button = new JButton("Delete " + tile.getName());
		JCheckBox building = new JCheckBox("Building", tile.isBuilding());
		JCheckBox entrance = new JCheckBox("Entrance", tile.isEntrance());
		JCheckBox exit = new JCheckBox("Exit", tile.isExit());

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text =  "do you want do delete the tile: " + tile.getName();
				if(JOptionPane.showConfirmDialog(frame,text) == 0){
					for(int i = 0 ; i < tiles.size() ; i++)
						if(tiles.get(i).equals(tile))
							tiles.remove(i);
					dispose();
					repaint();
				}
			}
		});
		
		invlName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tile.setName(invlName.getText());
				dispose();
			}
		});

		building.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tile.setBuilding(building.isSelected());
			}
		});

		entrance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tile.setEntrance(entrance.isSelected());
			}
		});

		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tile.setExit(exit.isSelected());
			}
		});

		north.add(name);
		north.add(invlName);
		north.add(button);
		north.add(building);
		north.add(entrance);
		north.add(exit);
	}

	private void initMisc() {

		JButton add = new JButton("add");

		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addPath();
			}
		});

		JButton remove = new JButton("remove");

		remove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removePath();
			}
		});

		JScrollPane scrollPane = new JScrollPane(jtable);

		south = new JPanel(new GridLayout(1, 2));
		south.add(add);
		south.add(remove);
		content.add(south, BorderLayout.SOUTH);
		content.add(scrollPane, BorderLayout.CENTER);
	}

	protected void removePath() {
		if(jtable.getSelectedRow() > -1){
			tile.getPaths().remove(jtable.getSelectedRow());
			dispose();
			new PopUp(frame, tile, tiles);
		}
	}

	protected void addPath() {
		JDialog dialog = new JDialog(frame, "Message", true);

		dialog.setLayout(new GridLayout(2, 1));
		JPanel messagePane = new JPanel(new GridLayout(2, 2));
		JPanel buttonPane = new JPanel(new GridLayout(1, 1));

		messagePane.add(new JLabel("Destination: "));

		JComboBox<Tile> boxDestination = new JComboBox<Tile>();
		for (int i = 0; i < tiles.size(); i++) {
			if (tiles.get(i).isBuilding() || tiles.get(i).isExit()) {
				boxDestination.addItem(tiles.get(i));
			}
		}
		messagePane.add(boxDestination);

		messagePane.add(new JLabel("Target: "));

		JComboBox<Tile> boxTarget = new JComboBox<Tile>();
		for (int i = 0; i < tiles.size(); i++) {
			if (!(tiles.get(i).isEntrance())) {
				boxTarget.addItem(tiles.get(i));
			}
		}
		messagePane.add(boxTarget);

		JButton button = new JButton("OK");
		buttonPane.add(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tile destination = (Tile) boxDestination.getSelectedItem();
				Tile target = (Tile) boxTarget.getSelectedItem();
				if (target != null && destination != null) {
					tile.addPath(destination, target);
					dialog.dispose();
					dispose();
					new PopUp(frame, tile, tiles);
				} else {
					JOptionPane.showMessageDialog(dialog,
							"no target or destination selected");
					dialog.dispose();
				}
			}
		});

		dialog.getContentPane().add(messagePane);
		dialog.getContentPane().add(button);
		dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		dialog.setLocationRelativeTo(null);
		dialog.pack();
		dialog.setVisible(true);
	}
}