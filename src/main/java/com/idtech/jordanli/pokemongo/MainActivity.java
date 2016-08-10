package com.idtech.jordanli.pokemongo;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnItemSelectedListener {

    ImageView imageView1;
    Spinner spinner;
    Integer pokeNum;
    Integer candyNum;
    EditText numpok;
    EditText numcand;
    int andrew;
    TextView TextView1;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        TextView1 = (TextView)findViewById(R.id.answerTextView);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.category_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        imageView1 = (ImageView) findViewById(R.id.imageView1);
        numpok = (EditText) findViewById(R.id.num_poke);
        numcand = (EditText) findViewById(R.id.num_candies);
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        if (pos == 0) {
            imageView1.setImageResource(R.drawable.pokemon_go_logo);
        } else if (pos == 1) {
            imageView1.setImageResource(R.drawable.pidgey);
            andrew = 12;
        } else if (pos == 2) {
            imageView1.setImageResource(R.drawable.rattata);
            andrew = 25;
        } else if (pos == 3) {
            imageView1.setImageResource(R.drawable.paris);
            andrew = 50;
        } else if (pos == 4) {
            imageView1.setImageResource(R.drawable.machoke);
            andrew = 100;
        } else if (pos == 5) {
            imageView1.setImageResource(R.drawable.magikarp);
            andrew = 400;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    public void doneButtonPressed(View view) {
        Integer numTransfer = 0;
        Integer numEvo = 0;
        pokeNum = Integer.parseInt(numpok.getText().toString());
        candyNum = Integer.parseInt(numcand.getText().toString());
        int evoNow = 0;
        while (candyNum > andrew && pokeNum > 0) {
            evoNow = candyNum / andrew;
            numEvo += evoNow;
            candyNum = (candyNum % andrew) + (evoNow * 2);
            numTransfer++;
            pokeNum -= evoNow;
            evoNow = 0;
        }
        while (pokeNum > 0) {
            while (candyNum < andrew && pokeNum > 0) {
                candyNum++;
                pokeNum--;
                numTransfer++;
            }
            if (pokeNum > 0) {
                numEvo++;
                numTransfer++;
                candyNum = 2;
                pokeNum--;
            }

        }
        TextView1 = (TextView)findViewById(R.id.answerTextView);
        String s="You should evolve " + numEvo.toString() + " Pokemon, and you will transfer " + numTransfer.toString() + " Pokemon throughout this process.";
        TextView1.setText(s);
    }
}
