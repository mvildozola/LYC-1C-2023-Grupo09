package lyc.compiler.assembler;
import java.io.FileWriter;
import java.io.IOException;
import lyc.compiler.simbolsTable.SimbolTable;
import lyc.compiler.tercetos.Terceto;
import lyc.compiler.simbolsTable.DataType;
import lyc.compiler.simbolsTable.SimbolRow;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;


public class Assembler {
       ArrayList<String> asm;
       Dictionary<String, String> dic;
       private static Assembler assembler;
       private Integer count;

    private Assembler() {
        this.asm = new ArrayList<String>();
        this.dic = new Hashtable<>();
        this.count = 1;

    }
    public SimbolTable simbolTable1 = SimbolTable.getSingletonInstance();
    public static Assembler getSingletonInstance() {
        if (assembler == null) {
            assembler = new Assembler();
        }
        return assembler;
    }

    public SimbolTable simbolTable = SimbolTable.getSingletonInstance();

    public void generar(ArrayList<Terceto> tercetos) 
    {
        Stack<String> pila = new Stack<String>();
        asm.add(".CODE\nSTART:\nMOV AX, @DATA\nMOV DS, AX\nMOV es,ax\n \n");
        for (Terceto terceto : tercetos) { 	
            String aux = "";
           //asm.add(terceto.getElement1() + ", "+  terceto.getElement2()+ ", "+  terceto.getElement3()); 		
           String el1 = terceto.getElement1();
           String el2 = terceto.getElement2();
           String el3 = terceto.getElement3();
           switch (el1){
                
                case "=":
                {
                    AddAsm("FLD " + GetAsmRef(el3));
                    AddAsm("FSTP " + el2);
                    aux = "aux" + count;
                    AddAsmRef(count.toString(), aux);
                    AddAsm("");
                    break;
                }
                case "+":
                {   
                    AddAsm("FLD " + GetAsmRef(el2));
                    AddAsm("FLD " + GetAsmRef(el3));
                    AddAsm("FADD ");
                    aux = "aux" + count;
                    AddAsmRef(count.toString(), aux);
                    AddAsm("FSTP " + aux );
                    AddAsm("");
                    break;
                }
                case "-":
                {
                    AddAsm("FLD " + GetAsmRef(el2));
                    AddAsm("FLD " + GetAsmRef(el3));
                    AddAsm("FSUB ");
                    aux = "aux" + count;
                    AddAsmRef(count.toString(), aux);
                    AddAsm("FSTP " + aux );
                    AddAsm("");
                    break;
                }
                case "*":
                {
                    AddAsm("FLD " + GetAsmRef(el2));
                    AddAsm("FLD " + GetAsmRef(el3));
                    AddAsm("FMUL ");
                    aux = "aux" + count;
                    AddAsmRef(count.toString(), aux);
                    AddAsm("FSTP " + aux );
                    AddAsm("");
                    break;
                }
                case "/":
                {
                    AddAsm("FLD " + GetAsmRef(el2));
                    AddAsm("FLD " + GetAsmRef(el3));
                    AddAsm("FDIV ");
                    aux = "aux" + count;
                    AddAsmRef(count.toString(), aux);
                    AddAsm("FSTP " + aux );
                    AddAsm("");
                    break;
                }
                case "CMP":
                {
                    AddAsm("FLD " + GetAsmRef(el2));
                    AddAsm("FLD " + GetAsmRef(el3));
                    AddAsm("FXCH");
                    AddAsm("FCOMP");
                    AddAsm("FSTSW AX");
                    AddAsm("SAHF");
                    break;
                }
                case "BLT":
                {
                    String e = "etiq_" + el2;
                    pila.push(e);
                    AddAsm("JB " + e);
                    AddAsm("");
                    break;
                }
                case "BLE":
                {
                    String e = "etiq_" + el2;
                    pila.push(e);
                    AddAsm("JNA " + e);
                    AddAsm("");
                    break;
                }
                case "BGE":
                {
                    String e = "etiq_" + el2;
                    pila.push(e);
                    AddAsm("JAE " + e);
                    AddAsm("");
                    break;
                }
                case "BGT":
                {
                    String e = "etiq_" + el2;
                    pila.push(e);
                    AddAsm("JA " + e);
                    AddAsm("");
                    break;
                }
                case "BEQ":
                {
                    String e = "etiq_" + el2;
                    pila.push(e);
                    AddAsm("JE " + e);
                    AddAsm("");
                    break;
                }
                case "BNE":
                {
                    String e = "etiq_" + el2;
                    pila.push(e);
                    AddAsm("JNE " + e);
                    AddAsm("");
                    break;
                }
                case "BI":
                {
                    AddAsm("JMP " + "etiq_" + el2);
                    AddAsm("");
                    String e = pila.pop();
                    asm.add(e);
                    break;
                }
                case "Etiq":
                {   
                    asm.add("etiq_[" + count +"]");
                    AddAsm("");
                    break;
                }
                case "Read":
                {   
                    AddAsm("getString " + GetAsmRef(el2) );
                    AddAsm("");
                    break;
                }
                case "Write":
                {   
                    AddAsm("displayString " + GetAsmRef(el2) );
                    AddAsm("displayString _NEWLINE");
                    AddAsm("");
                    break;
                }
                default: {
                    AddAsm("FLD " + simbolTable1.FindByValue(el1));//el1
                    aux = "aux" + count;
                    AddAsmRef(count.toString(), aux);
                    AddAsm("FSTP " + aux);
                    AddAsm("");
                    break;
                }      
            }
            count++;
            if(!pila.empty())
            {
                //System.out.println(count);
                String e = pila.peek();
                if(e.equals("etiq_[" + count +"]"))
                {
                    asm.add(e);
                    pila.pop();
                }
            }
        }
        List<String> variables = new ArrayList<String>();
        
        for(int i=0;i<simbolTable1.size_tabla();i++){
            String linea = "";
            String nombre = simbolTable1.Nombre(i);
            String tipo = simbolTable1.Tipo(i);
            String valor = simbolTable1.Valor(i);
            linea += nombre;
            if((tipo == "INT") && (valor.equals(""))) {
                linea +="    "+"dd ?" ;
                variables.add(linea);
            }
            if((tipo == "INT") && (!valor.equals(""))){
                    linea +="    "+"dd "+ valor;
                    variables.add(linea);
            }
            if((tipo == "FLOAT") && (valor.equals(""))) {
                linea +="    "+"dd ?" ;
                variables.add(linea);
            }
            if((tipo == "FLOAT") && (!valor.equals(""))) {
                linea +="    "+"dd "+valor ;
                variables.add(linea);
            }
            if((tipo == "STRING") && (!valor.equals(""))) {
                String name = nombre;//.replace(" ", "_");
                name +="    "+"db "+  "\""+valor+ "\" ";
                variables.add(name);
            }
            if((tipo == "STRING") && (valor.equals(""))) {
                linea +="    "+"DB "+ "MAXTEXTSIZE" ;
                variables.add(linea);
            }
        }
        variables.add("");
        for(int i=1; i < this.count ; i++)
            variables.add(String.format("%-20s %-5s %-30s", "@aux" + i,"dd", "?"));

        variables.add(String.format("%-20s %-5s %-30s,'$'", "_NEWLINE","db", "0DH,0AH"));
        variables.add("");
        asm.addAll(0, variables);
    }

    private void AddAsm(String value)
    {
        System.out.println(value);
        asm.add("\t" + value);
    }

    private String GetAsmRef(String value)
    {
        if(!value.startsWith("[") && !value.endsWith("]"))
        {
            return value;
        }       

        String v = value.replace("[", "")
                        .replace("]", "");
        return dic.get(v);
    }

    private void AddAsmRef(String num, String value)
    {
        System.out.println(value);
        dic.put(num, value);
    }

    public String generarSeccionDeVariables() {
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
            if((tipo == "STRING") && (valor.equals(""))) {
                linea +="    "+"DB "+ "MAXTEXTSIZE dup (?)" ;
                asm.add(linea);

            }
            if((tipo == "STRING") && (!valor.equals(""))) {
                linea +="    "+"DB "+  "\""+nombre+ "\" " + "dup(?)" ;
                asm.add(linea);

            }
            result += String.format("%s\n\n",linea);
            linea = "";
            }
            return result;
        }

    public void print() {
        for (int i=0;i<asm.size();i++) {
            System.out.println("assembler contiene: ");
            System.out.println(asm.get(i));
        }
    }
 
  @Override
  public String toString() {
    String result = "";
    for (String row : asm) {
      result += "\n" + row;
    }
    return result;
  }
}