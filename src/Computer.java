//213601 김보광
public class Computer {
	public int getInput() {
		int num = (int) (Math.random() * 10) % 3;
		// Math.random(): 0~1미만의 실수 생성
		//(int): int로 형변환 소수점 이하를 버리고 정수만 남김
		//결과: 0~9까지 숫자를 생성하고 3으로 나눈나머지 0,1,2 만 나옴
		return num;
	}
}
