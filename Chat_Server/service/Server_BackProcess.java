package Chat_Server.service;

import java.io.DataInputStream;
import java.io.DataOutputStream;       //백엔드
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;




public class Server_BackProcess extends Thread {
    Vector<ReceiveInfo> ClientList = new Vector<ReceiveInfo>();
    ArrayList<String> NickNameList = new ArrayList<String>();
    ServerSocket serversocket;
    Socket socket;
    private Server_GUI serverchatgui;

    public void setGUI(Server_GUI serverchatgui) {
        this.serverchatgui = serverchatgui;
    }

    public void Start_Server(int Port) {
        try {
            Collections.synchronizedList(ClientList);
            serversocket = new ServerSocket(Port);
            System.out.println("IP = " + InetAddress.getLocalHost() + ", PORT = " + Port);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void run() {
        try {
            NickNameList.add("Master");
            while (true) {
                socket = serversocket.accept(); // 포트 번호와 일치한 클라이언트의 소켓을 받습니다.
                System.out.println("[" + socket.getInetAddress() + "]에서 접속하셨습니다.");
                ReceiveInfo receive = new ReceiveInfo(socket);
                ClientList.add(receive);
                receive.start();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void Transmitall(String Message) {
        // 모든 클라이언트들에게 메세지를 전송해줍니다.
        for (int i = 0; i < ClientList.size(); i++) {
            try {
                ReceiveInfo ri = ClientList.elementAt(i);
                ri.Transmit(Message);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void removeClient(ReceiveInfo Client, String NickName) {
        // 퇴장한 유저 발생시 목록에서 삭제하는 역할을 합니다.
        ClientList.removeElement(Client);
        NickNameList.remove(NickName);
        System.out.println(NickName + "을 삭제 완료했습니다.");
        serverchatgui.UserList.setText(null);
        serverchatgui.AppendUserList(NickNameList);
    }

    class ReceiveInfo extends Thread {
        // 각 네트워크(클라이언트)로부터 소켓을 받아 다시 내보내는 역할을 합니다.
        private DataInputStream in;
        private DataOutputStream out;
        String NickName;
        String Message;

        public ReceiveInfo(Socket socket) {
            try {
                out = new DataOutputStream(socket.getOutputStream());
                in = new DataInputStream(socket.getInputStream());
                NickName = in.readUTF();
                NickNameList.add(NickName);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        public void run() {
            try {
                serverchatgui.UserList.setText(null);
                serverchatgui.AppendUserList(NickNameList);
                Transmitall(NickName + "님이 입장하셨습니다.\n");
                for (int i = 0; i < NickNameList.size(); i++) {
                    Transmitall("+++닉네임의시작+++" + NickNameList.get(i));
                }
                serverchatgui.AppendMessage(NickName + "님이 입장하셨습니다.\n");
                while (true) {
                    Message = in.readUTF();
                    serverchatgui.AppendMessage(Message);
                    Transmitall(Message);
                }
            } catch (Exception e) {
                removeClient(this, NickName);
                Transmitall(NickName + "님이 퇴장하셨습니다.\n");
                for (int i = 0; i < NickNameList.size(); i++) {
                    Transmitall("+++닉네임의시작+++" + NickNameList.get(i));
                }
                serverchatgui.AppendMessage(NickName + "님이 퇴장하셨습니다.\n");
            }
        }

        public void Transmit(String Message) {
            try {
                out.writeUTF(Message);
                out.flush();
            } catch (Exception e) {
                e.getStackTrace();
            }
        }
    }
}
