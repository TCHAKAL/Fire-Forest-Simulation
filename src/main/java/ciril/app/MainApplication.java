package ciril.app;

import ciril.forest.Forest;
import ciril.forest.Tree;
import ciril.simulation.Simulation;
import ciril.ui.Window;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static ciril.utils.StaticUtil.*;

public class MainApplication {

    //Déclatation des variables
    private static int width, height, nbTreesOnFire, probability;
    private static Forest forest;
    private static Simulation simulation;
    private static List<Tree> treesOnFire;
    private static Window windowSimulation;
    private static Scanner sc;

    public static void main(String[] args) {
        sc = new Scanner(System.in);
        simulation = new Simulation();
        while (true) {
            //Lire les dimentions de la foret
            readDimensions();
            //Créer et remplir la foret
            createForest();
            //Lire le nombre d'arbre en feu à l'état initial
            readNbTreesOnFire();
            //Lire la position de chaque arbre en feu
            readPositionsTreesOnFire();
            //Afficher l'état initail de la foret
            System.out.println(INITIAL_STATE);
            displayForest();
            //Lire la propabilité de propagation de feu
            readPropability();
            //Appeler la methode de propagarion
            propagate(forest, treesOnFire, simulation, probability, windowSimulation);
            //Afficher l'état final de la foret
            System.out.println(FINAL_STATE);
            displayForest();
            //Afficher les statistiques de la simulation
            showDialogStatistics(forest, simulation, windowSimulation);
        }
    }

    /**
     * Read the dimension of the forest
     */
    private static void readDimensions() {
        //Demander à l'utilisateur la largeur et la hauteur
        System.out.print(ASK_FOR_WIDHT);
        width = sc.nextInt();
        System.out.print(ASK_FOR_HEIGHT);
        height = sc.nextInt();
    }

    /**
     * Create and fill the forest
     */
    private static void createForest() {
        //Instancier la foret
        forest = new Forest();
        //Remplir la foret
        forest.getRandomForest(width, height);
    }

    /**
     * Read number of trees on fire
     */
    private static void readNbTreesOnFire() {
        //Demander à l'utilisateur le nombre d'arbres en feu
        System.out.print(ASK_FOR_NB_TREE_ON_FIRE);
        nbTreesOnFire = sc.nextInt();
    }

    /**
     * Read positions of each tree on fire and change it state
     * and finally add it to a list treesOnFire
     */
    private static void readPositionsTreesOnFire() {
        //Initialiser la liste qui va contenir les arbres en feu
        treesOnFire = new ArrayList<>();
        //Demander à l'utilisateur les coordonnées de chaque arbre de feu
        for (int n = 0; n < nbTreesOnFire; n++) {
            System.out.println(ASK_FOR_POSITION_TREE_ON_FIRE + n);
            System.out.print(" x (entre 0 et " + width + "): ");
            int x = sc.nextInt();
            System.out.print(" y (entre 0 et " + height + "): ");
            int y = sc.nextInt();
            //Vérifier si l'arbre n'est pas déjà insérer dans la liste
            if (!treesOnFire.contains(new Tree(x, y))) {
                //Changer l'état de l'arbre en feu
                simulation.changeState(forest, new Tree(x, y));
                //Ajouter l'arbre dans la liste des arbres en feu
                treesOnFire.add(new Tree(x, y));
            }
        }
    }

    /**
     * Display the forest in terminal and in the interface Swing
     */
    private static void displayForest() {
        forest.displayForest();
        if (windowSimulation == null) {
            windowSimulation = new Window();
            windowSimulation.displayForest(forest);
        }
    }

    /**
     * Read the probability of propagation
     */
    private static void readPropability() {
        //Demander à l'utilisateur la probabilité de propagation
        System.out.print(ASK_FOR_PROBABILITY);
        probability = sc.nextInt();
    }

    /**
     * Call the method simulation of propagation
     *
     * @param forest           the forest in question
     * @param treesOnFire      list of trees on fire at instant T
     * @param simulation       the class simulation
     * @param probability      probability of propagation
     * @param windowSimulation UI for the app
     */
    private static void propagate(Forest forest, List<Tree> treesOnFire, Simulation simulation, int probability, Window windowSimulation) {
        //Pour chaque point en feu on fait la windowSimulation de la propagation
        for (Tree treeOnFire : treesOnFire) {
            simulation.propagate(forest, treeOnFire, probability, windowSimulation);
        }
    }

    /**
     * Show the number of trees; number of trees as ash and the number of states of the simulation
     *
     * @param forest           the forest in question
     * @param simulation       the class simulation
     * @param windowSimulation UI for the app to add a dialog in
     */
    private static void showDialogStatistics(Forest forest, Simulation simulation, Window windowSimulation) {
        JOptionPane.showMessageDialog(windowSimulation.getFrameSimulation(),
                NB_TREES + forest.getGrille().length * forest.getGrille()[0].length +
                        "\n" + NB_TREES_ASH + forest.getNbAsh() +
                        "\n" + NB_STAGE_SIMULATION + simulation.getNbStage()

        );
        System.out.println(NB_TREES + width * height);
        System.out.println(NB_TREES_ASH + forest.getNbAsh());
        System.out.println(NB_STAGE_SIMULATION + simulation.getNbStage());
    }
}
