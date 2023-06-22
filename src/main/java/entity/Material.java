package entity;

public class Material {
    private final String NAME;
    private final double DENSITY;

    public String getName() {
        return NAME;
    }

    public double getDensity() {
        return DENSITY;
    }

    public Material() {
        this(null, 0.0);
    }

    public Material(String name, double density) {
        this.NAME = name;
        this.DENSITY = density;
    }

    public String toString() {
        return NAME + "; " + NAME;
    }
}