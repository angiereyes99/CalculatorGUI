package edu.csc413.calculator.evaluator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EvaluatorUI extends JFrame implements ActionListener {

    private TextField txField = new TextField();
    private Panel buttonPanel = new Panel();
    private StringBuilder strBuild;
    // total of 20 buttons on the calculator,
    // numbered from left to right, top to bottom
    // bText[] array contains the text for corresponding buttons
    private static final String[] bText = {
            "7", "8", "9", "+", "4", "5", "6", "-", "1", "2", "3",
            "*", "0", "^", "=", "/", "(", ")", "C", "CE"
    };

    private Button[] buttons = new Button[bText.length];

    public static void main(String argv[]) {
        EvaluatorUI calc = new EvaluatorUI();
    }

    public EvaluatorUI() {
        setLayout(new BorderLayout());
        this.txField.setPreferredSize(new Dimension(600, 50));
        this.txField.setFont(new Font("Courier", Font.BOLD, 28));

        add(txField, BorderLayout.NORTH);
        txField.setEditable(false);

        add(buttonPanel, BorderLayout.CENTER);
        buttonPanel.setLayout(new GridLayout(5, 4));

        //create 20 buttons with corresponding text in bText[] array
        Button bt;
        for (int i = 0; i < EvaluatorUI.bText.length; i++) {
            bt = new Button(bText[i]);
            bt.setFont(new Font("Courier", Font.BOLD, 28));
            buttons[i] = bt;
        }

        //add buttons to button panel
        for (int i = 0; i < EvaluatorUI.bText.length; i++) {
            buttonPanel.add(buttons[i]);
        }

        //set up buttons to listen for mouse input
        for (int i = 0; i < EvaluatorUI.bText.length; i++) {
            buttons[i].addActionListener(this);
        }

        setTitle("Calculator");
        setSize(400, 400);
        setLocationByPlatform(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     *  CITATIONS: Code influenced by sources:
     *        https://www.geeksforgeeks.org/java-swing-simple-calculator/
     *        https://www.leepoint.net/examples/components/calculator/calc.html
     *        https://stackoverflow.com/questions/22519055/java-add-the-number-or-symbol-to-the-text-field-when-pressing-a-button
     *  NOTE: This method allows buttons on calculator GUI to respond
     *        and execute. Used for loops to check for buttons and if buttons
     *        clicked were part of the operator, it would execute calculations
     *        based on priority and operator.
     */
    public void actionPerformed(ActionEvent eventObject) {
        Object txt = eventObject.getSource();
        for(int i = 0; i < 14; i++) { //this for loop iterates through the bText array buttons 0-13
            if (txt.equals(buttons[i])) {
                txField.setText(txField.getText() + bText[i]); //this displays said buttons to the txt field
            } else if (txt.equals(buttons[14])) { //if it is "=" it will give the answer
                Evaluator answer = new Evaluator();
                txField.setText(Integer.toString(answer.eval(txField.getText())));
            }
        }
        for(int i = 15; i < 18; i++ ){
            if (txt.equals(buttons[i])) {
                txField.setText(txField.getText() + bText[i]);
            }
        }
        for(int i = 18; i < 20; i++){
            if(txt.equals(buttons[i])){
                txField.setText(""); // THE CE and C button action
            }
        }
    }
}