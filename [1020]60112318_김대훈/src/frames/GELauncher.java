package frames;

public class GELauncher{
	private static GEMainFrame frame;
	public static void main(String[] args) {
		frame = GEMainFrame.getInstance();  // ���������� frame�� ���ۺ��� ������ �־�� �ϱ� ������ �ۿ� �����ϴ°� ����
		frame.init();
	}
}
