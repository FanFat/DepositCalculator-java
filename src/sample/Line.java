package sample;

public class Line {
    private int number;
    private String razm_vkl, nach_percent, na_chete;

    public Line(int number, String razm_vkl, String nach_percent, String na_chete) {
        this.number = number;
        this.razm_vkl = razm_vkl;
        this.nach_percent = nach_percent;
        this.na_chete = na_chete;
    }

    public int getNumber() {
        return number;
    }

    public String getRazm_vkl() {
        return razm_vkl;
    }


    public String getNach_percent() {
        return nach_percent;
    }

    public String getNa_chete() {
        return na_chete;
    }
}
