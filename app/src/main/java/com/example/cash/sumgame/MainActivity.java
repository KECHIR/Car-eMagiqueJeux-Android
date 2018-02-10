package com.example.cash.sumgame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.PersistableBundle;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    EditText grid12,grid11,grid13,grid21,grid22,grid23, grid31,grid32, grid33; /*
    des variables EditText pour récupérer les valeurs saisies par l'utilisateur
    */







    Button btnhelp;
    SharedPreferences sp;  // pour caculer le score et sauvegarder le high score
    SharedPreferences.Editor editor;
    Random rand=new Random(); // pour généré des valeurs aléatoire


    int matmodif [][] = new int[3][3]; // matrice pour stocké les valuers généré alétoirement de 1 à 9



    ArrayList <Integer> maListe = new <Integer> ArrayList(); // liste des valeurs de 1 à 9


   int nb_occ =0; /* une variable pour calculer le nombre d'occurence d'un nombre de 1 à 9 saisie par l'utilisateur
pour éviter la redondance d'un nombre
*/
   Button btnsubmit;
    private Chronometer chronometer; // déclaration d'un timer (chronomètre)
    TextView result1_4;
    TextView result2_4;
    TextView result3_4;
    TextView result4_1;
    TextView result4_2;
    TextView result4_3;  // déclaration des TextView pour les sommes qui vont étre calculer après la génération des nombres alétoires

    TextView meilleurscore; // Déclaration d'un text view pour stocké (settext) le meuilleur score.
    TextView scoreactuel;  // déclaration d'un text view pour affiché le score actuel après avoir trouvé le résultat juste (il affiche le temp qui a mis)
    // .
    long elapsedMillis = 0; // pour récupérer le temps en milliseconde
    int ressom1_4= 0;
    int ressom4_1= 0;

    int ressom2_4= 0;
    int ressom4_2= 0;

    int ressom3_4= 0;
    int ressom4_3=0; // déclaration des entiers pour calculer les sommes des nombres saisie par l'utilisateur pour les comparer après au somme juste

    EditText me[] [] = new EditText [3][3];  //matrice des EditText pour pour stocké les valeurs saisies par l'utilisateur

    int click;

