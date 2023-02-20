package ciril.ui;

import ciril.forest.Forest;
import ciril.utils.StaticUtil;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class SimulationWindow {
    JFrame frameSimulation;
    JPanel matrixPanel;

    public SimulationWindow() {
        this.frameSimulation = new JFrame();
        frameSimulation.setSize(1200, 750);
        this.matrixPanel = new JPanel();
        matrixPanel.setBackground(Color.BLACK);
    }

    /**
     * Display forest as matrix 2D
     *
     * @param forest
     */
    public void displayForest(Forest forest) {
        frameSimulation.setTitle(StaticUtil.APP_TITLE);
        frameSimulation.setVisible(true);
        frameSimulation.setContentPane(matrixPanel);
        //Rafrichir le panel pour voir chaque changement en temps réel
        refreshContent(forest);
    }


    /**
     * @param forest
     */
    private void refreshContent(Forest forest) {
        //Vider le panel
        matrixPanel.removeAll();
        //valider le suppression
        matrixPanel.validate();
        //
        changeCellsState(forest);
        //Revalider le panel
        matrixPanel.revalidate();
        //Rafrichir le panel
        matrixPanel.repaint();
        //Ajouter un temps pour bruler un arbre (pour l'affichage seulement)
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Change cells state like each tree in the forest
     *
     * @param forest
     */
    private void changeCellsState(Forest forest) {
        //Récupérer les dimentions de la foret
        int width = forest.getGrille().length;
        int height = forest.getGrille()[0].length;
        //Instancier un gridLayout pour l'affichage
        frameSimulation.setLayout(new GridLayout(width, height));
        //Instancier une grille avec les memes dimentions de la foret
        Grille grille = new Grille(width, height);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                //Si l'arbre est en feu alors changer le numéro de button à 1
                if (forest.getGrille()[i][j] == 1) {
                    grille.addOnFire(i, j);
                } else
                    //Si l'arbre est en cendre alors changer le numéro de button à 2
                    if (forest.getGrille()[i][j] == 2) {
                        grille.addOnAsh(i, j);
                    }
            }
        }
        //Afficher la grille
        displayMatrix(grille.getMatrix());
    }

    /**
     * Customise the display for each cell in the matrix
     *
     * @param matrix
     */
    private void displayMatrix(JButton[][] matrix) {
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
