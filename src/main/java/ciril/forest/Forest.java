package ciril.forest;

import ciril.utils.StaticUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
public class Forest {

    //Une foret est resprésenté par une grille 2D
    private int[][] grille;
    //Nombre de cases reduites en feu
    private int nbAsh;


    public Forest() {
        this.nbAsh = 0;
    }

    /**
     * Method that create a new forest with a random values
     *
     * @param width  height of the forest
     * @param height width of the forest
     */
    public void getRandomForest(int width, int height) {
        if (width > 0 && height > 0) {
            //Instancier la grille avec la hauteur et la largeur en entrée
            grille = new int[width][height];
            //Remplir les cases de la grille par des nombres aléatoires ( dans notre cas que des arbres vivante === 0 )
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    //si on veut remplir la foret avec des cases vides par exemple (y'a pas d'arbres)
                    int randomNum = ThreadLocalRandom.current().nextInt(0, 1);
                    this.grille[i][j] = randomNum;
                }
            }
        } else {
            System.out.println(StaticUtil.ERR_INVALID_FOREST);
        }
    }

    /**
     * Method to display a forest as a matrix 2D in terminal
     */
    public void displayForest() {
        if (this.grille != null) {
            //Ajouter une ligne au dessus
            for (int i = 0; i < this.grille[0].length; i++) {
                System.out.print(" ___");
            }
            System.out.println();
            //Afficher la valeur de chaque case de la grille
            for (int i = 0; i < this.grille.length; i++) {
                for (int j = 0; j < this.grille[0].length; j++) {
                    System.out.print("| " + this.grille[i][j] + "\t");
                }
                System.out.println("|");
            }
            //Ajouter une ligne au dessus
            for (int i = 0; i < this.grille[0].length; i++) {
                System.out.print(" ___");
            }
            System.out.println();
        }
    }

    public boolean isValidForest(){
        if (this != null && this.getGrille() != null) {
            return true;
        }
        return false;
    }
}
