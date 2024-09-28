package Trip_Matching_Theory;
import java.util.*;
public class IndividualList {
    private List<Individual> individuals;

    public IndividualList() {
        this.individuals = new ArrayList<>();
    }

    public void addIndividual(Individual individual) {
        individuals.add(individual);
    }

    public List<Individual> getIndividuals() {
        return individuals;
    }
}
