import javax.swing.JLabel;

public class Referee {
	private Computer com;
	private User user;
	private int win;
	private int lose;
	private int draw;
	private int step;
	public Referee(Computer com, User user) {//컴퓨터와 사용자 객체를 받음
		this.com = com;
		this.user = user;
	}
	public void playStart() {//게임 시작시 호출 -> 시작시 모두 0으로 초기화
		this.win = 0;
		this.lose = 0;
		this.draw = 0;
		this.step = 0;
	}
	
	
	public int playing(JLabel output) {//JLabel output: 게임 결과를 화면에 표시하기 위해 사용되는 GUI 컴포넌트
		this.step++; //게임이 진행될 때마다 라운드를 하나씩 증가
		int com = this.com.getInput(); //computer 객체의 getInput 메서드 실행값 저장(컴퓨터 선택 값)
		int user = this.user.getInput();
		judgment(com, user);//컴퓨터와 사용자의 선택을 비교하여 누가 이겼는지 판단
		resultPrint(com, user, output);//결과 출력
		return com; //컴퓨터 선택 반환
		
	}
	
	//게임이 끝났을 때 호출
	public void playStop(JLabel output) {
		System.out.println("======최종 결과=======");
		//result(output);
	}
	
	private void result(int com, int user, JLabel output) {//resultPrint로 부터 호출
		String text = "컴퓨터: "+change(com)+", 사용자: "+change(user)+", 승리: "+
				this.win+", 패배: "+this.lose+", 비김: "+this.draw;
		System.out.println(text);
		output.setText(text);//해당 문자열을 JLabel 객체에 설정하여 화면에 표시
	}
	private void judgment(int com, int user) {
		System.out.println("컴: "+com+", 나: "+user);
		if(user-com == 0) {
			System.out.println("비김");
			this.draw++; //이기면 변수값 1증가 
		}
		else if( (user-com == 1) || (user-com == -2) ) {
			System.out.println("승리");
			this.win++;
		}
		else {
			System.out.println("패배");
			this.lose++;
		}
	}
	private void resultPrint(int com, int user, JLabel output) {
		System.out.println("횟수: "+this.step+"/10");
		result(com, user, output);
	}
	
	public String change(int number) { //"가위 바위 보" 를 숫자 대신 문자로 출력해 주는 함수  
		if(number==0) {
			return "가위";
		}
		else if(number==1) {
			return "바위";
		}
		else {
			return "보";
		}
	}
}
