package zad4;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Maybe <T> {
	
	T t;
	public Maybe (T t) {
		this.t = t;
	}
	
	public T getT() {
		return t;
	}
	
	
	public void setT(T t) {
		this.t = t;
	}

	public static <T> Maybe<T> of(T str) {
		return new Maybe<T>(str);
	}
	
	

	public  Maybe<T>  filter(Predicate<T> p) {
		// TODO Auto-generated method stub
		T newT;
			if(p.test(this.getT()) ) {
				newT = this.getT();
			}else {
				newT = null;
			}
			return new Maybe<>(newT);
	}

	public void ifPresent(Consumer <T> consumer) {
		// TODO Auto-generated method stub
		if(t != null) {
			consumer.accept(t);
		}
	}

	public <Z> Maybe<Z> map(Function<T,Z> map) {
		// TODO Auto-generated method stub
		Z newT;
		if(this.getT()==null) {
			newT=null;
		}else {
			newT = map.apply(t);
		}
		return new Maybe<>(newT);
	}
	
	public T get() {
		if(this.getT()==null) {
			throw new NoSuchElementException("maybe is empty");
		}else {
			return this.t;
		}
	}

	public T orElse(T t) {
		// TODO Auto-generated method stub
		if(this.getT()==null) {
			return t;
		}else {
		return this.getT();	
		}
	}

	@Override
	public String toString() {
		if(this.getT()==null) {
			return "Maybe is Empty";
		}else {
			return "Maybe has value " + this.getT();
		}
	}
	
	



	



	
}
