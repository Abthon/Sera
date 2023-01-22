
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Color; 
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Font;

public class RightHomePanel{
    static JPanel drawRightHomePanel() throws IOException{
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(null);
        rightPanel.setBackground(new Color(0xffE2E8FF));

        JLabel headingLabel = new JLabel("IMAGINE A PLACE ...");
        headingLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 45));
        headingLabel.setForeground(new Color(0xff5A5FD6));
        headingLabel.setBounds(30,200,500,50);

        // setting home page image
        BufferedImage image = ImageIO.read(new File("./assets/image.png"));
        BufferedImage resizedImage = LLeftPanel.resize(image,512,384);
        ImageIcon icon = new ImageIcon(resizedImage);
        JLabel imageLabel = new JLabel(); 
        imageLabel.setIcon(icon);
        imageLabel.setBounds(600,20,512,385);


        // Setting the getStarted button
        JButton button = new JButton("Get Started");
        button.setFocusable(false);
        button.setBackground(new Color(0xff5A5FD6));
        button.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        button.setBounds(720,540,250,60);


        // adding components to the rightPanel
        rightPanel.add(headingLabel);
        rightPanel.add(imageLabel);
        rightPanel.add(button);
        
        return rightPanel;
    }
}
