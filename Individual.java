package Trip_Matching_Theory;
import java.util.List;
public class Individual {
    private String name;
    private List<String> preferences;

    public Individual(String name, List<String> preferences) {
        this.name = name;
        this.preferences = preferences;
    }

    public String getName() {
        return name;
    }

    public List<String> getPreferences() {
        return preferences;
    }
}
