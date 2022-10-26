import java.awt.*;
import java.awt.Graphics;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements ActionListener {
    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 25;
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
    static final int DELAY = 75;
    int bodyParts = 1;
    int appleEaten;
    int appleX;
    int appleY;
    char direction = 'R';
    boolean running = true;
    Timer timer;
    Random random;

    GamePanel(){
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.setVisible(true);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());

    }
    public void move () {



    }
    public void startGame() {
        newApple();
        running = true;
        timer = new Timer(DELAY,this);
        if (running){
            timer.setDelay(DELAY);
            timer.start();
        }
        else {
            timer.stop();
        }


    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
        drawApple(g);

    }

    public void draw (Graphics g) {
        int intervalle_line = SCREEN_HEIGHT / UNIT_SIZE;
        int intervalle_col = SCREEN_WIDTH / UNIT_SIZE;
        for (int y = 0; y < SCREEN_HEIGHT; y++ ){
            if (y == intervalle_line){
                g.drawLine(0,intervalle_line,SCREEN_WIDTH,intervalle_line);
                intervalle_line += UNIT_SIZE;
            }
        }
        for (int x = 0 ; x < SCREEN_WIDTH; x++) {
            if (x == intervalle_col) {
                g.drawLine(intervalle_col, 0, intervalle_col, SCREEN_HEIGHT);
                intervalle_col += UNIT_SIZE;
            }
        }
    }
    public void drawApple(Graphics g){
        if(running){
            g.setColor(Color.RED);
            newApple();
        }
    }
    public void checkCollisions() {

    }
    public void gameOver(Graphics g){

        timer.stop();
    }
    public void newApple(){
        appleX = random.nextInt(0,UNIT_SIZE);
        appleY = random.nextInt(0,UNIT_SIZE);
        appleX = appleX * UNIT_SIZE;
        appleY = appleY * UNIT_SIZE;
    }
    public void actionPerformed(ActionEvent e){

       // paintComponent();
    }
    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {

            switch(e.getKeyCode()) {

                case KeyEvent.VK_LEFT:
                    if(direction != 'R') {
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if(direction != 'L') {
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if(direction != 'D') {
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if(direction != 'U') {
                        direction = 'D';
                    }
                    break;
            }
        }
    }
}


