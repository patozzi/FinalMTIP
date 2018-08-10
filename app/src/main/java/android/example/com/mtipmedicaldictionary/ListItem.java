package android.example.com.mtipmedicaldictionary;

public class ListItem {

    private String term;
    private String definition;
    private String symptoms;
    private String treatment;
    private String musical_techniques;

    public ListItem (String term, String definition, String symptoms, String treatment, String musical_techniques){
        this.term = term;
        this.definition = definition;
        this.symptoms = symptoms;
        this.treatment = treatment;
        this.musical_techniques = musical_techniques;
    }

    public String getTerm() {
        return term;
    }

    public String getDefinition() {
        return definition;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public String getTreatment() {
        return treatment;
    }

    public String getMusical_techniques() {
        return musical_techniques;
    }
}
