import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import javax.swing.border.EmptyBorder;





class Viewer{
    private Controller controller;
    private JTextField ipAddress;
    private JTextArea messageText;
    private JTextArea receiveMessageText;
    private JFrame frame;
    JLabel imageLabel;

    Viewer() {
        controller = new Controller(this);


        JLabel ipAddress1 = new JLabel("IP:");
        ipAddress1.setBounds(25, 360, 300, 25);
        ipAddress = new JTextField("");
        ipAddress.setBounds(50, 360, 325, 25);

        receiveMessageText = new JTextArea();
        receiveMessageText.setEditable(false);
        JScrollPane scrollPaneReceive = new JScrollPane(receiveMessageText);
        scrollPaneReceive.setBounds(25, 140, 350, 200);

        JLabel Message = new JLabel("Сообщение:");
        Message.setBounds(25, 388, 300, 25);

        messageText = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(messageText);
        scrollPane.setBounds(25, 407, 350, 60);

        Font font1 = new Font("Century Gothic", Font.PLAIN, 12);

        JButton clearButton = new JButton("Очистить чат");
        clearButton.setBackground(Color.LIGHT_GRAY);
        clearButton.setForeground(Color.BLACK);
        clearButton.setBorder(new EmptyBorder(0,0,0,0));
        clearButton.setFont(font1);
        clearButton.setBounds(380, 140, 100, 20);
        clearButton.addActionListener(controller);
        clearButton.setActionCommand("Clear");


        JButton sendButton = new JButton("Отправить");
        sendButton.setBackground(Color.LIGHT_GRAY);
        sendButton.setForeground(Color.BLACK);
        sendButton.setBorder(new EmptyBorder(0,0,0,0));
        sendButton.setFont(font1);
        sendButton.setBounds(380, 407, 100, 15);
        sendButton.addKeyListener(controller);
        sendButton.addActionListener(controller);
        sendButton.setActionCommand("Send Message");



        ImageIcon icon= new ImageIcon("src/images/chat.png");
        imageLabel = new JLabel(icon);
        imageLabel.setBounds(200, 40,icon.getIconWidth(), icon.getIconHeight());


        frame = new JFrame("#LiveChat");
        frame.setLayout(null);
        frame.setSize(500, 500);
        frame.setResizable(false);
        frame.setLocation(200,0);
        frame.getContentPane().setBackground(Color.WHITE);


        frame.add(scrollPane);
        frame.add(sendButton);
        frame.add(clearButton);
        frame.add(scrollPaneReceive);
        frame.add(imageLabel);
        frame.add(ipAddress1);
        frame.add(ipAddress);
        frame.add(Message);

        frame.setVisible(true);


    }

    public String getIpAddress(){
        return ipAddress.getText();

    }

    public String getMessage(){
        return messageText.getText();
    }
    public void received(String value) {
        String text = receiveMessageText.getText();
        text = text + value + "\n";
        receiveMessageText.setText(text);
    }
    public void clear(){
        receiveMessageText.setText("");
    }
    public void messageTextClear(){
        messageText.setText("");
    }

}