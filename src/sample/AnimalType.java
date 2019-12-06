package sample;

/**
 * Abstract class AnimalType that utilizes interface animal.
 */
abstract class AnimalType implements Animal {
    private Species species;
    private String breeds;
    private String petName;
    private String animalID;

    /**
     * Constructor used to implement database fields in animal table.
     *
     * @param species  field Species
     * @param breeds   String
     * @param petName  String
     * @param animalID String
     */
    AnimalType(Species species, String breeds, String petName, String animalID) {
        this.species = species;
        this.breeds = breeds;
        this.petName = petName;
        this.animalID = animalID;
    }

    /**
     * Accessor to get Species field.
     *
     * @return Species species
     */
    @Override
    public Species getSpecies() {
        return species;
    }

    /**
     * Mutator to set Species field.
     *
     * @param species Species
     */
    public void setSpecies(Species species) {
        this.species = species;
    }

    /**
     * Accessor to get pet name field.
     *
     * @return String petName
     */
    public String getPetName() {
        return petName;
    }

    /**
     * Mutator to set pet name field.
     *
     * @param petName String
     */
    public void setPetName(String petName) {
        this.petName = petName;
    }

    /**
     * Accessor to get animal ID field.
     *
     * @return String animalID
     */
    @Override
    public String getAnimalID() {
        return animalID;
    }

    /**
     * Mutator to set animal ID field.
     *
     * @param animalID String
     */
    @Override
    public void setAnimalID(String animalID) {
        this.animalID = animalID;
    }

    public String getBreeds() {
        return breeds;
    }

    public void setBreeds(String breeds) {
        this.breeds = breeds;
    }

    /**
     * toString Method.
     *
     * @return formatted String
     */
    @Override
    public String toString() {
        return "Species: " + species + "\nBreed: " + breeds + "\nPet Name: " + petName + "\nAnimal ID: " + animalID;
    }
}

