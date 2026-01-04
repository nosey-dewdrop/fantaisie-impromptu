package day4;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Sudoku {
      int boardWidth = 400; 
      int boardHeight = 400;

      JFrame frame = new JFrame("sudoku!!!");
      JLabel textLabel = new JLabel();
      JPanel textPanel = new JPanel();


      public Sudoku(){

            frame.setVisible(true);
            frame.setSize(boardWidth, boardHeight);
            frame.setResizable(false); // user cannot resize it.
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // when click x, the app closes.
            frame.setLocationRelativeTo(null);
            frame.setLayout(new BorderLayout()); // place components in the window. nsewc place them. 

      }
}
