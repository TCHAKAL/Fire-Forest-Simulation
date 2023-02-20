package ciril.app;

import ciril.forest.Forest;
import ciril.forest.Tree;
import ciril.simulation.Simulation;
import ciril.ui.Window;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainApplication {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Simulation simulation = new Simulation();
        while (true) {
            //Déclarer le nombre d’étapes écoulées
            int nbStage = 0;

            //Demander à l'utilisateur la largeur et la hauteur
            System.out.print("Saisir la largeur de la foret : ");
            int l = sc.nextInt();
            System.out.print("Saisir la hauteur de la foret : ");
            int h = sc.nextInt();

            //Déclarer la foret
            Forest forest = new Forest();
            //Remplir la foret
            forest.getRandomForest(h, l);
            //Demander à l'utilisateur le nombre de points en feu
            System.out.print("Saisir combien de points sont en feu : ");
            int nbPointOnFire = sc.nextInt();
            //Déclarer la liste qui va contenir les points en feu
            List<Tree> pointsOnFire = new ArrayList<>();

            //Demander à l'utilisateur les coordonnées de chaque point de feu
            for (int n = 0; n < nbPointOnFire; n++) {
                System.out.print("Saisir les coordonnées du point de feu n° " + (n + 1) + "\n x (entre 0 et " + l + ") : ");
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
            System.out.println("\n----------- Etat initial ----------- ");
            forest.displayForest();
            Window windowSimulation = new Window();
            windowSimulation.displayForest(forest);

            //Demander à l'utilisateur la probabilité de propagation
            System.out.print("Saisir la probabilité de propagation en % : ");
            int p = sc.nextInt();
            //Pour chaque point en feu on fait la windowSimulation de la propagation
            for (Tree treeOnFire : pointsOnFire) {
                simulation.propagate(forest, treeOnFire, p, windowSimulation);
            }
            //Afficher l'état initail de la foret
            System.out.println("\n----------- Etat Fianl ----------- ");
            forest.displayForest();
            windowSimulation.displayForest(forest);

            System.out.println("Nombre d'arbres : "+l*h);
            System.out.println("Nombre d'arbres reduites en cendre : "+forest.getNbAsh());
            System.out.println("Nombre d'étapes de la simulation : "+simulation.getNbStage());
        }
    }
}
