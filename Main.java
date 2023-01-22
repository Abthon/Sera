import java.awt.*;
import javax.swing.*;



class LeftPanel extends JPanel {

    static JPanel drawLeftPanel(String name,JPanel homeScreen, CardLayout cl){
        JPanel leftPanel = new JPanel();
        drawInnerComponent(leftPanel,name,homeScreen,cl);
        leftPanel.setBounds(0,0,330,795);
        leftPanel.setLayout(null);
        leftPanel.setBackground(new Color(0xff3a5749));

        return leftPanel;
    }


    private static void drawInnerComponent(JPanel leftPanel,String name,JPanel homeScreen,CardLayout cl){

        JLabel welcomeLabel = new JLabel();
        welcomeLabel.setText("Welcome " + name);
        welcomeLabel.setBounds(25, 30, 400, 50);
        welcomeLabel.setForeground(new Color(0xfffafbfb));
        welcomeLabel.setFont(new Font("Comic Sans MS",Font.BOLD,28));
        leftPanel.add(welcomeLabel);

        byte[] emojiByteCode = new byte[]{(byte)0xF0, (byte)0x9F, (byte)0x98, (byte)0x81};
        String emoji = new String(emojiByteCode, java.nio.charset.Charset.forName("UTF-8"));
        JLabel Emoji  = new JLabel(emoji);
        Emoji.setFont(new Font(" ",Font.BOLD,80));
        Emoji.setBounds(100, 675, 400, 100);
        leftPanel.add(Emoji);


        // Email label
        JLabel emailAddress  = new JLabel();
        emailAddress.setText(" Walelignabenezer@gmail.com");
        // emailAddress.setForeground(new Color(0xff74857c)); 
        emailAddress.setBounds(33, 650, 400, 20);
        emailAddress.setFont(new Font("Comic Sans MS",Font.PLAIN,20));
        leftPanel.add(emailAddress);



		// #######################   Drawer Items ##########################################

        // Item 1
         JButton home = new JButton();
         home.setBorderPainted( false );
         home.setBackground(new Color(0xff3a5749));
         home.setForeground(new Color(0xff74857c));
		 home.setBounds(0,150,400,50);
		 home.setText("Home");
         home.setFont(new Font("Comic Sans MS",Font.BOLD,20));
		 home.setFocusable(false);
         home.addActionListener(new java.awt.event.ActionListener(){
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt){
                cl.show(homeScreen,"homeScreen");
            }
         });


		 // Item 2
		 JButton blogButton = new JButton();
         blogButton.setBorderPainted( false );
         blogButton.setBackground(new Color(0xff3a5749));
         blogButton.setForeground(new Color(0xff74857c));
		 blogButton.setBounds(0,220,400,50);
		 blogButton.setText("Write Article");
         blogButton.setFont(new Font("Comic Sans MS",Font.BOLD,20));
		 blogButton.setFocusable(false);


		 // Item 3
		 JButton chatRoomButton = new JButton();
         chatRoomButton.setBorderPainted( false );
         chatRoomButton.setBackground(new Color(0xff3a5749));
         chatRoomButton.setForeground(new Color(0xff74857c));
		 chatRoomButton.setBounds(0,290,400,50);
		 chatRoomButton.setText("Join chat room");
         chatRoomButton.setFont(new Font("Comic Sans MS",Font.BOLD,20));
		 chatRoomButton.setFocusable(false);
         chatRoomButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt){
                cl.show(homeScreen,"chatScreen");
            }
         });


		 // item 4 
		 JButton browserButton = new JButton();
         browserButton.setBorderPainted( false );
         browserButton.setBackground(new Color(0xff3a5749));
         browserButton.setForeground(new Color(0xff74857c));
		 browserButton.setBounds(0,360,400,50);
		 browserButton.setText("Google");
         browserButton.setFont(new Font("Comic Sans MS",Font.BOLD,20));
		 browserButton.setFocusable(false);
         browserButton.addActionListener(new java.awt.event.ActionListener(){
            @Override 
            public void actionPerformed(java.awt.event.ActionEvent evt){
                cl.show(homeScreen, "googleScreen");
            }
         });

         // item 5 
         JButton developer = new JButton();
         developer.setBorderPainted( false );
         developer.setBackground(new Color(0xff3a5749));
         developer.setForeground(new Color(0xff74857c));
         developer.setBounds(0,430,400,50);
         developer.setText("Developers");
         developer.setFont(new Font("Comic Sans MS",Font.BOLD,20));
         developer.setFocusable(false);


		 // adding all the three buttons to the leftpanel aka to the sidebar menu of the window.
         leftPanel.add(home);
		 leftPanel.add(blogButton);
		 leftPanel.add(chatRoomButton);
		 leftPanel.add(browserButton);
         leftPanel.add(developer);
		
    }
}


class MainPage extends JFrame{
	private int width; 
	private int height;

	MainPage(int width, int height){
		this.width = width;
		this.height = height;
		this.init();
	}

	void init(){
        try{
            LoginSignUpScreen lss = new LoginSignUpScreen();
            while(!LoginSignUpScreen.isTrue){
                System.out.println("");
            }
            lss.disposeFrame();
        }catch(java.io.IOException e){
            System.out.println(e.getStackTrace());
        }

        // configuring the JFrame
		this.setSize(this.width,this.height);
		this.drawInnerFrame();
        Client client = new Client(LoginSignUpScreen.name);
		this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
        client.run();
	}


	void drawInnerFrame(){
        CardLayout cl = new CardLayout();
        JPanel homeScreen = new JPanel();
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(null);
        rightPanel.setBounds(330,0,1500,795);

        homeScreen.setLayout(cl);
        homeScreen.setSize(this.width - 330,this.height);
        
        this.add(LeftPanel.drawLeftPanel(LoginSignUpScreen.name,homeScreen,cl));
        homeScreen.add(RightChatPanel.drawRightChatPanel(),"chatScreen");
        try{
            homeScreen.add(RightHomePanel.drawRightHomePanel(),"homeScreen");
        }catch(java.io.IOException e){
            // pass
        }
        homeScreen.add(RightGooglePanel.drawRightGooglePanel(), "googleScreen");
        rightPanel.add(homeScreen);
        this.add(rightPanel);
        cl.show(homeScreen,"homeScreen");
	}
}



class Main {
    public static void main(String[] args) throws java.io.IOException{
		MainPage m = new MainPage(1500 ,795);
    }
}



