import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Screen extends Canvas {

	private BufferedImage img_scissors;
	private BufferedImage img_rock;
	private BufferedImage img_paper;
	private int count = 0; 
	private int user=0;
	//타이머와 타이머 작업을 관리하는 객체
	private Timer timer = null;
	private TimerTask timerTask;
	private MainFrame mf;
	private boolean checkResult = false; //결과 확인 변수 
	
	public Screen(MainFrame main) {//메인프레임 객체를 받음
		this.mf = main;
		File scissors_file = new File("res/scissors.png");//이미지 파일 읽어옴
		File rock_file = new File("res/rock.png");
		File paper_file = new File("res/paper.png");
		try {
			//파일에서 이미지를 읽어와서 img_scissors, img_rock, img_paper 변수에 저장
			//가위, 바위, 보를 시각적으로 보여주는 이미지를 로드하는 과정
			this.img_scissors = ImageIO.read(scissors_file);
			this.img_rock = ImageIO.read(rock_file);
			this.img_paper = ImageIO.read(paper_file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		mf.getUser().buttonOff();//게임이 시작하기 전에는 사용자가 버튼을 누를 수 없도록 설정
	}
	public void start() {
		checkResult = false; //게임이 시작되면 결과를 아직 확인하지 않은 상태로 설정
		mf.getUser().buttonOn();// 사용자 버튼 활성화
		mf.getRef().playStart();// 게임 시작
		count = 0;
		user=0; 
		timer = new Timer();
		timerTask = new TimerTask() {
			@Override
			public void run() {
				repaint(); //화면 다시 그림
				if(!checkResult){//게임이 끝나지 않았을때 
					count++;//카운트 계속 증가 -> 이미지 번갈아 가며 출력
					user++;
				}
			}
		};
		timer.schedule(timerTask, 0, 500); //0.5초(500밀리초)마다 repaint() 메서드가 호출되어 화면을 갱신, 카운트 증가
	}
	
	public void stop() {
		if(this.mf.getUser().getInput() == -1) { //사용자가 아직 가위, 바위, 보를 선택하지 않았을 때의 조건
			JOptionPane.showMessageDialog(null,"가위 바위 보중 하나를 누루세요");//메세지 박스 뛰움
			return;
		}
		int com = mf.getRef().playing(mf.getOutput()); // 컴퓨터의 선택 가져옴
		int User= mf.getUser().getInput(); //User 클래스의 getInput()메서드 값 (사용자 값) 받아옴
		checkResult = true;
		count = com; // 컴퓨터의 선택 결과를 count에 저장
		user=User;
		repaint();
		//mf.getRef().playStop(mf.getOutput());
		if(timer != null)
			timer.cancel();//타이머를 종료하여 더 이상 화면 갱신 x
		mf.getUser().buttonOff();//사용자가 가위, 바위, 보 버튼을 클릭할 수 없도록 비활성화
	}
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		g.drawString("컴퓨터", 0, 100); 
		switch(count % 3) {
		case 0:
			g.drawImage(img_scissors, 0, 0, this); //사용자 & 컴퓨터 이미지쪽 위치 수정
			break;
		case 1:
			g.drawImage(img_rock, 0, 0, this);
			break;
		case 2:
			g.drawImage(img_paper, 0, 0, this);
			break;
		}
	
		g.drawString("사용자", 100, 100);
		switch(user % 3) {//사용자 선택에 따른 이미지를 출력하도록 함
		case 0:
			g.drawImage(img_scissors, 100, 0, this);
			break;
		case 1:
			g.drawImage(img_rock, 100, 0, this);
			break;
		case 2:
			g.drawImage(img_paper, 100, 0, this);
			break;
		}
		System.out.println("paint");
	}

}
