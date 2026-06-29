

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ZellerSwingApp extends JFrame implements ActionListener{
    
    private JComboBox<String> monthBox;
    private JComboBox<Integer> dayBox;
    private JTextField yearField;
    private JButton calculateButton;
    private JButton clearButton;

    private final String[] months = {
        "January","February","March","April","May","June","July","August","September","October","November","December"
    };

    public ZellerSwingApp(){
        setTitle("Day of the Week Calculator");
        setSize(500,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel monthLabel = new JLabel("Month:");
        monthLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(monthLabel, gbc);

        monthBox = new JComboBox<>(months);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(monthBox, gbc);

        JLabel dayLabel = new JLabel("Day:");
        dayLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(dayLabel, gbc);

        Integer[] days = new Integer[31];
        for(int i = 0; i < 31; i++){
            days[i] = i + 1;

        }
        dayBox = new JComboBox<>(days);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(dayBox, gbc);

        JLabel yearLabel = new JLabel("Year:");
        yearLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(yearLabel, gbc);

        yearField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(yearField, gbc);

        calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(calculateButton, gbc);

        clearButton = new JButton("Clear");
        clearButton.addActionListener(this);
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(clearButton, gbc);

        setLocationRelativeTo(null);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calculateButton) {
            String monthName = (String) monthBox.getSelectedItem();
            int day = (Integer) dayBox.getSelectedItem();
            String yearText = yearField.getText().trim();

            int year;

            try {
                year = Integer.parseInt(yearText);
                if (year <= 0) {
                    new ErrorWindow();
                    return;
                }
            } catch (NumberFormatException ex) {
                new ErrorWindow();
                return;
            }

            int month = getMonthNumber(monthName);

            if (!isValidDate(month, day, year)) {
                new ErrorWindow();
                return;
            }

            String weekday = getDayOfWeek(month, day, year);
            new ResultWindow(monthName, day, year, weekday);

        } else if (e.getSource() == clearButton) {
            monthBox.setSelectedIndex(0); // January
            dayBox.setSelectedIndex(0);   // 1
            yearField.setText("");
        }
    }


    private int getMonthNumber(String monthName){
        for(int i = 0; i < months.length; i++){
            if(months[i].equals(monthName)){
                return i + 1;
            }
        }
        return 1;
    }

    private boolean isValidDate(int month, int day, int year){
        if(year <= 0){
            return false;
        }
        int maxDays;
        
        switch(month){
            case 4:
            case 6:
            case 9:
            case 11:
                maxDays = 30;
                break;
            case 2:
                if(isLeapYear(year)){
                    maxDays = 29;
                } else {
                    maxDays = 28;
                }
                break;
            default:
                maxDays = 31;
        }

        return day >= 1 && day <= maxDays;
    }
    private boolean isLeapYear(int year){
        return (year % 400 == 0) || (year % 4 == 0 && year % 100 != 0);
    }

    private String getDayOfWeek(int month, int day, int year){
        if(month == 1){
            month = 13;
            year --;
        } else if (month == 2){
            month = 14;
            year--;
        }

        int q = day;
        int m = month;
        int k = year % 100;
        int j = year / 100;
        int h = (q + (13 * (m + 1)) / 5 + k + (k / 4) + (j / 4) + (5 * j)) % 7;

        switch (h) {
            case 0: return "Saturday";
            case 1: return "Sunday";
            case 2: return "Monday";
            case 3: return "Tuesday";
            case 4: return "Wednesday";
            case 5: return "Thursday";
            case 6: return "Friday";
            default: return "Unknown";
        }
    }

    class ResultWindow extends JFrame {
        public ResultWindow(String month, int day, int year, String weekday) {
            setTitle("Results");
            setSize(400, 150);
            setLayout(new BorderLayout());

            JLabel resultLabel = new JLabel(month + " " + day + ", " + year + " is a " + weekday);
            resultLabel.setFont(new Font("Arial", Font.BOLD, 20));
            add(resultLabel, BorderLayout.CENTER);

            JButton closeButton = new JButton("Close");
            closeButton.addActionListener(ev -> dispose());
            add(closeButton, BorderLayout.SOUTH);

            setLocationRelativeTo(null);
            setVisible(true);
        }
    }

    class ErrorWindow extends JFrame {
        public ErrorWindow() {
            setTitle("ERROR!");
            setSize(350, 150);
            setLayout(new BorderLayout());

            JLabel errorLabel = new JLabel("Invalid date! Try again.");
            errorLabel.setFont(new Font("Arial", Font.BOLD, 18));
            add(errorLabel, BorderLayout.CENTER);

            JButton okButton = new JButton("OK");
            okButton.addActionListener(ev -> dispose());
            add(okButton, BorderLayout.SOUTH);

            setLocationRelativeTo(null);
            setVisible(true);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ZellerSwingApp app = new ZellerSwingApp();
            app.setVisible(true);
        });
    }
}
    