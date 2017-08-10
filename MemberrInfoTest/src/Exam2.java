import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Exam2 extends JFrame {
	private JPanel deptPanel = new JPanel(new BorderLayout());
	private JPanel memberPanel = new JPanel(new BorderLayout());
	private String[] columnNames = { "부서", "부서전체전송" };
	private Object[][] data = { { "영업부", false }, { "인사부", false }, { "마케팅부", false }, { "개발부", false } };
	private JTable table;
	private DefaultTableModel model;
	private JScrollPane scroll;
	private JCheckBox box = new JCheckBox();

	private String[] columnNames2 = { "부서", "직급", "이름", "선택" };
	private JTable table2;
	private DefaultTableModel model2;
	private JScrollPane scroll2;
	private JCheckBox box2 = new JCheckBox();

	private DBManager db = new DBManager();

	private void memberInit() {
		model2 = new DefaultTableModel(columnNames2, 0) {
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				switch (columnIndex) {
				case 3:
					return Boolean.class;
				default:
					return String.class;
				}
			}
		};
		table2 = new JTable(model2);
		scroll2 = new JScrollPane(table2);

		table2.getModel().addTableModelListener(new CheckBoxModelListener2());

		model2.fireTableDataChanged();
		memberPanel.add(scroll2, BorderLayout.CENTER);
	}

	private void addInit(String checkDept) {
		try {
			model2.setRowCount(0);
			for (Member m : db.selectData(checkDept)) {
				model2.addRow(new Object[] { m.getDept(), m.getRank(), m.getName(), m.getCheckbox() });
			}
		} catch (Exception e) {

		}
	}

	private void deptInit() {
		model = new DefaultTableModel(columnNames, 0) {
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				switch (columnIndex) {
				case 1:
					return Boolean.class;
				default:
					return String.class;
				}
			}
		};
		table = new JTable(model);
		scroll = new JScrollPane(table);

		for (Object[] o : data)
			model.addRow(o);
		table.getModel().addTableModelListener(new CheckBoxModelListener1());
		deptPanel.add(scroll, BorderLayout.CENTER);

	}

	class CheckBoxModelListener1 implements TableModelListener {
		public boolean isCellEditable(int i, int c) {
			if (c != 1)
				return false;
			else
				return true;
		}

		@Override
		public void tableChanged(TableModelEvent e) {
			int row = e.getFirstRow();
			int column = e.getColumn();
			if (column == 1) {
				TableModel model = (TableModel) e.getSource();
				String deptName = (String) model.getValueAt(row, 0);
				Boolean checked = (Boolean) model.getValueAt(row, column);

				if (checked) {
					addInit(deptName);
				}
			}
		}
	}

	class CheckBoxModelListener2 implements TableModelListener {

		public boolean isCellEditable(int i, int c) {
			if (c != 3)
				return false;
			else
				return true;
		}

		@Override
		public void tableChanged(TableModelEvent e) {
			int row = e.getFirstRow();
			int column = e.getColumn();
			if (column == 3) {
				TableModel model = (TableModel) e.getSource();
				String deptName = (String) model.getValueAt(row, 0);
				String rankName = (String) model.getValueAt(row, 1);
				String nameName = (String) model.getValueAt(row, 2);
				Boolean checked = (Boolean) model.getValueAt(row, column);

				if (checked) {
					String s = deptName + ", " + rankName + ", " + nameName;
					System.out.println(s);
				} else {
				}

			}
		}

	}

	private void compInit() {
		this.setLayout(new BorderLayout());
		memberInit();
		deptInit();

		this.add(deptPanel, BorderLayout.WEST);
		this.add(memberPanel, BorderLayout.EAST);

	}

	private void eventInit() {

	}

	public Exam2() {
		this.setSize(900, 200);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.compInit();
		this.eventInit();
		this.setResizable(false);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new Exam2();
	}

}
