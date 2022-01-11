package ru.cft.focusstart.task3.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LoseWindow extends JDialog {
    private ActionListener newGameListener;
    private ActionListener exitListener;

    public LoseWindow(JFrame owner) {
        super(owner, "Lose", true);
        GridBagLayout layout = new GridBagLayout();
        Container contentPane = getContentPane();
        contentPane.setLayout(layout);
        contentPane.add(createLoseLabel(layout));
        contentPane.add(createNewGameButton(layout));
        contentPane.add(createExitButton(layout));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        setPreferredSize(new Dimension(300, 130));
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }

    public void setNewGameListenerLoseWindow(ActionListener newGameListener) {
        this.newGameListener = newGameListener;
    }

    public void setExitListenerLoseWindow(ActionListener exitListener) {
        this.exitListener = exitListener;
    }

    private JLabel createLoseLabel(GridBagLayout layout) {
        JLabel label = new JLabel("You lose!");
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        layout.setConstraints(label, gbc);
        return label;
    }

    private JButton createNewGameButton(GridBagLayout layout) {
        JButton newGameButton = new JButton("New game");
        newGameButton.setPreferredSize(new Dimension(100, 25));
        newGameButton.addActionListener(e -> {
            dispose();
            if (newGameListener != null) {
                newGameListener.actionPerformed(e);
            }
        });
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.5;
        gbc.insets = new Insets(15, 0, 0, 0);
        layout.setConstraints(newGameButton, gbc);
        return newGameButton;
    }

    private JButton createExitButton(GridBagLayout layout) {
        JButton exitButton = new JButton("Exit");
        exitButton.setPreferredSize(new Dimension(100, 25));
        exitButton.addActionListener(e -> {
            dispose();
            if (exitListener != null) {
                exitListener.actionPerformed(e);
            }
            System.exit(0);
        });
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.5;
        gbc.insets = new Insets(15, 5, 0, 0);
        layout.setConstraints(exitButton, gbc);
        return exitButton;
    }
}
