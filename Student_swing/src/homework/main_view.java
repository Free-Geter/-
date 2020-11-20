package homework;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;

public class main_view extends JFrame{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public main_view() 
	{ 
	setTitle("登录");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setSize(600,470);
	this.setLocation(300,120);
	JPanel A=new JPanel();
	A.setBackground(Color.WHITE);//C:\Users\DELL\Desktop
	ImageIcon im =new ImageIcon("C:\\\\Users\\\\DELL\\\\Desktop\\\\pi.png");
    JLabel a=new JLabel(im);
	A.add(a);
	JPanel B=new JPanel(new BorderLayout());
	B.setBackground(Color.PINK);
    JSplitPane jSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,A,B);//分屏的方式：左右HORIZONTAL_SPLIT，上下VERTICAL_SPLIT
    jSplitPane.setDividerLocation(200);//左边占的长度
    this.add(jSplitPane);
    jSplitPane.setDividerSize(0);//分界线的宽度 设置为0 即不显示出分界线
    A.setBorder(BorderFactory.createLineBorder(Color.BLUE));
    B.setBorder(BorderFactory.createLineBorder(Color.BLUE));
    
    JLabel w=new JLabel(" 我  是 ");
    JButton g=new JButton("管理员");
    
    JPanel G=new JPanel();    
    G.setBackground(Color.PINK);
    G.add(g);
    g.setContentAreaFilled(false);
    
    JButton x=new JButton("学生");
    JPanel X=new JPanel();
    X.add(x);
    X.setBackground(Color.PINK);
    x.setContentAreaFilled(false);
    
    B.add(w,BorderLayout.NORTH);
    w.setHorizontalAlignment(SwingConstants.CENTER);//居中
    w.setPreferredSize(new Dimension(0,150));//宽度150
    w.setFont(new Font("楷体",Font.PLAIN,25));//设置字体的字体，样子，大小	
    B.add(X,BorderLayout.CENTER);
    B.add(G,BorderLayout.SOUTH);
    G.setPreferredSize(new Dimension(0,200));//宽度200
    
    x.addActionListener(new ActionListener()    //监听学生按钮
			{

		public void actionPerformed(ActionEvent e)
		{
              new student_login(); 
		}
			});
    
    g.addActionListener(new ActionListener()   //监听管理员按钮
    		{
    	public void actionPerformed(ActionEvent e)
		{
              new admin_login(); 
		}
    		});
    
	}
	
	public static void main(String[] args)
	{		
		new main_view().setVisible(true);
	
	}
	
}
