package homework;

import java.sql.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class student_login extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ValidCode vcode = new ValidCode();
	JTextField co;
	static JTextField user;
	JPasswordField pass;
	JButton ok,register;

	public student_login()
	{
		super("ѧ����½");			
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(450,350);	
		this.setLocationRelativeTo(null);
		setVisible(true);			
		
		//��������Ϊ��ɫ����
	    JPanel frame=new JPanel();        
	    frame.setBackground(Color.PINK);
	    this.add(frame);
		
	    //�˺š������������ͼ��
	    Icon login = new ImageIcon("C:\\\\Users\\\\DELL\\\\Desktop\\\\user.png");
		JLabel l= new JLabel();
		l.setIcon(login);		
		Icon password = new ImageIcon("C:\\\\Users\\\\DELL\\\\Desktop\\\\password.png");		
		JLabel p= new JLabel();
		p.setIcon(password);
		
		JLabel code=new JLabel("��֤��");
		code.setFont(new Font("����",Font.BOLD,17));
		
		user=new JTextField();
		pass=new JPasswordField();
		co=new JTextField();
		ok=new JButton("��¼");
		ok.setContentAreaFilled(false);
		register=new JButton("ע��");
		register.setContentAreaFilled(false);
		
		
		l.setBounds(80, 50, 60, 40);
		p.setBounds(80, 100, 60, 40);
		code.setBounds(70, 150, 60, 40);
	    user.setBounds(150, 50, 150, 30);
		pass.setBounds(150, 100, 150, 30);
		co.setBounds(150, 150, 150, 30);
		ok.setBounds(120, 220, 70, 30);
		register.setBounds(250, 220, 70, 30);
		vcode.setBounds(310, 145, 100, 40);
		
		frame.setLayout(null);
		frame.add(l);
		frame.add(p);
		frame.add(code);
		frame.add(user);
		frame.add(pass);
		frame.add(co);
		frame.add(ok);
		frame.add(register);
		frame.add(vcode);
	
	   register.addActionListener(new ActionListener()
			   {
			   public void actionPerformed(ActionEvent e)
			   {
				   new register();
				   closeThis();
			   }
			   });
	
	   ok.addActionListener(new ActionListener()    //������¼��ť
				{

			public void actionPerformed(ActionEvent e)
			{
				String jusername=user.getText();
				char s[]=pass.getPassword();
				String jpassword=new String(s);
				String coo=co.getText();
			   
				try {
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");//���ض�Ӧ��jdbc����
					String url="jdbc:sqlserver://127.0.0.1:1433;DatabaseName=SCHOOL";//���������ַ���
					String user="sa";//sa��������Ա
					String password="�������";//����
					Connection conn=DriverManager.getConnection(url,user,password);//�������ݿ����Ӷ���
					Statement st=conn.createStatement();//����SQL���ִ�ж���

					Md5 md5 = new Md5(); 
					String newString = md5.EncoderByMd5(jpassword);
					
				    String  strSQL="(Select * from  dbo.PY where ID='"+jusername+"'And PAWD='"+newString+"' )";
					ResultSet rs=st.executeQuery(strSQL);
												
					if(coo.isEmpty()) {
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
							new student_view();	
			    			closeThis();			    
					}	
					
					else
					{ 
						JOptionPane.showMessageDialog(null,"�û��������ڻ��������","����!",JOptionPane.ERROR_MESSAGE);
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
				}catch (NoSuchAlgorithmException e1) {
				      // TODO Auto-generated catch block
				      e1.printStackTrace();
				    } catch (UnsupportedEncodingException e1) {
				      // TODO Auto-generated catch block
				      e1.printStackTrace();
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
			
	public  void closeThis()//�رյ�ǰ����
	{
		this.dispose();
	}
		
}
