package homework;
import java.sql.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.DriverManager;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

	public class register extends JFrame{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private ValidCode vcode = new ValidCode();
		JTextField user,pass,idd,ph,co;
		JButton pho,register,exit;

		public register()
		{
			super("注册");			
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setSize(500,500);	
			this.setLocationRelativeTo(null); //此窗口将置于屏幕的中央
			setVisible(true);
			
			JPanel frame=new JPanel();        
		    frame.setBackground(Color.PINK);
		    this.add(frame);			
			
			JLabel username=new JLabel("账号");
			JLabel password=new JLabel("密码");
			JLabel id=new JLabel("学号");
			JLabel phone=new JLabel("手机号码");
			JLabel code=new JLabel("验证码");
			JLabel photo=new JLabel();
			user=new JTextField();
			pass=new JTextField();
			idd=new JTextField();
			ph=new JTextField();
			co=new JTextField();
			pho=new JButton("上传照片");
			register=new JButton("注  册");
			exit=new JButton("退  出");
			
			username.setBounds(58, 46, 60, 40);
			username.setFont(new Font("楷体",Font.BOLD,17));
			user.setBounds(100,50,120,30);
			password.setBounds(54,100,120,30);
			password.setFont(new Font("楷体",Font.BOLD,17));
			pass.setBounds(100,100,120,30);
			id.setBounds(54,150,120,30);
			id.setFont(new Font("楷体",Font.BOLD,17));
			idd.setBounds(100,150,120,30);
			phone.setBounds(22,200,120,30);
			phone.setFont(new Font("楷体",Font.BOLD,17));
			ph.setBounds(100,200,120,30);
			code.setBounds(41,250,120,30);
			code.setFont(new Font("楷体",Font.BOLD,17));
			co.setBounds(100,250,120,30);
			vcode.setBounds(112, 300, 100, 40);
			photo.setBounds(280,100,150,150);
			pho.setBounds(305,280,100,30);
			pho.setContentAreaFilled(false);
			register.setBounds(150,380,70,30);
			exit.setBounds(250,380,70,30);
		
			
			frame.setLayout(null);
			frame.add(username);
			frame.add(user);
			frame.add(password);
			frame.add(pass);
			frame.add(id);
			frame.add(idd);
			frame.add(phone);
			frame.add(ph);
			frame.add(code);
			frame.add(co);
			frame.add(photo);
			frame.add(pho);
			frame.add(register);
			frame.add(exit);
			frame.add(vcode);
	
	
			pho.addActionListener(new ActionListener()
					{
				public void actionPerformed(ActionEvent e)
				{
					JFileChooser fileChooser = new JFileChooser("C:\\\\Users\\\\小甘同学\\\\Pictures");
					fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
					int returnVal = fileChooser.showOpenDialog(fileChooser);
					if(returnVal == JFileChooser.APPROVE_OPTION)
					{ 
					    File filePath = fileChooser.getSelectedFile();//获取图片路径						
						System.out.println(filePath);
						String f=filePath.getPath();
						String filePath1=f.replaceAll("\\\\", "\\\\\\\\");	//将\转义为\\
						ImageIcon p = new ImageIcon(filePath1);
						photo.setIcon(p);	
					}
				}
					});
		
			register.addActionListener(new ActionListener()
					{
			public void actionPerformed(ActionEvent e)
			{
				String user1=user.getText().trim();
				String pass1=pass.getText().trim();
				String id1=idd.getText().trim();
				String ph1=ph.getText().trim();
				String co1=co.getText();
						  		  			
				try {
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					String url="jdbc:sqlserver://127.0.0.1:1433;DatabaseName=SCHOOL";
					String user="sa";//sa超级管理员
					String password="你的密码";//密码
					Connection conn=DriverManager.getConnection(url,user,password);
					Statement st=conn.createStatement();

				    String  strSQL="(insert * from  dbo.PY where ID='"+user1+"' )";
					ResultSet rs=st.executeQuery(strSQL);
					
					Md5 md5 = new Md5(); 
					String newString = md5.EncoderByMd5(pass1);
					
					if(co1.isEmpty()) {
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
						JOptionPane.showMessageDialog(null,"用户名已存在","错误!", JOptionPane.ERROR_MESSAGE);						
					}
					else
					{ 
						String sql = "insert into dbo.PY(ID,PAWD,Sno,phone) values('"+user1+"','"+newString+"','"+id1+"','"+ph1+"') "; 
						PreparedStatement pst = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);  
						pst.executeUpdate();
						pst.close();  
						JOptionPane.showMessageDialog(null,"注册成功");
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
		
		exit.addActionListener(new ActionListener()
		{
	public void actionPerformed(ActionEvent e)
	{
		 closeThis();
		 new student_login();
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

