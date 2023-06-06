package lyc.compiler.files;

import java.io.FileWriter;
import java.io.IOException;

import lyc.compiler.tercetos.Tercetos;

public class IntermediateCodeGenerator implements FileGenerator {

    @Override
    public void generate(FileWriter fileWriter) throws IOException {
        Tercetos simbolTable = Tercetos.getSingletonInstance();
        fileWriter.write(simbolTable.toString());
    }
}
