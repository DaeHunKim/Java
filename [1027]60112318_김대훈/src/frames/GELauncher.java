package frames;

public class GELauncher{
	private static GEMainFrame frame;
	public static void main(String[] args) {
		frame = GEMainFrame.getInstance();  // 개념적으로 frame은 시작부터 끝까지 있어야 하기 때문에 밖에 선언하는게 맞음
		frame.init();
	}
}
