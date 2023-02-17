package ciril.forest;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {

        System.out.print("Saisir le nombre de lignes dans la matrice: ");
        Scanner sc = new Scanner(System.in);
        int h = sc.nextInt();

        System.out.print("Saisir le nombre de colonnes dans la matrice: ");
        int l = sc.nextInt();
        //fermez scanner
        sc.close();

        //déclarer la matrice
        int[][] forest = new int[h][l];
        //créer une foret
        getRandomForest(forest, h, l);
        //afficher la foret
        displayForest(forest);

    }

    static void getRandomForest(int[][] forest, int h, int l) {
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < l; j++) {
                int randomNum = ThreadLocalRandom.current().nextInt(0, 2);
                forest[i][j] = randomNum;
            }
        }
    }

    static void displayForest(int[][] forest) {
        for (int i = 0; i < forest.length; i++) {
            for (int j = 0; j < forest[0].length; j++) {
                System.out.print(forest[i][j] + "\t");
            }
            System.out.println();
        }
    }

    //retourner la liste des voisins de la cellule en feu
    static int[][] getNeighbors(int[][] forest, int i, int j, int l, int h) {
        //les voisins de forest(i,j) sont forest(i, j+1), forest(i, j-1), forest(i-1, j), forest(i+1,j)
        if (forest.length > 0) {
            int[][] neighbors = new int[][];
            if (0 > l + 1 && l + 1 > forest.length) {
                //voisin à droite
                neighbors[1][h] = l + 1;
            }
        }
        return null;
    }

    //appel récursive pour les voisins


}