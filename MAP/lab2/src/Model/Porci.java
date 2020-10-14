package Model;

public class Porci implements Animale {
    private String name;
    private int weight;

    public Porci(String Name, int Weight) {
        this.name = Name;
        this.weight = Weight;
    }

    public void getWeightOver3() {
        if (this.weight > 3)
            System.out.println(this.name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getName() {
        return this.name;
    }

    public int getWeight() {
        return this.weight;
    }

    public String toString() {
        String x = name + "   " + weight;
        return x;
    }
}