Long scoreprecedent= Long.valueOf(0); // déclaration d'une valeur pour récupérer le meuilleurs score déja obtenue (meuillleur score précédent)
Long timepause= 0L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);









        Intent intentrecu = getIntent();

        matmodif[0][0]=intentrecu.getIntExtra("matrice[0][0]",0);
        matmodif[0][1]=intentrecu.getIntExtra("matrice[0][1]",0);
        matmodif[0][2]=intentrecu.getIntExtra("matrice[0][2]",0);
        matmodif[1][0]=intentrecu.getIntExtra("matrice[1][0]",0);
        matmodif[1][1]=intentrecu.getIntExtra("matrice[1][1]",0);
        matmodif[1][2]=intentrecu.getIntExtra("matrice[1][2]",0);
        matmodif[2][0]=intentrecu.getIntExtra("matrice[2][0]",0);
        matmodif[2][1]=intentrecu.getIntExtra("matrice[2][1]",0);
        matmodif[2][2]=intentrecu.getIntExtra("matrice[2][2]",0);

        for (int i = 0; i < matmodif.length; i++) {
            for (int j = 0; j < matmodif.length; j++) {
                Log.e("matrice [" + i + "]" + "[" + j + "]", String.valueOf(matmodif[i][j]));
            }
            // ce bout de code juste pour vérifié dans la console que la matrice est remplie et que il n'ya pas de la redandonce des nombres on résumé ce le résulat attendue par l'utilisateur
        }







        for (int i = 0; i < matmodif.length; i++) {
            for (int j = 0; j < matmodif.length; j++) {
                if (i == 0) {
                    ressom1_4 = ressom1_4 + matmodif[i][j];
                    ressom4_1 = ressom4_1 + matmodif[j][i];

                            }

                if (i == 1) {
                    ressom2_4 = ressom2_4 + matmodif[i][j];
                    ressom4_2 = ressom4_2 + matmodif[j][i];
                                                              // calculer les Sommes verticaux et horisontaux de la matrice déja remplie
                            }

                if (i == 2) {
                    ressom3_4 = ressom3_4 + matmodif[i][j];
                    ressom4_3 = ressom4_3 + matmodif[j][i];

                            }


                                                      }


                                                  }

        Log.e("som1_4",String.valueOf(ressom1_4));  // ce Bout de code aussi pour voir dans la console qu'il calcule bien les sommes
        Log.e("som2_4",String.valueOf(ressom2_4));  //  les notation resom1_4 juste pour identifier que c'est la case de la ligne 1 colonne 4 comme ma matrice est de 3x3
                                                        // j'ai ajouter ces notations pour dire que la ligne 1 colonne 4 que c'est bien la somme de la ligne 1
        Log.e("som3_4",String.valueOf(ressom3_4));
        Log.e("som4_1",String.valueOf(ressom4_1));
        Log.e("som4_2",String.valueOf(ressom4_2));
        Log.e("som4_3",String.valueOf(ressom4_3));

        result1_4 = (TextView) findViewById(R.id.result1_4);
        result1_4.setText(String.valueOf(ressom1_4));
        result2_4 = (TextView)  findViewById(R.id.result2_4);
        result2_4.setText(String.valueOf(ressom2_4));
        result3_4 = (TextView) findViewById(R.id.result3_4);
        result3_4.setText(String.valueOf(ressom3_4));           // ==> dans cette partie on affiche a l'utilisateurs les sommes pour qu'il essaye de trouvé les nombres correspondants
                                                                // à ces sommes
        result4_1 = (TextView) findViewById(R.id.result4_1);
        result4_1.setText(String.valueOf(ressom4_1));
        result4_2 = (TextView) findViewById(R.id.result4_2);
        result4_2.setText(String.valueOf(ressom4_2));
        result4_3 = (TextView) findViewById(R.id.result4_3);
        result4_3.setText(String.valueOf(ressom4_3));


//icccccccccccciiiiiiiiiiiiiiiiiic

        grid11 = (EditText) findViewById(R.id.grid1_1);
        me [0][0] =grid11;

        grid12 = (EditText) findViewById(R.id.grid1_2);
        me [0][2] =grid12;
        grid13 = (EditText) findViewById(R.id.grid1_3);
        me [0][2] =grid13;
        grid22 = (EditText) findViewById(R.id.grid2_2);
        me [1][1] =grid22;
        grid21 = (EditText) findViewById(R.id.grid2_1);
        me [1][0] =grid21;
        grid23 = (EditText) findViewById(R.id.grid2_3);
        me [1][2] =grid23;
        grid31 = (EditText) findViewById(R.id.grid3_1);
        me [2][0] =grid31;
        grid32 = (EditText) findViewById(R.id.grid3_2);
        me [2][1] =grid32;
        grid33 = (EditText) findViewById(R.id.grid3_3);
        me [2][2] =grid33;
//icccccccccccccciiiiiiiiiiiicccccccccccccci

