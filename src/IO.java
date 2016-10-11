import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by msrabon on 11-Oct-16.
 * Project Name: K-Map.
 */
public class IO extends JFrame implements ActionListener {
    String input;
    StringBuilder terms, thisTerm;
    Scanner sc;
    Set<Character> totalVariables;
    TextField t;
    JButton b;
    JFrame frame;
    boolean fl;

    public IO() {
        fl = false;
        makeFrame();
        while (fl == false) {
            try {
                Thread.sleep(50);
            } catch (Exception e) {
            }
        }
    }

    public void displayMap(boolean[][] map, char[] vocabulary) {
        Graphics g = frame.getGraphics();
        int ycor = 200, xcor = 150, side = 50;
        g.drawLine(xcor - side / 2, ycor - side / 2, xcor - side / 2 - 30, ycor - side / 2 - 30);
        int r = map.length;
        int c = map[0].length;
        g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        if (r == 2) {
            g.drawString("0", xcor - side / 2 - 20, ycor + 5);
            g.drawString("1", xcor - side / 2 - 20, ycor + 5 + side);
            g.drawString(new Character(vocabulary[0]).toString(), xcor - side / 2 - 32, ycor - 22);
            //g.drawString(new Character(vocabulary[1]).toString(),xcor-side/2-10,ycor-side/2-16);
        } else if (r == 4) {
            g.drawString("00", xcor - side / 2 - 20, ycor + 5);
            g.drawString("01", xcor - side / 2 - 20, ycor + 5 + side);
            g.drawString("11", xcor - side / 2 - 20, ycor + 5 + 2 * side);
            g.drawString("10", xcor - side / 2 - 20, ycor + 5 + 3 * side);
            g.drawString(new Character(vocabulary[0]).toString(), xcor - side / 2 - 47, ycor - 22);
            g.drawString(new Character(vocabulary[1]).toString(), xcor - side / 2 - 32, ycor - 22);
        }
        if (c == 2) {
            g.drawString("0", xcor - 3, ycor - side / 2 - 10);
            g.drawString("1", xcor - 3 + side, ycor - side / 2 - 10);
            g.drawString(new Character(vocabulary[1]).toString(), xcor - side / 2 - 10, ycor - side / 2 - 16);
        } else if (c == 4) {
            g.drawString("00", xcor - 6, ycor - side / 2 - 10);
            g.drawString("01", xcor - 6 + side, ycor - side / 2 - 10);
            g.drawString("11", xcor - 6 + 2 * side, ycor - side / 2 - 10);
            g.drawString("10", xcor - 6 + 3 * side, ycor - side / 2 - 10);
            if (r == 2) {
                g.drawString(new Character(vocabulary[1]).toString(), xcor - side / 2 - 15, ycor - side / 2 - 16);
                g.drawString(new Character(vocabulary[2]).toString(), xcor - side / 2, ycor - side / 2 - 16);
            } else {
                g.drawString(new Character(vocabulary[2]).toString(), xcor - side / 2 - 15, ycor - side / 2 - 16);
                g.drawString(new Character(vocabulary[3]).toString(), xcor - side / 2, ycor - side / 2 - 16);
            }
        }
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                g.drawRect(xcor - side / 2, ycor - side / 2, side, side);
                if (map[i][j])
                    g.drawString("1", xcor - 5, ycor + 5);
                else
                    g.drawString("0", xcor - 5, ycor + 5);
                xcor += side;
            }
            ycor += side;
            xcor = 150;
        }
    }

    public void displaySolution(StringBuilder solution) {
        String sol = solution.toString();
        StringBuilder s1 = new StringBuilder();
        for (int i = 0; i < sol.length(); i++) {
            if (i == sol.length() - 1)
                continue;
            if (sol.charAt(i) == ',')
                s1.append('+');
            else
                s1.append(sol.charAt(i));
        }
        sol = s1.toString();
        TextField f1 = new TextField(100);
        f1.setBounds(100, 500, 500, 25);
        f1.setText("Solution: " + sol);
        f1.setEditable(false);
        frame.add(f1);
    }

    public void makeFrame() {
        frame = new JFrame("Karnaugh Map");
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel l = new JLabel("Enter Expression: ");
        frame.setLayout(null);
        l.setBounds(600 - 200, 20, 200, 25);
        frame.add(l);
        t = new TextField(50);
        t.setBounds(700 - 100, 20, 500, 25);
        frame.add(t);
        b = new JButton("Submit");
        b.setBounds(700 + 500 + 10 - 100, 20, 80, 25);
        frame.add(b);
        b.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        input = t.getText();
        thisTerm = new StringBuilder();
        terms = new StringBuilder();
        totalVariables = new HashSet<Character>();
        for (int i = 0; i < input.length(); i++) {
            if (Character.isLetter(input.charAt(i))) {
                thisTerm.append(input.charAt(i));
                totalVariables.add(input.charAt(i));
            } else if (input.charAt(i) == '!') {
                char last = thisTerm.charAt(thisTerm.length() - 1);
                thisTerm.setCharAt(thisTerm.length() - 1, Character.toLowerCase(last));
            } else if (input.charAt(i) == '+') {
                terms.append(thisTerm + ",");
                thisTerm = new StringBuilder();
            }
        }
        terms.append(thisTerm);
        fl = true;
    }
}
