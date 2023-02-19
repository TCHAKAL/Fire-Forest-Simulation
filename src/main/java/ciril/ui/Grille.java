package ciril.ui;

import javax.swing.JButton;

public class Grille{

    JButton[][] matrice;
    JButton b;

    public Grille(int l, int L){
        matrice = new JButton[l][L];
        for(int i = 0; i< l; i++){
            for(int j = 0; j < L; j++){
                b = new JButton("0");
                b.setEnabled(false);
                matrice[i][j] = b;
            }
        }
    }

    public void addOnFire(int x, int y){
        matrice[x][y].setText("1");
        matrice[x][y].setEnabled(false);
    }
    public void addOnAsh(int x, int y){
        matrice[x][y].setText("2");
        matrice[x][y].setEnabled(false);
    }

    public void display(){
        int i,j;
        for (i=0;i<matrice.length;i++){
            for (j=0;j<matrice.length;j++){
                System.out.print(matrice[i][j].getText() + " ");
            }
            System.out.println(" ");
        }
    }

    public JButton[][] getMatrice() {
        return matrice;
    }


}