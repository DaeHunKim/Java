package entity;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class GEModel {
	public GEModel(){
	}
	static public Object read(String fileName) throws IOException, ClassNotFoundException{
		FileInputStream fileInputStream = new FileInputStream(fileName);
		BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
		ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream);
		Object object = objectInputStream.readObject();
		objectInputStream.close();
		return object;
	}
	static public void save(String fileName, Object object) throws IOException{
		FileOutputStream fileOutputStream = new FileOutputStream(fileName);
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream);
		objectOutputStream.writeObject(object);
		objectOutputStream.close();
	}
	
	static public void print(){
		PrinterJob pJob = PrinterJob.getPrinterJob();
		if (! pJob.printDialog())
			return;
			try {
				pJob.print();
			} catch (PrinterException pe) {
				System.out.println("프린터 에러 " + pe.getMessage() );
			}
	}
}
