package ciril.forest;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
@NoArgsConstructor
public class Tree {

    //Un arbre est représenté par ses coordonnées dans la grille x et y
    private int x;
    private int y;
    private int z;

    public Tree(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tree tree = (Tree) o;
        return x == tree.x && y == tree.y;
    }

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
        if (forest.isValidForest()) {
            //Récupérer les dimentions de la foret
            int width = forest.getGrille().length, //largeur
                    height = forest.getGrille()[0].length; //hauteur
            if (width > 0 && height > 0) {
                //Déclarer la liste des voisins
                List<Tree> neighbors = new ArrayList<>();
                //Voisin au dessus
                if (x + 1 < width && (forest.getGrille()[x+1][y]!=3 || forest.getGrille()[x+1][y]!=4)) {
                    neighbors.add(new Tree(x + 1, y));
                }
                //Voisin en dessous
                if (0 <= x - 1) {
                    neighbors.add(new Tree(x - 1, y));
                }
                //Voisin à gauche
                if (0 <= y - 1) {
                    neighbors.add(new Tree(x, y - 1));
                }
                //Voisin à droite
                if (y + 1 < height) {
                    neighbors.add(new Tree(x, y + 1));
                }
                return neighbors;
            }
        }
        return null;
    }

    /**
     * Method to display the list of trees neighbors of this tree in a forest in the terminal
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
     *
     * @param probabilty integer for the number of probabilty
     * @return boolean to apply probability
     */
    public boolean isPropagable(int probabilty) {
        int randomNum = ThreadLocalRandom.current().nextInt(0, 101);
        return probabilty == 0 ? false : randomNum <= probabilty;
    }
}