// début programme facile



        click = intentrecu.getIntExtra("level",0);

        sp = getPreferences(MODE_PRIVATE);
        editor = sp.edit();  // on mit le sharedprefercens on mode edit  modifié

        if (click==1) {
            scoreprecedent = sp.getLong("meilleurscorelevelfacile",0); // cette instruction permette de récupérer le meuilleur score déja obtenue a travers sa clé key="meilleurscore"

            int min = (int)(scoreprecedent/60000); // pour calculer les minute
            int sec = (int) ((scoreprecedent/1000) % 60);  // pour calculer les seconde
            Log.e("scoreprecedentfacile",String.valueOf(min)+":"+String.valueOf(sec));

    //        Toast.makeText(this, "Niveau facile", Toast.LENGTH_SHORT).show();

            for (int i = 1; i < me.length; i++) {
                for (int j =0 ; j < me.length; j++){
                    me[i][j].setText(String.valueOf(matmodif[i][j]));
                    me[i][j].setEnabled(false);
                    me[i][j].setTextColor(getResources().getColor(R.color.colornoir));
                }

            }

        } else if(click == 2) {

            scoreprecedent = sp.getLong("meilleurscorelevelmoyen",0); // cette instruction permette de récupérer le meuilleur score déja obtenue a travers sa clé key="meilleurscore"

            int min = (int)(scoreprecedent/60000); // pour calculer les minute
            int sec = (int) ((scoreprecedent/1000) % 60);  // pour calculer les seconde
            Log.e("scoreprecedentfacile",String.valueOf(min)+":"+String.valueOf(sec));



         //   Toast.makeText(this, "Niveau moyen", Toast.LENGTH_SHORT).show();
            me[0][2].setText(String.valueOf(matmodif[0][2]));
            me[1][1].setText(String.valueOf(matmodif[1][1]));
            me[2][0].setText(String.valueOf(matmodif[2][0]));
        } else {

            scoreprecedent = sp.getLong("meilleurscorelevelhard",0); // cette instruction permette de récupérer le meuilleur score déja obtenue a travers sa clé key="meilleurscore"

            int min = (int)(scoreprecedent/60000); // pour calculer les minute
            int sec = (int) ((scoreprecedent/1000) % 60);  // pour calculer les seconde
            Log.e("scoreprecedentfacile",String.valueOf(min)+":"+String.valueOf(sec));
//            Toast.makeText(this, "Niveau hard", Toast.LENGTH_SHORT).show();


        }

//fin programme facile

        chronometer = (Chronometer) findViewById(R.id.chronometer);
        chronometer.setBase(SystemClock.elapsedRealtime());



        btnhelp = findViewById(R.id.help);
        btnhelp.setEnabled(true);   // on fait récupére le button help




        btnsubmit = ( Button) findViewById(R.id.Buttonsubmit); // on récupére le button submit ********






/*
        scoreprecedent = sp.getLong("meilleurscore",0); // cette instruction permette de récupérer le meuilleur score déja obtenue a travers sa clé key="meilleurscore"

        int min = (int)(scoreprecedent/60000); // pour calculer les minute
        int sec = (int) ((scoreprecedent/1000) % 60);  // pour calculer les seconde
        Log.e("scoreprecedent",String.valueOf(min)+":"+String.valueOf(sec));*/ // dans la console cette instruction pour bien vérifié qu'il récupére bien le meuilleure score  déja obtenue

        meilleurscore = findViewById(R.id.score); // ici je récupére le TextView pour pouvoir affiché le meilleur score quand le user clique sur le button statistique
        scoreactuel =findViewById(R.id.score1); // ici je récupére le TextView pour pouvoir affiché le meilleur score quand le user clique sur le button statistique



    } // fin de la méthode onCreate

    // cycle de vie


    @Override
    protected void onStart() {
        super.onStart();


        chronometer.start();  // comme a chaque méthode Oncreate on démarre le chrono
        Log.e("cycle de vie","on start is called");

    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.e("cycle de vie","on resume is called");
        chronometer.setBase(SystemClock.elapsedRealtime()-timepause);
        chronometer.start();


    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.e("cycle de vie","on pause is called");
        chronometer.stop();
        timepause = SystemClock.elapsedRealtime() - chronometer.getBase();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong("timepause",timepause);
        outState.putInt("click",click);
    }



    @Override
    protected void  onRestoreInstanceState (Bundle donnees) {
        super.onRestoreInstanceState(donnees);


timepause = donnees.getLong("timepause");
click = donnees.getInt("click");

    }

//fin de Cycle de Vie











