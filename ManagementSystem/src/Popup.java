import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.LineBorder;

import org.w3c.dom.css.RGBColor;
//TEST
public class Popup extends JFrame {
	Popup self = this;
	
	// 여기에 만든 클래스 추가 후 이벤트부분에 이름바꿔주기
	private Report report = new Report();
	private Board board = new Board();
	private Board1 board1 = new Board1();
	private Board2 board2 = new Board2();
	private Home home = new Home();
	private Email email = new Email();
	private Email1 email1 = new Email1();
	private Email2 email2 = new Email2();
	private Email3 email3 = new Email3();
	
	private CardLayout card = new CardLayout();	
	private JPopupMenu popup1 = new JPopupMenu();
	private JPopupMenu popup2 = new JPopupMenu();
	private JPopupMenu popup3 = new JPopupMenu();
	
	private JPanel menuPanel = new JPanel();
	
	private JPanel bottomPanel = new JPanel();	
	private JPanel contentPanel = new JPanel();
	
	private JButton b1 = new JButton("홈");
	private JButton b2 = new JButton("회원정보");
	private JButton b3 = new JButton("메일/팩스");
	private JButton b4 = new JButton("보고/결재");
	private JButton b5 = new JButton("게시판");
	private JButton b6 = new JButton("내 계정");
	private JButton minimize = new JButton(" ㅡ ");
	private JButton exit = new JButton(" X ");
	
	private JMenuItem post1 = new JMenuItem("공지사항");
	private JMenuItem post2 = new JMenuItem("자유게시판");
	private JMenuItem post3 = new JMenuItem("자료실");
	private JMenuItem post4 = new JMenuItem("로그아웃");
	private JMenuItem post5 = new JMenuItem("회원정보변경");
	private JMenuItem post6 = new JMenuItem("메일작성");
	private JMenuItem post7 = new JMenuItem("받은메일함");
	private JMenuItem post8 = new JMenuItem("보낸메일함");
	private JMenuItem post9 = new JMenuItem("휴지통");

	private Point compCoords = null;

	public Popup() {
		this.setSize(900,600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(Popup.EXIT_ON_CLOSE);
		this.setUndecorated(true); // 타이틀바제거
		this.compInit();
		this.eventInit();
		this.setResizable(false);
		this.setVisible(true);
	}

	public void menuBar() {
		add(popup1);
		add(popup2);
		add(popup3);
		
		popup1.add(post1);
		popup1.add(post2);
		popup1.add(post3);		
		popup2.add(post4);
		popup2.add(post5);
		popup3.add(post6);
		popup3.add(post7);
		popup3.add(post8);
		popup3.add(post9);
		
		menuPanel.setPreferredSize(new Dimension(900, 35));
		b1.setPreferredSize(new Dimension(50, 30));
		b2.setPreferredSize(new Dimension(130, 30));
		b3.setPreferredSize(new Dimension(130, 30));
		b4.setPreferredSize(new Dimension(130, 30));
		b5.setPreferredSize(new Dimension(130, 30));
		b6.setPreferredSize(new Dimension(130, 30));
//		minimize.setPreferredSize(new Dimension(50, 30));
//		exit.setPreferredSize(new Dimension(50, 30));		

		b1.setBorderPainted(false);
		b1.setContentAreaFilled(false);
		b2.setBorderPainted(false);
		b2.setContentAreaFilled(false);
		b3.setBorderPainted(false);
		b3.setContentAreaFilled(false);
		b4.setBorderPainted(false);
		b4.setContentAreaFilled(false);
		b5.setBorderPainted(false);
		b5.setContentAreaFilled(false);
		b6.setBorderPainted(false);
		b6.setContentAreaFilled(false);
		minimize.setBorderPainted(false);
		minimize.setContentAreaFilled(false);
		exit.setBorderPainted(false);
		exit.setContentAreaFilled(false);
		
		menuPanel.add(b1);
		menuPanel.add(b2);
		menuPanel.add(b3);
		menuPanel.add(b4);		
		menuPanel.add(b5);
		menuPanel.add(b6);
		//menuPanel.add(Box.createHorizontalStrut(100));
		menuPanel.add(minimize);
		menuPanel.add(exit);
	}

	public void compInit() {
		contentPanel.setLayout(card);
		contentPanel.setPreferredSize(new Dimension(900, 500));

		menuBar();
		this.add(menuPanel, BorderLayout.NORTH);
		this.add(contentPanel, BorderLayout.CENTER);

		menuPanel.setBackground(new Color(173, 233, 247));// menubar색상변경

	}

	public void eventInit() {
		b5.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e){
				popup1.show(e.getComponent(),30,30);
			}
		});
		
		b6.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e){
				popup2.show(e.getComponent(),30,30);
			}
		});
		
		/* 기능들 클래스 만들어지면 위에서 생성한 인스턴스의 이름을 report자리에 넣어주면된다.*/
		b1.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				contentPanel.add(home, "home"); 
				card.show(contentPanel, "home");
			}
		});
		
		b2.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				contentPanel.add(report, "info");
				card.show(contentPanel, "info");
			}
		});
		
		b3.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e){
				popup3.show(e.getComponent(),30,30);
			}
		});
		
		b4.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				contentPanel.add(report, "report");
				card.show(contentPanel, "report");
			}
		});		
		
		post1.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				contentPanel.add(board, "border1");
				card.show(contentPanel, "border1");
			}
		});	
		
		post2.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				contentPanel.add(board1, "border2");
				card.show(contentPanel, "border2");
			}
		});	
		
		post3.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				contentPanel.add(board2, "border3");
				card.show(contentPanel, "border3");
			}
		});	
		
		post4.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				contentPanel.add(report, "logout");
				card.show(contentPanel, "logout");
			}
		});	
		
		post5.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				contentPanel.add(report, "mychange");
				card.show(contentPanel, "mychange");
			}
		});	
		post6.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				contentPanel.add(email, "email");
				card.show(contentPanel, "email");
			}
		});	
		post7.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				contentPanel.add(email1, "email1");
				card.show(contentPanel, "email1");
			}
		});	
		post8.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				contentPanel.add(email2, "email2");
				card.show(contentPanel, "email2");
			}
		});
		post9.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				contentPanel.add(email3, "email3");
				card.show(contentPanel, "email3");
			}
		});

		exit.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		
		minimize.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				self.setState(JFrame.ICONIFIED);
			}
		});
		
		compCoords = null;

		menuPanel.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				compCoords = null;
			}

			public void mousePressed(MouseEvent e) {
				compCoords = e.getPoint();
			}
		});

		menuPanel.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				Point currCoords = e.getLocationOnScreen();
				self.setLocation(currCoords.x - compCoords.x, currCoords.y - compCoords.y);
			}
		});
	}

	public static void main(String[] args) {
		new Popup();
	}

}
