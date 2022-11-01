package LoginService;

import Chat_Server.Server_GUI;

import javax.swing.*;
import java.awt.event.*;

public class LoginHelper extends JFrame implements ActionListener, KeyListener {
    Server_GUI server_gui = null;
    JPanel Port_Log = new JPanel();    //기본적으로 JAVA Swing 을 이용하여 구현했습니다.
    JLabel Port_Label = new JLabel("입력을 허용할 포트 번호를 입력하세요. (Port = 0-65535)");
    JLabel Any_Thing = new JLabel("카카오 먹통사태 이후 만들어 보았습니다.");
    JButton Port_Connection = new JButton("접속!");
    JTextField Port_PORTNUMBER = new JTextField(25);

    public LoginHelper() {
        setTitle("로그인 창");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 닫았을 때 메모리에서 제거되도록 설정합니다.
        setLocationRelativeTo(this);
        setSize(350, 200);
        Port_PORTNUMBER.addKeyListener(this);
        Port_Log.add(Any_Thing);
        Port_Log.add(Port_Label);
        Port_Log.add(Port_PORTNUMBER);
        Port_Connection.addActionListener(this);
        Port_Log.add(Port_Connection);
        add(Port_Log);

        setResizable(false);
        setVisible(true);
    }

        public void clickButton (ActionEvent e){
            // "접속!" 버튼을 누르면 작동이 됩니다.
            try {
                int Port = Integer.parseInt(Port_PORTNUMBER.getText().trim());
                if (e.getSource() == Port_Connection) {
                    server_gui = new Server_GUI(Port);
                    setVisible(false);
                }
            } catch (Exception a) {
                JOptionPane.showMessageDialog(null, "올바르지 않은 입력입니다!");
            }
        }

        public void pressButton (KeyEvent e){
            // 텍스트필드에 값을 입력한 후 엔터키를 누르면 작동이 됩니다.
            try {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    int Port = Integer.parseInt(Port_PORTNUMBER.getText().trim());
                    server_gui = new Server_GUI(Port);
                    setVisible(false);
                }
            } catch (Exception a) {
                JOptionPane.showMessageDialog(null, "올바르지 않은 입력입니다!");
            }

        }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
