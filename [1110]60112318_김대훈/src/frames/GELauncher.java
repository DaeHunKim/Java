package frames;

public class GELauncher{
	private static GEMainFrame frame;  // ���������� frame�� ���ۺ��� ������ �־�� �ϱ� ������ �ۿ� �����ϴ°� ����
	public static void main(String[] args) {
		frame = GEMainFrame.getInstance(); 
		frame.init();
	}
}
