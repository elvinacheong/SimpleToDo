package sg.edu.rp.c346.id22016916.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etElement;
    Button btnAdd, btnClear, btnDelete;
    ListView lvTask;
    Spinner spnAddDel;

    ArrayList<String> alTask;
    ArrayAdapter<String> aaTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etElement = findViewById(R.id.editTextTask);
        btnAdd = findViewById(R.id.buttonAdd);
        btnClear = findViewById(R.id.buttonClear);
        btnDelete = findViewById(R.id.buttonDelete);
        spnAddDel = findViewById(R.id.spinnerItems);
        lvTask = findViewById(R.id.listViewTasks);

        alTask = new ArrayList<>();
        aaTask = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alTask);
        lvTask.setAdapter(aaTask);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = etElement.getText().toString();
                alTask.add(task);
                aaTask.notifyDataSetChanged();
                etElement.setText("");
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alTask.clear();
                aaTask.notifyDataSetChanged();
                etElement.setText("");
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (alTask.isEmpty()) {
                    Toast.makeText(MainActivity.this, "You don't have any tasks to remove", Toast.LENGTH_SHORT).show();
                    etElement.setText("");
                }
                else {
                    int pos = Integer.parseInt(etElement.getText().toString());
                    if (pos < 0 || pos > alTask.size()) {
                        Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_SHORT).show();
                        etElement.setText("");
                    }
                    else {
                        alTask.remove(pos - 1);
                        aaTask.notifyDataSetChanged();
                        etElement.setText("");
                    }
                }
            }
        });

        spnAddDel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position) {
                    case 0:
                        btnAdd.setEnabled(true);
                        btnDelete.setEnabled(false);
                        etElement.setHint("Type in a new task here");
                        break;
                    case 1:
                        btnAdd.setEnabled(false);
                        btnDelete.setEnabled(true);
                        etElement.setHint("Type in the index of the task to be removed");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}

