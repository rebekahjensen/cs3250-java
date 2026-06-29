import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI {
    private JTextField nameField;
    private JLabel greetingLabel;
    private JButton submitButton;
    private JButton clearButton;

    public GUI() {
        JFrame frame = new JFrame("Our GUI");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setLayout(new GridLayout(3, 2, 10, 10));

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField();

        greetingLabel = new JLabel("Hello ");
        submitButton = new JButton("Submit");
        clearButton = new JButton("Clear");

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(greetingLabel);
        panel.add(new JLabel(""));
        panel.add(submitButton);
        panel.add(clearButton);

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                greetingLabel.setText("Hello " + name);
            }
        });

        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nameField.setText("");
                greetingLabel.setText("Hello ");
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new GUI();
    }
}