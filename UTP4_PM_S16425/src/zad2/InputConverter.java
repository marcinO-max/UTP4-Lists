package zad2;

import java.awt.List;
import java.util.ArrayList;
import java.util.function.Function;

public class InputConverter<T> {
	
	T src;

	public InputConverter(T src) {
		this.src=src;
	}
	
	public <T,R> T convertBy(Function... function) {
		List tmp = new ArrayList<>();
		
		tmp.add(function[0].apply(src));
		
		for(int i = 1 ; i <function.length;i++) {
			tmp.add(function[i].apply(tmp.get(i-1)));
		}
		
		return (T)tmp.get(tmp.size()-1);
	}
}
