package lyc.compiler.tercetos;

import java.util.ArrayList;

public class Tercetos {
   ArrayList<Terceto> tercetos;
   private static Tercetos terceto;

    private Tercetos() {
      this.tercetos = new ArrayList<Terceto>(); 
      System.out.println("Inicializando Tercetos");
    }

    public static Tercetos getSingletonInstance() {
        if (terceto == null){
          terceto = new Tercetos();
        }
        return terceto;
    }

    //ArrayList<Terceto> tercetos = new ArrayList<Terceto>();

    public Integer create(String param1, String param2, String param3) {
        tercetos.add(new Terceto(param1, param2, param3));
        return tercetos.size();
    }

    public Integer create(String param1, Integer param2, Integer param3) {
      tercetos.add(new Terceto(param1, "[" + param2 + "]", "[" + param3 + "]"));
      return tercetos.size();
    }

    public Integer create(String param1, Integer param2, String param3) {
      tercetos.add(new Terceto(param1, "[" + param2 + "]", param3 ));
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
      System.out.println("elemento1 y elemento2 del terceto:" + terceto.getElement1() + " " +terceto.getElement2());
      return tercetos.size();
    }

    

    public Integer size() {
      return tercetos.size();
    }

    public String getValue(Integer index){
      return tercetos.get(index -1).getElement1();
    }
    public String getValue2(Integer index){
        return tercetos.get(index -1).getElement2();
    }
    public String getValue3(Integer index){
        return tercetos.get(index -1).getElement3();
    }

   public String ConvertToAssembler(String i)
   {
       String retVal="";
       switch(i) {
           case "<":
               retVal= "BGE";
               break;
           case ">":
               retVal= "BLE";
               break;
           case "<=":
               retVal= "BGT";
               break;
           case ">=":
               retVal="BLT";
               break;
           default:break;
       }
       return retVal;
   }
    public String Saltar_True(String c)
    {
        String comp ="";
        switch(c){
            case "<":
                comp="BLT";
                break;
            case ">":
                comp="BGT";
                break;
            case "<=":
                comp="BLE";
                break;
            case ">=":
                comp="BGE";
                break;
            default:break;
        }
        return comp;
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

    @Override
    public String toString() {
      int index = 1;
      String result = "";
      for (Terceto row : tercetos) {
        result += 
        String.format("[%s] %s",
        (index++), row.terceto()
        );
      }
      return result;
    }
}
