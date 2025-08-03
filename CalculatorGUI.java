// src/CalculatorGUI.java
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CalculatorGUI extends JFrame implements ActionListener {
    private final JTextField display;
    private String operand1 = "";
    private String operand2 = "";
    private String operator = "";

    public CalculatorGUI() {
        setTitle("Calculator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(4, 4, 5, 5));
        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "C", "0", "=", "+"
        };

        for (String text : buttons) {
            JButton btn = new JButton(text);
            btn.setFont(new Font("Arial", Font.BOLD, 20));
            btn.addActionListener(this);
            panel.add(btn);
        }

        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(panel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if ("0123456789".contains(cmd)) {
            if (operator.isEmpty()) {
                operand1 += cmd;
                display.setText(operand1);
            } else {
                operand2 += cmd;
                display.setText(operand2);
            }
        } else if ("+-*/".contains(cmd)) {
            operator = cmd;
        } else if ("=".equals(cmd)) {
            try {
                double num1 = Double.parseDouble(operand1);
                double num2 = Double.parseDouble(operand2);
                double result = switch (operator) {
                    case "+" -> num1 + num2;
                    case "-" -> num1 - num2;
                    case "*" -> num1 * num2;
                    case "/" -> num2 == 0 ? Double.NaN : num1 / num2;
                    default -> num2;
                };
                display.setText(Double.isNaN(result) ? "Error" : String.valueOf(result));
            } catch (NumberFormatException ex) {
                display.setText("Error");
            }
            operand1 = operand2 = operator = "";
        } else if ("C".equals(cmd)) {
            operand1 = operand2 = operator = "";
            display.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CalculatorGUI::new);
    }
}
