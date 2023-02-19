package ciril.forest.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tree {

    //Un arbre est représenté par ses coordonnées dans la grille x et y
    private int x;
    private int y;

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    /**
     * Return list of trees neighbors of this tree
     *
     * @param forest the forest in question
     * @return list of trees neighbors
     */
    public List<Tree> getNeighbors(Forest forest) {
        //Les voisins de forest(x,y) sont forest(x, y+1), forest(x, y-1), forest(x-1, y), forest(x+1,y)
        if (forest != null) {
            //Récupérer les dimentions de la foret
            int l = forest.getGrille().length, //largeur
                    h = forest.getGrille()[0].length; //hauteur

            if (l > 0 && h > 0) {
                //Déclarer la liste des voisins
                List<Tree> neighbors = new ArrayList<>();
                //Voisin à droite
                if (x + 1 < l) {
                    neighbors.add(new Tree(x + 1, y));
                }
                //Voisin à gauche
                if (0 <= x - 1) {
                    neighbors.add(new Tree(x - 1, y));
                }
                //Voisin au dessus
                if (0 <= y - 1) {
                    neighbors.add(new Tree(x, y - 1));
                }
                //Voisin en dessous
                if (y + 1 < h) {
                    neighbors.add(new Tree(x, y + 1));
                }
                return neighbors;
            }
        }
        return null;
    }

    /**
     * Method to display the list of trees neighbors of this tree in a forest
     *
     * @param forest the forest in question
     */
    public void displayNeighbors(Forest forest) {
        //Charger la liste des voisins
        List<Tree> neighbors = getNeighbors(forest);
        //Si la liste n'est pas vide
        if (neighbors != null && !neighbors.isEmpty()) {
            //Afficher les voisins à droite, à gauche, au dessus et en dessous
            System.out.println("La liste des voisins de (" + x + "," + y + ") sont :");
            for (Tree neighbor : neighbors) {
                System.out.print(neighbor);
                System.out.print("\t");
            }
            System.out.println();
        } else {
            System.out.println("Pas de voisins !");
        }
    }

    /**
     * Method to generate a probability according to the input number
     * Exemple : if nbProbabilty = 5 then the probabilty = 1/5 = 20%
     *
     * @param nbProbabilty integer for the number of probabilty
     * @return boolean to apply probability
     */
    public boolean isPropagable(int nbProbabilty) {
        return nbProbabilty == 0 ? false : new Random().nextInt(nbProbabilty) == 0;
    }
}
