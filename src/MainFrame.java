import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainFrame extends JFrame implements ActionListener { //JFrmae 상속 받음 -> GUI 창을 만드는 클래스 
	private final String startLbl = "시작"; //시작 정지 버튼
	private final String stopLbl = "정지";
	private Screen screen;
	private User user;
	private Computer com;
	private Referee ref;
	private JLabel output;
	public MainFrame() {
		this.setTitle("SRP Game !!!");
		this.setSize(800, 600); //창 크기 설정 
		this.setLayout(new BorderLayout()); //창 레이아웃 설정 
		
		this.com = new Computer();//컴퓨터 객체를 생성 
		this.user = new User(this);//사용자 객체를 생성 & user에 MainFrame 객체를 전달
		this.ref = new Referee(com, user);//Referee 객체를 생성 -> 승패 판단
		
		this.screen = new Screen(this); //게임 화면 초기화 역할
		this.add(screen, BorderLayout.CENTER);
		
		//버튼 생성 & 삽입 
		JPanel panel = new JPanel();
		JButton btn_start = new JButton(startLbl);
		btn_start.addActionListener(this);
		JButton btn_stop = new JButton(stopLbl);
		btn_stop.addActionListener(this);
		panel.add(btn_start);
		panel.add(btn_stop);
		
		this.add(panel, BorderLayout.WEST);
		
		//결과 추가 
		this.output = new JLabel("결과");
		this.add(output, BorderLayout.NORTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //사용자가 창을 닫으면 프로그램이 종료
		
		this.setVisible(true);//창을 화면에 보이게 설정
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals(startLbl)) {
			screen.start(); //screen 클래스의 start 메소드 동작 
		}
		else if(e.getActionCommand().equals(stopLbl)) {
			screen.stop();
		}
	}

	public User getUser() { //객체 반환용
		return user; 
	}

	public Computer getCom() {
		return com;
	}

	public Referee getRef() {
		return ref;
	}

	public JLabel getOutput() {
		return output;
	}
}
