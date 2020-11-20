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
	setTitle("��¼");
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
    JSplitPane jSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,A,B);//�����ķ�ʽ������HORIZONTAL_SPLIT������VERTICAL_SPLIT
    jSplitPane.setDividerLocation(200);//���ռ�ĳ���
    this.add(jSplitPane);
    jSplitPane.setDividerSize(0);//�ֽ��ߵĿ�� ����Ϊ0 ������ʾ���ֽ���
    A.setBorder(BorderFactory.createLineBorder(Color.BLUE));
    B.setBorder(BorderFactory.createLineBorder(Color.BLUE));
    
    JLabel w=new JLabel(" ��  �� ");
    JButton g=new JButton("����Ա");
    
    JPanel G=new JPanel();    
    G.setBackground(Color.PINK);
    G.add(g);
    g.setContentAreaFilled(false);
    
    JButton x=new JButton("ѧ��");
    JPanel X=new JPanel();
    X.add(x);
    X.setBackground(Color.PINK);
    x.setContentAreaFilled(false);
    
    B.add(w,BorderLayout.NORTH);
    w.setHorizontalAlignment(SwingConstants.CENTER);//����
    w.setPreferredSize(new Dimension(0,150));//���150
    w.setFont(new Font("����",Font.PLAIN,25));//������������壬���ӣ���С	
    B.add(X,BorderLayout.CENTER);
    B.add(G,BorderLayout.SOUTH);
    G.setPreferredSize(new Dimension(0,200));//���200
    
    x.addActionListener(new ActionListener()    //����ѧ����ť
			{

		public void actionPerformed(ActionEvent e)
		{
              new student_login(); 
		}
			});
    
    g.addActionListener(new ActionListener()   //��������Ա��ť
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
