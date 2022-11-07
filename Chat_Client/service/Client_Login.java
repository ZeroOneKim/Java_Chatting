package Chat_Client.service;

import Chat_Client.Client_view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Client_Login extends JFrame implements ActionListener {
    private JPanel Login_GUIPanel = new JPanel();
    private JTextField NickName_Text = new JTextField(20);
    private JTextField Port_Text = new JTextField("####", 20);
    private JTextField IPAddress_Text = new JTextField("###.###.###.###", 20);
    private JLabel NickName_Label = new JLabel("유저 입력");
    private JLabel Port_Label = new JLabel("포트 입력");
    private JLabel IPAddress_Label = new JLabel("주소 입력");
    private JButton Login_GUI_Button = new JButton("접속!");

    public Client_Login() {
        setTitle("로그인 화면");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(300, 300);
        setResizable(false);
        setVisible(true);
        Login_GUI_Button.setPreferredSize(new Dimension(260, 40));
        Login_GUI_Button.addActionListener(this);
        Login_GUIPanel.add(NickName_Label);
        Login_GUIPanel.add(NickName_Text);
        Login_GUIPanel.add(Port_Label);
        Login_GUIPanel.add(Port_Text);
        Login_GUIPanel.add(IPAddress_Label);
        Login_GUIPanel.add(IPAddress_Text);
        Login_GUIPanel.add(Login_GUI_Button);
        add(Login_GUIPanel);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == Login_GUI_Button) {
                String NickName = NickName_Text.getText().trim();
                String IPAddress = IPAddress_Text.getText().trim();
                int Port = Integer.parseInt(Port_Text.getText().trim());
                new Client_GUI(NickName, IPAddress, Port);
                setVisible(false);
            }
        } catch (Exception a) {
            // 만약 올바르지 않는 값이 입력되면 팝업창을 띄워줍니다.
            JOptionPane.showMessageDialog(null, "올바르지 않은 입력입니다!");
        }
    }
}
