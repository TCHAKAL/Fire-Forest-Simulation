package ciril.forest.models;

import ciril.forest.ui.Window;

import java.util.List;

public class Simulation {

    /**
     * Change the state of cell in a forest
     * Dictionary of states :
     * 0 === living tree
     * 1 === burning tree
     * 2 === tree in ash
     *
     * @param forest the forest where to change the state cell
     * @param tree   the tree to change the state
     */
    public static void changeState(Forest forest, Tree tree) {
        //Si la position de la case dépasse la taille de la foret alors on fait rien
        if (checkTreeIncideForest(forest, tree)) {
            switch (forest.getGrille()[tree.getX()][tree.getY()]) {
                case 0: //Case avec arbre verte ==> elle passera à l'état en feu
                    forest.getGrille()[tree.getX()][tree.getY()] = 1;
                    break;
                case 1:  //Case arbre en feu ==> elle passera à l'état cendre
                    forest.getGrille()[tree.getX()][tree.getY()] = 2;
                    break;
            }
        }
    }

    /**
     * Change state for a list cells in a forest with probability
     * if cell is a living tree then apply probability to change state , else change state directly
     *
     * @param forest
     * @param listTrees
     * @param probability
     */
    public static void changeStateListCellsWithProbability(Forest forest, List<Tree> listTrees, int probability) {
        //Pour chaque arbre on vérifie si l'arbre est dans la foret
        for (Tree tree : listTrees) {
            if (checkTreeIncideForest(forest, tree)) {
                //Si la cellule contient un arbre en feu alors on change l'état à cendre
                if (forest.getGrille()[tree.getX()][tree.getY()] == 1) {
                    changeState(forest, tree);
                } else if (forest.getGrille()[tree.getX()][tree.getY()] == 0 && tree.isPropagable(probability)) {
                    // Si la cellule contient un arbre vivant et probablement propageable alors on change l'état
                    changeState(forest, tree);
                }
            }
        }
    }

    /**
     * Method forest fire propagation simulation
     *
     * @param forest      the froest in question
     * @param treeOnFire  cell on fire
     * @param probability integer for the number of probabilty for exemple 1 is 100% 2 is 1/2 == 50% ...
     * @param simulation
     */
    public static void propagate(Forest forest, Tree treeOnFire, int probability, Window simulation) throws InterruptedException {

        //Charger la liste des arbres voisins de l'arbre en feu
        List<Tree> neighbors = treeOnFire.getNeighbors(forest);
        //Changer l'état des arbres voisins de l'arbre en feu avec la probalité donnée
        changeStateListCellsWithProbability(forest, neighbors, probability);
        //changer l'état de l'arbre en feu à l'état cendre
        changeState(forest, treeOnFire);
        //afficher la foret à l'instant t+1
        forest.displayForest();
        simulation.displayForest(forest);
        //Pour chaque voisin à l'état 0 (arbre vivant) alors propager ( appel récursive)
        for (Tree neighbor : neighbors) {
            if (forest.getGrille()[neighbor.getX()][neighbor.getY()] == 1) {
                propagate(forest, neighbor, probability, simulation);
            }
        }
    }

    /**
     * Check tree if it's inside the forest
     * @param forest
     * @param tree
     * @return boolean
     */
    private static boolean checkTreeIncideForest(Forest forest, Tree tree) {
//        if (tree.getX() > forest.getGrille().length || tree.getY() > forest.getGrille()[tree.getX()].length) {
//            System.err.println("La position indiqué est en dehors de la foret");
//            return false;
//        }
        return true;
    }
}
