package sample;

abstract class AnimalType implements Animal {
    private Species species;
    private Breeds breeds;
    private String petName;
    private String animalID;

    AnimalType(Species species, Breeds breeds, String petName, String animalID) {
        this.species = species;
        this.breeds = breeds;
        this.petName = petName;
        this.animalID = animalID;
    }

    @Override
    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

    public Breeds getBreeds() {
        return breeds;
    }

    public void setBreeds(Breeds breeds) {
        this.breeds = breeds;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    @Override
    public String getAnimalID() {
        return animalID;
    }

    @Override
    public void setAnimalID(String animalID) {
        this.animalID = animalID;
    }

    @Override
    public String toString() {
        return "Specie: " + species + "Breed: " + breeds + "Pet Name:" + petName + "Animal ID:" + animalID;
    }
}
