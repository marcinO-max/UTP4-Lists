/**
 *
 *  @author Piątkowski Marcin S16425
 *
 */

package zad1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.*;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Pattern;

/*<--
 *  niezbędne importy
 */
public class Main {
  public static void main(String[] args) throws IOException {
    /*<--
     *  definicja operacji w postaci lambda-wyrażeń:
     *  - flines - zwraca listę wierszy z pliku tekstowego
     *  - join - łączy napisy z listy (zwraca napis połączonych ze sobą elementów listy napisów)
     *  - collectInts - zwraca listę liczb całkowitych zawartych w napisie
     *  - sum - zwraca sumę elmentów listy liczb całkowitych
     */
	  Function<String,List<String>> flines = fileName ->{
		  Scanner scanner;
		  List<String> tmp = new ArrayList<>();
		  scanner = new Scanner(fileName);
		  while(scanner.hasNext()) {
			  tmp.add(scanner.nextLine());
		  }
		  scanner.close();
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

  }
}
