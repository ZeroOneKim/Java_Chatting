package Chat_Client.service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

class Client_GUI extends JFrame implements ActionListener, KeyListener {
    //클라이언트용 채팅창
    String NickName;
    Client_Backend CB = new Client_Backend();
    JPanel ClientGUIPanel = new JPanel();
    JLabel UserLabel = new JLabel("유저 목록");
    JLabel User = new JLabel(NickName);
    JTextField Chat = new JTextField(45);
    JButton Enter = new JButton("전송");
    TextArea ChatList = new TextArea(30, 50);
    TextArea UserList = new TextArea(30, 15);

    public Client_GUI(String NickName, String IPAddress, int Port) {
        this.NickName = NickName;

        setTitle("고객 창");
        setVisible(true);
        setLocationRelativeTo(null);
        setSize(750, 600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ChatList.setEditable(false);
        UserList.setEditable(false);
        Chat.addKeyListener(this);
        Enter.addActionListener(this);

        ClientGUIPanel.add(User);
        ClientGUIPanel.add(ChatList);
        ClientGUIPanel.add(UserLabel);
        ClientGUIPanel.add(UserList);
        ClientGUIPanel.add(Chat);
        ClientGUIPanel.add(Enter);
        add(ClientGUIPanel);
        CB.setGui(this);
        CB.getUserInfo(NickName, IPAddress, Port);
        CB.start(); // 채팅창이 켜짐과 동시에 접속을 실행해줍니다.
    }

    public void actionPerformed(ActionEvent e) {
        // 전송 버튼을 누르고, 입력값이 1이상일때만 전송되도록 합니다.
        String Message = Chat.getText().trim();
        if (e.getSource() == Enter && Message.length() > 0) {
            CB.Transmit(NickName + " : " + Message + "\n");
            Chat.setText(null);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        // 키보드 엔터키를 누르고, 입력값이 1이상일때만 전송되도록 합니다.
        String Message = Chat.getText().trim();
        if (e.getKeyCode() == KeyEvent.VK_ENTER && Message.length() > 0) {
            CB.Transmit(NickName + " : " + Message + "\n");
            Chat.setText(null);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void AppendMessage(String Message) {
        ChatList.append(Message);
    }

    public void AppendUserList(ArrayList NickName) {
        // 유저목록을 유저리스트에 띄워줍니다.
        String name;
        UserList.setText(null);
        for (int i = 0; i < NickName.size(); i++) {
            name = (String) NickName.get(i);
            UserList.append(name + "\n");
        }
    }


}
