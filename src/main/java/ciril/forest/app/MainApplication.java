package ciril.forest.app;

import ciril.forest.models.Forest;
import ciril.forest.models.Location;
import ciril.forest.models.Simulation;
import ciril.forest.ui.Fenetre;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainApplication {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
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
            List<Location> pointsOnFire = new ArrayList<>();

            //Demander à l'utilisateur les coordonnées de chaque point de feu
            for (int n = 0; n < nbPointOnFire; n++) {
                System.out.print("Saisir les coordonnées du point de feu n° " + (n + 1) + "\n x (entre 0 et "+l+") : ");
                int x = sc.nextInt();
                System.out.print(" y (entre 0 et "+h+"): ");
                int y = sc.nextInt();
                //Vérifier si le point n'est pas déjà insérer dans la liste
                if(!pointsOnFire.contains( new Location(x, y))){
                    //Changer l'état du point en feu
                    Simulation.changeState(forest, new Location(x, y));
                    //Ajouter le point dans la liste des points en feu
                    pointsOnFire.add(new Location(x, y));
                }
            }
            //Afficher l'état initail de la foret
            System.out.println("\n----------- Etat initial ----------- ");
            forest.displayForest();
            //Demander à l'utilisateur la probabilité de propagation
            System.out.print("Saisir la probabilité de propagation (exemple:1 pour 100%, 2 pour 50%, 3 pour 33%.....) : ");
            int p = sc.nextInt();
            //Pour chaque point en feu on fait la simulation de la propagation
            for (Location locationOnFire : pointsOnFire) {
                Simulation.propagate(forest, locationOnFire,p);
            }
        }
    }
}
