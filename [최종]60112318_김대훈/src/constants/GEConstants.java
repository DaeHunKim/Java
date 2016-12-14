package constants;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JMenu;

import menus.GEMenuColor;
import menus.GEMenuEdit;
import menus.GEMenuFile;
import shapes.GEEllipse;
import shapes.GEGroup;
import shapes.GELine;
import shapes.GEPolygon;
import shapes.GERectangle;
import shapes.GEShape;
import shapes.GEText;

public class GEConstants {
	public static Toolkit tk = Toolkit.getDefaultToolkit();
	public static Image img = tk.getImage("rsc/ROTATE_CURSOR.png");
	public static Point point = new Point(0, 0);
	public static Cursor ROTATE_CURSOR = tk.createCustomCursor(img, point, "rsc/Ellipse.png");

	public static Cursor DEFAULT_CURSOR = new Cursor(Cursor.DEFAULT_CURSOR);
	public static Cursor DRAW_CURSOR 		= new Cursor(Cursor.CROSSHAIR_CURSOR);
	public static Cursor MOVE_CURSOR 		= new Cursor(Cursor.MOVE_CURSOR);
	public static Cursor EE_RESIZE_CURSOR 	= new Cursor(Cursor.E_RESIZE_CURSOR);
	public static Cursor WW_RESIZE_CURSOR 	= new Cursor(Cursor.W_RESIZE_CURSOR);
	public static Cursor SS_RESIZE_CURSOR 	= new Cursor(Cursor.S_RESIZE_CURSOR);
	public static Cursor NN_RESIZE_CURSOR 	= new Cursor(Cursor.N_RESIZE_CURSOR);
	public static Cursor SE_RESIZE_CURSOR 	= new Cursor(Cursor.SE_RESIZE_CURSOR);
	public static Cursor NE_RESIZE_CURSOR 	= new Cursor(Cursor.NE_RESIZE_CURSOR);
	public static Cursor SW_RESIZE_CURSOR 	= new Cursor(Cursor.SW_RESIZE_CURSOR);
	public static Cursor NW_RESIZE_CURSOR 	= new Cursor(Cursor.NW_RESIZE_CURSOR);	
	
	//ToolBarButtons
	public enum EButtons{
		select("Select.png", "Select_Selected.png", new GEGroup()),
		rectangle("Rectangle.png", "Rectangle_Selected.png", new GERectangle()),
		ellipse("Ellipse.png", "Ellipse_Selected.png", new GEEllipse()),
		line("Line.png", "Line_Selected.png", new GELine()),
		heart("Heart.png", "Heart_Selected.png", new GERectangle()),
		polygon("Polygon.png", "Polygon_Selected.png", new GEPolygon()),
		text("Text.png", "Text_Selected.png", new GEText());
		
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
		New("New"),
		Open("Open"),
		Close("Close"),	
		Save("Save"),
		Save_as("Save_as"),
		Print("Print"),	
		Exit("Exit");
		private String name;
		private EFileMenuItems(String name) { this.name = name;	}
		public String getName() {return name;}
	}
	public static String SWORKSPACE = "data//";// .//data
	public static String SFILECONFIG = "config//file.config"; // .//config//file.config
	public static String SSAVEORNOT = "변경 내용을 저장하시겠습니까?";
	public static String SFILEKIND = "Graphics Editor";
	public static String SFILEEXTENSION = "gps";
	
	public enum EEditMenuItems{
		Copy("Copy"),
		Cut("Cut"),
		Paste("Paste"),		
		Delete("Delete"),		
		Undo("Undo"),		
		Redo("Redo"),
		Group("Group"),		
		Ungroup("Ungroup"),		
		SelectAll("SelectAll");		
		private String name;		
		private EEditMenuItems(String name) {this.name = name;}
		public String getName() {return name;};
	}
	
	public enum EColorMenuItems{
		LineColor("LineColor"),
		ResetLineColor("ResetLineColor"),
		FillColor("FillColor"),
		ResetFillColor("ResetFillColor"),
		TextColor("TextColor");		
		private String name;		
		private EColorMenuItems(String name) {this.name = name;}
		public String getName() {return name;};
	}
	
	// GEMainFrame
	public static final int FRAME_W = 500;
	public static final int FRAME_H= 600;
	public static final String TITLE_MAINFRAME = "Graphic Editor";
	// GEMenu	
	public static final String TITLE_FILEMENU = "파일";
	public static final String TITLE_EDITMENU = "편집";
	public static final String TITLE_COLORMENU = "컬러";
	// GEToolbarShape
	public static final String TITLE_SHAPETOOLBAR = "Shape Tools";
	public static int WIDTH_SHAPETOOLBAR = 500;
	public static int HEIGHT_SHAPETOOLBAR = 300;
	public enum EToolBarButtons{Ellipse, Rectangle, Line, Polygon, Heart, Text};
	public static final String IMG_URL = "rsc/";
	public static final String SUFFIX_TOOLBAR_BTN = ".png";
	public static final String SUFFIX_TOOLBAR_BTN_SLT = "_Selected.png";
	// EPolygon_State
	public enum EState{ Idle, TwoPointDrawing, NPointsDrawing, Moving, Resize, Rotate };
	// GEColorMenu
	public static final String FILLCOLOR_TITLE = "Selected Fill Color";
	public static final String LINECOLOR_TITLE = "Selected LINE Color";
	// Color
	public static final Color FOREGROUND_COLOR = Color.BLACK;
	public static final Color BACKGROUND_COLOR = Color.WHITE;
	public static final Color COLOR_LINE_DEFAULT = Color.BLACK;
	public static final Color COLOR_FILL_DEFAULT = Color.WHITE;
	// GEAnchor
	public static final Color ANCHOR_LINECOLOR = Color.BLACK;
	public static final Color ANCHOR_FILLCOLOR = Color.WHITE;
	public static final int RR_OFFSET = 40;
	public static final int ANCHOR_W = 6;
	public static final int ANCHOR_H = 6;
	// GETransformer
	public static final int DEFAULT_DASH_OFFSET = 4;
	public static final int DEFAULT_DASHEDLINE_WIDTH = 1;
	// GEGroup
	public static final int GROUP_BOUNDARY_MIN = 1;
	public static final int GROUP_BOUNDARY_MAX = 2000;
}
