package zad2;

import java.io.IOException;
import java.util.function.Function;

public interface F<T,S> extends Function<T,S> {

	@Override
	default S apply(T t) {
		try {
			return metoda(t);
		}catch(IOException e) {
			throw new RuntimeException();
		}
	}
	
	public S metoda (T t) throws IOException;
	
	
	
	
	

}
