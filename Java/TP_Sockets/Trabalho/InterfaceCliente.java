import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import javax.swing.*;
import java.io.InputStream;
import java.util.Scanner;

public class InterfaceCliente extends JFrame implements ActionListener, KeyListener {
    private JTextArea texto;
    private JTextField txtMsg;
    private JButton btnSend;
    private JButton btnSair;
    private JLabel lblHistorico;
    private JLabel lblMsg;
    private JPanel pnlContent;
    private static Socket socket;
    private OutputStream ou ;
    private Writer ouw;
    private BufferedWriter bfw;
    private JTextField txtIP;
    private JTextField txtNome;
    public int porta = 12345;


    public InterfaceCliente() throws IOException{
    JLabel lblMessage = new JLabel("Seja bem vindo ao chat!");
    txtIP = new JTextField("127.0.0.1");
    txtNome = new JTextField("Cliente");
    Object[] texts = {lblMessage, txtIP, txtNome };
    JOptionPane.showMessageDialog(null, texts);
     pnlContent = new JPanel();
     texto              = new JTextArea(10,20);
     texto.setEditable(false);
     texto.setBackground(new Color(240,240,240));
     txtMsg                       = new JTextField(20);
     lblHistorico     = new JLabel("Histórico");
     lblMsg        = new JLabel("Mensagem");
     btnSend                     = new JButton("Enviar");
     btnSend.setToolTipText("Enviar Mensagem");
     btnSair           = new JButton("Sair");
     btnSair.setToolTipText("Sair do Chat");
     btnSend.addActionListener(this);
     btnSair.addActionListener(this);
     btnSend.addKeyListener(this);
     txtMsg.addKeyListener(this);
     JScrollPane scroll = new JScrollPane(texto);
     texto.setLineWrap(true);
     pnlContent.add(lblHistorico);
     pnlContent.add(scroll);
     pnlContent.add(lblMsg);
     pnlContent.add(txtMsg);
     pnlContent.add(btnSair);
     pnlContent.add(btnSend);
     pnlContent.setBackground(Color.LIGHT_GRAY);
     texto.setBorder(BorderFactory.createEtchedBorder(Color.BLUE,Color.BLUE));
     txtMsg.setBorder(BorderFactory.createEtchedBorder(Color.BLUE, Color.BLUE));
     setTitle(txtNome.getText());
     setContentPane(pnlContent);
     setLocationRelativeTo(null);
        setResizable(false);
        setSize(250,300);
        setVisible(true);
         setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

public void conectar() {

    String SERVER_ADDRESS = txtIP.getText();

    try {
        socket = new Socket(SERVER_ADDRESS, this.porta);
        ou = socket.getOutputStream();
        ouw = new OutputStreamWriter(ou);
        bfw = new BufferedWriter(ouw);
        bfw.write(txtNome.getText() + " conectou\r\n");
        bfw.flush();
    } catch (Exception e) {
        System.out.println("Nao foi possivel incializar cliente de ip " + SERVER_ADDRESS + ": " + e.getMessage());
    }
}

public void escutar (InputStream servidor){
 
    Scanner s = new Scanner(servidor);
    while (s.hasNextLine()) {
    texto.setText(texto.getText() + s.nextLine() + "\r\n");
    }
    s.close();
}
 
public void enviarMensagem(String msg) throws IOException{

    if(msg.equals("Sair")){
      bfw.write("Desconectado \r\n");
      texto.append("Desconectado \r\n");
    }else{
      bfw.write(txtNome.getText() + " diz -> " + msg+"\r\n");
      texto.append(txtNome.getText() + " diz -> " + txtMsg.getText()+"\r\n");
    }
     bfw.flush();
     txtMsg.setText("");
}

public void sair() throws IOException{

    enviarMensagem("Sair");
    bfw.close();
    ouw.close();
    ou.close();
    socket.close();
 }

 @Override
public void actionPerformed(ActionEvent e) {

  try {
     if(e.getActionCommand().equals(btnSend.getActionCommand()))
        enviarMensagem(txtMsg.getText());
     else
        if(e.getActionCommand().equals(btnSair.getActionCommand()))
        sair();
     } catch (IOException e1) {
          e1.printStackTrace();
     }
}

@Override
public void keyPressed(KeyEvent e) {

    if(e.getKeyCode() == KeyEvent.VK_ENTER){
       try {
          enviarMensagem(txtMsg.getText());
       } catch (IOException e1) {
           // TODO Auto-generated catch block
           e1.printStackTrace();
       }
   }
}

@Override
public void keyReleased(KeyEvent arg0) {
  // TODO Auto-generated method stub
}

@Override
public void keyTyped(KeyEvent arg0) {
  // TODO Auto-generated method stub
}

public static void main(String []args) throws IOException{

    InterfaceCliente app = new InterfaceCliente();
    app.conectar();
    app.escutar(socket.getInputStream());
 }
}