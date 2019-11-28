package sample;

public class Widget extends AnimalType {
    Widget(Species species, DogBreeds breeds, String petName, String animalID) {
        super(species, breeds, petName, animalID);
    }

    @Override
    public void setBreed(DogBreeds breed) {

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
