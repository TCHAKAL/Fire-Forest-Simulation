package ciril.utils;

public interface StaticUtil {
    public static final String APP_TITLE = "Simulation de feu de foret";
    public static final String ASK_FOR_WIDHT = "Veuillez renseigner la largeur de la foret : ";
    public static final String ASK_FOR_HEIGHT = "Veuillez renseigner la hauteur de la foret : ";
    public static final String ASK_FOR_NB_TREE_ON_FIRE = "Veuillez renseigner le nombre d'arbres en feu : ";
    public static final String ASK_FOR_POSITION_TREE_ON_FIRE = "Veuillez renseigner la position de l'arbre en feu n° ";
    public static final String INITIAL_STATE = "\n----------- Etat initial ----------- ";
    public static final String FINAL_STATE = "\n----------- Etat fianl -----------  ";
    public static final String ASK_FOR_PROBABILITY = "Veuillez renseigner la probabilité de propagation en % : ";
    public static final String NB_TREES = "Nombre d'arbres : ";
    public static final String NB_TREES_ASH = "Nombre d'arbres reduits en cendre : ";
    public static final String NB_STAGE_SIMULATION = "Nombre d'étapes de la simulation : ";
    public static final String TIME_OF_SIMULATION = "Temps de la simulation en secondes : ";
    public static final String ERR_TREE_OUT_OF_FOREST = "La position indiqué est en dehors de la foret";
    public static final String ERR_INVALID_FOREST = "Vous ne pouvez pas insantier une foret avec des dimentions <= 0";
}
