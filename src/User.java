import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JPanel;

public class User implements ActionListener {
	private MainFrame mf;//MainFrame 참조
	private final static String SCISSORS = "가위";
	private final static String ROCK = "바위";
	private final static String PAPER = "보";
	private JButton btnS;//가위 바위 보 버튼
	private JButton btnR;
	private JButton btnP;
	private int num = -1;//사용자가 선택한 값을 저장하는 정수형 (변수 기본값 -1로 설정)
	public User(MainFrame main) {//MainFrame 객체를 인자로 받아서 해당 메인 프레임을 클래스 내에서 사용할 수 있도록 저장
		this.mf = main;
		
		//새로운 JPanel을 생성한 후, 가위, 바위, 보 버튼을 각각 생성하여 panelUser에 추가
		JPanel panelUser = new JPanel();
		btnS = new JButton(SCISSORS);
		btnS.addActionListener(this); //this: 이벤트를 현재 User 클래스가 처리하도록 등록  
		btnR = new JButton(ROCK);
		btnR.addActionListener(this);
		btnP = new JButton(PAPER);
		btnP.addActionListener(this);
		panelUser.add(btnS);
		panelUser.add(btnR);
		panelUser.add(btnP);
		this.mf.add(panelUser, BorderLayout.SOUTH); //위 버튼들이 포함된 패널은 하단에 배치
		
	}
	public void buttonOn() { //세 개의 버튼(가위, 바위, 보)을 화면에 보이도록 설정
		btnS.setVisible(true);
		btnR.setVisible(true);
		btnP.setVisible(true);
	}
	public void buttonOff() { //사용자가 버튼을 선택한 후에는 num 을 -1로 초기화하고 버튼들을 화면에서 숨김
		this.num = -1;
		btnS.setVisible(false);
		btnR.setVisible(false);
		btnP.setVisible(false);
	}
	public int getInput() { //사용자가 클릭한 버튼에 해당하는 값을 반환
		return num; //선택한 값 num 변수에 저장
	}
	@Override
	//this때문에 이 클래스에서 actionPerformed() 메서드를 정의해야 함
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals(SCISSORS)) { //가위 버튼 클릭시 "가위" 반환될 때
			this.num = 0; //num에는 0저장
		}
		else if(e.getActionCommand().equals(ROCK)) {
			this.num = 1;
		}
		else if(e.getActionCommand().equals(PAPER)) {
			this.num = 2;
		}
		System.out.println("num: "+this.num);
	}
}
