package lyc.compiler.tercetos;

public class Terceto {
    private String element1;
    public String getElement1() {
        return element1;
    }

    public void setElement1(String element1) {
        this.element1 = element1;
    }

    private String element2;
    public String getElement2() {
        return element2;
    }

    public void setElement2(String element2) {
        this.element2 = element2;
    }

    private String element3;

    public String getElement3() {
        return element3;
    }

    public void setElement3(String element3) {
        this.element3 = element3;
    }

    public Terceto(String element1, String element2, String element3) {
        this.element1 = element1;
        this.element2 = element2;
        this.element3 = element3;
    }

    public String terceto() {
        return String.format("(%s,%s,%s)\n",
             this.element1,
             this.element2,
             this.element3);
    }
}
