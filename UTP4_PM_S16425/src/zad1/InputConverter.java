package zad1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class InputConverter<T> {
	
	T src;

	public InputConverter(T src) {
		this.src=src;
	}
	
	public <X> X convertBy(Function<T,?> t,Function... function) {
		List tmp = new ArrayList<>();
		
		tmp.add(function[0].apply(src));
		
		for(int i = 1 ; i <function.length;i++) {
			tmp.add(function[i].apply(tmp.get(i-1)));
		}
		
		
		
		
		
		return (X)tmp;
	}
}
