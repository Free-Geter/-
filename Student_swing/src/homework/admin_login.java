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
		super("����Ա��½");			
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(450,350);	
		this.setLocationRelativeTo(null);
		setVisible(true);	
		
		//��������Ϊ��ɫ����
	    JPanel frame=new JPanel();        
	    frame.setBackground(Color.PINK);
	    this.add(frame);
		
	    //�˺š������������ͼ��
	    Icon login = new ImageIcon("C:\\\\Users\\\\С��ͬѧ\\\\Pictures\\\\1.png");
		JLabel l= new JLabel();
		l.setIcon(login);		
		Icon password = new ImageIcon("C:\\\\Users\\\\С��ͬѧ\\\\Pictures\\\\2.png");		
		JLabel p= new JLabel();
		p.setIcon(password);
		
		JLabel code=new JLabel("��֤��");
		code.setFont(new Font("����",Font.BOLD,17));
		
		user=new JTextField();
	    pass=new JPasswordField();
	    co=new JTextField();
		ok=new JButton("��¼");
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

		
		ok.addActionListener(new ActionListener()    //������¼��ť
				{

			public void actionPerformed(ActionEvent e)
			{
				String jusername=user.getText();
				char s[]=pass.getPassword();
				String jpassword=new String(s);
				String coo=co.getText();	
				
				try {
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					//���ض�Ӧ��jdbc����
					String url="jdbc:sqlserver://127.0.0.1:1433;DatabaseName=SCHOOL";
					//���������ַ���
					String user="sa";//sa��������Ա
					String password="jiaganyu";//����
					Connection conn=DriverManager.getConnection(url,user,password);
					//�������ݿ����Ӷ���
					Statement st=conn.createStatement();
					//����SQL���ִ�ж���

				    String  strSQL="(Select * from  dbo.GLY where ID1='"+jusername+"'And PAWD1='"+jpassword+"' )";
				   // String  strSQL1="(Select * from  dbo.GLY where ID1='"+jusername+"'And PAWD1='"+jpassword+"' )";
					ResultSet rs=st.executeQuery(strSQL);
					//ResultSet rs1=st.executeQuery(strSQL1);
									
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
							new admin_view();	
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