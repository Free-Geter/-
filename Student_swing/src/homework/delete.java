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
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class delete extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public delete()
	{
	super("ɾ����Ϣ");			
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	this.setSize(500,550);		
	this.setLocationRelativeTo(null);
	setVisible(true);
	
	JPanel frame=new JPanel();        
    frame.setBackground(Color.PINK);
    this.add(frame);
    
    JCheckBox optionA=new JCheckBox(" ����");
	JCheckBox optionB=new JCheckBox(" �Ա�");
	JCheckBox optionC=new JCheckBox(" ����");
	JCheckBox optionD=new JCheckBox(" רҵ");
	JCheckBox optionE=new JCheckBox(" �ɼ�");
	JCheckBox optionF=new JCheckBox(" �ȼ�");

	optionA.setContentAreaFilled(false);  optionA.setFont(new Font("����",Font.PLAIN,20));
	optionB.setContentAreaFilled(false);  optionB.setFont(new Font("����",Font.PLAIN,20));
	optionC.setContentAreaFilled(false);  optionC.setFont(new Font("����",Font.PLAIN,20));
	optionD.setContentAreaFilled(false);  optionD.setFont(new Font("����",Font.PLAIN,20));
	optionE.setContentAreaFilled(false);  optionE.setFont(new Font("����",Font.PLAIN,20));
	optionF.setContentAreaFilled(false);  optionF.setFont(new Font("����",Font.PLAIN,20));
	
	JLabel j=new JLabel("ѧ��:");
	j.setFont(new Font("����",Font.PLAIN,20));//������������壬���ӣ���С	
	JTextField c1=new JTextField(12);
	
	JLabel cc=new JLabel("�γ̺�:");cc.setFont(new Font("����",Font.PLAIN,20));
	JTextField c=new JTextField(12);
	
	JLabel j11=new JLabel("�γ̺�:");j11.setFont(new Font("����",Font.PLAIN,20));
	JTextField c11=new JTextField(10);
	JLabel j12=new JLabel("�γ���:");j12.setFont(new Font("����",Font.PLAIN,20));	
	JTextField c12=new JTextField(10);
	
    JLabel j1=new JLabel("(��ܰ���ѣ�����ɾ��Ŷ~)");j1.setFont(new Font("����",Font.PLAIN,12));
    
    JButton aa=new JButton("ȷ��"); aa.setFont(new Font("����",Font.PLAIN,20));
    aa.setBackground(Color.GREEN); 
    JButton bb=new JButton("����"); bb.setFont(new Font("����",Font.PLAIN,20));
    bb.setBackground(Color.RED);
    
    
    aa.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e)
        {
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
    				
    				String a=c1.getText().trim();
    				String a1=c.getText().trim();
    				String  s="(Select * from dbo.Student where Sno='"+a+"')";
     				ResultSet r=st.executeQuery(s);
     				
     				if(r.next())
     			   {
    				if(optionA.isSelected())
    	        	{
    					String strSQL="update dbo.Student set Sname="+null+" where Sno='"+a+"'";  
    					st.executeUpdate(strSQL);
    	        	}
    				if(optionB.isSelected())
    	        	{
    					String strSQL="update dbo.Student set Ssex="+null+" where Sno='"+a+"'";
    					st.executeUpdate(strSQL);
    	        	}
    				if(optionC.isSelected())
    	        	{
    					String strSQL="update dbo.Student set Sage="+null+" where Sno='"+a+"'"; 
    					st.executeUpdate(strSQL);
    	        	}
    				if(optionD.isSelected())
    	        	{
    					String strSQL="update dbo.Student set Sdept="+null+" where Sno='"+a+"'"; 
    					st.executeUpdate(strSQL);
    	        	}	
    				if(optionE.isSelected())
    	        	{
    					String strSQL="update dbo.SC set Grade="+null+" where Sno='"+a+"' And Cno='"+a1+"'";
    					st.executeUpdate(strSQL);
    	        	}	
    				if(optionF.isSelected())
    	        	{
    					String strSQL="update dbo.SC set LEVEL="+null+" where Sno='"+a+"' And Cno='"+a1+"'";
    					st.executeUpdate(strSQL);
    	        	}	
    				
    				JOptionPane.showMessageDialog(null,"ɾ���ɹ�Ŷ~");
     			}
     				else
     				{
     					String cc=c11.getText().trim();
     					String strSQL="delete from  dbo.Course where Cno='"+cc+"' ";  
     				    int rr=st.executeUpdate(strSQL);
     					if(rr==1)
     					{
     						JOptionPane.showMessageDialog(null,"ɾ���ɹ�Ŷ~");
     					}
     					else
     					{
     						JOptionPane.showMessageDialog(null,"�γ̲�����Ŷ~");
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
        	c1.setText("");
        	c11.setText("");
        	c12.setText("");
        	optionA.setSelected(false);
        	optionB.setSelected(false);
        	optionC.setSelected(false);
        	optionD.setSelected(false);
        	optionE.setSelected(false);
        	optionF.setSelected(false);
        }
    });
    
    
    ImageIcon im1 =new ImageIcon("C:\\\\Users\\\\С��ͬѧ\\\\Pictures\\\\c.gif");
    JLabel i=new JLabel(im1);
    ImageIcon im2 =new ImageIcon("C:\\\\Users\\\\С��ͬѧ\\\\Pictures\\\\b.gif");
    JLabel i1=new JLabel(im2);
    JLabel i2=new JLabel(im2);
    JLabel i3=new JLabel(im2);
    
	
    
    frame.setLayout(null);//���ɲ���
    j.setBounds(30, 20,  50, 20);  c1.setBounds(90, 20, 150, 25);j1.setBounds(10, 480, 450, 15);

    optionA.setBounds(80, 70, 150, 20); optionB.setBounds(80, 120, 150, 20);
    optionC.setBounds(80, 170, 150, 20);optionD.setBounds(80, 220, 150, 20);
    
    optionE.setBounds(80, 320, 150, 20); optionF.setBounds(80, 370, 150, 20);
    
    j11.setBounds(300, 150,  70, 30); c11.setBounds(380, 150, 100, 25);
    j12.setBounds(300, 250,  70, 30); c12.setBounds(380, 250, 100, 25);
    aa.setBounds(100, 420, 100, 30); bb.setBounds(300, 420, 100, 30);  
    cc.setBounds(20, 270, 70, 20);c.setBounds(90, 270, 150, 25);
    i.setBounds(220,330,60,60); i1.setBounds(220,250,60,60);
    i2.setBounds(220,170,60,60); i3.setBounds(220,90,60,60);
   

    frame.add(aa);frame.add(bb);frame.add(i);frame.add(c);frame.add(cc);
    frame.add(i1); frame.add(i2);frame.add(i3);    
    frame.add(j);frame.add(c1); 
    frame.add(optionA);frame.add(optionB);frame.add(optionC); frame.add(optionD);
    frame.add(optionE);frame.add(optionF);
    frame.add(j11);frame.add(c11); frame.add(j12); frame.add(c12); frame.add(j1);  
} 
}