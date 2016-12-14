package constants;

import java.awt.Color;

import javax.swing.JMenu;

import menus.GEMenuColor;
import menus.GEMenuEdit;
import menus.GEMenuFile;
import shapes.GEEllipse;
import shapes.GELine;
import shapes.GEPolygon;
import shapes.GERectangle;
import shapes.GEShape;

public class GEConstants {
	//ToolBarButtons
	public enum EButtons{
		rectangle("Rectangle.png", "Rectangle_Selected.png", new GERectangle()),
		ellipse("Ellipse.png", "Ellipse_Selected.png", new GEEllipse()),
		line("Line.png", "Line_Selected.png", new GELine()),
//		heart("Heart.png", "Heart_Selected.png"),
		polygon("Polygon.png", "Polygon_Selected.png", new GEPolygon());
//		text("Text.png", "Text_Selected.png");
		
		private String buttonImage, selectedbuttonImage;
		private GEShape myShape;
		
		private EButtons(String buttonImage, String selectedbuttonImage, GEShape myShape){
			this.buttonImage = buttonImage;
			this.selectedbuttonImage = selectedbuttonImage;
			this.myShape = myShape;
		}
		public String getButtonImage() {return this.buttonImage;}
		public String getSelectedbuttonImage() {return this.selectedbuttonImage;	}
		public GEShape getShape(){ return this.myShape; }
	}
	//GEMenu
	public enum EMenus{
		file("File", new GEMenuFile()),
		edit("Edit", new GEMenuEdit()),
		color("Color", new GEMenuColor());
		
		private String name;
		private JMenu menu;
		
		private EMenus(String name,JMenu menu){
			this.name = name;
			this.menu = menu;
		}
		public String getName() {return name;}
		public JMenu newMenu() {return menu;}
	}
	// EMenuItem
	public enum EFileMenuItems{
		New("new"),
		Open("open"),
		Close("close"),	
		Save("save"),
		Save_as("save_as"),
		Print("print"),	
		Exit("exit");
		private String name;
		private EFileMenuItems(String name) { this.name = name;	}
		public String getName() {return name;}
	}
	public enum EEditMenuItems{
		Copy("copy"),
		Cut("cut"),
		Paste("paste"),		
		Delete("delete"),		
		Undo("undo"),		
		Redo("redo"),
		Group("group"),		
		Ungroup("ungroup"),		
		SelectAll("select_All");		
		private String name;		
		private EEditMenuItems(String name) {this.name = name;}
		public String getName() {return name;};
	}
	public enum EColorMenuItems{
		LineColor("lineColor"),
		FillColor("fillColor"),
		TextColor("textColor");		
		private String name;		
		private EColorMenuItems(String name) {this.name = name;}
		public String getName() {return name;};
	}
	
	// GEMainFrame
	public static final int FRAME_W = 400;
	public static final int FRAME_H= 600;
	public static final String TITLE_MAINFRAME = "과제7_Draw구현";
	// GEMenu	
	public static final String TITLE_FILEMENU = "파일";
	public static final String TITLE_EDITMENU = "편집";
	public static final String TITLE_COLORMENU = "컬러";
	// GEToolbarShape
	public static final String TITLE_SHAPETOOLBAR = "Shape Tools";
	public static int WIDTH_SHAPETOOLBAR = 400;
	public static int HEIGHT_SHAPETOOLBAR = 200;
	public enum EToolBarButtons{Ellipse, Rectangle, Line, Polygon, Heart, Text};
	public static final String IMG_URL = "rsc/";
	public static final String SUFFIX_TOOLBAR_BTN = ".png";
	public static final String SUFFIX_TOOLBAR_BTN_SLT = "_Selected.png";
	//GEDrawingPanel
	public static final Color FOREGROUND_COLOR = Color.BLACK;
	public static final Color BACKGROUND_COLOR = Color.WHITE;
	//EPolygon_State
	public enum EState{ Idle, TwoPointDrawing, NPointsDrawing };
}
