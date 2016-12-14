package constants;

import javax.swing.JMenu;

import menus.GEMenuColor;
import menus.GEMenuEdit;
import menus.GEMenuFile;

public class GEConstants {
	//ToolBarButtons
	public enum EButtons{
		rectangle("Rectangle.png", "Rectangle_Selected.png"),
		ellipse("Ellipse.png", "Ellipse_Selected.png"),
		line("Line.png", "Line_Selected.png"),
		heart("Heart.png", "Heart_Selected.png"),
		polygon("Polygon.png", "Polygon_Selected.png"),
		text("Text.png", "Text_Selected.png");
		
		private String buttonImage, selectedbuttonImage;
			
		private EButtons(String buttonImage, String selectedbuttonImage){
			this.buttonImage = buttonImage;
			this.selectedbuttonImage = selectedbuttonImage;
		}
		public String getButtonImage() {return buttonImage;}
		public String getSelectedbuttonImage() {return selectedbuttonImage;	}
	}
	//GEMenu
	public enum EMenus{
		file("File", new GEMenuFile()),
		edit("Edit", new GEMenuEdit()),
		color("Color", new GEMenuColor());
		
		private String menuName;
		private JMenu menu;
		
		private EMenus(String menuName,JMenu menu){
			this.menuName = menuName;
			this.menu = menu;
		}
		public String getMenuName() {return menuName;}
		public JMenu getMenu() {return menu;}
	}
	// GEMainFrame
	public static final int WIDTH_MAINFRAME = 400;
	public static final int HEIGHT_MAINFRAME = 600;
	public static final String TITLE_MAINFRAME = "과제5_Enum 활용";
	// GEMenu	
	public static final String TITLE_FILEMENU = "파일";
	public static final String TITLE_EDITMENU = "편집";
	public static final String TITLE_COLORMENU = "컬러";
	// GEMenuItems
	public static enum EFileMenuItems {New, Open, Save, Save_as, Exit};
	public static enum EEditMenuItems {Undo, Redo, Cut, Copy, Paste, Delete, Select_all, Deselect_all};
	public static enum EColorMenuItems {Line_color, Fill_color};
	// GEToolbarShape
	public static final String TITLE_SHAPETOOLBAR = "Shape Tools";
	public static int WIDTH_SHAPETOOLBAR = 400;
	public static int HEIGHT_SHAPETOOLBAR = 200;
	public static enum EToolBarButtons{Ellipse, Rectangle, Line, Polygon, Heart, Text};
	public static final String IMG_URL = "rsc/";
	public static final String SUFFIX_TOOLBAR_BTN = ".png";
	public static final String SUFFIX_TOOLBAR_BTN_SLT = "_Selected.png";
}
