package Cooking;

public class Ingredients {
    private int sugar;
    private int fructose;
    private int citric_acid;
    private boolean natural_flavor;
    private boolean artificial_flavor;

    public int getSugar() {
        return sugar;
    }

    public void setSugar(int sugar) {
        this.sugar = sugar;
    }

    public int getFructose() {
        return fructose;
    }

    public void setFructose(int fructose) {
        this.fructose = fructose;
    }

    public int getCitric_acid() {
        return citric_acid;
    }

    public void setCitric_acid(int citric_acid) {
        this.citric_acid = citric_acid;
    }

    public boolean isNatural_flavor() {
        return natural_flavor;
    }

    public void setNatural_flavor(boolean natural_flavor) {
        this.natural_flavor = natural_flavor;
    }

    public boolean isArtificial_flavor() {
        return artificial_flavor;
    }

    public void setArtificial_flavor(boolean artificial_flavor) {
        this.artificial_flavor = artificial_flavor;
    }

    @Override
    public String toString() {
        return "Ingredients{" +
                "sugar=" + sugar +
                ", fructose=" + fructose +
                ", citric_acid=" + citric_acid +
                ", natural_flavor=" + natural_flavor +
                ", artificial_flavor=" + artificial_flavor +
                '}';
    }
}
