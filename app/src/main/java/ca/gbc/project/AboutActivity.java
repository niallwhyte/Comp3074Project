package ca.gbc.project;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView namesTextView = findViewById(R.id.namesTextView);
        String[] names = {"Niall Whyte 101377899", "Haider Farooqui 101292102"};
        StringBuilder namesList = new StringBuilder();
        for (String name : names) {
            namesList.append("- ").append(name).append("\n");
        }
        namesTextView.setText(namesList.toString());
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
