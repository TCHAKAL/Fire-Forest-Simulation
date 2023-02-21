package ciril.ui;

import javax.swing.JButton;

public class Grille {

    JButton[][] matrix;
    JButton button;

    /**
     *
     * @param width
     * @param height
     */
    public Grille(int width, int height) {
        matrix = new JButton[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                button = new JButton("0");
                button.setEnabled(false);
                matrix[i][j] = button;
            }
        }
    }

    /**
     * Set the state of button in position (x,y) at 1 on fire
     * @param x
     * @param y
     */
    public void addOnFire(int x, int y) {
        matrix[x][y].setText("1");
        matrix[x][y].setEnabled(false);
    }

    /**
     * Set the state of button in position (x,y) at 1 at ash
     * @param x
     * @param y
     */
    public void addOnAsh(int x, int y) {
        matrix[x][y].setText("2");
        matrix[x][y].setEnabled(false);
    }

    public JButton[][] getMatrix() {
        return matrix;
    }

}