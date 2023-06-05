package lyc.compiler.tercetos;

import java.util.ArrayList;

public class Tercetos {

    ArrayList<Terceto> tercetos = new ArrayList<Terceto>();

    public Integer create(String param1, String param2, String param3) {
        tercetos.add(new Terceto(param1, param2, param3));
        return tercetos.size();
    }

    public Integer create(String param1, Integer param2, Integer param3) {
      tercetos.add(new Terceto(param1, "[" + param2 + "]", "[" + param3 + "]"));
      return tercetos.size();
    }

    public Integer update(Integer index, String param2) {
      Integer index1 = index -1;
      Terceto terceto = tercetos.get(index1);
      terceto.setElement2(param2);
      tercetos.set(index1, terceto);
      System.out.println("update:" + index1 + " " +param2);
      return tercetos.size();
    }

    public Integer update(Integer index, Integer param2) {
      Integer index1 = index -1;
      Terceto terceto = tercetos.get(index1);
      terceto.setElement2("[" + param2 + "]");
      tercetos.set(index1, terceto);
      System.out.println("update:" + index1 + " " +param2);
      return tercetos.size();
    }

    

    public Integer size() {
      return tercetos.size();
    }

    public String getValue(Integer index){
      return tercetos.get(index -1).getElement1();
    }

    public void print() {
      int index = 1;
      System.out.println("Intermedia:");
        for (Terceto row : tercetos) {
          System.out.println(
            String.format("[%s] %-20s",
            (index++),
            row.terceto()
          ));
        }
    }
}
