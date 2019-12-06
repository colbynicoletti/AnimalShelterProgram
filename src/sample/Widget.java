package sample;

/**
 * Widget Class extended by class AnimalType
 */
@SuppressWarnings("ALL")
public class Widget extends AnimalType {

    /**
     * Widget Constructor that is utilized for database fields
     *
     * @param species  Species
     * @param breeds   String
     * @param petName  String
     * @param animalID String
     */
    Widget(Species species, String breeds, String petName, String animalID) {
        super(species, breeds, petName, animalID);
    }

    /**
     * Mutator to set Species field.
     *
     * @param species Species
     */
    @Override
    public void setSpecies(Species species) {

    }

    /**
     * Mutator to set breed field.
     *
     * @param breed String
     */
    public void setBreed(String breed) {
    }

    /**
     * Accessor to get breed field.
     *
     * @return null
     */
    @Override
    public String getBreed() {
        return null;
    }

    /**
     * Mutator to set pet name field.
     *
     * @param petName String
     */
    @Override
    public void setName(String petName) {
    }

    /**
     * Accessor to get name field.
     *
     * @return
     */
    @Override
    public String getName() {
        return null;
    }
}