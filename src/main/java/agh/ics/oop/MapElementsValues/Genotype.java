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
            genes[i] = randomGene();
        }
        currentGeneNr = (int) (Math.random() * size );
    }
    public Genotype(Animal animal1, Animal animal2){//!! lewa prawa strona genotypu ma być wylosowana
        Genotype genotype1 = animal1.getGenotype();
        Genotype genotype2 = animal2.getGenotype();
        int energy1 =  animal1.getEnergy();
        int energy2 =  animal2.getEnergy();
        int sumEnergy = energy1+energy2;
        Settings settings = animal1.getSettings();

        size = Array.getLength(genotype1.getGenes());
        genes = new int[size];

        int genesFrom1 = (int) ((((float)energy1/(float)sumEnergy))*((float)size));

        for (int i = 0; i < genesFrom1; i++) {
            genes[i] = genotype1.getGenes()[i];
        }
        for (int i = genesFrom1; i < size; i++) {
            genes[i] = genotype2.getGenes()[i];
        }

        int mutationsNumber = (int) (Math.random() * (settings.maxMutation-settings.minMutation))+settings.minMutation;
        for(int i=0; i<mutationsNumber; i++){
            if(settings.mutationVariant){
                int randomPlace = (int) (Math.random() * size );
                genes[randomPlace] = adjustGene( genes[randomPlace] );
            }
            else{
                genes[(int) (Math.random() * size )] = randomGene();
            }
        }
        currentGeneNr = (int) (Math.random() * size );
    }
    private int randomGene(){
        return (int) (Math.random() * 7 + 0.1 );
    }
    private int adjustGene(int gene){
        if(Math.random()<=0.5){
            return (gene+1)%8;
        }
        else{
            return (gene+7)%8;
        }

    }
    public int getGene(boolean variant) {
        int gene = genes[currentGeneNr];
        if(variant && (Math.random()<=0.2)){
            currentGeneNr=(int) (Math.random() * size );
        }
        else{
            currentGeneNr = (currentGeneNr+1)%size;
        }
        return gene;
    }
    public int[] getGenes(){
        return genes;
    }

    public int getCurrentGeneNr(){
        return currentGeneNr;
    }

}
