import java.awt.Color;
import java.awt.Font;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.RenderingHints;
import javax.swing.border.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



class LLeftPanel extends JPanel {

    public static BufferedImage resize(BufferedImage image, int width, int height) {
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
        Graphics2D g2d = (Graphics2D) bi.createGraphics();
        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        g2d.drawImage(image, 0, 0, width, height, null);
        g2d.dispose();
        return bi;
    }

    static JPanel drawLeftPanel() throws IOException{
        JPanel leftPanel = new JPanel();
        leftPanel.setBounds(0,0,600,1500);
        leftPanel.setLayout(null);


        // Resizing and changing the background image
        BufferedImage gradientBgImage = ImageIO.read(new File("./assets/GradientBG.jpeg"));
        BufferedImage resizedImage=resize(gradientBgImage,leftPanel.getWidth(),leftPanel.getHeight());
        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setIcon(new ImageIcon(resizedImage));
        backgroundLabel.setBounds(0,0,600,1500); 

        
        JLabel label1  = new JLabel();
        label1.setText("UNIQUE");
        label1.setForeground(new Color(0xffffff));
        label1.setBounds(100,100,300,100);
        label1.setFont(new Font("Showcard Gothic", Font.BOLD, 65));


        JLabel label2 = new JLabel();
        label2.setText("Developer");
        label2.setForeground(new Color(0xffffff));
        label2.setBounds(250,label1.getHeight() + 50, 200,110);
        label2.setFont(new Font("Showcard Gothic", Font.BOLD, 35));


        JLabel label3 = new JLabel();
        label3.setText("A Complete Software Solution");
        label3.setForeground(new Color(0xffffff));
        label3.setBounds(200,label1.getHeight() + 100, 500,110);
        label3.setFont(new Font("Showcard Gothic", Font.PLAIN, 25));


        JLabel label4 = new JLabel();
        BufferedImage emoji = ImageIO.read(new File("./assets/Emoji.png"));
        BufferedImage resizedEmoji=resize(emoji,450,450);
        ImageIcon emojiIcon = new ImageIcon(resizedEmoji);
        label4.setIcon(emojiIcon);
        label4.setBounds(80,410,500,500);


        leftPanel.add(label1);
        leftPanel.add(label2);
        leftPanel.add(label3);
        leftPanel.add(label4);
        leftPanel.add(backgroundLabel);
        leftPanel.setOpaque(true);


        return leftPanel;
    }
}




class LRightPanel{
    private static JButton login;
    private static JButton signUp;
    private static JTextField userNameField;
    private static JTextField emailField;
    private static JTextField passwordField;
    private static Listener listener;

