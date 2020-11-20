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
			super("ע��");			
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setSize(500,500);	
			this.setLocationRelativeTo(null); //�˴��ڽ�������Ļ������
			setVisible(true);
			
			JPanel frame=new JPanel();        
		    frame.setBackground(Color.PINK);
		    this.add(frame);			
			
			JLabel username=new JLabel("�˺�");
			JLabel password=new JLabel("����");
			JLabel id=new JLabel("ѧ��");
			JLabel phone=new JLabel("�ֻ�����");
			JLabel code=new JLabel("��֤��");
			JLabel photo=new JLabel();
			user=new JTextField();
			pass=new JTextField();
			idd=new JTextField();
			ph=new JTextField();
			co=new JTextField();
			pho=new JButton("�ϴ���Ƭ");
			register=new JButton("ע  ��");
			exit=new JButton("��  ��");
			
			username.setBounds(58, 46, 60, 40);
			username.setFont(new Font("����",Font.BOLD,17));
			user.setBounds(100,50,120,30);
			password.setBounds(54,100,120,30);
			password.setFont(new Font("����",Font.BOLD,17));
			pass.setBounds(100,100,120,30);
			id.setBounds(54,150,120,30);
			id.setFont(new Font("����",Font.BOLD,17));
			idd.setBounds(100,150,120,30);
			phone.setBounds(22,200,120,30);
			phone.setFont(new Font("����",Font.BOLD,17));
			ph.setBounds(100,200,120,30);
			code.setBounds(41,250,120,30);
			code.setFont(new Font("����",Font.BOLD,17));
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
					JFileChooser fileChooser = new JFileChooser("C:\\\\Users\\\\С��ͬѧ\\\\Pictures");
					fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
					int returnVal = fileChooser.showOpenDialog(fileChooser);
					if(returnVal == JFileChooser.APPROVE_OPTION)
					{ 
					    File filePath = fileChooser.getSelectedFile();//��ȡͼƬ·��						
						System.out.println(filePath);
						String f=filePath.getPath();
						String filePath1=f.replaceAll("\\\\", "\\\\\\\\");	//��\ת��Ϊ\\
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
					String user="sa";//sa��������Ա
					String password="�������";//����
					Connection conn=DriverManager.getConnection(url,user,password);
					Statement st=conn.createStatement();

				    String  strSQL="(insert * from  dbo.PY where ID='"+user1+"' )";
					ResultSet rs=st.executeQuery(strSQL);
					
					Md5 md5 = new Md5(); 
					String newString = md5.EncoderByMd5(pass1);
					
					if(co1.isEmpty()) {
						JOptionPane.showMessageDialog(null, "��������֤��!","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
					}
					else
					{
						if(!isValidCodeRight()) {
							JOptionPane.showMessageDialog(null, "��֤�����,����������!","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
						}
						else {

					if(rs.next())
					{					 
						JOptionPane.showMessageDialog(null,"�û����Ѵ���","����!", JOptionPane.ERROR_MESSAGE);						
					}
					else
					{ 
						String sql = "insert into dbo.PY(ID,PAWD,Sno,phone) values('"+user1+"','"+newString+"','"+id1+"','"+ph1+"') "; 
						PreparedStatement pst = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);  
						pst.executeUpdate();
						pst.close();  
						JOptionPane.showMessageDialog(null,"ע��ɹ�");
					}
				    conn.close();				   
					//�ر����ݿ�����	
				             } 
				}
				}
				catch (ClassNotFoundException ex) {
					System.out.println("û���ҵ���Ӧ�����ݿ�������");
				}
				catch (SQLException ex) {
					System.out.println("���ݿ����ӻ��������ݿ����ʧ��");
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
		
		
		public  void closeThis()//�رյ�ǰ����
		{
			this.dispose();
		}
		
	}

