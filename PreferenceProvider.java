package Trip_Matching_Theory;
import java.util.List;
public class PreferenceProvider {
    private Pr_List patientPreferences;
    private Pr_List doctorPreferences;
    private Pr_List methodPreferences;

    public PreferenceProvider() {
        patientPreferences = new Pr_List(5, 2);
        doctorPreferences = new Pr_List(10, 3);
        methodPreferences = new Pr_List(6, 1);
    }


    public Pr_List getPatientPreferences() {
        return patientPreferences;
    }

    public Pr_List getDoctorPreferences() {
        return doctorPreferences;
    }

    public Pr_List getMethodPreferences() {
        return methodPreferences;
    }
}
