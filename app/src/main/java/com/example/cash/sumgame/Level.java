package com.example.cash.sumgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

public class Level extends AppCompatActivity {
    int click;
    int matmodif [][] = new int[3][3]; // matrice pour stocké les valuers généré alétoirement de 1 à 9
    Random rand=new Random(); // pour généré des valeurs aléatoire


    ArrayList<Integer> maListe = new <Integer> ArrayList(); // liste des valeurs de 1 à 9


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);









    }



    public void Onclickfacile (View v){
        Intent intentfacile = new Intent(this, MainActivity.class);

        maListe.add(1);  maListe.add(2);  maListe.add(3);  maListe.add(4);  maListe.add(5);  maListe.add(6);
        maListe.add(7);  maListe.add(8);  maListe.add(9);
        // remplisage de la liste par des nombre de 1,2,.... jusqu'à 9

        for (int i = 0; i < matmodif.length; i++) {
            for (int j = 0; j < matmodif.length; j++) {
                int nbg;
                nbg = maListe.get(rand.nextInt(maListe.size())+0); // on choisie alétoirement un nombre de 1 à 9 dans la liste (maListe) déja remplie


                for (int k=0;k<maListe.size();k++){
                    if (nbg== maListe.get(k)) {

                        matmodif[i][j] = nbg;       // on parcour la liste (maListe) jusqu'au trouve le nombre déja généré et on le retire de la liste

                        maListe.remove(k);         // par remove pour à fin d'éviter de le générée une autre fois

                    }
                }
            }
        } // à la fin de ses deux boucle on obtient une matrice remplie de valeurs de 1 à 9 placé aléatoirement dans cette matrice sans avoir des redondances des nombres
        for (int i = 0; i < matmodif.length; i++) {
            for (int j = 0; j < matmodif.length; j++) {
                Log.e("matrice [" + i + "]" + "[" + j + "]", String.valueOf(matmodif[i][j]));
            }
            // ce bout de code juste pour vérifié dans la console que la matrice est remplie et que il n'ya pas de la redandonce des nombres on résumé ce le résulat attendue par l'utilisateur
        }


        intentfacile.putExtra("matrice[0][0]",matmodif[0][0]);
        intentfacile.putExtra("matrice[0][1]",matmodif[0][1]);
        intentfacile.putExtra("matrice[0][2]",matmodif[0][2]);
        intentfacile.putExtra("matrice[1][0]",matmodif[1][0]);
        intentfacile.putExtra("matrice[1][1]",matmodif[1][1]);
        intentfacile.putExtra("matrice[1][2]",matmodif[1][2]);
        intentfacile.putExtra("matrice[2][0]",matmodif[2][0]);
        intentfacile.putExtra("matrice[2][1]",matmodif[2][1]);
        intentfacile.putExtra("matrice[2][2]",matmodif[2][2]);


     click =1;
        intentfacile.putExtra("level",click);
        startActivity(intentfacile);

    }

    public void Onclickmoyen (View v){
        Intent intentfacile = new Intent(this, MainActivity.class);
        maListe.add(1);  maListe.add(2);  maListe.add(3);  maListe.add(4);  maListe.add(5);  maListe.add(6);
        maListe.add(7);  maListe.add(8);  maListe.add(9);
        // remplisage de la liste par des nombre de 1,2,.... jusqu'à 9

        for (int i = 0; i < matmodif.length; i++) {
            for (int j = 0; j < matmodif.length; j++) {
                int nbg;
                nbg = maListe.get(rand.nextInt(maListe.size())+0); // on choisie alétoirement un nombre de 1 à 9 dans la liste (maListe) déja remplie


                for (int k=0;k<maListe.size();k++){
                    if (nbg== maListe.get(k)) {

                        matmodif[i][j] = nbg;       // on parcour la liste (maListe) jusqu'au trouve le nombre déja généré et on le retire de la liste

                        maListe.remove(k);         // par remove pour à fin d'éviter de le générée une autre fois

                    }
                }
            }
        } // à la fin de ses deux boucle on obtient une matrice remplie de valeurs de 1 à 9 placé aléatoirement dans cette matrice sans avoir des redondances des nombres
        for (int i = 0; i < matmodif.length; i++) {
            for (int j = 0; j < matmodif.length; j++) {
                Log.e("matrice [" + i + "]" + "[" + j + "]", String.valueOf(matmodif[i][j]));
            }
            // ce bout de code juste pour vérifié dans la console que la matrice est remplie et que il n'ya pas de la redandonce des nombres on résumé ce le résulat attendue par l'utilisateur
        }


        intentfacile.putExtra("matrice[0][0]",matmodif[0][0]);
        intentfacile.putExtra("matrice[0][1]",matmodif[0][1]);
        intentfacile.putExtra("matrice[0][2]",matmodif[0][2]);
        intentfacile.putExtra("matrice[1][0]",matmodif[1][0]);
        intentfacile.putExtra("matrice[1][1]",matmodif[1][1]);
        intentfacile.putExtra("matrice[1][2]",matmodif[1][2]);
        intentfacile.putExtra("matrice[2][0]",matmodif[2][0]);
        intentfacile.putExtra("matrice[2][1]",matmodif[2][1]);
        intentfacile.putExtra("matrice[2][2]",matmodif[2][2]);




        click =2;
        intentfacile.putExtra("level",click);
        startActivity(intentfacile);

    }

    public void Onclickhard (View v){
        Intent intentfacile = new Intent(this, MainActivity.class);
        maListe.add(1);  maListe.add(2);  maListe.add(3);  maListe.add(4);  maListe.add(5);  maListe.add(6);
        maListe.add(7);  maListe.add(8);  maListe.add(9);
        // remplisage de la liste par des nombre de 1,2,.... jusqu'à 9

        for (int i = 0; i < matmodif.length; i++) {
            for (int j = 0; j < matmodif.length; j++) {
                int nbg;
                nbg = maListe.get(rand.nextInt(maListe.size())+0); // on choisie alétoirement un nombre de 1 à 9 dans la liste (maListe) déja remplie


                for (int k=0;k<maListe.size();k++){
                    if (nbg== maListe.get(k)) {

                        matmodif[i][j] = nbg;       // on parcour la liste (maListe) jusqu'au trouve le nombre déja généré et on le retire de la liste

                        maListe.remove(k);         // par remove pour à fin d'éviter de le générée une autre fois

                    }
                }
            }
        } // à la fin de ses deux boucle on obtient une matrice remplie de valeurs de 1 à 9 placé aléatoirement dans cette matrice sans avoir des redondances des nombres
        for (int i = 0; i < matmodif.length; i++) {
            for (int j = 0; j < matmodif.length; j++) {
                Log.e("matrice [" + i + "]" + "[" + j + "]", String.valueOf(matmodif[i][j]));
            }
            // ce bout de code juste pour vérifié dans la console que la matrice est remplie et que il n'ya pas de la redandonce des nombres on résumé ce le résulat attendue par l'utilisateur
        }
        intentfacile.putExtra("matrice[0][0]",matmodif[0][0]);
        intentfacile.putExtra("matrice[0][1]",matmodif[0][1]);
        intentfacile.putExtra("matrice[0][2]",matmodif[0][2]);
        intentfacile.putExtra("matrice[1][0]",matmodif[1][0]);
        intentfacile.putExtra("matrice[1][1]",matmodif[1][1]);
        intentfacile.putExtra("matrice[1][2]",matmodif[1][2]);
        intentfacile.putExtra("matrice[2][0]",matmodif[2][0]);
        intentfacile.putExtra("matrice[2][1]",matmodif[2][1]);
        intentfacile.putExtra("matrice[2][2]",matmodif[2][2]);


        click =3;
        intentfacile.putExtra("level",click);
        startActivity(intentfacile);

    }











}
