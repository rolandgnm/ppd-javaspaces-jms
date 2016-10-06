package br.com.ppdchat.view;

/**
 * Created by Roland on 10/5/16.
 */

public class NicknameDialog extends javax.swing.JFrame {

    /**
     * Creates new form NicknameDialog
     */
    public NicknameDialog() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lbChooseNick = new javax.swing.JLabel();
        txtNickname = new javax.swing.JTextField();
        btnEnterChat = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 10, 5));
        jPanel1.setLayout(new java.awt.BorderLayout());

        lbChooseNick.setText("Escolha um Nickname:");
        jPanel1.add(lbChooseNick, java.awt.BorderLayout.NORTH);
        jPanel1.add(txtNickname, java.awt.BorderLayout.CENTER);

        btnEnterChat.setText("Entrar");
        jPanel1.add(btnEnterChat, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(jPanel1, java.awt.BorderLayout.LINE_START);

        pack();
    }

    private javax.swing.JButton btnEnterChat;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbChooseNick;
    private javax.swing.JTextField txtNickname;
}
