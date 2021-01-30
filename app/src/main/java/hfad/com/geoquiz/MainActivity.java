package hfad.com.geoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /*variables miembro para capturar los diferentes View desde Java*/
    private ImageButton mTrueButton;
    private ImageButton mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mPreviousButton;
    private TextView mQuestionTextView;
    private int aciertos=0, preguntasRespondidas=0;
    private TextView mtextViewCorrectas;
    private TextView mtextViewNumPregunta;
    /*indice para saber que pregunta del array estamos leyendo*/
    private int mCurrentIndex=0;
    /*Array de objetos Question*/
    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_one,false),
            new Question(R.string.question_two,true),
            new Question(R.string.question_three,false),
            new Question(R.string.question_four,false),
            new Question(R.string.question_five,true)
    };

    /*Metodo que nos devuelve el ID de Recurso de la pregunta que queremos mostrar*/
    private void updateQuestion(){
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    /*Metodo para comprobar si la respuesta es correcta o no*/
    /*Le pasamos un booleano cuando lo llamamos desde cada boton*/
    /*Hacer notar que trabajamos con los ID de los recursos String no con el String directamente*/
    private void checkAnswer(boolean userPressedTrue){
        boolean answerIsTrue=mQuestionBank[mCurrentIndex].isAnswerTrue();
        int messageResId=0;
        if(userPressedTrue==answerIsTrue){
            messageResId=R.string.correct_toast;
            // Si la respuesta es correcta, sumamos un acierto
            aciertos++;

        }else{
            messageResId=R.string.incorrect_toast;
        }

        // Pase lo que pase, sumamos una pregunta respondida en total
        preguntasRespondidas++;
        Toast.makeText(this,messageResId, Toast.LENGTH_SHORT).show();
    }

    /* Método para actualizar el valor de las preguntas respondidas */
    private void updateText(){
        mtextViewCorrectas.setText(String.valueOf(aciertos));
        mtextViewNumPregunta.setText(String.valueOf(aciertos)+"/"+String.valueOf(preguntasRespondidas));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQuestionTextView=(TextView)findViewById(R.id.textView);

        // Ejercicio 1: Al pulsar el textview se cambia de pregunta
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex=(mCurrentIndex+1)%mQuestionBank.length;
                updateQuestion();
            }
        });
        mTrueButton = (ImageButton)findViewById(R.id.buttonTrue);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
                updateText();
            }
        });
        mFalseButton = (ImageButton) findViewById(R.id.buttonFalse);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
                updateText();
            }
        });
        mNextButton=(ImageButton)findViewById(R.id.buttonNext);
        /*Gestion del boton siguiente*/
        /*Calculamos el indice de pregunta en el que nos encontramos*/
        /*Aumentamos en 1 la pregunta, con la % nos aseguramos que no pasamos de las preguntas existentes*/
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex=(mCurrentIndex+1)%mQuestionBank.length;
                updateQuestion();
            }
        });
        mPreviousButton=(ImageButton)findViewById(R.id.buttonPrevious);
        /*Gestion del boton anterior*/
        /*Calculamos el indice de pregunta en el que nos encontramos*/
        /*Disminuimos en 1 la pregunta, con la % nos aseguramos que no pasamos de las preguntas existentes*/
        mPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex=(mCurrentIndex-1)%mQuestionBank.length;
                // Si llega del 0 al -1, se da la vuelta
                if(mCurrentIndex==-1)
                    mCurrentIndex=4;
                updateQuestion();
            }
        });
        updateQuestion();

        // Ejercicio 4, creamos los dos textview y les ponemos el valor que corresponde, en principio 0
        // Hay que iniciarlizarlos aquí, antes de poder usarlos en el método updateText
        mtextViewCorrectas=(TextView)findViewById(R.id.textViewCorrectas);
        mtextViewCorrectas.setText(String.valueOf(aciertos));

        mtextViewNumPregunta=(TextView)findViewById(R.id.textViewNumPregunta);
        mtextViewNumPregunta.setText(String.valueOf(aciertos)+"/"+String.valueOf(preguntasRespondidas));
    }
}