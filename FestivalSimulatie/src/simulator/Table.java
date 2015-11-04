package simulator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public class Table {
	private JTable table;
	private DefaultTableModel model;
	private Tile tile;
	private JButton button = new JButton();

	public Table(Tile tile) {
		setModel();
		this.tile = tile;
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});;
	}

	private void setModel() {
		table = new JTable(new AbstractTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public int getColumnCount() {
				return 2;
			}

			@Override
			public int getRowCount() {
				if (tile.getPaths().size() == 0)
					return 0;
				else
					return tile.getPaths().size();
			}

			@Override
			public Object getValueAt(int row, int col) {
				if (col == 0) {
					return tile.getPaths().get(row).getDestination().getName();
				} else if (col == 1) {
					return tile.getPaths().get(row).getTarget().getName();
				}
				return null;
			}

			@Override
			public String getColumnName(int col) {
				if (col == 0) {
					return "Destination:";
				} else if (col == 1) {
					return "Target:";
				}
				return null;
			}
		});
		table.setCellSelectionEnabled(true);
	}

	public void addRow(Tile destination, Tile target) {
		model.addRow(new Object[] { destination, target });
	}

	public void removeRow(int row) {
		model.removeRow(row);
	}

	public JTable getJTable() {
		return table;
	}
}
