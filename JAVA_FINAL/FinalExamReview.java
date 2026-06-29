import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class FinalExamReview extends JFrame implements ActionListener {
    private JTextField firstOp, secondOp, operator;
    private JLabel result;
    private JButton calculate;

    public static void main(String[] args) {
        FinalExamReview gui = new FinalExamReview();
        gui.setVisible(true);
    }

    public FinalExamReview() {
        super("Simple Calculator");

        //set size to be 250x100
        setSize(250, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        firstOp = new JTextField("0");
        secondOp = new JTextField("0");
        operator = new JTextField("+");
        result = new JLabel("0");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1,0));
        panel.add(firstOp);
        panel.add(operator);
        panel.add(secondOp);
        panel.add(new JLabel("="));
        panel.add(result);

        setLayout(new GridLayout(2,0));
        add(panel);

        calculate = new JButton("Calculate");
        calculate.setActionCommand("exec");
        calculate.addActionListener(this);
        add(calculate);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("exec")) {
            System.out.println("Button was pressed");
            String op1, op2, func;
            op1 = firstOp.getText().trim();
            op2 = secondOp.getText().trim();
            func = operator.getText().trim();
            double op1Double, op2Double;
            try {
                op1Double = Double.parseDouble(op1);
                op2Double = Double.parseDouble(op2);
            } catch (NumberFormatException exception) {
                ErrorWindow err = new ErrorWindow("Invalid operands!");
                err.setVisible(true);
                return;
            }
            switch (func) {
                case "+":
                    result.setText((op1Double + op2Double) + "");
                    break;
                case "-":
                    result.setText((op1Double - op2Double) + "");
                    break;
                case "*":
                    result.setText((op1Double * op2Double) + "");
                    break;
                case "/":
                    if (op2Double == 0) {
                        ErrorWindow err = new ErrorWindow("Division by 0!");
                        err.setVisible(true);
                        return;
                    }
                    result.setText((op1Double / op2Double) + "");
                    break;
                default:
                    ErrorWindow err = new ErrorWindow("Invalid operator!");
                    err.setVisible(true);
            }

        }
    }
    class ErrorWindow extends JFrame implements ActionListener {
        public ErrorWindow(String message) {
            super("Error!");
            setSize(100,100);
            setLayout(new GridLayout(2,0));
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            add(new JLabel(message));

            JButton button = new JButton("OK");
            button.addActionListener(this);
            add(button);
        }
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    }
}
