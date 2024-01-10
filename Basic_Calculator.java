/** Joshua Plobner */
/** AP ITEC 2610 */
/** November 20 2022 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Basic_Calculator extends JFrame implements ActionListener {

    /** This defines variables for all elements of the GUI */
    JFrame window;
    JTextArea box;
    JButton[] number_buttons = new JButton[10];
    JButton[] function_buttons = new JButton[8];
    JButton addition_button, subtraction_button, multiply_button, division_button;
    JButton decimal_button, equal_button, clear_last_button, clear_all_button;
    char operation_button;
    double number_one, number_two, answer;
    String output;

    /** This constructor creates all elements in the GUI window */
    Basic_Calculator(){

        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); /** This allows the user to close the GUI window */
        window.setSize(300, 400); /** This creates the size of the GUI */
        window.setResizable(false); /** This will keep the GUI at a fixed size, the user will not be able to expand the window */

        box = new JTextArea(); /** This creates a text area where the users calculations are displayed based on what they press */
        box.setBounds(50, 50, 50, 50);
        box.setEditable(false); /** The user cannot type anything in the text area */

        /** This creates the scroll bar on the east side of the GUI */
        JScrollPane scrollPane = new JScrollPane(box);
        window.add(scrollPane, BorderLayout.EAST);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        /** This defines all operator buttons including clearing everything in the text area and clearing the recent input from user */
        addition_button = new JButton("+");
        subtraction_button = new JButton("-");
        multiply_button = new JButton("*");
        division_button = new JButton("/");
        decimal_button = new JButton(".");
        equal_button = new JButton("=");
        clear_last_button = new JButton("Clear Last");
        clear_all_button = new JButton("Clear All");

        /** The operator buttons are stored in an array */
        function_buttons[0] = addition_button;
        function_buttons[1] = subtraction_button;
        function_buttons[2] = multiply_button;
        function_buttons[3] = division_button;
        function_buttons[4] = decimal_button;
        function_buttons[5] = equal_button;
        function_buttons[6] = clear_last_button;
        function_buttons[7] = clear_all_button;
        decimal_button decimal = new decimal_button();
        decimal_button.addActionListener(decimal);

        /** This for loop allows the operator JButtons to work */
        for(int i = 0; i<8; i++) {
            function_buttons[i].addActionListener(this);
        }

        /** This for loop allows the number JButtons to work */
        for(int i = 0; i<10; i++) {
            number_buttons[i] = new JButton(String.valueOf(i));
            number_buttons[i].addActionListener(this);
        }

        /** This creates a panel for the number and operator buttons, it creates a 4 by 4 grid layout, there are four rows and four columns for all the required buttons */
        JPanel frame = new JPanel();
        window.add(frame, BorderLayout.SOUTH);
        frame.setLayout(new GridLayout(4, 4));

        /** This block of code adds number seven, number eight, number nine, and the addition button to the first row of the JPanel */
        frame.add(number_buttons[7]);
        frame.add(number_buttons[8]);
        frame.add(number_buttons[9]);
        frame.add(addition_button);

        /** This block of code adds number four, number five, number six, and the subtraction button to the second row of the JPanel */
        frame.add(number_buttons[4]);
        frame.add(number_buttons[5]);
        frame.add(number_buttons[6]);
        frame.add(subtraction_button);

        /** This block of code adds number one, number two, number three, and the multiply button to the third row of the JPanel */
        frame.add(number_buttons[1]);
        frame.add(number_buttons[2]);
        frame.add(number_buttons[3]);
        frame.add(multiply_button);

        /** This block of code adds number zero, decimal button, equals button, and division button to the third row of the JPanel */
        frame.add(number_buttons[0]);
        frame.add(decimal_button);
        frame.add(equal_button);
        frame.add(division_button);

        /** This creates a JPanel that adds the clear last and clear all button in the top of the GUI */
        JPanel top = new JPanel();
        top.setLayout(new GridLayout(1, 2));
        top.add(clear_last_button);
        top.add(clear_all_button);
        window.add(top, BorderLayout.NORTH);

        window.add(box);

        /** This makes the GUI visible to the user */
        window.setVisible(true);

    }

    /** This main method calls the basic_calculator class that will open the GUI */
    public static void main(String[] args) {

        new Basic_Calculator();

    }

    /** This class will check if the user clicks the decimal more than once, it will throw an error if this is the case */
    class decimal_button implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == decimal_button) {
                box.setText(box.getText().concat("."));
                operation_button = '.';
            }
            if(box.getText().contains("..")) {
                try {
                    throw new Exception("ERROR! \n You cannot press decimal button more than once!");
                } catch (Exception e1) {
                    box.setText("ERROR! \n You cannot press decimal button more than once!");
                }
            }
        }
    }

    /** This method will determine what buttons the user clicks, and it will be displayed in the text area. Additionally, the switch/case will determine what the answers are for a given equation, the answer along with the equation will be displayed */
    @Override
    public void actionPerformed(ActionEvent e) {

        /** This determines which button to display to the screen based on what the user clicks. If the user clicks clear all then the text area will be empty */
        for(int i = 0; i < 10; i++) {
            if(e.getSource() == number_buttons[i]) {
                box.setText(box.getText().concat(String.valueOf(i)));
            }
            if(e.getSource() == clear_all_button) {
                box.setText("");
            }

        }

        /** This determines if the user presses clear last, if they do then the most recent character will be removed from the string */
        if(e.getSource() == clear_last_button) {
            String string = box.getText();
            box.setText("");
            for(int j = 0; j < string.length()-1; j++){
                int k = string.length();
                box.setText(string.replace(String.valueOf(string.charAt(k-1)),""));
            }
        }

        /** This determines if the user presses the addition button */
        if(e.getSource() == addition_button) {
            number_one = Double.parseDouble(box.getText());
            box.setText("");
            operation_button = '+';
        }

        /** This determines if the user presses the subtraction button */
        if(e.getSource() == subtraction_button) {
            number_one = Double.parseDouble(box.getText());
            box.setText("");
            operation_button = '-';
        }

        /** This determines if the user presses the multiply button */
        if(e.getSource() == multiply_button) {
            number_one = Double.parseDouble(box.getText());
            box.setText("");
            operation_button = '*';
        }

        /** This determines if the user presses the division button */
        if(e.getSource() == division_button) {
            number_one = Double.parseDouble(box.getText());
            box.setText("");
            operation_button = '/';
        }

        /** This determines if the user presses the equals button */
        if(e.getSource() == equal_button) {
            number_two = Double.parseDouble(box.getText());

            /** This switch/case performs the calculations based on the user inputs */
            switch(operation_button) {
                case '+':
                    answer = number_one + number_two;
                    output = String.format("%.2f+" + "\n" + "%.2f=" + "\n" + "%.2f", number_one, number_two, answer);
                    break;
                case '-':
                    answer = number_one - number_two;
                    output = String.format("%.2f-" + "\n" + "%.2f=" + "\n" + "%.2f", number_one, number_two, answer);
                    break;
                case'*':
                    answer = number_one * number_two;
                    output = String.format("%.2f*" + "\n" + "%.2f=" + "\n" + "%.2f", number_one, number_two, answer);
                    break;
                case'/':
                    answer = number_one / number_two;
                    output = String.format("%.2f/" + "\n" + "%.2f=" + "\n" + "%.2f", number_one, number_two, answer);
                    break;
                case '.':

            }

            /** This returns the answer in the text area */
            box.setText(box.getText().concat("="));
            box.setText(output);
            number_one = answer;

            /** This allows the program to decide if the user is trying to divide by zero, if the user tries to divide by 0 then throw an error */
            if(operation_button == '/' && number_two == 0) {
                box.setText("ERROR! You cannot divide by zero!");
            }
        }
    }
}