public int nombre_occ(Long l, Long m [][],  EditText me[] []) { // la fonction nombre_occ pour calculer le nombre d'occurence d'un nombre
    nb_occ = 0;
    for (int i = 0; i < m.length; i++) {
        for (int j = 0; j < m.length; j++) {

            if (l == m[i][j]) {

                nb_occ++;
                 // on cacule le nombre d'occurence d'un nombre (entre 1 et 9 saisie)

                              }


                                           }


                                        }

                                               return nb_occ;

                                                              }

public int comparegrid(Long m [][],int mat [][]){
    int ifgridtrue=0;
    for (int i = 0; i < m.length; i++) {
        for (int j = 0; j < m.length; j++) {
            if (m[i][j] == mat[i][j]) {
                ifgridtrue ++;

                                      }

                                           }
                                       }
                              return ifgridtrue;
                                                } // comparegrid c'est une fonction qui compare la matrice déja remplie dans la méthode onCreate par des nombres (1-9) placé alétoirement

                                                  // et la matrice remplie par l'utilisateur ( si le compte est bon la fonction retourne un entier qui devrait étre 9 si ce n'est pas le cas la solution fournit par le user est fausse)
//fonction pour vérifier que la matrice est bien remplie par l'utilisateur







    public int testCaseVide() {
        int nbCases = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (me[i][j].getText().toString().matches("")) {
                    nbCases++;

                }
            }

        }

        return nbCases;
}  // une fonction qui vérifier si l'utilisateur a bien remplie les case







    public void Valider(View v) { // la méthode valider appéle par le button submit dans son event onClick

     final EditText grid11;
     final EditText grid12;
     final EditText grid13;
     final EditText grid21;
     final EditText grid22;
     final EditText grid23;
     final EditText grid31;
     final EditText grid32;
     final EditText grid33;
// début de récupération ce que l'utilisateur a saisie comme nombre
     Long m[][] = new Long[3][3];

     Long som1_4 = Long.valueOf(0), som4_1 = Long.valueOf(0), som2_4 = Long.valueOf(0), som4_2 = Long.valueOf(0), som3_4 = Long.valueOf(0), som4_3 = Long.valueOf(0);


     grid11 = (EditText) findViewById(R.id.grid1_1);
     me[0][0] = grid11;
     m[0][0] = Long.valueOf(grid11.getText().toString());

     grid12 = (EditText) findViewById(R.id.grid1_2);
     me[0][2] = grid12;
     m[0][1] = Long.valueOf(grid12.getText().toString());


     grid13 = (EditText) findViewById(R.id.grid1_3);
     me[0][2] = grid13;
     m[0][2] = Long.valueOf(grid13.getText().toString());


     grid21 = (EditText) findViewById(R.id.grid2_1);
     me[1][0] = grid21;
     m[1][0] = Long.valueOf(grid21.getText().toString());

     grid22 = (EditText) findViewById(R.id.grid2_2);
     me[1][1] = grid22;
     ;
     m[1][1] = Long.valueOf(grid22.getText().toString());


     grid23 = (EditText) findViewById(R.id.grid2_3);
     me[1][2] = grid23;
     m[1][2] = Long.valueOf(grid23.getText().toString());

     grid31 = (EditText) findViewById(R.id.grid3_1);
     me[2][0] = grid31;
     m[2][0] = Long.valueOf(grid31.getText().toString());

     grid32 = (EditText) findViewById(R.id.grid3_2);
     me[2][1] = grid32;
     m[2][1] = Long.valueOf(grid32.getText().toString());


     grid33 = (EditText) findViewById(R.id.grid3_3);
     me[2][2] = grid33;
     m[2][2] = Long.valueOf(grid33.getText().toString());


     //  récupération des valeurs saisie par l'utilistauer

     if (grid11.getText().toString().length() == 0) {
         Toast.makeText(this, "Vous devez renseigner tous les champs !", Toast.LENGTH_SHORT).show();
         return;
     } else {


         for (int i = 0; i < m.length; i++) {
             for (int j = 0; j < m.length; j++) {
                 if (i == 0) {
                     som1_4 = som1_4 + m[i][j];
                     som4_1 = som4_1 + m[j][i];

                 }

                 if (i == 1) {
                     som2_4 = som2_4 + m[i][j];
                     som4_2 = som4_2 + m[j][i];

                 }

                 if (i == 2) {
                     som3_4 = som3_4 + m[i][j];
                     som4_3 = som4_3 + m[j][i];

                 }


             }


         }  // les deux boucles pour calculer les sommes verticaux et horizontons des valuers saisies par l'utilisateur


         int nb_occtotal = 0;
         for (int i = 0; i < m.length; i++) {
             for (int j = 0; j < m.length; j++) {


                 nb_occtotal = nb_occtotal + nombre_occ(m[i][j], m, me); // dans ces deux boucles on vérifier que ya pas la redondances des nombres en appelant la fonction nombre_occ déclaré en haut


             }


         } // cette partie de code calcule le nombre d'occurence totale si il est egale a 9 donc ya pas de doubles sinon on informe le users qu'il doit pas pas saisie deux nombres égaux


         if (nb_occtotal == 9) {  // si le ya pas de redandance de nombre faire


             int ismattrue = comparegrid(m, matmodif);  // premiére solution si l'utilisateur à bien saisie les nombres dans les cases qu'il fallait on retourne un nombre qui devrait égale a 9


             if ((ismattrue == 9) || ((som4_1 == ressom4_1) && (som1_4 == ressom1_4) && (som4_2 == ressom4_2) && (som2_4 == ressom2_4) && (som4_3 == ressom4_3) && (som3_4 == ressom3_4))) {
// si l'utilisateur à mis les nombres des les bons endroit  " OU "  2 emme solution possible que les nombres saisis par l'utilisateur leurs sommes correspondent bien aux sommes déja calculé
                 Toast.makeText(this, "Congratulations!!!", Toast.LENGTH_SHORT).show();
                 // on active le button continue
                 btnsubmit.setEnabled(false); // on désactive le button submit
                 btnhelp.setEnabled(false);    // on désactive aussi le button help


                 chronometer.stop();  // on stop le chrono
                 elapsedMillis = SystemClock.elapsedRealtime() - chronometer.getBase(); // on récupére le temps qu'il a mis pour trouvé la solution
                 if (click == 1) {

                     if (scoreprecedent == Long.valueOf(0)) {   // si l'utilisateur joue pour la premiére fois son score est le meuilleure score
                         editor.putLong("meilleurscorelevelfacile", elapsedMillis);
                         editor.commit();  // on le garde dans la mémoire cache
                     } else if (elapsedMillis < scoreprecedent) {  // si le score actuel inférieure au meuilleurs score alors le

                         editor.putLong("meilleurscorelevelfacile", elapsedMillis);  // le score actuel deviens le meuilleure score
                         editor.commit();
                     }


                 } else if (click == 2) {
                     if (scoreprecedent == Long.valueOf(0)) {   // si l'utilisateur joue pour la premiére fois son score est le meuilleure score
                         editor.putLong("meilleurscorelevelmoyen", elapsedMillis);
                         editor.commit();  // on le garde dans la mémoire cache
                     } else if (elapsedMillis < scoreprecedent) {  // si le score actuel inférieure au meuilleurs score alors le

                         editor.putLong("meilleurscorelevelmoyen", elapsedMillis);  // le score actuel deviens le meuilleure score
                         editor.commit();
                     }


                 } else {

                     if (scoreprecedent == Long.valueOf(0)) {   // si l'utilisateur joue pour la premiére fois son score est le meuilleure score
                         editor.putLong("meilleurscorelevelhard", elapsedMillis);
                         editor.commit();  // on le garde dans la mémoire cache
                     } else if (elapsedMillis < scoreprecedent) {  // si le score actuel inférieure au meuilleurs score alors le

                         editor.putLong("meilleurscorelevelhard", elapsedMillis);  // le score actuel deviens le meuilleure score
                         editor.commit();
                     }

                 }


             } else  // si l'utlisateur n'a pas encore trouvé de solution on affiche try aigain essaye encore

             {

                 Toast.makeText(this, "result is false!!! Try Again", Toast.LENGTH_SHORT).show();


             }

         } else Toast.makeText(this, "Numbers 1-9 must be\n" +
                 "used once and once", Toast.LENGTH_SHORT).show();
// cette partie si l'utilisateur à saisie des nombres pareils


     }



    }  // fin de la méthode submit




