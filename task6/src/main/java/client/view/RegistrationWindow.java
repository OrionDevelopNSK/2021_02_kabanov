package client.view;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.function.Consumer;

public class RegistrationWindow extends JDialog implements RegistrationWindowInterface {
    public static final int MAX_LENGTH_NAME = 12;
    public static final int MIN_LENGTH_NAME = 2;

    private Consumer<String> userNameListener;
    private Consumer<Integer> portNumberListener;
    private final JLabel warningLabel;
    private JButton okButton;
    private JCheckBox checkBox;
    private JTextField portField;

    public RegistrationWindow(JFrame frame) {
        super(frame, "Регистрация", true);
        JTextField nameField = new JTextField();
        createPortNumber();
        createCheckBoxPort(portField);
        createOkButton(nameField);
        JLabel label = new JLabel("Введите имя (от 1 до 12 символов):", SwingConstants.CENTER);
        warningLabel = new JLabel("", SwingConstants.CENTER);
        label.setPreferredSize(new Dimension(200, 35));
        warningLabel.setPreferredSize(new Dimension(250, 50));
        warningLabel.setForeground(Color.RED);
        nameField.setPreferredSize(new Dimension(175, 35));
        JPanel allPanel = new JPanel();
        allPanel.setBorder(BorderFactory.createEtchedBorder());
        allPanel.add(checkBox);
        allPanel.add(portField);
        allPanel.add(label);
        allPanel.add(nameField);
        allPanel.add(okButton);
        allPanel.add(warningLabel);
        allPanel.setPreferredSize(new Dimension(280, 250));
        Container contentPane = getContentPane();
        contentPane.add(allPanel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        setPreferredSize(new Dimension(280, 250));
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }

    @Override
    public void setTextToLabelRegistrationWindow(String description) {
        warningLabel.setText(description);
        okButton.setEnabled(true);
    }

    @Override
    public void closeRegistrationWindow(){
        dispose();
    }

    @Override
    public void setUserNameListenerRegistrationWindow(Consumer<String> userNameListener) {
        this.userNameListener = userNameListener;
    }

    @Override
    public void setPortNumberListenerRegistrationWindow(Consumer<Integer> portNumberListener) {
        this.portNumberListener = portNumberListener;
    }

    private void createOkButton(JTextField nameField) {
        okButton = new JButton("OK");
        okButton.setPreferredSize(new Dimension(175, 35));
        okButton.addActionListener(e -> {

            if (userNameListener != null) {
                boolean isCorrect = localNameVerification(nameField);
                if (isCorrect){
                    userNameListener.accept(nameField.getText());
                    okButton.setEnabled(false);
                }
            }
            nameField.setText("");
        });
    }

    private boolean localNameVerification(JTextField nameField){
        if(nameField.getText().length() < MIN_LENGTH_NAME){
            warningLabel.setText("Имя слишком короткое");
            return false;
        }if(Character.isDigit(nameField.getText().charAt(0))){
            warningLabel.setText("Имя не может начинаться с цифры");
            return false;
        }else if(!nameField.getText().matches("[a-zA-Zа-яА-Я0-9]+")){
            warningLabel.setText("<html>Имя должно содержать<br>только цифры и буквы</html>");
            return false;
        }else if (nameField.getText().length() > MAX_LENGTH_NAME){
            warningLabel.setText("Имя слишком длинное");
            return false;
        }else{
            return true;
        }
    }

    private void createCheckBoxPort(JTextField nameField) {
        checkBox = new JCheckBox("Порт");
        checkBox.setSelected(false);
        nameField.setEnabled(false);
        nameField.setBackground(Color.lightGray);
        checkBox.addActionListener(e -> {
            if (checkBox.isSelected()) {
                nameField.setEnabled(true);
                nameField.setBackground(Color.WHITE);
            }else {
                nameField.setEnabled(false);
                nameField.setBackground(Color.lightGray);
            }
        });
    }

    private void createPortNumber() {
        portField = new JTextField("7777");
        PlainDocument document = (PlainDocument)portField.getDocument();
        document.setDocumentFilter(new DigitFilter());
        document.addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                portNumberListener.accept(Integer.parseInt(portField.getText()));
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                portNumberListener.accept(Integer.parseInt(portField.getText()));
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                portNumberListener.accept(Integer.parseInt(portField.getText()));
            }
        });
    }


    static class DigitFilter extends DocumentFilter {
        private static final String DIGITS = "\\d+";

        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            if (string.matches(DIGITS)) {
                super.insertString(fb, offset, string, attr);
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String string, AttributeSet attrs) throws BadLocationException {
            if (string.matches(DIGITS)) {
                super.replace(fb, offset, length, string, attrs);
            }
        }
    }
}







