package Trip_Matching_Theory;
import java.io.Serializable;
import java.util.*;
import org.moeaframework.core.Problem;
import org.moeaframework.core.Solution;
import org.moeaframework.core.Variable;
import org.moeaframework.core.variable.Permutation;
public class StableMatchingProblem implements Problem{
    private IndividualList patients;
    private IndividualList doctors;
    private IndividualList methods;
    private PreferenceProvider preferenceProvider;
    private Matches matches;

    public StableMatchingProblem(IndividualList patients, IndividualList doctors, IndividualList methods, PreferenceProvider preferenceProvider) {
        this.patients = patients;
        this.doctors = doctors;
        this.methods = methods;
        this.preferenceProvider = preferenceProvider;
        this.matches = new Matches(patients.getIndividuals().size() + doctors.getIndividuals().size() + methods.getIndividuals().size());
    }

    public Matches stableMatchingThreeDimensional(Variable var) {
        Set<Integer> matchedNodes = new HashSet<>();
        Queue<Integer> unmatchedNodes = new LinkedList<>();
        Permutation castVar = (Permutation) var;
        int[] decodeVar = castVar.toArray();

        for (int val : decodeVar) {
            unmatchedNodes.add(val);
        }

        while (!unmatchedNodes.isEmpty()) {
            int newNode = unmatchedNodes.poll();
            if (matchedNodes.contains(newNode)) {
                continue;
            }

            // Assuming preferences are retrieved for patients, doctors, and methods
            Pr_List patientPreferences = preferenceProvider.getPatientPreferences();
            Pr_List doctorPreferences = preferenceProvider.getDoctorPreferences();
            Pr_List methodPreferences = preferenceProvider.getMethodPreferences();

            for (int i = 0; i < patientPreferences.size(); i++) {
                int preferredDoctor = patientPreferences.getIndexByPosition(i);
                if (matches.isAlreadyMatch(preferredDoctor, newNode)) break;

                if (!matches.isFull(preferredDoctor, getCapacity(preferredDoctor))) {
                    matches.addMatch(preferredDoctor, newNode);
                    matchedNodes.add(newNode);
                    break;
                } else {
                    int loser = getLeastScoreNode(preferredDoctor, newNode, matches.getIndividualMatches(preferredDoctor));
                    if (loser != newNode) {
                        matches.disMatch(preferredDoctor, loser);
                        unmatchedNodes.add(loser);
                        matchedNodes.remove(loser);
                    }
                }
            }
        }
        return matches;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public int getNumberOfVariables() {
        return 0;
    }

    @Override
    public int getNumberOfObjectives() {
        return 0;
    }

    @Override
    public int getNumberOfConstraints() {
        return 0;
    }

    public void evaluate(Solution solution) {
        Matches result = stableMatchingThreeDimensional(solution.getVariable(0));
        double[] satisfactions = getAllSatisfactions(result);
        double fitnessScore = calculateFitness(satisfactions);
        solution.setAttribute("matches", (Serializable) result);
        solution.setObjective(0, -fitnessScore);
    }

    @Override
    public Solution newSolution() {
        return null;
    }

    @Override
    public void close() {

    }

    private double calculateFitness(double[] satisfactions) {
        // Implement your fitness calculation logic here
        return Arrays.stream(satisfactions).average().orElse(0);
    }

    private int getCapacity(int node) {
        // Implement logic to get the capacity of a given node (doctor/method)
        return 1; // Placeholder, adjust as needed
    }

    private int getLeastScoreNode(int preferredNode, int newNode, List<Integer> currentMatches) {
        // Implement logic to find the least preferable node among current matches
        return currentMatches.get(0); // Placeholder, adjust as needed
    }

    private int getLastChoiceOf(int node) {
        // Implement logic to find the last choice of the given node
        return -1; // Placeholder, adjust as needed
    }

    private double[] getAllSatisfactions(Matches result) {
        // Implement logic to calculate satisfactions for all nodes
        return new double[] {1.0, 1.0}; // Placeholder, adjust as needed
    }
}
