package entity;

import java.util.Vector;

import shapes.GEShape;

public class GEStack {
	private final int MAXSIZE = 10;
	private Vector<Vector<GEShape>> undoStack, redoStack;
	
	public GEStack(){
		this.undoStack = new Vector<Vector<GEShape>>();
		this.redoStack = new Vector<Vector<GEShape>>();
		
	}
	
	@SuppressWarnings("unchecked")
	public void undoPush(Vector<GEShape> shapes){
		this.undoStack.add((Vector<GEShape>)shapes.clone());
		if(this.undoStack.size() > MAXSIZE){
			this.undoStack.remove(0);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void redoPush(Vector<GEShape> shapes){
		this.redoStack.add((Vector<GEShape>)shapes.clone());
		if(this.redoStack.size() > MAXSIZE){
			this.redoStack.remove(0);
		}
	}

	public Vector<GEShape> pop(){
		System.out.println(this.redoStack.size() + "redo || undo" + (this.undoStack.size()-1));
		if(this.undoStack.size()>0){
			return this.undoStack.remove(this.undoStack.size()-1);
		}
		return new Vector<GEShape>();
	}
	
	public Vector<GEShape> redoPop(){
		if(this.redoStack.size()>undoStack.size()){
			System.out.println(this.redoStack.size() + "redo || undo" + (this.undoStack.size()+1));
			this.undoStack.add(this.redoStack.get(undoStack.size()));
			return this.undoStack.get(this.undoStack.size()-1);
		}
		if(redoStack.size()==0){
			return new Vector<GEShape>();
		}
		return this.redoStack.get(this.undoStack.size()-1);
	}
}
