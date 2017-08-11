import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

public class MemberInfo extends JPanel {
	MemberInfo self = this;
	private DBManager db = new DBManager();

	private int addPersonnum = 0;
	private int tmp = 0;
	private JPanel searchPanel = new JPanel(new BorderLayout());
	private JPanel searchSubPanel = new JPanel(new GridBagLayout());
	private JPanel addPanel = new JPanel(new BorderLayout());
	private JPanel westPanel = new JPanel(new BorderLayout());

	private JPanel deptPanel = new JPanel(new BorderLayout());
	private JPanel memberPanel = new JPanel(new BorderLayout());

	private JList addList = new JList();
	private JLabel totalPerson = new JLabel("추가된 사람 : ");
	private JLabel dept = new JLabel("부서");
	private JLabel rank = new JLabel("직급");
	private JLabel name = new JLabel("이름");

	private JTextField deptText = new JTextField();
	private JTextField rankText = new JTextField();
	private JTextField nameText = new JTextField();

	private DefaultListModel addModel;
	private JButton searchButton = new JButton("검색");
	private JButton sendButton = new JButton("보내기");

	// deptPanel 설정
	private String[] columnNames = { "부서", "부서선택" };
	private Object[][] data = { { "영업부", false }, { "인사부", false }, { "마케팅부", false }, { "개발부", false } };
	/* 나중에 data 이부분도 db에서 처리해야함 (부서종류 뽑아오기) */

	private JTable table;
	private DefaultTableModel model;
	private JScrollPane scroll;
	private JCheckBox box = new JCheckBox();

	// memeberPanel 설정
	private String[] columnNames2 = { "부서", "직급", "이름", "추가" };
	private JTable table2;
	private DefaultTableModel model2;
	private JScrollPane scroll2;
	private JCheckBox box2 = new JCheckBox();
	private JTableHeader header;

	public void searchInit() {
		deptText.setPreferredSize(new Dimension(100, 30));
		rankText.setPreferredSize(new Dimension(100, 30));
		nameText.setPreferredSize(new Dimension(100, 30));
		searchButton.setPreferredSize(new Dimension(100, 30));

		GridBagConstraints c = new GridBagConstraints();

		c.gridy = 1;
		c.gridx = 1;
		searchSubPanel.add(dept, c);

		c.gridy = 1;
		c.gridx = 2;
		searchSubPanel.add(deptText, c);

		c.gridy = 2;
		c.gridx = 1;
		searchSubPanel.add(rank, c);

		c.gridy = 2;
		c.gridx = 2;
		searchSubPanel.add(rankText, c);

		c.gridy = 3;
		c.gridx = 1;
		searchSubPanel.add(name, c);

		c.gridy = 3;
		c.gridx = 2;
		searchSubPanel.add(nameText, c);

		searchPanel.add(searchSubPanel, BorderLayout.NORTH);
		searchPanel.add(searchButton, BorderLayout.SOUTH);
		westPanel.add(searchPanel, BorderLayout.NORTH);
	}

