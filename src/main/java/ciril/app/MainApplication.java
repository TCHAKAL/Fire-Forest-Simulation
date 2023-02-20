package ciril.app;

import ciril.forest.Forest;
import ciril.forest.Tree;
import ciril.simulation.Simulation;
import ciril.utils.StaticUtil;
import ciril.ui.Window;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static ciril.utils.StaticUtil.*;

public class MainApplication {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Simulation simulation = new Simulation();
        while (true) {
            //Demander à l'utilisateur la largeur et la hauteur
            System.out.print(ASK_FOR_WIDHT);
            int l = sc.nextInt();
            System.out.print(ASK_FOR_HEIGHT);
            int h = sc.nextInt();

            //Déclarer la foret
            Forest forest = new Forest();
            //Remplir la foret
            forest.getRandomForest(h, l);
            //Demander à l'utilisateur le nombre de points en feu
            System.out.print(ASK_FOR_NB_TREE_ON_FIRE);
            int nbPointOnFire = sc.nextInt();
            //Déclarer la liste qui va contenir les points en feu
            List<Tree> pointsOnFire = new ArrayList<>();

            //Demander à l'utilisateur les coordonnées de chaque point de feu
            for (int n = 0; n < nbPointOnFire; n++) {
                System.out.println(ASK_FOR_POSITION_TREE_ON_FIRE + n);
                System.out.print(" x (entre 0 et " + l + "): ");
                int x = sc.nextInt();
                System.out.print(" y (entre 0 et " + h + "): ");
                int y = sc.nextInt();
                //Vérifier si le point n'est pas déjà insérer dans la liste
                if (!pointsOnFire.contains(new Tree(x, y))) {
                    //Changer l'état du point en feu
                    simulation.changeState(forest, new Tree(x, y));
                    //Ajouter le point dans la liste des points en feu
                    pointsOnFire.add(new Tree(x, y));
                }
            }
            //Afficher l'état initail de la foret
            System.out.println(INITIAL_STATE);
            forest.displayForest();
            Window windowSimulation = new Window();
            windowSimulation.displayForest(forest);

            //Demander à l'utilisateur la probabilité de propagation
            System.out.print(ASK_FOR_PROBABILITY);
            int probability = sc.nextInt();
            //appeler la methode de propagarion
            propagate(forest, pointsOnFire, simulation, probability, windowSimulation);

            //Afficher l'état initail de la foret
            System.out.println(FINAL_STATE);
            forest.displayForest();
            //windowSimulation.displayForest(forest);

            System.out.println(NB_TREES + l * h);
            System.out.println(NB_TREES_ASH + forest.getNbAsh());
            System.out.println(NB_STAGE_SIMULATION + simulation.getNbStage());

            showDialogStatistics(forest,simulation,windowSimulation);

        }
    }

    private static void propagate(Forest forest, List<Tree> pointsOnFire, Simulation simulation, int probability, Window windowSimulation) {
        //Pour chaque point en feu on fait la windowSimulation de la propagation
        for (Tree treeOnFire : pointsOnFire) {
            simulation.propagate(forest, treeOnFire, probability, windowSimulation);
        }
    }

    private static void showDialogStatistics(Forest forest, Simulation simulation, Window windowSimulation) {
        JOptionPane.showMessageDialog(windowSimulation.getFrameSimulation(),
                NB_TREES + forest.getGrille().length * forest.getGrille()[0].length +
                        "\n" + NB_TREES_ASH + forest.getNbAsh() +
                        "\n" + NB_STAGE_SIMULATION + simulation.getNbStage()

        );
    }
}
