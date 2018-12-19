package at.jku.ssw.battleship.view;

import at.jku.ssw.battleship.model.Game;
import at.jku.ssw.battleship.model.State;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static javax.swing.JOptionPane.showMessageDialog;

public class BattleShipGui extends JFrame implements MouseListener {
    //.jar file images source
    /*ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    URL resource = classLoader.getResource("O.png");
    ClassLoader classLoader2 = Thread.currentThread().getContextClassLoader();
    URL resource2 = classLoader.getResource("X.png");*/
    private ImageIcon imageIcon2 = new ImageIcon(new ImageIcon("O.png").getImage().getScaledInstance(90, 90, Image.SCALE_FAST));
    private ImageIcon imageIcon3 = new ImageIcon(new ImageIcon("X.png").getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
    private Game game = new Game(Game.createRandomPlayingField(5, 12));
    private JPanel panel;
    private JLabel labels[][] = new JLabel[5][5];
    private JLabel ships = new JLabel();

    //constructor
    public BattleShipGui() {
        creator();
        gridLabelCreator();
        windowGUI();
        this.add(panel, BorderLayout.CENTER);
        this.add(ships, BorderLayout.NORTH);
    }
    //setting the gui information
    private void windowGUI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(500, 500));
        this.setSize(500, 500);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setTitle("Battleship");
    }
    //create the component
    private void creator() {

        ships.setText("Ships" + game.getShipCount());
        panel = new JPanel();
        GridLayout experimentLayout = new GridLayout(5, 5, 2, 2);
        panel.setLayout(experimentLayout);

        for (int r = 0; r < labels.length; r++) {
            //setting the Jlabels features for each one
            for (int c = 0; c < labels[r].length; c++) {
                labels[r][c] = new JLabel();
                labels[r][c].setHorizontalTextPosition(JLabel.CENTER);
                labels[r][c].setVerticalAlignment(SwingConstants.CENTER);
                labels[r][c].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                labels[r][c].setBackground(Color.LIGHT_GRAY);


                labels[r][c].setOpaque(true);
                labels[r][c].addMouseListener(this);
            }

        }
    }
//adding Jframes to the panel
    private void gridLabelCreator() {
        for (int c = 0; c < labels.length; c++) {
            for (int r = 0; r < labels[c].length; r++) {
                panel.add(labels[r][c]);
            }
        }
    }
// mouse event handling
    @Override
    public void mouseClicked(MouseEvent e) {
        for (int r = 0; r < labels.length; r++) {
            for (int c = 0; c < labels[r].length; c++) {
                if (labels[c][r] == e.getSource()) {
                    game.fireAt(c, r);
                    //ships count
                    ships.setText("ships " + game.getShipCount());

                    for (int t = 0; t < labels.length; t++) {
                        for (int m = 0; m < labels[t].length; m++) {

                            if (game.getField().getState(m, t) == State.MISS) {
                                labels[m][t].setIcon(imageIcon2);


                            } else if (game.getField().getState(m, t) == State.HIT) {
                                labels[m][t].setIcon(imageIcon3);

                            }
                        }


                    }
                    //pop up window
                    if(game.getShipCount()==0){
                        showMessageDialog(null, "YOU WIN THE GAME");
                    }


                }

            }
        }
    }


    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
//main
    public static void main(String[] args) {
        new BattleShipGui();
    }
}


