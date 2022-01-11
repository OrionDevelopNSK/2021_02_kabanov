package client.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DisconnectWindow extends JDialog implements DisconnectWindowInterface{
    private final JLabel warningLabel;

    public DisconnectWindow(JFrame frame) {
        super(frame, "Ошибка", true);
        warningLabel = new JLabel("Ошибка соединения с сервером ", SwingConstants.CENTER);
        warningLabel.setPreferredSize(new Dimension(280, 250));
        warningLabel.setForeground(Color.RED);
        JPanel allPanel = new JPanel();
        allPanel.setBorder(BorderFactory.createEtchedBorder());
        allPanel.add(warningLabel);
        allPanel.setPreferredSize(new Dimension(270, 240));
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
    public void setTextToLabelDisconnectWindow(String message) {
        warningLabel.setText(message);
    }


    @Override
    public void openDisconnectWindow(String description){
        setVisible(true);
        warningLabel.setText(description);
    }
}
