import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
//제발!!!
class Board extends JPanel {
	Board self = this;
	CardLayout ca;
	JLabel subject = new JLabel("공지사항", JLabel.LEFT);
	private JPanel panel = new JPanel();
	private JPanel northPanel = new JPanel();
	private JPanel centerPanel = new JPanel();
	private JPanel southPanel = new JPanel();
	//private JLabel label = new JLabel("공지사항");
	private JComboBox combo = new JComboBox();
	private JTextField  search = new JTextField();
	private JButton button = new JButton("검색");
	String header[] = { "No.", "제목", "작성자", "날짜", "조회수" };
	Object contents[][] = { { "1", "제목1", "홍길동", "날짜1", "조회수1" }, { "2", "제목2", "마이콜", "날짜2", "조회수2" } };
	private DefaultTableModel model;
	private JTable table = new JTable();
	private JScrollPane scroll;
	
	
	public void compInit() {
		//setLayout(ca = new CardLayout());
		model = new DefaultTableModel(header, 0){
			public boolean isCellEditable(int row, int col){
				return false;
			}
		};
		this.table = new JTable(model);
		setLayout(new BorderLayout());
		subject.setFont(new Font("HY견고딕", Font.BOLD, 20));
		this.add(subject,BorderLayout.NORTH);
		centerPanel.setBorder(BorderFactory.createEmptyBorder(100, 69, 150, 40));
		centerPanel.setLayout(new BorderLayout(5, 5));
		//centerPanel.add(label, BorderLayout.WEST);
		this.table.getTableHeader().setResizingAllowed(false);
		this.table.getTableHeader().setReorderingAllowed(false);
		this.model.addRow(contents[0]);
		this.model.addRow(contents[1]);
		this.table.getColumnModel().getColumn(0).setPreferredWidth(50);
		this.table.getColumnModel().getColumn(1).setPreferredWidth(300);
		this.table.getColumnModel().getColumn(2).setPreferredWidth(100);
		this.table.getColumnModel().getColumn(3).setPreferredWidth(100);
		this.scroll = new JScrollPane(table);
		centerPanel.add(scroll, BorderLayout.CENTER);
		combo.addItem("작성자");
		combo.addItem("제목");
		this.search.setPreferredSize(new Dimension(200,25));
		this.southPanel.add(combo);
		this.southPanel.add(search);
		this.southPanel.add(button);
		this.add(centerPanel, BorderLayout.CENTER);
		this.add(southPanel, BorderLayout.SOUTH);
	}

	public Board() {
		this.setSize(800, 700);
		this.compInit();
		//this.setResizable(false);
		this.setVisible(true);
	}
}