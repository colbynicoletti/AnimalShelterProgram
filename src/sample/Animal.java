package sample;

/**
 * Animal interface implemented in abstract class AnimalType.
 */
public interface Animal {
    void setSpecies(Species species);

    Species getSpecies();

    void setBreed(String breed);

    String getBreed();

    void setName(String petName);

    String getName();

    void setAnimalID(String animalID);

    String getAnimalID();
}

