package sample;

public class Widget extends AnimalType {
    Widget(Species species, Breeds breeds, String petName, String animalID) {
        super(species, breeds, petName, animalID);
    }

    @Override
    public void setBreed(Breeds breed) {

    }

    @Override
    public Breeds getBreed() {
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
