package ciril.forest;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Saisir la largeur de la foret : ");
        int l = sc.nextInt();

        System.out.print("Saisir la hauteur de la foret : ");
        int h = sc.nextInt();

        //déclarer la foret
        int[][] forest = new int[h][l];
        //remplir la foret
        getRandomForest(forest, h, l);
        //afficher la foret
        displayForest(forest);

        System.out.print("Saisir le point de feu\n i : ");
        int i = sc.nextInt();
        System.out.print(" j : ");
        int j = sc.nextInt();

        propagate(forest, new Point(i, j));
        //fermez scanner
        sc.close();
    }

    static void getRandomForest(int[][] forest, int h, int l) {
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < l; j++) {
                int randomNum = ThreadLocalRandom.current().nextInt(0, 1);
                forest[i][j] = randomNum;
            }
        }
    }

    static void displayForest(int[][] forest) {
        for (int i = 0; i < forest.length; i++) {
            System.out.print(" ___ ");
        }
        System.out.println();
        for (int i = 0; i < forest.length; i++) {
            for (int j = 0; j < forest[0].length; j++) {
                System.out.print("| " + forest[i][j] + "\t");
            }
            System.out.println();
        }
        for (int i = 0; i < forest.length; i++) {
            System.out.print(" ___");
        }
        System.out.println();
    }

    //retourner la liste des voisins de la cellule en feu
    static List<Point> getNeighbors(int[][] forest, int i, int j) {
        //les voisins de forest(i,j) sont forest(i, j+1), forest(i, j-1), forest(i-1, j), forest(i+1,j)
        int l = forest.length, h = forest[0].length;
        if (l > 0 && h > 0) {
            //déclarer la liste des voisins
            List<Point> neighbors = new ArrayList<>();
            //voisin à droite
            if (i + 1 < l) {
                neighbors.add(new Point(i + 1, j));
            }
            //voisin à gauche
            if (0 <= i - 1) {
                neighbors.add(new Point(i - 1, j));
            }
            //voisin au dessus
            if (0 <= j - 1) {
                neighbors.add(new Point(i, j - 1));
            }
            //voisin en dessous
            if (j + 1 < h) {
                neighbors.add(new Point(i, j + 1));
            }
            return neighbors;
        }
        return null;
    }

    static void displatNeighbors(int[][] forest, Point point) {
        List<Point> neighbors = getNeighbors(forest, point.getI(), point.getJ());
        if(neighbors!=null && !neighbors.isEmpty()){
            System.out.println("La liste des voisins de "+point+" sont :");
            for (Point neighbor : neighbors) {
                System.out.print(neighbor);
                System.out.print("\t");
            }
            System.out.println();
        }else{
            System.out.println("Pas de voisins !");
        }

    }

    static void changeState(int[][] forest, Point point) {
        if (point.getI() > forest.length || point.getJ() > forest[point.getI()].length) {
            return;
        }
        switch (forest[point.getI()][point.getJ()]) {
            case 0:
                forest[point.getI()][point.getJ()] = 1;
                break;
            case 1:
                forest[point.getI()][point.getJ()] = 2;
                break;
        }
    }

    static  void changeStateNeighbors(int[][] forest,List<Point> neighbors){
        for(Point neighbor: neighbors){
            changeState(forest,neighbor);
        }
    }
    static void propagate(int[][] forest, Point pointOnFire) {
        //allumer le feu
        changeState(forest,pointOnFire);
        //afficher la liste des voisins de la case en feu
        displatNeighbors(forest, pointOnFire);
        //changer l'état de la case en feu
        changeState(forest,pointOnFire);
        //charger la liste des voisins
        List<Point> neighbors = getNeighbors(forest, pointOnFire.getI(), pointOnFire.getJ());
        //changer l'état des voisin de la case en feu
        changeStateNeighbors(forest,neighbors);
        //afficher la foret à l'instant t+1
        displayForest(forest);

        //Pour chaque voisin à l'état 0 alors propager
        for (Point neighbor : neighbors) {
            if (forest[neighbor.getI()][neighbor.getJ()] == 1) {
                propagate(forest, neighbor);
            }
        }
    }

}