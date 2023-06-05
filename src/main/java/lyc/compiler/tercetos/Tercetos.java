package lyc.compiler.tercetos;

import java.util.ArrayList;

public class Tercetos {

    ArrayList<Terceto> tercetos = new ArrayList<Terceto>();

    public Integer create(String param1, String param2, String param3) {
        tercetos.add(new Terceto(param1, param2, param3));
        return tercetos.size();
    }

    public void print() {
      System.out.println("Intermedia:");
        for (Terceto row : tercetos) {
          System.out.println(
            String.format("%-20s\n",
            row.terceto()
          ));
        }
    }
}
