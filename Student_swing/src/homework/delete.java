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
	super("删除信息");			
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	this.setSize(500,550);		
	this.setLocationRelativeTo(null);
	setVisible(true);
	
	JPanel frame=new JPanel();        
    frame.setBackground(Color.PINK);
    this.add(frame);
    
    JCheckBox optionA=new JCheckBox(" 姓名");
	JCheckBox optionB=new JCheckBox(" 性别");
	JCheckBox optionC=new JCheckBox(" 年龄");
	JCheckBox optionD=new JCheckBox(" 专业");
	JCheckBox optionE=new JCheckBox(" 成绩");
	JCheckBox optionF=new JCheckBox(" 等级");

	optionA.setContentAreaFilled(false);  optionA.setFont(new Font("楷体",Font.PLAIN,20));
	optionB.setContentAreaFilled(false);  optionB.setFont(new Font("楷体",Font.PLAIN,20));
	optionC.setContentAreaFilled(false);  optionC.setFont(new Font("楷体",Font.PLAIN,20));
	optionD.setContentAreaFilled(false);  optionD.setFont(new Font("楷体",Font.PLAIN,20));
	optionE.setContentAreaFilled(false);  optionE.setFont(new Font("楷体",Font.PLAIN,20));
	optionF.setContentAreaFilled(false);  optionF.setFont(new Font("楷体",Font.PLAIN,20));
	
	JLabel j=new JLabel("学号:");
	j.setFont(new Font("楷体",Font.PLAIN,20));//设置字体的字体，样子，大小	
	JTextField c1=new JTextField(12);
	
	JLabel cc=new JLabel("课程号:");cc.setFont(new Font("楷体",Font.PLAIN,20));
	JTextField c=new JTextField(12);
	
	JLabel j11=new JLabel("课程号:");j11.setFont(new Font("楷体",Font.PLAIN,20));
	JTextField c11=new JTextField(10);
	JLabel j12=new JLabel("课程名:");j12.setFont(new Font("楷体",Font.PLAIN,20));	
	JTextField c12=new JTextField(10);
	
    JLabel j1=new JLabel("(温馨提醒：谨慎删除哦~)");j1.setFont(new Font("宋体",Font.PLAIN,12));
    
    JButton aa=new JButton("确定"); aa.setFont(new Font("楷体",Font.PLAIN,20));
    aa.setBackground(Color.GREEN); 
    JButton bb=new JButton("重置"); bb.setFont(new Font("楷体",Font.PLAIN,20));
    bb.setBackground(Color.RED);
    
    
    aa.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e)
        {
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
    				
    				JOptionPane.showMessageDialog(null,"删除成功哦~");
     			}
     				else
     				{
     					String cc=c11.getText().trim();
     					String strSQL="delete from  dbo.Course where Cno='"+cc+"' ";  
     				    int rr=st.executeUpdate(strSQL);
     					if(rr==1)
     					{
     						JOptionPane.showMessageDialog(null,"删除成功哦~");
     					}
     					else
     					{
     						JOptionPane.showMessageDialog(null,"课程不存在哦~");
     					}
     				}
     				
     				conn.close();
        		}catch (ClassNotFoundException ex) {
    				System.out.println("没有找到对应的数据库驱动类");
    			}
    			catch (SQLException ex) {
    				System.out.println("数据库连接或者是数据库操作失败");
    			}      	
        	}
    });
    
  //重置清零
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
    
    
    ImageIcon im1 =new ImageIcon("C:\\\\Users\\\\小甘同学\\\\Pictures\\\\c.gif");
    JLabel i=new JLabel(im1);
    ImageIcon im2 =new ImageIcon("C:\\\\Users\\\\小甘同学\\\\Pictures\\\\b.gif");
    JLabel i1=new JLabel(im2);
    JLabel i2=new JLabel(im2);
    JLabel i3=new JLabel(im2);
    
	
    
    frame.setLayout(null);//自由布局
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