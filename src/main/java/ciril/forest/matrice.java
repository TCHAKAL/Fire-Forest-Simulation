package ciril.forest;

public class matrice {

    int[][] matrice=new int[5][5];
    public int[][] getMatrice() {
        return matrice;
    }

    public matrice() {
        int i, j;
        for(i=0; i<matrice.length; i++){
            for(j=0; j<matrice.length ; j++){
                matrice[i][j]= (int) Math.round(Math.random());
            }
        }
    }

    // methode affichage
    public void afficher() {
        int i, j;
        for(i=0; i<matrice.length; i++){
            for(j=0; j<matrice.length ; j++){
                System.out.print(matrice[i][j]);
            }
            System.out.println("");
        }
    }
}