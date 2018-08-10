package android.example.com.mtipmedicaldictionary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ParentActivity extends AppCompatActivity {

    private TextView mTerms,mDefinition,mSymptoms,mTreatment,mMusicalTechniques;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item);

        mTerms = (TextView) findViewById(R.id.textViewTerm);
        mDefinition = (TextView) findViewById(R.id.textViewDefinition);
        mSymptoms = (TextView) findViewById(R.id.textViewSymptoms);
        mTreatment = (TextView) findViewById(R.id.textViewTreatment);
        mMusicalTechniques = (TextView) findViewById(R.id.textViewMusicalTechniques);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("termBundle")) {
            Bundle b = intent.getBundleExtra("termBundle");
            mTerms.setText(b.getString("term"));
            mDefinition.setText(b.getString("definition"));
            mSymptoms.setText(b.getString("symptoms"));
            mTreatment.setText(b.getString("treatment"));
            mMusicalTechniques.setText(b.getString("musicalTechniques"));

        }
    }
}
