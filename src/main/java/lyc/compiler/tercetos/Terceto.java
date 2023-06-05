package lyc.compiler.tercetos;

public class Terceto {
    private String element1;
    private String element2;
    private String element3;

    public Terceto(String element1, String element2, String element3) {
        this.element1 = element1;
        this.element2 = element2;
        this.element3 = element3;
    }

    public String terceto() {
        return String.format("[%s,%s,%s]\n",
             this.element1,
             this.element2,
             this.element3);
    }
}
