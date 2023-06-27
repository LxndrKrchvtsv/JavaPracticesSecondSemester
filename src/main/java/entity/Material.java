package entity;

public class Material {
    private final String name;
    private final double density;

    public String getName() {
        return name;
    }

    public double getDensity() {
        return density;
    }

    public Material() {
        this(null, 0.0);
    }

    public Material(String name, double density) {
        this.name = name;
        this.density = density;
    }

    public String toString() {
        return name + "; " + name;
    }
}