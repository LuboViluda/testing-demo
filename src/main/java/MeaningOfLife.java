/**
 * Created by lubomir.viluda on 3/12/2017.
 */
public class MeaningOfLife {
    private int counter = 0;

    public MeaningOfLife() {
    }


    public Object getEmptyObject() {
        return new Object();
    }

    public void incrementCounter() {
        counter++;
    }

    public int getCounter() {
        return counter;
    }

    public int computeMeaning() {
        throw new LifeHasNoMeaningException("Our testing exception");
    }

    public int callComputeMeaning() {
        int meaning = 0;
        meaning = computeMeaning();
        return meaning;
    }

}
