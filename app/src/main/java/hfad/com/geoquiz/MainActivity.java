package hfad.com.geoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    private Button buttonNext, buttonTrue, buttonFalse;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=findViewById(R.id.textView);
        buttonNext=findViewById(R.id.buttonNext);
        buttonTrue=findViewById(R.id.buttonTrue);
        buttonFalse=findViewById(R.id.buttonFalse);

        i=0;

        Resources res = getResources();
        String[] preguntas = res.getStringArray(R.array.preguntas);
        textView.setText(preguntas[i]);

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                if(i<5)
                    textView.setText(preguntas[i]);
                else {
                    i = 0;
                    textView.setText(preguntas[i]);
                }
            }
        });


        buttonTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i==1 || i==4)
                    Toast.makeText(MainActivity.this, "Has acertado", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Has fallado", Toast.LENGTH_SHORT).show();
            }
        });

        buttonFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i==0 || i==2 || i==3)
                    Toast.makeText(MainActivity.this, "Has acertado", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Has fallado", Toast.LENGTH_SHORT).show();
            }
        });
    }
}