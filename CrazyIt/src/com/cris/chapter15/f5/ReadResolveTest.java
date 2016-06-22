package com.cris.chapter15.f5;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 反序列化的时候，在readObject之后会调用该方法它可以实现保护性复制整个对象
 * @author sound2gd
 *
 */
public class ReadResolveTest {

	public static void main(String[] args) {
		try (ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream("src/com/cris/chapter15/f5/obj.txt"));) {
			oos.writeObject(Orientation.HOR);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/com/cris/chapter15/f5/obj.txt"))) {
			Orientation hor = (Orientation) ois.readObject();
			System.out.println(hor);
			System.out.println("反序列化后的对象是否相等?"+(hor == Orientation.HOR));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

class Orientation implements Serializable{
	public static final Orientation HOR = new Orientation(1);
	public static final Orientation VER = new Orientation(2);
	private int value;
	private Orientation(int value) {
		super();
		this.value = value;
	}
	//加入以下方法可以避免上面的问题
	private Object readResolve() throws Exception{
		if (value == 1){
			return HOR;
		}else if(value == 2){
			return VER;
		}else{
			return null;
		}
		
	}
}
