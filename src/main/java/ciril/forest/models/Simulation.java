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
     * @param forest   the forest where to change the state cell
     * @param location the location to change the state
     */
    public static void changeState(Forest forest, Location location) {
        //Si la position de la case dépasse la taille de la foret alors on fait rien
        if (checkCellIncideForest(forest, location)) {
            switch (forest.getGrille()[location.getX()][location.getY()]) {
                case 0: //Case avec arbre verte ==> elle passera à l'état en feu
                    forest.getGrille()[location.getX()][location.getY()] = 1;
                    break;
                case 1:  //Case arbre en feu ==> elle passera à l'état cendre
                    forest.getGrille()[location.getX()][location.getY()] = 2;
                    break;
            }
        }
    }

    /**
     * Change state for a list cells in a forest with probability
     * if cell is a living tree then apply probabilty to change state , else change state directly
     *
     * @param forest
     * @param listCells
     * @param probability
     */
    public static void changeStateListCellsWithProbability(Forest forest, List<Location> listCells, int probability) {
        //Pour chaque point dans la foret on vérifier si le point est dans la foret
        for (Location cell : listCells) {
            if (checkCellIncideForest(forest, cell)) {
                //Si la cellule contient un arbre en feu alors on change l'état à cendre
                if (forest.getGrille()[cell.getX()][cell.getY()] == 1) {
                    changeState(forest, cell);
                } else if (forest.getGrille()[cell.getX()][cell.getY()] == 0 && cell.isPropagable(probability)) {
                    // Si la cellule contient un arbre vivant et probablement propageable alors on change l'état
                    changeState(forest, cell);
                }
            }
        }
    }

    /**
     * Method forest fire propagation simulation
     *
     * @param forest         the froest in question
     * @param locationOnFire cell on fire
     * @param probability    integer for the number of probabilty for exemple 1 is 100% 2 is 1/2 == 50% ...
     * @param simulation
     */
    public static void propagate(Forest forest, Location locationOnFire, int probability, Window simulation) throws InterruptedException {

        //afficher la liste des voisins de la case en feu
        //locationOnFire.displayNeighbors(forest);

        //Charger la liste des voisins de la cellule en feu
        List<Location> neighbors = locationOnFire.getNeighbors(forest);
        //Changer l'état des voisins de la case en feu avec la probalité donnée
        changeStateListCellsWithProbability(forest, neighbors, probability);
        //changer l'état de la case en feu à l'état cendre
        changeState(forest, locationOnFire);
        //afficher la foret à l'instant t+1
        forest.displayForest();
        simulation.displayForest(forest);
        //Pour chaque voisin à l'état 0 (arbre vivant) alors propager ( appel récursive)
        for (Location neighbor : neighbors) {
            if (forest.getGrille()[neighbor.getX()][neighbor.getY()] == 1) {
                propagate(forest, neighbor, probability, simulation);
            }
        }
    }

    private static boolean checkCellIncideForest(Forest forest, Location location) {
//        if (location.getX() > forest.getGrille().length || location.getY() > forest.getGrille()[location.getX()].length) {
//            System.err.println("La position indiqué est en dehors de la foret");
//            return false;
//        }
        return true;
    }
}
