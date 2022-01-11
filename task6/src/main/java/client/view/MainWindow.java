package client.view;

import dto.Message;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindow extends JFrame implements MainWindowInterface {
    private MessageListener messageListener;
    private JTextPane textPaneChat;
    private JTextPane textPaneUsers;
    private JTextArea textAreaInput;
    private JButton send;

    public MainWindow() {
        super("Чат");
        createTextPaneChat();
        createTextPaneUsers();
        createTextAreaInput();
        JPanel panelChatAndUsers = new JPanel();
        JPanel panelInputTextAreaAndButton = new JPanel();
        JPanel panelAll = new JPanel();
        JPanel panelChat = new JPanel();
        JPanel panelUsers = new JPanel();
        panelChat.setPreferredSize(new Dimension(370, 320));
        panelUsers.setPreferredSize(new Dimension(170, 320));
        panelChatAndUsers.setPreferredSize(new Dimension(600, 320));
        panelInputTextAreaAndButton.setPreferredSize(new Dimension(548, 100));
        panelAll.setPreferredSize(new Dimension(575, 500));
        JScrollPane scrollPaneChat = new JScrollPane(textPaneChat,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        JScrollPane scrollPaneUsers = new JScrollPane(textPaneUsers,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        JScrollPane scrollAreaInput = new JScrollPane(textAreaInput,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        Border border = BorderFactory.createEtchedBorder();
        Border borderOnChat = BorderFactory.createTitledBorder(border, "Чат");
        Border borderOnUsers = BorderFactory.createTitledBorder(border, "Пользователи");
        Border borderOnInput = BorderFactory.createTitledBorder(border, "Поле ввода");
        panelChat.add(scrollPaneChat);
        panelUsers.add(scrollPaneUsers);
        panelChat.setBorder(borderOnChat);
        panelUsers.setBorder(borderOnUsers);
        panelChatAndUsers.add(panelChat);
        panelChatAndUsers.add(panelUsers);
        panelInputTextAreaAndButton.add(scrollAreaInput);
        panelInputTextAreaAndButton.add(createSendButton());
        panelInputTextAreaAndButton.setBorder(borderOnInput);
        panelAll.add(panelChatAndUsers);
        panelAll.add(panelInputTextAreaAndButton);
        Container contentPane = getContentPane();
        contentPane.add(panelAll);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        setPreferredSize(new Dimension(620, 490));
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }

    @Override
    public void setMessageListenerMainWindow(MessageListener messageListener) {
        this.messageListener = messageListener;
    }

    @Override
    public void setMessageTextMainWindow(Message message) {
        textPaneChat.setEditable(true);
        appendToPane(textPaneChat, message.getLocalDateTime(), Color.WHITE, true);
        appendToPane(textPaneChat, message.getNameSender(), Color.BLACK, true);
        appendToPane(textPaneChat, message.getMessage(), Color.BLACK, false);
        appendToPane(textPaneChat, "\n", Color.RED, false);
        textPaneChat.setEditable(false);
    }

    @Override
    public void setUsersTextMainWindow(String users) {
        textPaneUsers.setEditable(true);
        textPaneUsers.setText(users);
        textPaneUsers.setEditable(false);
    }

    private void createTextPaneChat(){
        textPaneChat = new JTextPane ();
        textPaneChat.setPreferredSize(new Dimension(320, 280));
        textPaneChat.setBackground(Color.lightGray);
    }

    private void createTextPaneUsers(){
        textPaneUsers = new JTextPane ();
        textPaneUsers.setPreferredSize(new Dimension(120, 280));
        textPaneUsers.setBackground(Color.lightGray);
    }

    private void createTextAreaInput() {
        textAreaInput = new JTextArea (4,32);
        textAreaInput.setLineWrap(true);
        textAreaInput.setAutoscrolls(true);
        textAreaInput.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                send.setEnabled(!textAreaInput.getText().isEmpty());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                send.setEnabled(!textAreaInput.getText().isEmpty());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                send.setEnabled(!textAreaInput.getText().isEmpty());
            }
        });
    }

    private JButton createSendButton() {
        send = new JButton("Отправить");
        send.setPreferredSize(new Dimension(150, 60));
        send.setEnabled(false);
        send.addActionListener(e -> {
            if (messageListener != null) {
                messageListener.onMessageSend(textAreaInput.getText());
                textAreaInput.setText(null);
            }
        });
        return send;
    }

    private void appendToPane(JTextPane jTextPane, String message, Color color, boolean isBoldFont){
        formatText(jTextPane, color, isBoldFont);
        jTextPane.replaceSelection(message);
    }

    private void formatText(JTextPane jTextPane, Color color, boolean isBoldFont) {
        StyleContext styleContext = StyleContext.getDefaultStyleContext();
        AttributeSet attributeSet = styleContext.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, color);
        attributeSet = styleContext.addAttribute(attributeSet, StyleConstants.Bold, isBoldFont);
        int length = jTextPane.getDocument().getLength();
        jTextPane.setCaretPosition(length);
        jTextPane.setCharacterAttributes(attributeSet, false);
    }




}
