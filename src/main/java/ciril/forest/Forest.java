package ciril.forest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
@NoArgsConstructor
public class Forest {

    //Une foret est resprésenté par une grille 2D
    private int[][] grille;

    private int nbAsh = 0;

    /**
     * Method that create a new forest with a random values
     *
     * @param h height of the forest
     * @param l width of the forest
     */
    public void getRandomForest(int h, int l) {
        //Instancier la grille avec la hauteur h et la largeur l
        grille = new int[h][l];
        //Remplir les cases de la grille par des nombre aléatoire ( dans notre cas que des arbres vivante === 0 )
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < l; j++) {
                //si on veut remplir la foret avec des cases vides par exemple (y'a pas d'arbres)
                int randomNum = ThreadLocalRandom.current().nextInt(0, 1);
                this.grille[i][j] = randomNum;
            }
        }
    }

    /**
     * Method to display a forest as a matrix 2D
     */
    public void displayForest() {
        //Ajouter une ligne au dessus
        for (int i = 0; i < this.grille.length; i++) {
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
        for (int i = 0; i < this.grille.length; i++) {
            System.out.print(" ___");
        }
        System.out.println();
    }
}
