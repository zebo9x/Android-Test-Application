package tk.physicz.test.testapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    static private String SHAREDPREFS = Common.SHAREDPREFS;
    static private String AVAILABLE = Common.AVAILABLE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int available = 9; // Default value

        ListView listView = (ListView) findViewById(R.id.listView);
        TextView textView = (TextView) findViewById(R.id.textView);
        Resources res = getResources();
        SharedPreferences sPrefs = getSharedPreferences(SHAREDPREFS, MODE_PRIVATE); SharedPreferences.Editor pEditor = sPrefs.edit();

        String[] tests = res.getStringArray(R.array.list);
        int testslenght = tests.length;
        available = sPrefs.getInt(AVAILABLE, (testslenght<available)?testslenght:available);
        tests = Arrays.copyOfRange(tests, 0, (testslenght<available)?testslenght:available);
        String uppertext = String.format(res.getQuantityString(R.plurals.tests_counter, available), available);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.test_list_element, R.id.element, tests);
        AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = createIntent(position);
                if(intent != null)startActivity(intent);
            }
        };

        textView.setText(Html.fromHtml(uppertext));
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(onItemClickListener);
    }

    Intent createIntent(int pos) {
        Intent intent = null;
        if(pos==1) {
            intent = new Intent(this, FirstClass.class);
        } else {
            Toast.makeText(this, "not 1", Toast.LENGTH_SHORT).show();
            intent = new Intent(this, FirstClass.class);
        }
        return intent;
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "on pause", Toast.LENGTH_SHORT).show();
    }
}
