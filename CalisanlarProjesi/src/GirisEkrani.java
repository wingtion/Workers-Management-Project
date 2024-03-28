import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GirisEkrani {
    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel messageLabel;

    public GirisEkrani() {
        CalisanIslemleri calisanIslemleri = new CalisanIslemleri();

        // Create the login frame
        frame = new JFrame("Login");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setBounds(400,200,330,200);

        // Username label and field
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(20, 20, 80, 25);
        frame.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(100, 20, 160, 25);
        frame.add(usernameField);

        // Password label and field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(20, 50, 80, 25);
        frame.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(100, 50, 160, 25);
        frame.add(passwordField);

        // Login button
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(100, 80, 80, 25);
        frame.add(loginButton);

        // Message label
        messageLabel = new JLabel("");
        messageLabel.setBounds(20, 110, 250, 25);
        frame.add(messageLabel);


        // Action listener for the login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredUsername = usernameField.getText();
                String enteredPassword = new String(passwordField.getPassword());
                
                boolean loginSuccessful = calisanIslemleri.girisYap(enteredUsername,enteredPassword);

                if (loginSuccessful) {
                    CalisanEkrani calisanEkrani = new CalisanEkrani(frame , true);
                    frame.setVisible(false);

                    calisanEkrani.setVisible(true);
                    System.exit(1);

                } else {
                    messageLabel.setForeground(Color.red);
                    messageLabel.setText("Invalid username or password");
                }
            }
        });

        // Display the frame
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GirisEkrani();
            }
        });
    }
}

