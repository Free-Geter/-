package homework;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class add extends JFrame{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public add() {
		super("�����Ϣ");			
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(500,550);		
		this.setLocationRelativeTo(null);
		setVisible(true);
		
		JPanel frame=new JPanel();        
	    frame.setBackground(Color.PINK);
	    this.add(frame);
	    
	    JLabel j=new JLabel("ѧ��:"); j.setFont(new Font("����",Font.PLAIN,20));//������������壬���ӣ���С   	
	    JLabel j1=new JLabel("����:");j1.setFont(new Font("����",Font.PLAIN,20));	
	    JLabel j2=new JLabel("�Ա�:");j2.setFont(new Font("����",Font.PLAIN,20));   	
	    JLabel j3=new JLabel("����:");j3.setFont(new Font("����",Font.PLAIN,20));    
	    JLabel j4=new JLabel("רҵ:");j4.setFont(new Font("����",Font.PLAIN,20));  
	    JLabel j5=new JLabel("�γ�:");j5.setFont(new Font("����",Font.PLAIN,20));    
	    JLabel j6=new JLabel("�ɼ�:");j6.setFont(new Font("����",Font.PLAIN,20)); 
	    JLabel j7=new JLabel("�ȼ�:");j7.setFont(new Font("����",Font.PLAIN,20));    
	    
	    JLabel j11=new JLabel("�γ̺�:");j11.setFont(new Font("����",Font.PLAIN,20));  
	    JLabel j12=new JLabel("�γ���:");j12.setFont(new Font("����",Font.PLAIN,20));   
	    JLabel j13=new JLabel("ѧ��:"); j13.setFont(new Font("����",Font.PLAIN,20));
	    
	    JLabel j8=new JLabel("(��ܰ���ѣ����ѧ����Ϣѧ�ű���Ŷ~���ұ�һ��Ϊ�γ���Ϣ)");
	    j8.setFont(new Font("����",Font.PLAIN,11));
	    
	    JButton aa=new JButton("ȷ��");
	    aa.setFont(new Font("����",Font.PLAIN,20));
	    aa.setBackground(Color.GREEN);
	    JButton bb=new JButton("����");
	    bb.setFont(new Font("����",Font.PLAIN,20));
	    bb.setBackground(Color.RED);
	    
	    ImageIcon im1 =new ImageIcon("C:\\\\Users\\\\С��ͬѧ\\\\Pictures\\\\a.gif");
	    JLabel i=new JLabel(im1);
	    ImageIcon im2 =new ImageIcon("C:\\\\Users\\\\С��ͬѧ\\\\Pictures\\\\b.gif");
	    JLabel i1=new JLabel(im2);
	    JLabel i2=new JLabel(im2);
	    JLabel i3=new JLabel(im2);
	    
	    JTextField c=new JTextField(15);//ѧ��
	    JTextField c1=new JTextField(15);//����
	    JTextField c3=new JTextField(15);//����
	    JTextField c11=new JTextField(15);//�γ̺�
	    JTextField c12=new JTextField(15);//�γ���
	    JTextField c13=new JTextField(15);//ѧ��   
	    JTextField c2=new JTextField(15);//�Ա�
	    JTextField c4=new JTextField(15);//רҵ
		JTextField c7=new JTextField(15);//�ȼ�	   
	    JTextField c5=new JTextField(20);//�γ�
	    JTextField c6=new JTextField(20);//�ɼ�
	    
	    aa.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
            	try {
    				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    				String url="jdbc:sqlserver://127.0.0.1:1433;DatabaseName=SCHOOL";
    				String user="sa";//sa��������Ա
    				String password="jiaganyu";//����
    				Connection conn=DriverManager.getConnection(url,user,password);
    				Statement st=conn.createStatement();
   
    				String a=c.getText().trim();    String a1=c1.getText().trim();			
    				String a2=c3.getText().trim();  String a3=c5.getText().trim();				
    				String a4=c6.getText().trim();  String a11=c11.getText().trim();			
    				String a12=c12.getText().trim();String a13=c13.getText().trim();			
    				String a5=c2.getText().trim();  String a6=c4.getText().trim();			
    				String a7=c7.getText().trim();
 			
    				String  s="(Select * from dbo.Student where Sno='"+a+"')";
     				ResultSet r=st.executeQuery(s);
				
     				if(r.next())
     				{
     				JOptionPane.showMessageDialog(null, "��ͬѧ�Ѵ���Ŷ~","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
     				}
     				else
     				{			
     					if(a.equals(""))
     					{
     					String  s3="insert into dbo.Course(Cno,Cname,Ccredit) values('"+a11+"','"+a12+"','"+a13+"')";
     					int r3=st.executeUpdate(s3);
     					if(r3==1)
						{
							JOptionPane.showMessageDialog(null, "��ӳɹ�","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);				    
					    }
     					}
     					else
     					{
     					String  s1="insert into dbo.student(Sno,Sname,Ssex,Sage,Sdept) values('"+a+"','"+a1+"','"+a5+"','"+a2+"','"+a6+"')";
     					int r1=st.executeUpdate(s1);
     					String  s2="insert into dbo.SC(Sno,Cno,Grade,LEVEL) values('"+a3+"','"+a4+"','"+a7+"')";
     					int r2=st.executeUpdate(s2);
						if(r1==1&&r2==1)
						{
						JOptionPane.showMessageDialog(null, "��ӳɹ�","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);				    
					    }	
     					}
     				}		
     				conn.close();
            	}catch (ClassNotFoundException ex) {
    				System.out.println("û���ҵ���Ӧ�����ݿ�������");
    			}
    			catch (SQLException ex) {
    				System.out.println("���ݿ����ӻ��������ݿ����ʧ��");
    			}      	
            }
	    });
	        
	    //��������
	    bb.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
            	c.setText("");
            	c1.setText("");
            	c2.setText("");   	
				c3.setText("");
				c4.setText("");
				c5.setText("");
            	c6.setText("");
            	c11.setText("");
            	c12.setText("");
            	c13.setText("");
            }
	    });    
	    
	    frame.setLayout(null);//���ɲ���
	    j.setBounds(20, 30,  50, 20); c.setBounds(80, 30, 120, 25);      
	    j1.setBounds(20, 70, 50, 20);c1.setBounds(80, 70, 100, 25);        
	    j2.setBounds(20, 110, 50, 30);c2.setBounds(80, 110, 100, 25);        
	    j3.setBounds(20, 150, 50, 30);c3.setBounds(80, 150, 100, 25);        
	    j4.setBounds(20, 190, 50, 30);c4.setBounds(80, 190, 100, 25);        
	    j5.setBounds(20, 230, 50, 30);c5.setBounds(80, 230, 100, 25);  	      
	    j6.setBounds(20, 270, 50, 30);c6.setBounds(80, 270, 100, 25);   
	    j7.setBounds(20, 310, 50, 30); c7.setBounds(80, 310, 100, 25);
	       
	    j11.setBounds(300, 120, 70, 30); c11.setBounds(380, 120, 100, 25);  
	    j12.setBounds(300, 170, 70, 30); c12.setBounds(380, 170, 100, 25);  
	    j13.setBounds(300, 230, 70, 30); c13.setBounds(380, 230, 100, 25); 
	       
	    aa.setBounds(100, 400, 100, 30); bb.setBounds(300, 400, 100, 30);       
	    j8.setBounds(10, 480, 450, 15); 
	    i.setBounds(220,330,60,60); i1.setBounds(220,250,60,60);
	    i2.setBounds(220,170,60,60);i3.setBounds(220,90,60,60);
        
	    frame.add(j);  frame.add(j1); frame.add(j2);  
	    frame.add(j3); frame.add(j4); frame.add(j5);	   
	    frame.add(j6); frame.add(j7); frame.add(j11);    
	    frame.add(j12);frame.add(j13);frame.add(c11);   
	    frame.add(c12);frame.add(c13);frame.add(c); 
	    frame.add(c1); frame.add(c2); frame.add(c3);    
	    frame.add(c4); frame.add(c5); frame.add(c6);  
	    frame.add(c7); frame.add(aa); frame.add(bb);
	    frame.add(j8); frame.add(i);  frame.add(i1);   
	    frame.add(i2); frame.add(i3);   
    } 	
    } 