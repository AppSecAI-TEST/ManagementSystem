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
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

public class Exam2 extends JFrame {
	private JPanel deptPanel = new JPanel(new BorderLayout());
	private JPanel memberPanel = new JPanel(new BorderLayout());
	private String[] columnNames = { "�μ�", "�μ���ü����" };
	private Object[][] data = { { "������", false }, { "�λ��", false }, { "�����ú�", false }, { "���ߺ�", false } };
	private JTable table;
	private DefaultTableModel model;
	private JScrollPane scroll;
	private JCheckBox box = new JCheckBox();

	private String[] columnNames2 = { "�μ�", "����", "�̸�", "����" };
	private JTable table2;
	private DefaultTableModel model2;
	private JScrollPane scroll2;
	private JCheckBox box2 = new JCheckBox();
	private JTableHeader header;

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

		table2.getColumnModel().getColumn(3).setHeaderRenderer(new HeaderRenderer(table2.getTableHeader(), model2));

		System.out.println("���̺� update");
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

	class HeaderRenderer implements TableCellRenderer{
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
		                check.setSelected(!check.isSelected()); //���ݼ����� �ݴ�� true/false����
		                TableModel m = table.getModel();
		                Boolean f = check.isSelected();
		                for (int i = 0; i < m.getRowCount(); i++) {
		                    m.setValueAt(f, i, 3);     
		                }
		                if(check.isSelected()==true){
		                	System.out.println("���õǾ����� true");
		                	String deptName = (String) model.getValueAt(0, 0);
		                	System.out.println("deptName 0,0"+deptName);
		                	deptName = (String) model.getValueAt(1, 0);
		                	System.out.println("deptName 1,0"+deptName);
		                	// �߰�����Ʈ�� �߰��ϴ� �ҽ��ڵ� �ؾ���
		                	
		                }else{
		                	System.out.println("���õǾ��������� false");
		                	String deptName = (String) model.getValueAt(0, 0);
		                	System.out.println("deptName 0,0"+deptName);
		                	deptName = (String) model.getValueAt(1, 0);
		                	System.out.println("deptName 1,0"+deptName);
		                	// �߰�����Ʈ���� �����ϴ� �ҽ��ڵ��ؾ���
		                	
		                }
		                ((JTableHeader) e.getSource()).repaint();
		            }
		        }
		    });
		
	}
		@Override
		public Component getTableCellRendererComponent(
		        JTable tbl, Object val, boolean isS, boolean hasF, int row, int col) {
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
			
			if(column == 3) {
				TableModel model = (TableModel) e.getSource();
				String deptName = (String) model.getValueAt(row, 0);
				String rankName = (String) model.getValueAt(row, 1);
				String nameName = (String) model.getValueAt(row, 2);
				Boolean checked = (Boolean) model.getValueAt(row, column);

				if (checked) {
					String s = deptName + ", " + rankName + ", " + nameName;
					System.out.println(s);
					// �߰�����Ʈ�� �߰��ϴ� �ҽ��ڵ� �ؾ���
					
				} else{
					// �߰�����Ʈ�� �����ϴ� �ҽ��ڵ� �ؾ���
					
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
