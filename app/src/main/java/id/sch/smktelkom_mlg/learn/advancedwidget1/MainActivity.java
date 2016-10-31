package id.sch.smktelkom_mlg.learn.advancedwidget1;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {
    Spinner spJumlah;
    LinearLayout llAnak;
    TextView tvHasil;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spJumlah = (Spinner) findViewById(R.id.spinnerJumlahAnak);
        Integer[] arJumlah = new Integer[10];
        for (int i = 0; i < 10; i++) {
            arJumlah[i] = i + 1;
        }
        ArrayAdapter adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, arJumlah);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spJumlah.setAdapter(adapter);

        llAnak = (LinearLayout) findViewById(R.id.LinearLayoutAnak);
        tvHasil = (TextView) findViewById(R.id.textViewHasil);

        findViewById(R.id.buttonProses).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doProses();
            }
        });

        spJumlah.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                addEditText((int) spJumlah.getSelectedItem());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void addEditText(int jumlah) {
        llAnak.removeAllViews();
        for (int i = 1; i <= jumlah; i++) {
            View v = LayoutInflater.from(this).inflate(R.layout.layout_anak, llAnak, false);
            v.setTag("Anak" + i);
            llAnak.addView(v);
        }
    }

    private void doProses() {
        int jumlah = (int) spJumlah.getSelectedItem();
        String hasil = "";
        for (int i = 1; i <= jumlah; i++) {
            LinearLayout llNow = (LinearLayout) llAnak.findViewWithTag("Anak" + i);

            EditText etNama = (EditText) llNow.findViewById(R.id.editTextNama);
            EditText etUmur = (EditText) llNow.findViewById(R.id.editTextUmur);

            String nama = etNama.getText().toString().trim();
            String umur = etUmur.getText().toString();

            if (umur.isEmpty())
                umur = "0";
            if (!nama.isEmpty())
                hasil += "Anak ke-" + i + ": " + nama + " umur " + umur + " tahun\n";

        }

        tvHasil.setText(hasil);
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
