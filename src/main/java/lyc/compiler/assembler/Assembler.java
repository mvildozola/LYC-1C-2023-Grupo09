package lyc.compiler.assembler;
import java.io.FileWriter;
import java.io.IOException;
import lyc.compiler.simbolsTable.SimbolTable;
import lyc.compiler.simbolsTable.SimbolRow;
import java.util.ArrayList;
import java.util.Iterator;


public class Assembler {
       ArrayList<String> asm;
       private static Assembler assembler;

    private Assembler() {
        this.asm = new ArrayList<String>();
        System.out.println("Inicializando Assembler");
    }
    public SimbolTable simbolTable1 = SimbolTable.getSingletonInstance();
    public static Assembler getSingletonInstance() {
        if (assembler == null) {
            assembler = new Assembler();
        }
        return assembler;
    }

    public SimbolTable simbolTable = SimbolTable.getSingletonInstance();

    public  String generarSeccionDeVariables() {
        System.out.println(".DATA\n");
        String var = ".DATA";
        String result = String.format("%s\n\n",var);

        for(int i=0;i<simbolTable1.size_tabla();i++){
            String linea = "@";
            String nombre = simbolTable1.Nombre(i);
            String tipo = simbolTable1.Tipo(i);
            String valor = simbolTable1.Valor(i);
            linea += nombre;
            if((tipo == "INT") && (valor.equals(""))) {
                linea +="    "+"DD ?" ;
                asm.add(linea);

            }
            if((tipo == "INT") && (!valor.equals(""))){
                    linea +="    "+"DW "+ valor;
                    asm.add(linea);

                }
            if((tipo == "FLOAT") && (valor.equals(""))) {
                linea +="    "+"DD ?" ;
                asm.add(linea);

            }
            if((tipo == "FLOAT") && (!valor.equals(""))) {
                linea +="    "+"DD "+nombre ;
                asm.add(linea);

            }
            if((tipo == "STRING") && (valor.equals(""))) {
                linea +="    "+"DB "+ "MAXTEXTSIZE dup (?)" ;
                asm.add(linea);

            }
            if((tipo == "STRING") && (!valor.equals(""))) {
                linea +="    "+"DB "+  "\""+nombre+ "\"" + "dup(?)" ;
                asm.add(linea);

            }
            result += String.format("%s\n\n",linea);
            linea = "";
            }
             System.out.println("Result contiene: "+ result);
             return result;


        }


    public void print() {

        for (int i=0;i<asm.size();i++) {
            System.out.println("assembler contiene: ");
            System.out.println(asm.get(i));
        }

    }
}