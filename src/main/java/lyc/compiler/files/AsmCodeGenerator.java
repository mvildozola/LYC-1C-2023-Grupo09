package lyc.compiler.files;

import java.io.FileWriter;
import java.io.IOException;
import lyc.compiler.assembler.Assembler;

public class AsmCodeGenerator implements FileGenerator {

    @Override
    public void generate(FileWriter fileWriter) throws IOException {
        Assembler assembler = Assembler.getSingletonInstance();
        fileWriter.write("include macros2.asm\ninclude number.asm\n\n.MODEL LARGE\n.386\n.STACK 200h\n.DATA\n");
        //fileWriter.write("@MAXTEXTSIZE equ 50\n");
        //fileWriter.write(assembler.generarSeccionDeVariables());
        //fileWriter.write(".CODE\nSTART:\nMOV AX, @DATA\nMOV DS, AX\nMOV es,ax\n \n");
        fileWriter.write(assembler.toString());
        fileWriter.write("\n \nMOV AX, 4C00h\nINT 21h\nEND START");
    }

}