    static void drawInnerPannel(JPanel rightJPanel) throws IOException{
        listener = new Listener();
        JPanel innerPannel = new JPanel();
        Border innerBorder = BorderFactory.createLineBorder(new Color(100,100,255),5);
        innerPannel.setBorder(innerBorder);
        innerPannel.setLayout(null);
        innerPannel.setBounds(100,250,650,500);


        // Setting background color for the right pannel
        BufferedImage gradientBgImage = ImageIO.read(new File("./assets/GradientBG.jpeg"));
        BufferedImage resizedImage=LLeftPanel.resize(gradientBgImage,rightJPanel.getWidth(),rightJPanel.getHeight());
        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setIcon(new ImageIcon(resizedImage));
        backgroundLabel.setBounds(0,0,1000,1500); 


        // Setting background color for the right inner pannel
        BufferedImage innerGradientBgImage = ImageIO.read(new File("./assets/GradientBG.jpeg"));
        BufferedImage innerResizedImage=LLeftPanel.resize(innerGradientBgImage,rightJPanel.getWidth(),rightJPanel.getHeight());
        JLabel innerBackgroundLabel = new JLabel();
        innerBackgroundLabel.setIcon(new ImageIcon(innerResizedImage));
        innerBackgroundLabel.setBounds(5,5,640,490); 
        

        // UserName label
        JLabel userName = new JLabel();
        userName.setText("UserName: ");
        userName.setBounds(50,50,250,50);
        userName.setForeground(Color.white);
        userName.setFont(new Font("comic Sans", Font.BOLD, 20));


        // password label
        JLabel password = new JLabel();
        password.setText("Password: ");
        password.setBounds(50,130,250,50);
        password.setForeground(Color.white);
        password.setFont(new Font("comic Sans", Font.BOLD, 20));


        // Email label
        JLabel email = new JLabel();
        email.setText("Email id: ");
        email.setBounds(50,150,250,150);
        email.setForeground(new Color(255,255,255));
        email.setFont(new Font("comic Sans", Font.BOLD, 20));


        // usrName Field
        userNameField = new JTextField();
        userNameField.setText("username ");
        userNameField.setBounds(250,70,150,30);


        // Password Field
        passwordField = new JTextField();
        passwordField.setText("Password ");
        passwordField.setBounds(250,145,150,30);


        // Email Field
        emailField = new JTextField();
        emailField.setText("Email id ");
        emailField.setBounds(250,210,150,30);


        // signup button
        signUp = new JButton("SignUp");
        signUp.setFocusable(false);
        signUp.setBounds(360,350, 100,50);
        signUp.setBackground(Color.pink);
        signUp.addActionListener(listener);


        // Login button
        login = new JButton("Login");
        login.setFocusable(false);
        login.setBounds(130,350, 100,50);
        login.setBackground(new Color(100,100,255));
        login.addActionListener(listener);

        innerPannel.add(userName);
        innerPannel.add(password);
        innerPannel.add(email);
        innerPannel.add(userNameField);
        innerPannel.add(passwordField);
        innerPannel.add(emailField);
        innerPannel.add(signUp);
        innerPannel.add(login);
        rightJPanel.add(innerPannel);
        rightJPanel.add(backgroundLabel);
        innerPannel.add(innerBackgroundLabel);

    
    }

    static void drawInnerLabel(JPanel rightJPanel){
        JLabel label = new JLabel();
        label.setFont(new Font("Tahoma", Font.BOLD, 50));
        label.setText("Welcome");
        label.setForeground(new Color(255,255,255));
        label.setBounds(300,100,400,100);
        rightJPanel.add(label);
    }


    static JPanel drawLRightPanel() throws IOException{
        JPanel LrightPanel = new JPanel();
        LrightPanel.setLayout(null);
        LrightPanel.setBackground(Color.gray);
        LrightPanel.setBounds(600,0,1000,1500);
        LRightPanel.drawInnerLabel(LrightPanel);
        LRightPanel.drawInnerPannel(LrightPanel);
        return LrightPanel;
    }


    static class Listener implements ActionListener {
        private String name;
        private String emailId;
        private String password;

        @Override
        public void actionPerformed(ActionEvent evt){
            this.name = userNameField.getText();
            this.emailId = emailField.getText();
            this.password = passwordField.getText();

            if(evt.getSource() == login){
                // validate it with databae and stuff like that
                LoginSignUpScreen.name = this.name;
                LoginSignUpScreen.isTrue = true;
                System.out.println(LoginSignUpScreen.name);
            }else if(evt.getSource() == signUp){
                // Do something
                LoginSignUpScreen.name = this.name;
                LoginSignUpScreen.isTrue = true;
                System.out.println(LoginSignUpScreen.name);
            }
        }
    }


}



class LoginSignUpScreen extends JFrame{
    int width = 1500;
    int height = 1000;
    static String name;
    static boolean isTrue = false;

    LoginSignUpScreen() throws IOException{
        this.drawFrame();
    }


    void drawFrame() throws IOException{
        this.setSize(this.width, this.height);
        this.setTitle("Unique Developer");
        this.add(LLeftPanel.drawLeftPanel());
        this.add(LRightPanel.drawLRightPanel());
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    
    void disposeFrame(){
        this.dispose();    
    }
}



