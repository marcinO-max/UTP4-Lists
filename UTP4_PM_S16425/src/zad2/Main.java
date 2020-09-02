/**
 *
 *  @author Piątkowski Marcin S16425
 *
 */

package zad2;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.regex.Matcher;


/*<-- niezbędne import */
public class Main {
  public static void main(String[] args){
    /*<--
     *  definicja operacji w postaci lambda-wyrażeń:
     *  - flines - zwraca listę wierszy z pliku tekstowego
     *  - join - łączy napisy z listy (zwraca napis połączonych ze sobą elementów listy napisów)
     *  - collectInts - zwraca listę liczb całkowitych zawartych w napisie
     *  - sum - zwraca sumę elmentów listy liczb całkowitych
     */
	  
	  Function<String,List<String>> flines = fileName ->{
		
		 ArrayList<String> tmp = new ArrayList<>();
		 Scanner scanner = new Scanner(fileName.toString());
		 try {
			 
			 int a;
			 while(scanner.hasNext()) {
				 tmp.add(scanner.nextLine());
			 }
			 scanner.close();
		 }catch(IOException e) {
			 System.out.println(e.getMessage());
		 }
		  return tmp;
	  };
	  
	  Function <List<String>, String> join = zlacz ->{
		  FileInputStream fis =null;
		  StringBuilder sb = new StringBuilder();
		  try {
		  fis = new FileInputStream(zlacz.toString());
		  int a;
		  while((a=fis.read()) != -1) {
			  sb.append((char)a);
		  }
		 }catch(IOException e) {
			 System.out.println(e.getMessage());
		 }
		  return sb.toString();
	  };
	  
	  Function <String,List<Integer>> collectInts = liczba ->{
		  ArrayList<Integer> lista = new ArrayList<>();
		  java.util.regex.Pattern p = java.util.regex.Pattern.compile("\\d+");
		  Matcher m = p.matcher(liczba);
		  while(m.find()==true) {
			  lista.add(Integer.valueOf(liczba));
		  }
		  
		  return lista;
	  };
	  
	  
	  Function<List<Integer>, Integer> sum = zwrot ->{
		  return zwrot.stream().mapToInt(Integer::intValue).sum();
	  };

    String fname = System.getProperty("user.home") + "/LamComFile.txt"; 
    InputConverter<String> fileConv = new InputConverter<>(fname);
    List<String> lines = fileConv.convertBy(flines);
    String text = fileConv.convertBy(flines, join);
    List<Integer> ints = fileConv.convertBy(flines, join, collectInts);
    Integer sumints = fileConv.convertBy(flines, join, collectInts, sum);

    System.out.println(lines);
    System.out.println(text);
    System.out.println(ints);
    System.out.println(sumints);

    List<String> arglist = Arrays.asList(args);
    InputConverter<List<String>> slistConv = new InputConverter<>(arglist);  
    sumints = slistConv.convertBy(join, collectInts, sum);
    System.out.println(sumints);

    // Zadania badawcze:
    // Operacja flines zawiera odczyt pliku, zatem może powstac wyjątek IOException
    // Wymagane jest, aby tę operację zdefiniowac jako lambda-wyrażenie
    // Ale z lambda wyrażeń nie możemy przekazywac obsługi wyjatków do otaczającego bloku
    // I wobec tego musimy pisać w definicji flines try { } catch { }
    // Jak spowodować, aby nie było to konieczne i w przypadku powstania wyjątku IOException
    // zadziałała klauzula throws metody main
  }
}
