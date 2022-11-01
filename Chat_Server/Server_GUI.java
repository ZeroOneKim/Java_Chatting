package Chat_Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Server_GUI extends JFrame implements ActionListener, KeyListener {
    JPanel ServerGUI_Panel = new JPanel();
    JLabel ServerLabel = new JLabel("Main Server");
    JLabel UserLabel = new JLabel("유저 목록");
    JTextField Chat = new JTextField(45);
    JButton Enter = new JButton("전송");
    TextArea ServerChatList = new TextArea(30, 50);
    TextArea UserList = new TextArea(30, 15);

    public Server_GUI(int Port) {
        setTitle("메인 서버");
        setVisible(true);
        setLocationRelativeTo(null);
        setSize(750, 520);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ServerChatList.setEditable(false);
        UserList.setEditable(false);
        Chat.addKeyListener(this);
        Enter.addActionListener(this);

        ServerGUI_Panel.add(ServerLabel);
        ServerGUI_Panel.add(ServerChatList);
        ServerGUI_Panel.add(UserLabel);
        ServerGUI_Panel.add(UserList);
        ServerGUI_Panel.add(Chat);
        ServerGUI_Panel.add(Enter);
        add(ServerGUI_Panel);


    }


    @Override
    public void actionPerformed(ActionEvent e) {    }
    @Override
    public void keyTyped(KeyEvent e) {    }
    @Override
    public void keyPressed(KeyEvent e) {    }
    @Override
    public void keyReleased(KeyEvent e) {    }
}
