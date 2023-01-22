import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.border.*;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;



class RightChatPanel implements ActionListener{
    static JTextField textField;
    static JTextArea textArea;
    static AbstractBorder brdrLeft;
    static AbstractBorder brdrRight;
    static JScrollPane scrollPane;
    static int ycor = 30;
    static int padding = 70;
    static java.util.ArrayList<String> onlineUsers;

    static JPanel drawRightChatPanel(){
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(null);
        rightPanel.setBackground(new Color(0xffbad4c6));
        rightPanel.setBounds(330,0,1500,795);
        drawInnerComponent(rightPanel);
        
        return rightPanel;
    }

    private static void drawInnerComponent(JPanel rightPanel){
        

        brdrLeft = new TextBubbleBorder(Color.BLACK,2,12,16);
        brdrRight = new TextBubbleBorder(Color.BLACK,2,16,16,false);

        textArea = new JTextArea(1500,1500);
        textArea.setFont(new Font("Comic Sans MS",Font.BOLD,20));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);


        // Scroll pane
        scrollPane = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(0,5,820,695);
        rightPanel.add(scrollPane);


        // Text Field 
        textField = new JTextField();
        textField.setBounds(0,708,700,50);
        textField.setFocusable(true);
        textField.setFont(new Font("Comic Sans MS",Font.BOLD,15));
        rightPanel.add(textField);
        
        
        // Search Button
        JButton send = new JButton();
        send.setText("Send");
        send.setFont(new Font("Comic Sans MS",Font.BOLD,15));
        send.setBounds(720,708,100,50);
        send.setFocusable(false);
        send.addActionListener(new RightChatPanel());
        rightPanel.add(send);
        
        //  Panel for list of online user's
        JPanel onlineUsersPanel = new JPanel();
        onlineUsersPanel.setLayout(null);
        onlineUsersPanel.setBounds(821,0,1500-821,1500);
        onlineUsersPanel.setBackground(new Color(0xffbad4c6));
        JLabel label = new JLabel("Hahaha");
        label.setBounds(900,30,100,100);
        onlineUsersPanel.add(label);
        rightPanel.add(onlineUsersPanel);
        
        
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        
        String message = textField.getText();
        Client.out.println(message);
        textField.setText("");
    }



    void addMessage(String message,String name){
        JLabel nameLabel = new JLabel();           
        nameLabel.setText(name);
        nameLabel.setFont(new Font("Comic Sans MS",Font.BOLD,18));
        nameLabel.setBounds(5,ycor,120,50);
        // System.out.println("Working");


        if (!message.isEmpty()){
            JLabel label = new JLabel(message);
            label.setBounds(120,ycor,200,50);
            label.setFont(new Font("Comic Sans MS",Font.BOLD,20));
            Font labelFont = label.getFont();
            String labelText = label.getText();

            int stringWidth = label.getFontMetrics(labelFont).stringWidth(labelText);
            int componentWidth = label.getWidth();
            label.setSize(stringWidth + componentWidth/2-50,50);        
            label.setBorder(brdrLeft);
            textArea.add(label);
            textArea.add(nameLabel);
            textField.setText("");
            ycor += (50 + padding);
            // textArea.invalidate();
            // textArea.repaint();
            textArea.revalidate();
            scrollPane.repaint(); 
            // scrollPane.revalidate();
        }else{
            // Alerting them!
        }
    }
}




class Client implements Runnable{
    private Socket client;
    private BufferedReader in;
    static PrintWriter out;
    private String name;

    Client(String name){
        this.name = name;
    }


    static void addOnlineUsers(java.util.ArrayList<String> onlineUsers){
        RightChatPanel.onlineUsers = onlineUsers;
    }

    @Override
    public void run(){
        try{
            client = new Socket("127.0.0.1",5000);
            out = new PrintWriter(client.getOutputStream(),true);
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out.println(this.name);

            System.out.println("Started");
            String message;
            while((message = in.readLine()) != null){
                    if (!message.isEmpty()){
                        // System.out.println(java.util.Arrays.toString(message.split("-")));
                        new RightChatPanel().addMessage(message.split("-")[0],message.split("-")[1]);
                    }
            }

        }catch(IOException e){

            // passed
        }
    }


}



class TextBubbleBorder extends AbstractBorder {

    private Color color;
    private int thickness = 4;
    private int radii = 8;
    private int pointerSize = 7;
    private Insets insets = null;
    private BasicStroke stroke = null;
    private int strokePad;
    private int pointerPad = 4;
    private boolean left = true;
    RenderingHints hints;

    TextBubbleBorder(
            Color color) {
        this(color, 4, 8, 7);
    }

    TextBubbleBorder(
            Color color, int thickness, int radii, int pointerSize) {
        this.thickness = thickness;
        this.radii = radii;
        this.pointerSize = pointerSize;
        this.color = color;

        stroke = new BasicStroke(thickness);
        strokePad = thickness / 2;

        hints = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        int pad = radii + strokePad;
        int bottomPad = pad + pointerSize + strokePad;
        insets = new Insets(pad, pad, bottomPad, pad);
    }

    TextBubbleBorder(
            Color color, int thickness, int radii, int pointerSize, boolean left) {
        this(color, thickness, radii, pointerSize);
        this.left = left;
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return insets;
    }

    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
        return getBorderInsets(c);
    }

    @Override
    public void paintBorder(
            Component c,
            Graphics g,
            int x, int y,
            int width, int height) {

        Graphics2D g2 = (Graphics2D) g;

        int bottomLineY = height - thickness - pointerSize;

        RoundRectangle2D.Double bubble = new RoundRectangle2D.Double(
                0 + strokePad,
                0 + strokePad,
                width - thickness,
                bottomLineY,
                radii,
                radii);

        Polygon pointer = new Polygon();

        if (left) {
            // left point
            pointer.addPoint(
                    strokePad + radii + pointerPad,
                    bottomLineY);
            // right point
            pointer.addPoint(
                    strokePad + radii + pointerPad + pointerSize,
                    bottomLineY);
            // bottom point
            pointer.addPoint(
                    strokePad + radii + pointerPad + (pointerSize / 2),
                    height - strokePad);
		} else {
			// left point
			pointer.addPoint(
					width - (strokePad + radii + pointerPad),
					bottomLineY);
			// right point
			pointer.addPoint(
					width - (strokePad + radii + pointerPad + pointerSize),
					bottomLineY);
			// bottom point
			pointer.addPoint(
					width - (strokePad + radii + pointerPad + (pointerSize / 2)),
					height - strokePad);
		}

        Area area = new Area(bubble);
        area.add(new Area(pointer));

        g2.setRenderingHints(hints);

        // Paint the BG color of the parent, everywhere outside the clip
        // of the text bubble.
        Component parent  = c.getParent();
        if (parent!=null) {
            Color bg = parent.getBackground();
            Rectangle rect = new Rectangle(0,0,width, height);
            Area borderRegion = new Area(rect);
            borderRegion.subtract(area);
            g2.setClip(borderRegion);
            g2.setColor(bg);
            g2.fillRect(0, 0, width, height);
            g2.setClip(null);
        }

        g2.setColor(color);
        g2.setStroke(stroke);
        g2.draw(area);
    }
}



 