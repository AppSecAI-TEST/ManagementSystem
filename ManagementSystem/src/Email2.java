import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

class Email2 extends JPanel {
	Email2 self = this;
	CardLayout ca;
	JLabel subject = new JLabel("보낸메일함", JLabel.LEFT);
	private JPanel panel = new JPanel();
	private JPanel northPanel = new JPanel();
	private JPanel centerPanel = new JPanel();
	private JPanel southPanel = new JPanel();
	private JButton button = new JButton("삭제");
	JCheckBox chk = new JCheckBox();
	String header[] = { "", "No.", "제목", "받는사람", "날짜" };
	Object contents[][] = { { Boolean.FALSE, "1", "제목1", "홍길동", "0000-00-00" },
			{ Boolean.FALSE, "2", "제목2", "마이콜", "0000-00-00" } };
	private DefaultTableModel model;
	private JTable table = new JTable();
	private JScrollPane scroll;

	public void compInit() {
		model = new DefaultTableModel(header, 0) {
			public boolean isCellEditable(int row, int col) {
				if (col > 0)
					return false;
				else
					return true;
			}

			@Override
			public Class<?> getColumnClass(int columnIndex) {
				switch (columnIndex) {
				case 0:
					return Boolean.class;
				default:
					return String.class;
				}

			}

		};
		this.table = new JTable(model);
		setLayout(new BorderLayout());
		subject.setFont(new Font("HY견고딕", Font.BOLD, 20));
		this.add(subject, BorderLayout.NORTH);
		centerPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		centerPanel.setLayout(new BorderLayout(4, 5));
		this.table.getTableHeader().setResizingAllowed(false);
		this.table.getTableHeader().setReorderingAllowed(false);
		this.model.addRow(contents[0]);
		this.model.addRow(contents[1]);
		this.table.getColumnModel().getColumn(0).setPreferredWidth(15);
		this.table.getColumnModel().getColumn(1).setPreferredWidth(15);
		this.table.getColumnModel().getColumn(2).setPreferredWidth(450);
		this.table.getColumnModel().getColumn(3).setPreferredWidth(100);
		this.table.getColumnModel().getColumn(3).setPreferredWidth(150);
		this.scroll = new JScrollPane(table);
		centerPanel.add(scroll, BorderLayout.CENTER);
		this.southPanel.add(button);
		this.add(centerPanel, BorderLayout.CENTER);
		this.add(southPanel, BorderLayout.SOUTH);
		table.getModel().addTableModelListener(new CheckBoxModelListener());
	}

	public Email2() {
		this.setSize(800, 700);
		this.compInit();
		this.setVisible(true);
	}
}