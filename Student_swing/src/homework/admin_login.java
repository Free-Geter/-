package homework;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class admin_login extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ValidCode vcode = new ValidCode();
	JTextField co,user;
	JPasswordField pass;
	JButton ok;

	public admin_login() {
		super("管理员登陆");			
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(450,350);	
		this.setLocationRelativeTo(null);
		setVisible(true);	
		
		//将整体设为粉色背景
	    JPanel frame=new JPanel();        
	    frame.setBackground(Color.PINK);
	    this.add(frame);
		
	    //账号、密码所代表的图形
	    Icon login = new ImageIcon("C:\\\\Users\\\\小甘同学\\\\Pictures\\\\1.png");
		JLabel l= new JLabel();
		l.setIcon(login);		
		Icon password = new ImageIcon("C:\\\\Users\\\\小甘同学\\\\Pictures\\\\2.png");		
		JLabel p= new JLabel();
		p.setIcon(password);
		
		JLabel code=new JLabel("验证码");
		code.setFont(new Font("楷体",Font.BOLD,17));
		
		user=new JTextField();
	    pass=new JPasswordField();
	    co=new JTextField();
		ok=new JButton("登录");
		ok.setContentAreaFilled(false);
		
		
		l.setBounds(80, 50, 60, 40);
		p.setBounds(80, 100, 60, 40);
		code.setBounds(70, 150, 60, 40);
	    user.setBounds(150, 50, 150, 30);
		pass.setBounds(150, 100, 150, 30);
		co.setBounds(150, 150, 150, 30);
		ok.setBounds(180, 220, 70, 30);
		vcode.setBounds(310, 145, 100, 40);

		
		frame.setLayout(null);
		frame.add(l);
		frame.add(p);
		frame.add(code);
		frame.add(user);
		frame.add(pass);
		frame.add(co);
		frame.add(ok);
		frame.add(vcode);

		
		ok.addActionListener(new ActionListener()    //监听登录按钮
				{

			public void actionPerformed(ActionEvent e)
			{
				String jusername=user.getText();
				char s[]=pass.getPassword();
				String jpassword=new String(s);
				String coo=co.getText();	
				
				try {
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					//加载对应的jdbc驱动
					String url="jdbc:sqlserver://127.0.0.1:1433;DatabaseName=SCHOOL";
					//配置连接字符串
					String user="sa";//sa超级管理员
					String password="jiaganyu";//密码
					Connection conn=DriverManager.getConnection(url,user,password);
					//创建数据库连接对象
					Statement st=conn.createStatement();
					//创建SQL语句执行对象

				    String  strSQL="(Select * from  dbo.GLY where ID1='"+jusername+"'And PAWD1='"+jpassword+"' )";
				   // String  strSQL1="(Select * from  dbo.GLY where ID1='"+jusername+"'And PAWD1='"+jpassword+"' )";
					ResultSet rs=st.executeQuery(strSQL);
					//ResultSet rs1=st.executeQuery(strSQL1);
									
					if(coo.isEmpty()) {
						JOptionPane.showMessageDialog(null, "请输入验证码!","提示消息",JOptionPane.WARNING_MESSAGE);
					}
					else
					{
						if(!isValidCodeRight()) {
							JOptionPane.showMessageDialog(null, "验证码错误,请重新输入!","提示消息",JOptionPane.WARNING_MESSAGE);
						}
						else {
						
						if(rs.next())
					{							 				    					
							new admin_view();	
			    			closeThis();			    
					}	
					
					else
					{ 
						JOptionPane.showMessageDialog(null,"用户名不存在或密码错误","错误!",JOptionPane.ERROR_MESSAGE);
					}
				    conn.close();
				   
					//关闭数据库连接	
				}
					}
				}
				catch (ClassNotFoundException ex) {
					System.out.println("没有找到对应的数据库驱动类");
				}
				catch (SQLException ex) {
					System.out.println("数据库连接或者是数据库操作失败");
				}
			}
				
			});
	
			}
	public boolean isValidCodeRight() {		
		
		if(co == null) {
			return false;
		}else if(vcode == null) {
			return true;
		}else if(vcode.getCode() .equals(co.getText())) {
			return true;
		}else 
			return false;
	
}
	
	public  void closeThis()//关闭当前界面
	{
		this.dispose();
	}
	
	}