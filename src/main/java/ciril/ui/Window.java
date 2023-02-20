package ciril.ui;

import ciril.forest.Forest;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class Window {
    JFrame frameSimulation;
    JPanel matrix;

    public Window() {
        this.frameSimulation = new JFrame();
        frameSimulation.setSize(1200, 750);
        this.matrix = new JPanel();
        matrix.setBackground(Color.BLACK);
    }

    public void displayForest(Forest forest) {
        frameSimulation.setTitle("Simulation de feu de foret");
        frameSimulation.setVisible(true);
        frameSimulation.setContentPane(matrix);
        //Rafrichir le panel
        refreshContent(forest);
    }


    public void refreshContent(Forest forest) {
        matrix.removeAll();
        matrix.validate();//valider le suppression

        int l = forest.getGrille().length;
        int h = forest.getGrille()[0].length;
        frameSimulation.setLayout(new GridLayout(l, h));
        Grille grille = new Grille(l, h);
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < forest.getGrille()[i].length; j++) {
                if (forest.getGrille()[i][j] == 1) {
                    grille.addOnFire(i, j);
                } else if (forest.getGrille()[i][j] == 2) {
                    grille.addOnAsh(i, j);
                }
            }
        }
        displayMatrix(grille.getMatrice());
        // long start = System.currentTimeMillis();

        matrix.revalidate();
        matrix.repaint();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void displayMatrix(JButton[][] matrix) {
        JButton[][] matrice = matrix;
        for (int i = 0; i < matrice.length; i++) {
            for (int j = 0; j < matrice[i].length; j++) {
                frameSimulation.getContentPane().add(matrice[i][j]);
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
