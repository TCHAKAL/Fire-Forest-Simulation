package ciril.forest.ui;

import ciril.forest.models.Forest;

import javax.swing.*;
import java.awt.*;

public class Fenetre {
    public Fenetre(Forest forest) {
        JFrame simulation = new JFrame();
        simulation.setSize(1200, 750);
        simulation.setTitle("Simulation de feu de foret");
        //Instanciation d'un objet JPanel
        JPanel pan = new JPanel();

        //Définition de sa couleur de fond
        pan.setBackground(Color.BLACK);

        //On prévient notre JFrame que notre JPanel sera son content pane
        simulation.setContentPane(pan);

        simulation.setLayout(new GridLayout(forest.getGrille().length, forest.getGrille()[0].length));
        Grille g = new Grille(forest.getGrille().length, forest.getGrille()[0].length);
        for (int i = 0; i < forest.getGrille().length; i++) {
            for (int j = 0; j < forest.getGrille()[i].length; j++) {
                if (forest.getGrille()[i][j] == 1) {
                    g.addOnFire(i, j);
                } else if (forest.getGrille()[i][j] == 2) {
                    g.addOnAsh(i, j);
                }
            }
        }
        displayMatrix(simulation, g.getMatrice());
        simulation.setVisible(true);
    }

    public static void displayMatrix(JFrame f, JButton[][] matrix) {
        JButton[][] matrice = matrix;
        for (int i = 0; i < matrice.length; i++) {
            for (int j = 0; j < matrice.length; j++) {
                f.getContentPane().add(matrice[i][j]);
                if (matrice[i][j].getText().equals("0")) {
                    matrice[i][j].setBackground(Color.green);
                } else if (matrice[i][j].getText().equals("1")) {
                    matrice[i][j].setBackground(Color.red);
                } else {
                    matrice[i][j].setBackground(Color.gray);
                }
            }
        }
    }
}
