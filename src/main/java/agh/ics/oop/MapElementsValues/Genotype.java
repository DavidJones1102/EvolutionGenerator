package agh.ics.oop.MapElementsValues;

import agh.ics.oop.MapElements.Animal;

import java.lang.reflect.Array;

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
    public Genotype(Animal animal1, Animal animal2){//!! lewa prawa strona genotypu ma być wylosowana
        Genotype genotype1 = animal1.getGenotype();
        Genotype genotype2 = animal2.getGenotype();
        int energy1 =  animal1.getEnergy();
        int energy2 =  animal2.getEnergy();
        int sumEnergy = energy1+energy2;

        size = Array.getLength(genotype1.getGenes());
        genes = new int[size];

        int genesFrom1 = (int) (energy1/sumEnergy)*size;
        int[] genesFromAnimal = {genesFrom1,size-genesFrom1}; //Tablica zawiera liczbę genów jaką zwierzę odziedziczy od kojelno pierwszego i drugiego rodzica

        for (int i = 0; i < genesFrom1; i++) {
            genes[i] = genotype1.getGenes()[i];
        }
        for (int i = genesFrom1; i < size; i++) {
            genes[i] = genotype2.getGenes()[i];
        }

        int mutationsNumber = (int) (Math.random() * size );
        for(int i=0; i<mutationsNumber; i++){
            genes[(int) (Math.random() * size )] = (int) (Math.random() * 7 + 0.1 );
        }
        currentGeneNr = (int) (Math.random() * size );
    }

    public int getGene() {
        int gene = genes[currentGeneNr];
        currentGeneNr = (currentGeneNr+1)%size;
        return gene;
    }
    public int[] getGenes(){
        return genes;
    }

}
