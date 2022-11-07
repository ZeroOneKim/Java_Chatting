package Chat_Server.service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class Server_GUI extends JFrame implements ActionListener, KeyListener {
    LoginHelper loginHelper;
    JPanel ServerGUI_Panel = new JPanel();
    JLabel ServerLabel = new JLabel("Main Server");
    JLabel UserLabel = new JLabel("유저 목록");
    JTextField Chat = new JTextField(45);
    JButton Enter = new JButton("전송");
    JButton previous = new JButton("뒤로");
    TextArea ServerChatList = new TextArea(30, 50);
    TextArea UserList = new TextArea(30, 15);
    Server_BackProcess S_BP = new Server_BackProcess();
    public Server_GUI(int Port) {
        setTitle("메인 서버");
        setVisible(true);
        setLocationRelativeTo(null);
        setSize(750, 600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ServerGUI_Panel.add(previous);

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

        UserList.append("Master\n"); //서버 생성시 master 의 이름으로.
        S_BP.setGUI(this);
        S_BP.Start_Server(Port);
        S_BP.start();
    }
    public void actionPerformed(ActionEvent e) {
        String Message = Chat.getText().trim();
        if (e.getSource() == Enter && Message.length() > 0) {
            AppendMessage("서버 : " + Message + "\n");
            S_BP.Transmitall("서버 : " + Message + "\n");
            Chat.setText(null);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
    public void keyPressed(KeyEvent e) {
        String Message = Chat.getText().trim();
        if (e.getKeyCode() == KeyEvent.VK_ENTER && Message.length() > 0) {
            AppendMessage("서버 : " + Message + "\n");
            S_BP.Transmitall("서버 : " + Message + "\n");
            Chat.setText(null);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public void AppendMessage(String Message) {
        ServerChatList.append(Message);
    }

    public void AppendUserList(ArrayList NickName) {
        String name;
        for (int i = 0; i < NickName.size(); i++) {
            name = (String) NickName.get(i);
            UserList.append(name + "\n");
        }
    }

}
