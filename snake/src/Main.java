import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
public class Main {
    public static int taille_serpent = 0;
    public static boolean [] direction_serpent = {false,true,false,false};    //haut droite bas gauche



    public static int[][] new_tableau(int largeur, int hauteur){
        int[][] tableau = new int[largeur][hauteur];
        for (int[] ints : tableau) {
            Arrays.fill(ints, 0);
        }
        return (tableau);
    }
    public static void print_array(int[][] tableau){
        for (int[] ints : tableau) {
            for (int x = 0; x < ints.length; x++) {
                if (x == 0) {
                    System.out.print("| " + ints[x]);
                }
                System.out.print(" | " + ints[x]);
                if (x == ints.length - 1) {
                    System.out.println(" | ");
                }
            }
        }
        return;
    }
    public static int[][] prepare_tableau(int[][] tableau) {
        int taille_tableau = tableau.length * tableau[0].length;
        int randomNumFruit = ThreadLocalRandom.current().nextInt(0, taille_tableau);
        int randomNumSpawn = ThreadLocalRandom.current().nextInt(0, taille_tableau);
        for (int y = 0; y < tableau.length; y++) {
            for (int x = 0; x < tableau.length; x++) {
                if (randomNumFruit == 0) {
                    tableau[x][y] = 2;
                }
                if (randomNumSpawn == 0) {
                    tableau[x][y] = 1;
                }
                randomNumSpawn--;
                randomNumFruit--;
            }
        }
        return (tableau);
    }
    public static void choose_direction(char key_pressed){
        for (int i = 0; i < direction_serpent.length; i++){
            if (key_pressed == 'z'){
                direction_serpent[0] = true;
            }
            else if (key_pressed == 'd'){
                direction_serpent[1] = true;
            }
            else if (key_pressed == 's'){
                direction_serpent[2] = true;
            }
            else if (key_pressed == 'q'){
                direction_serpent[3] = true;
            }
            else{
                direction_serpent[i] = false;
            }
        }
    }
    public static void move(){

    }

    public static void main(String [] args){
        //print_array(prepare_tableau(new_tableau(17,14)));
        //JFrame frame = new JFrame("Bobux");
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //frame.setSize(400,400);
        //frame.setVisible(true);
        //new main();
        new GameFrame ();

    }

}

