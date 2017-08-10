import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class MemberInfo extends JFrame{
	MemberInfo self = this;
	private JPanel searchPanel = new JPanel(new BorderLayout());
	private JPanel searchSubPanel = new JPanel(new GridBagLayout());
	private JPanel addPanel = new JPanel(new BorderLayout());
	private JPanel westPanel = new JPanel(new BorderLayout());
	
	private JPanel deptPanel = new JPanel();
	private JPanel memberPanel = new JPanel();	
	
	private JList addList = new JList();
	private JLabel totalPerson = new JLabel("추가된 사람 : ");
	private JLabel dept = new JLabel("부서");
	private JLabel rank = new JLabel("직급");
	private JLabel name = new JLabel("이름");
	
	private JTextField deptText = new JTextField();
	private JTextField rankText = new JTextField();
	private JTextField nameText = new JTextField();
	
	private JButton searchButton = new JButton("검색");
	
	public void searchInit(){
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
	
	public void addListInit(){
		addPanel.add(totalPerson, BorderLayout.NORTH);
		DefaultListModel addModel = new DefaultListModel();
		
		/*DB완성시 나중에 삭제*/
		addModel.addElement("개발1팀대리김땡땡");
		addModel.addElement("개발2팀사원박땡땡");
		addModel.addElement("인사팀과장신땡땡");
		addModel.addElement("영업팀대리이땡땡");
	    /*삭제*/
		
		addList.setModel(addModel);
		addPanel.add(new JScrollPane(addList), BorderLayout.CENTER);
		addList.setSelectedIndex(0);//리스트첫번째 항목이 선택되게함		
		westPanel.add(addPanel, BorderLayout.SOUTH);
	}
	
	public void deptListInit(){
		
	}
	
	public void compInit(){
		this.setLayout(new BorderLayout());
		searchInit();
		addListInit();
		this.add(westPanel, BorderLayout.WEST);
	}
	
	public void eventInit(){
		
	}
	
	public MemberInfo(){
		this.setSize(900,500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.compInit();
		this.eventInit();
		this.setResizable(false);
		this.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		new MemberInfo();
	}

}
