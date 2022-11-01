import java.awt.*;
import java.awt.Graphics;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements ActionListener {
    static final int SCREEN_WIDTH = 625;
    static final int SCREEN_HEIGHT = 625;
    static final int UNIT_SIZE = 25;
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / (UNIT_SIZE*UNIT_SIZE);

    static final int DELAY = 100;
    final int[] x = new int[GAME_UNITS];
    final int[] y = new int[GAME_UNITS];

    int bodyParts = 3;
    int appleEaten;
    int appleX;
    int appleY;
    char direction = 'L';
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
        startGame();

    }
    public void move () {
        for(int i = bodyParts; i > 0; i--){
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
        switch (direction) {
            case 'U' -> y[0] = y[0] - UNIT_SIZE;
            case 'D' -> y[0] = y[0] + UNIT_SIZE;
            case 'L' -> x[0] = x[0] - UNIT_SIZE;
            case 'R' -> x[0] = x[0] + UNIT_SIZE;
        }
    }
    public void initializeTabs(){
        int position = 300;
        for (int i = 0; i < bodyParts; i++){
            x[i] = position;
            y[i] = x[0];
            position += UNIT_SIZE;

        }
        System.out.print(x[0] );

    }
    public void startGame() {
        initializeTabs();
        newApple();
        running = true;
        timer = new Timer(DELAY,this);
        timer.start();
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
        if (running){
            for(int i = 0; i < bodyParts; i++){
                g.fillRect(x[i],y[i],UNIT_SIZE,UNIT_SIZE);
            }
        }
    }
    public void drawApple(Graphics g){
        if(running){
            g.setColor(Color.RED);
            g.fillRect(appleX,appleY,UNIT_SIZE,UNIT_SIZE);
        }
    }
    public void checkCollisions() {
            for (int i = 1 ; i < bodyParts; i++){
                if ((x[0] == x[i]) && (y[0] == y[i])){
                    running = false;
                }
            }
            if ((x[0] >= SCREEN_WIDTH)  || (y[0] >= SCREEN_HEIGHT)){
                running = false;
            }
            else if ((x[0] < 0) || (y[0] < 0)){
                running = false;
            }



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
        if(running) {
            move();
            checkApple();
            checkCollisions();
        }
        repaint();

    }
    public void checkApple(){
        if((x[0] == appleX) && (y[0] == appleY)){
            bodyParts++;
            appleEaten++;
            newApple();
        }
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