	public void addListInit() {
		addPanel.add(totalPerson, BorderLayout.NORTH);
		addModel = new DefaultListModel();

		addList.setModel(addModel);
		addPanel.add(new JScrollPane(addList), BorderLayout.CENTER);
		westPanel.add(addPanel, BorderLayout.CENTER);
		westPanel.add(sendButton, BorderLayout.SOUTH);
		addList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index;
				if (e.getClickCount() == 2) { // 더블클릭시
					String s = (String) addList.getSelectedValue();
					StringTokenizer tokens = new StringTokenizer(s);
					String dept = tokens.nextToken(", ");
					String rank = tokens.nextToken(", ");
					String name = tokens.nextToken(", ");
					index = addList.locationToIndex(e.getPoint());
					addModel.remove(index);
					totalPerson.setText("추가된 사람 : " + addModel.getSize());
					for (int i = 0; i < model2.getRowCount(); i++) {
						boolean result = model2.getValueAt(i, 0).equals(dept) && model2.getValueAt(i, 1).equals(rank)
								&& model2.getValueAt(i, 2).equals(name);
						if (result) {
							model2.setValueAt(false, i, 3);
							model2.fireTableDataChanged();
						}
					}
				}
			}
		});
	}

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

		table2.getColumnModel().getColumn(3).setHeaderRenderer(new HeaderRenderer(table2.getTableHeader(), model2));

		System.out.println("테이블 update");
		model2.fireTableDataChanged();
		memberPanel.add(scroll2, BorderLayout.CENTER);
	}

	private void memberAddInit(String checkDept) {
		try {
			// model2.setRowCount(0); //table에서 row전체삭제
			for (Person m : db.selectData(checkDept)) {
				model2.addRow(new Object[] { m.getDept(), m.getRank(), m.getName(), m.getCheckbox() });
			}
		} catch (Exception e) {

		}
	}

	private void memberRemoveInit(String checkDept) {
		try {
			int count = db.selectDeptPerson(checkDept);
			int startRow = model2.getRowCount() - 1;

			while (count > 0) {
				model2.removeRow(startRow);
				startRow--;
				count--;
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

	class HeaderRenderer implements TableCellRenderer {
		private final JCheckBox check = new JCheckBox();
		private TableModel model2;

		public HeaderRenderer(JTableHeader header, TableModel model2) {
			this.model2 = model2;
			check.setOpaque(false);
			check.setFont(header.getFont());
			header.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					JTable table = ((JTableHeader) e.getSource()).getTable();
					TableColumnModel columnModel = table.getColumnModel();
					int viewColumn = columnModel.getColumnIndexAtX(e.getX());
					int modelColumn = table.convertColumnIndexToModel(viewColumn);
					if (modelColumn == 3) {
						check.setSelected(!check.isSelected()); // 지금선택의 반대로
																// true/false변경
						TableModel m = table.getModel();
						Boolean f = check.isSelected();
						for (int i = 0; i < m.getRowCount(); i++) {
							m.setValueAt(f, i, 3);
						}
						((JTableHeader) e.getSource()).repaint();
					}
				}
			});
		}

		@Override
		public Component getTableCellRendererComponent(JTable tbl, Object val, boolean isS, boolean hasF, int row,
				int col) {
			TableCellRenderer r = tbl.getTableHeader().getDefaultRenderer();
			JLabel l = (JLabel) r.getTableCellRendererComponent(tbl, val, isS, hasF, row, col);
			l.setIcon(new CheckBoxIcon(check));
			return l;
		}
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
					if (tmp == 1) {
						deptText.setText("");
						rankText.setText("");
						nameText.setText("");
						model2.setRowCount(0);
						tmp = 0;
					}
					memberAddInit(deptName);
				} else {
					memberRemoveInit(deptName);
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
				String s = deptName + ", " + rankName + ", " + nameName;
				System.out.println(s);

				if (checked) {
					addModel.addElement(s);
					totalPerson.setText("추가된 사람 : " + addModel.getSize());
					addList.ensureIndexIsVisible(addModel.getSize());
				} else {
					addModel.removeElement(s);
					totalPerson.setText("추가된 사람 : " + addModel.getSize());
				}
			}
		}
	}

	public void compInit() {
		this.setLayout(new BorderLayout());
		westPanel.setPreferredSize(new Dimension(200, 500));
		searchInit();
		addListInit();
		memberInit();
		deptInit();

		this.add(westPanel, BorderLayout.WEST);
		this.add(deptPanel, BorderLayout.CENTER);
		this.add(memberPanel, BorderLayout.EAST);
	}

	public void eventInit() {
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (deptText.getText().equals("") && rankText.getText().equals("") && nameText.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "검색조건을 넣어주세요");
				} else {
					String msg = "";
					if (!deptText.getText().equals(""))
						msg += "and dept like '%" + deptText.getText() + "%'";
					if (!rankText.getText().equals(""))
						msg += "and rank like '%" + rankText.getText() + "%'";
					if (!nameText.getText().equals(""))
						msg += "and name like '%" + nameText.getText() + "%'";

					msg += "";
					try {
						for (int i = 0; i < model.getRowCount(); i++) {
							model.setValueAt(false, i, 1);
						}
						model2.setRowCount(0); // table에서 row전체삭제
						for (Person m : db.searchData(msg)) {
							model2.addRow(new Object[] { m.getDept(), m.getRank(), m.getName(), m.getCheckbox() });
						}
						tmp = 1;

					} catch (Exception e1) {
					}

				}

			}
		});

	}

	public MemberInfo() {
		this.setSize(900, 500);
		this.compInit();
		this.eventInit();
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new MemberInfo();
	}
}
