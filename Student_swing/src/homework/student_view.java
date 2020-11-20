package homework;

import java.awt.Color;//省略咯！！
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.sql.*;
import java.util.Vector;

public class student_view extends JFrame{
	/**
	 * 
	 */	
	private static final long serialVersionUID = 1L;
	JTabbedPane jtbp; //定义选项卡
	JPanel jp1,jp2,jp3;	//定义面板
	
	public student_view() throws SQLException, ClassNotFoundException
	{
	super("学生登陆");			
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setSize(800,600);	
	this.setLocationRelativeTo(null);
	setVisible(true);
	
	MenuBar bar = new MenuBar();// 创建菜单栏
	bar.setFont(new Font("楷体",Font.PLAIN,30));
	Menu fileMenu = new Menu("FILE");// 创建“文件”菜单
	fileMenu.setFont(new Font("楷体",Font.PLAIN,17));
	MenuItem open = new MenuItem("OPEN");
	MenuItem exit = new MenuItem("EXIT");
	Menu help = new Menu("HELP");// 创建“帮助"菜单
	help.setFont(new Font("楷体",Font.PLAIN,17));
	MenuItem print = new MenuItem("PRINT");
	
	exit.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e)
        {
        	 new student_login();
        	 closeThis();   	
        }
	});
	
	fileMenu.add(print);	   
	fileMenu.add(open);
	fileMenu.addSeparator();// 设置菜单分隔符
	fileMenu.add(exit);
	bar.add(fileMenu);// 将文件添加到菜单栏上	
	bar.add(help);// 将文件添加到菜单栏上
	setMenuBar(bar);// 设置菜单栏，使用这种方式设置菜单栏可以不占用布局空间
	
	//创建组件	
	jp1= new JPanel();
	jp2= new JPanel();
	jp3= new JPanel();
	jp1.setBackground(Color.WHITE);
	jp2.setBackground(Color.WHITE);
	jp3.setBackground(Color.WHITE);
	
	
	//jp1面板上上的内容
	String[][] datas = {};
    String[] titles = { "学号", "姓名","性别","年龄","专业" };        
    String[][] datas1 = {};
    String[] titles1 = { "课程号", "课程名","学分"};      

    DefaultTableModel myModel  = new DefaultTableModel(datas, titles);// myModel存放表格的数据
    DefaultTableModel myModel1 = new DefaultTableModel(datas1, titles1);
    JTable table  = new JTable(myModel);// 表格对象table的数据来源是myModel对象   
    JTable table1 = new JTable(myModel1);
    table.setPreferredScrollableViewportSize(new Dimension(550, 100));// 表格的显示尺寸
    table1.setPreferredScrollableViewportSize(new Dimension(550, 100));
    // 产生一个带滚动条的面板
    JScrollPane scrollPane = new JScrollPane(table);
    JScrollPane scrollPane1 = new JScrollPane(table1);
    //行高
    table.setRowHeight(20);
    table1.setRowHeight(20);
 
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String url="jdbc:sqlserver://127.0.0.1:1433;DatabaseName=SCHOOL";
		String user="sa";//sa超级管理员
		String password="你的密码";//密码
		Connection conn=DriverManager.getConnection(url,user,password);
		Statement st=conn.createStatement();
		
		String  strSQL="(Select * from  dbo.student where Sname='"+ student_login.user.getText()+"')";
		ResultSet rs=st.executeQuery(strSQL);
		if(rs.next())
		{
			 Vector<String> ve = new Vector<String>();
				ve.addElement(rs.getString(1));
				ve.addElement(rs.getString(2));
				ve.addElement(rs.getString(3));
				ve.addElement(rs.getString(4));
				ve.addElement(rs.getString(5));
				myModel.addRow(ve);				 	
		}
		
		String  s1="(Select * from dbo.student,dbo.SC where Sname='"+student_login.user.getText()+"' And student.Sno=SC.Sno)";
		ResultSet r1=st.executeQuery(s1);
		if(r1.next())
		{	
		String  s2="(Select * from dbo.Course,dbo.SC where Sno='"+r1.getString(7)+"' And Course.Cno=SC.Cno)";
		ResultSet r2=st.executeQuery(s2);
		while(r2.next())
		{
			Vector<String> ve1 = new Vector<String>();
			ve1.addElement(r2.getString(1));
			ve1.addElement(r2.getString(2));
			ve1.addElement(r2.getString(4));
			myModel1.addRow(ve1);	
		}
		}
    
    ImageIcon im1 =new ImageIcon("C:\\\\Users\\\\小甘同学\\\\Pictures\\\\Saved Pictures\\\\1.png");
    JLabel j=new JLabel(im1);
    ImageIcon im2 =new ImageIcon("C:\\\\Users\\\\小甘同学\\\\Pictures\\\\1.gif");
    JLabel j1=new JLabel(im2);
    JButton again=new JButton("刷 新~");
    again.setContentAreaFilled(false);	
    again.setFont(new Font("楷体",Font.BOLD,14));
    again.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e)
        {    	
        	try {
        	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    		String url="jdbc:sqlserver://127.0.0.1:1433;DatabaseName=SCHOOL";
    		String user="sa";//sa超级管理员
    		String password="jiaganyu";//密码
    		Connection conn=DriverManager.getConnection(url,user,password);
    		Statement st=conn.createStatement();
    		
			while(myModel1.getRowCount()>0)
			{
				myModel1.removeRow(myModel1.getRowCount()-1);
				}
    		
    		String  s1="(Select * from dbo.student,dbo.SC where Sname='"+student_login.user.getText()+"' And student.Sno=SC.Sno)";
    		ResultSet r1=st.executeQuery(s1);
    		if(r1.next())
    		{	
    		String  s2="(Select * from dbo.Course,dbo.SC where Sno='"+r1.getString(7)+"' And Course.Cno=SC.Cno)";
    		ResultSet r2=st.executeQuery(s2);
    		while(r2.next())
    		{
    			Vector<String> ve1 = new Vector<String>();
    			ve1.addElement(r2.getString(1));
    			ve1.addElement(r2.getString(2));
    			ve1.addElement(r2.getString(4));
    			myModel1.addRow(ve1);	
    		}
    		conn.close();
    		}
        }catch (ClassNotFoundException ex) {
			System.out.println("没有找到对应的数据库驱动类");
		}
		catch (SQLException ex) {
			System.out.println("数据库连接或者是数据库操作失败");
		}
        }
    });
       
    
        	
    //jp2面板上的内容
    String[][] datas2 = {};
    String[] titles2 = { "课程号", "课程名","学分" };        
    DefaultTableModel myModel2 = new DefaultTableModel(datas2, titles2);
    JTable table2  = new JTable(myModel2);
    table2.setRowHeight(20);
    table2.setPreferredScrollableViewportSize(new Dimension(550, 400));
    JScrollPane scrollPane2 = new JScrollPane(table2); 
    String  s2="(Select * from dbo.Course)";
    ResultSet r2=st.executeQuery(s2);
	while(r2.next())
	{
		Vector<String> ve2 = new Vector<String>();
		ve2.addElement(r2.getString(1));
		ve2.addElement(r2.getString(2));
		ve2.addElement(r2.getString(4));
		myModel2.addRow(ve2);	
	}
	conn.close();
	
	JLabel a=new JLabel("请输入你想选的课的课程号：");
	a.setFont(new Font("楷体",Font.BOLD,18));
	JTextField b=new JTextField(20);
	JButton c=new JButton("确定");
	c.setFont(new Font("楷体",Font.BOLD,20));
	
	c.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e)
        {
        	try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				String url="jdbc:sqlserver://127.0.0.1:1433;DatabaseName=SCHOOL";
				String user="sa";//sa超级管理员
				String password="你的密码";//密码
				Connection conn=DriverManager.getConnection(url,user,password);
				Statement st=conn.createStatement();

				String ok=b.getText().trim();
			    String s="(Select * from dbo.Course where Cno='"+ok+"' )";
				ResultSet r=st.executeQuery(s);
				if(r.next())
				{
					String s1="(Select * from dbo.student,dbo.SC where Sname='"+student_login.user.getText()+"' And SC.Sno=student.Sno )";
					ResultSet r1=st.executeQuery(s1);
					if(r1.next())
					{
					String s2="(Select * from dbo.SC where Sno='"+r1.getString(1)+"' And Cno='"+ok+"' )";
					ResultSet r2=st.executeQuery(s2);
					if(r2.next())
					{
						JOptionPane.showMessageDialog(null, "你已经选过该科目了~","提示消息",JOptionPane.WARNING_MESSAGE);
					}
					else
					{	
					String ss="(Select * from dbo.student where Sname='"+student_login.user.getText()+"')";
					ResultSet rr=st.executeQuery(ss);
					if(rr.next())
					{
						String  strSQL="insert into dbo.SC(Sno,Cno) values('"+rr.getString(1)+"','"+ok+"')";
						int rr1=st.executeUpdate(strSQL);
						if(rr1==1)
						{
						JOptionPane.showMessageDialog(null, "选课成功","提示消息",JOptionPane.WARNING_MESSAGE);				    
					    }		
					}
					}
				}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "没有这种科目哦~","提示消息",JOptionPane.WARNING_MESSAGE);
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
	
	

	
	//jp3上的内容
	ImageIcon im3 =new ImageIcon("C:\\\\Users\\\\小甘同学\\\\Pictures\\\\f.gif");
	JLabel j2=new JLabel(im3);
	JLabel ja1=new JLabel("你想查询的科目是：");
	JLabel ja2=new JLabel("你的成绩是：");
	JLabel ja3=new JLabel("你的等级是：");
	JLabel ja4=new JLabel("(输入课程号哦~)");
	ja1.setFont(new Font("楷体",Font.BOLD,20));
	ja2.setFont(new Font("楷体",Font.BOLD,20));
	ja3.setFont(new Font("楷体",Font.BOLD,20));
	ja4.setFont(new Font("楷体",Font.BOLD,15));
	JTextField b1=new JTextField(15);
	JTextField b2=new JTextField(15);
	JTextField b3=new JTextField(15);
	JButton c1=new JButton("查 询");
	
	c1.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e)
        {
        	try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				String url="jdbc:sqlserver://127.0.0.1:1433;DatabaseName=SCHOOL";
				String user="sa";//sa超级管理员
				String password="你的密码";//密码
				Connection conn=DriverManager.getConnection(url,user,password);
				Statement st=conn.createStatement();
				
				String B1=b1.getText().trim();
				String L="(Select * from dbo.student,dbo.SC where Sname='"+student_login.user.getText()+"' And Cno='"+B1+"' And SC.Sno=student.Sno )";
				ResultSet M=st.executeQuery(L);
				if(M.next())
				{
					b2.setText(M.getString(9));
					b3.setText(M.getString(10));
				}
				else
				{
					JOptionPane.showMessageDialog(null, "没有该科目的成绩哦~","提示消息",JOptionPane.WARNING_MESSAGE);
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
	
	
    jp1.setLayout(null);//自由布局
    jp2.setLayout(null);//自由布局
    jp3.setLayout(null);//自由布局
    
    //jp1中组件的位置
    scrollPane.setBounds(50, 190, 550, 70);
    scrollPane1.setBounds(50, 290, 550, 100);
    j.setBounds(250, 20, 150, 150);
    j1.setBounds(180, 370, 300, 150);
    again.setBounds(490, 140, 80, 30);
    
    
    //jp2中组件的位置
    scrollPane2.setBounds(50, 20, 550, 400);
    a.setBounds(50, 470, 270, 30);
    b.setBounds(320, 470, 150, 25);
    c.setBounds(500, 470, 80, 27);
    
    
    //jp3中组件的位置
    j2.setBounds(430, 330, 200, 200);
    ja1.setBounds(50, 50, 200, 30);
    ja2.setBounds(80, 220, 150, 30);
    ja3.setBounds(80, 270, 150, 30);
    ja4.setBounds(255, 80, 150, 30);
    b1.setBounds(260, 50, 150, 25);
    b2.setBounds(260, 220, 100, 25);
    b3.setBounds(260, 270, 100, 25);
    c1.setBounds(450, 50, 70, 30);
    
    // 将组件添加入jp1窗口中  
    jp1.add(scrollPane);
    jp1.add(scrollPane1);
    jp1.add(j);
    jp1.add(j1);
    jp1.add(again);
    
    // 将组件添加入jp2窗口中
    jp2.add(scrollPane2);
    jp2.add(a);
    jp2.add(b);
    jp2.add(c);
    
    // 将组件添加入jp3窗口中
    jp3.add(j2);
    jp3.add(ja1);
    jp3.add(ja2);
    jp3.add(ja3);
    jp3.add(ja4);
    jp3.add(b1);
    jp3.add(b2);
    jp3.add(b3);
    jp3.add(c1);
	
	jtbp=new JTabbedPane(JTabbedPane.LEFT); //创建选项卡并使选项卡垂直排列
	jtbp.add("个人信息",jp1);		
	jtbp.add("选课",jp2);
	jtbp.add("成绩查询",jp3);
	jtbp.setFont(new Font("楷体",Font.PLAIN,30)); 
	this.add(jtbp);    //添加选项卡窗格到容器	
	}
	
	public  void closeThis()//关闭当前界面
	{
		this.dispose();
	}
}