public void helpClick (View v) {

        int chercher=0;
        int i=rand.nextInt(3)+0;
        int j=rand.nextInt(3)+0;
        Log.e("indexi",String.valueOf(i));
        Log.e("indexj",String.valueOf(j));





    Log.e("case",me[i][j].getText().toString());







   // String nbr = me[i][j].getText().toString();
   // ;

    //if (nbr==0L) {Toast.makeText(this, "vide vide vide", Toast.LENGTH_SHORT).show();}

            me[i][j].setText(String.valueOf(matmodif[i][j]));// on stocke la valeur dans la matrice edittext (me)
            me [i][j]  .setTextColor(getResources().getColor(R.color.green));

            Button btnhel = findViewById(R.id.help);
            btnhel.setEnabled(false);
          //  chercher=1;}


 //   } while (chercher==0);

} // la méthode helpclick appélé par le button help consiste à générer deux index i et j alétoirement entre 0 et 2
  // peut avec ces indexes on récupérer une valeurs dans la matrice déja généré et on le stocke  dans la matrice edit text pour que le users
    // puisse le visualisé



public void OnclickNewgame (View v) {

    Intent intenetnewgame = new Intent(  this, Level.class);
    startActivity(intenetnewgame);

  this.finish();
}





    public void Currenthighscore (View v){
        Long  data ;

        int mins = (int)(elapsedMillis/60000);
        int secs = (int) ((elapsedMillis/1000) % 60);


    //    Log.e("score actuelle", String.valueOf(mins) +":"+String.valueOf(secs));
      //  scoreactuel.setText("Current score:"+String.valueOf(mins) +":"+String.valueOf(secs));





        if (click == 1) {
            data = sp.getLong("meilleurscorelevelfacile", 0);
           int  min = (int) (data / 60000);
            int sec = (int) ((data / 1000) % 60);
           // meilleurscore.setText("High score:"+String.valueOf(min) +":"+String.valueOf(sec));
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("High score:"+String.valueOf(min) +":"+String.valueOf(sec)+"\n"+"Current score:"+String.valueOf(mins) +":"+String.valueOf(secs));
            builder.setCancelable(true);

            AlertDialog dialog = builder.create();
            dialog.show();
        } else if (click==2) {

            data = sp.getLong("meilleurscorelevelmoyen", 0);
            int min = (int) (data / 60000);
            int sec = (int) ((data / 1000) % 60);
           // meilleurscore.setText("High score:"+String.valueOf(min) +":"+String.valueOf(sec));
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("High score:"+String.valueOf(min) +":"+String.valueOf(sec)+"\n"+"Current score:"+String.valueOf(mins) +":"+String.valueOf(secs));
            builder.setCancelable(true);

            AlertDialog dialog = builder.create();
            dialog.show();

        } else {

            data = sp.getLong("meilleurscorelevelhard", 0);
            int min = (int) (data / 60000);
            int sec = (int) ((data / 1000) % 60);
          //  meilleurscore.setText("High score:"+String.valueOf(min) +":"+String.valueOf(sec));

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("High score:"+String.valueOf(min) +":"+String.valueOf(sec)+"\n"+"Current score:"+String.valueOf(mins) +":"+String.valueOf(secs));
            builder.setCancelable(true);

            AlertDialog dialog = builder.create();
            dialog.show();



        }



    }  // cette méthode pour récupérée et affiché les scores high et current score






}  // fin de programme
