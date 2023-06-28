package runner;

import entity.Material;
import entity.Subject;

public class Runner {
    public static void main(String[] args) {
        final Material STEEL = new Material("steel", 7850.0);
        Subject wire = new Subject("wire", STEEL, 0.03);

        String wireInformation = wire.toString();

        printInformation("A wire information: ", wireInformation, "");

        final Material COPPER = new Material("copper", 8500.0);

        wire.setMaterial(COPPER);

        double wireMass = wire.getMass();

        printInformation("The wire mass is: ", String.valueOf(wireMass), "kg");
    }

    static void printInformation(String description, String information, String massSymbol) {
        System.out.println(description + " " + information + massSymbol);
    }
}
