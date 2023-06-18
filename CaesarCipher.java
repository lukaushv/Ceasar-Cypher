import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CaesarCipher extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JTextField keytxt;
    private JButton btnEncode;
    private JButton btnDecode;

    private String values = "abcdefghijklmnopqrstuvwxyz ";
    private JTextPane textPaneInput;
    private JTextPane textPaneOutput;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CaesarCipher frame = new CaesarCipher();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public CaesarCipher() {
        setResizable(false);
        setTitle("Ceaser Cipher");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 529, 346);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        textPaneInput = new JTextPane();
        textPaneInput.setBounds(10, 28, 497, 90);
        contentPane.add(textPaneInput);

        textPaneOutput = new JTextPane();
        textPaneOutput.setBounds(10, 207, 497, 90);
        contentPane.add(textPaneOutput);

        keytxt = new JTextField();
        keytxt.setBounds(82, 151, 86, 20);
        contentPane.add(keytxt);
        keytxt.setColumns(10);

        btnEncode = new JButton("Encrypt");
        btnEncode.setBounds(212, 150, 89, 23);
        contentPane.add(btnEncode);

        btnDecode = new JButton("Decrypt");
        btnDecode.setBounds(311, 150, 89, 23);
        contentPane.add(btnDecode);

        JLabel lblKey = new JLabel("Key:");
        lblKey.setFont(new Font("SansSerif", Font.BOLD, 11));
        lblKey.setBounds(36, 154, 46, 14);
        contentPane.add(lblKey);

        JLabel lblText = new JLabel("Input:");
        lblText.setFont(new Font("SansSerif", Font.BOLD, 11));
        lblText.setBounds(10, 11, 46, 14);
        contentPane.add(lblText);

        JLabel lblOutput = new JLabel("Output:");
        lblOutput.setFont(new Font("SansSerif", Font.BOLD, 11));
        lblOutput.setBounds(10, 189, 46, 14);
        contentPane.add(lblOutput);

        btnDecode.addActionListener(this);
        btnEncode.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnEncode)
        {
            try {
                Integer.parseInt(keytxt.getText());
                textPaneOutput.setText(Encode(textPaneInput.getText(), Integer.parseInt(keytxt.getText())));
            } catch(NumberFormatException e1) {
                JOptionPane.showMessageDialog(this, "Enter digits only");
            }

        }

        if (e.getSource() == btnDecode)
        {
            try {
                Integer.parseInt(keytxt.getText());
                textPaneOutput.setText(Decode(textPaneInput.getText(), Integer.parseInt(keytxt.getText())));
            } catch(NumberFormatException e1) {
                JOptionPane.showMessageDialog(this, "Enter digits only");
            }

        }

    }

    private String Encode(String text, int key) {
        char charEnc;
        int valEnc;
        int newEnc;
        String encrypted = "";
        text = text.toLowerCase();
        for (int i = 0; i < text.length(); i++) {
            charEnc = text.charAt(i);
            valEnc = values.indexOf(charEnc);
            newEnc = (key + valEnc) % values.length();
            encrypted += values.charAt(newEnc);
        }
        return encrypted.toUpperCase();
    }

    private String Decode(String text, int key) {
        char charEnc;
        int valEnc;
        int newEnc;
        String decoded = "";
        text = text.toLowerCase();
        for (int i = 0; i < text.length(); i++) {
            charEnc = text.charAt(i);
            valEnc = values.indexOf(charEnc);
            newEnc = (valEnc - key) % values.length();
            // check for negative
            if (newEnc < 0) {
                newEnc += Math.abs(values.length());
            }
            decoded += values.charAt(newEnc);
        }
        return decoded.toUpperCase();
    }

}