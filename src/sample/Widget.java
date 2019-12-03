package sample;

public class Widget extends AnimalType {

    Widget(Species species, String breeds, String petName, String animalID) {
        super(species, breeds, petName, animalID);
    }


    @Override
    public void setSpecies(Species species) {

    }

    public void setBreed(String breed) {
    }

    @Override
    public String getBreed() {
        return null;
    }

    @Override
    public void setName(String petName) {
    }

    @Override
    public String getName() {
        return null;
    }

}

