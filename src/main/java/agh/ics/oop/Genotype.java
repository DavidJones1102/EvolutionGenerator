package agh.ics.oop;

public class Genotype {
    private int[] genes;
    private int size;
    private int currentGeneNr;
    public Genotype(int sizeGiven){
        size = sizeGiven;
        genes = new int[size];
        for (int i = 0; i < size; i++) {
            genes[i] = (int) (Math.random() * 7 + 0.1 );
        }
        currentGeneNr = (int) (Math.random() * size );
    }

    public int getGene() {
        int gene = genes[currentGeneNr];
        currentGeneNr = (currentGeneNr+1)%size;
        return gene;
    }

}
