package homework;

import java.awt.Color;//ʡ�Կ�����
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
	JTabbedPane jtbp; //����ѡ�
	JPanel jp1,jp2,jp3;	//�������
	
	public student_view() throws SQLException, ClassNotFoundException
	{
	super("ѧ����½");			
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setSize(800,600);	
	this.setLocationRelativeTo(null);
	setVisible(true);
	
	MenuBar bar = new MenuBar();// �����˵���
	bar.setFont(new Font("����",Font.PLAIN,30));
	Menu fileMenu = new Menu("FILE");// �������ļ����˵�
	fileMenu.setFont(new Font("����",Font.PLAIN,17));
	MenuItem open = new MenuItem("OPEN");
	MenuItem exit = new MenuItem("EXIT");
	Menu help = new Menu("HELP");// ����������"�˵�
	help.setFont(new Font("����",Font.PLAIN,17));
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
	fileMenu.addSeparator();// ���ò˵��ָ���
	fileMenu.add(exit);
	bar.add(fileMenu);// ���ļ���ӵ��˵�����	
	bar.add(help);// ���ļ���ӵ��˵�����
	setMenuBar(bar);// ���ò˵�����ʹ�����ַ�ʽ���ò˵������Բ�ռ�ò��ֿռ�
	
	//�������	
	jp1= new JPanel();
	jp2= new JPanel();
	jp3= new JPanel();
	jp1.setBackground(Color.WHITE);
	jp2.setBackground(Color.WHITE);
	jp3.setBackground(Color.WHITE);
	
	
	//jp1������ϵ�����
	String[][] datas = {};
    String[] titles = { "ѧ��", "����","�Ա�","����","רҵ" };        
    String[][] datas1 = {};
    String[] titles1 = { "�γ̺�", "�γ���","ѧ��"};      

    DefaultTableModel myModel  = new DefaultTableModel(datas, titles);// myModel��ű�������
    DefaultTableModel myModel1 = new DefaultTableModel(datas1, titles1);
    JTable table  = new JTable(myModel);// ������table��������Դ��myModel����   
    JTable table1 = new JTable(myModel1);
    table.setPreferredScrollableViewportSize(new Dimension(550, 100));// ������ʾ�ߴ�
    table1.setPreferredScrollableViewportSize(new Dimension(550, 100));
    // ����һ���������������
    JScrollPane scrollPane = new JScrollPane(table);
    JScrollPane scrollPane1 = new JScrollPane(table1);
    //�и�
    table.setRowHeight(20);
    table1.setRowHeight(20);
 
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String url="jdbc:sqlserver://127.0.0.1:1433;DatabaseName=SCHOOL";
		String user="sa";//sa��������Ա
		String password="�������";//����
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
    
    ImageIcon im1 =new ImageIcon("C:\\\\Users\\\\С��ͬѧ\\\\Pictures\\\\Saved Pictures\\\\1.png");
    JLabel j=new JLabel(im1);
    ImageIcon im2 =new ImageIcon("C:\\\\Users\\\\С��ͬѧ\\\\Pictures\\\\1.gif");
    JLabel j1=new JLabel(im2);
    JButton again=new JButton("ˢ ��~");
    again.setContentAreaFilled(false);	
    again.setFont(new Font("����",Font.BOLD,14));
    again.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e)
        {    	
        	try {
        	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    		String url="jdbc:sqlserver://127.0.0.1:1433;DatabaseName=SCHOOL";
    		String user="sa";//sa��������Ա
    		String password="jiaganyu";//����
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
			System.out.println("û���ҵ���Ӧ�����ݿ�������");
		}
		catch (SQLException ex) {
			System.out.println("���ݿ����ӻ��������ݿ����ʧ��");
		}
        }
    });
       
    
        	
    //jp2����ϵ�����
    String[][] datas2 = {};
    String[] titles2 = { "�γ̺�", "�γ���","ѧ��" };        
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
	
	JLabel a=new JLabel("����������ѡ�ĿεĿγ̺ţ�");
	a.setFont(new Font("����",Font.BOLD,18));
	JTextField b=new JTextField(20);
	JButton c=new JButton("ȷ��");
	c.setFont(new Font("����",Font.BOLD,20));
	
	c.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e)
        {
        	try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				String url="jdbc:sqlserver://127.0.0.1:1433;DatabaseName=SCHOOL";
				String user="sa";//sa��������Ա
				String password="�������";//����
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
						JOptionPane.showMessageDialog(null, "���Ѿ�ѡ���ÿ�Ŀ��~","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
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
						JOptionPane.showMessageDialog(null, "ѡ�γɹ�","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);				    
					    }		
					}
					}
				}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "û�����ֿ�ĿŶ~","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
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
	
	

	
	//jp3�ϵ�����
	ImageIcon im3 =new ImageIcon("C:\\\\Users\\\\С��ͬѧ\\\\Pictures\\\\f.gif");
	JLabel j2=new JLabel(im3);
	JLabel ja1=new JLabel("�����ѯ�Ŀ�Ŀ�ǣ�");
	JLabel ja2=new JLabel("��ĳɼ��ǣ�");
	JLabel ja3=new JLabel("��ĵȼ��ǣ�");
	JLabel ja4=new JLabel("(����γ̺�Ŷ~)");
	ja1.setFont(new Font("����",Font.BOLD,20));
	ja2.setFont(new Font("����",Font.BOLD,20));
	ja3.setFont(new Font("����",Font.BOLD,20));
	ja4.setFont(new Font("����",Font.BOLD,15));
	JTextField b1=new JTextField(15);
	JTextField b2=new JTextField(15);
	JTextField b3=new JTextField(15);
	JButton c1=new JButton("�� ѯ");
	
	c1.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e)
        {
        	try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				String url="jdbc:sqlserver://127.0.0.1:1433;DatabaseName=SCHOOL";
				String user="sa";//sa��������Ա
				String password="�������";//����
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
					JOptionPane.showMessageDialog(null, "û�иÿ�Ŀ�ĳɼ�Ŷ~","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
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
	
	
    jp1.setLayout(null);//���ɲ���
    jp2.setLayout(null);//���ɲ���
    jp3.setLayout(null);//���ɲ���
    
    //jp1�������λ��
    scrollPane.setBounds(50, 190, 550, 70);
    scrollPane1.setBounds(50, 290, 550, 100);
    j.setBounds(250, 20, 150, 150);
    j1.setBounds(180, 370, 300, 150);
    again.setBounds(490, 140, 80, 30);
    
    
    //jp2�������λ��
    scrollPane2.setBounds(50, 20, 550, 400);
    a.setBounds(50, 470, 270, 30);
    b.setBounds(320, 470, 150, 25);
    c.setBounds(500, 470, 80, 27);
    
    
    //jp3�������λ��
    j2.setBounds(430, 330, 200, 200);
    ja1.setBounds(50, 50, 200, 30);
    ja2.setBounds(80, 220, 150, 30);
    ja3.setBounds(80, 270, 150, 30);
    ja4.setBounds(255, 80, 150, 30);
    b1.setBounds(260, 50, 150, 25);
    b2.setBounds(260, 220, 100, 25);
    b3.setBounds(260, 270, 100, 25);
    c1.setBounds(450, 50, 70, 30);
    
    // ����������jp1������  
    jp1.add(scrollPane);
    jp1.add(scrollPane1);
    jp1.add(j);
    jp1.add(j1);
    jp1.add(again);
    
    // ����������jp2������
    jp2.add(scrollPane2);
    jp2.add(a);
    jp2.add(b);
    jp2.add(c);
    
    // ����������jp3������
    jp3.add(j2);
    jp3.add(ja1);
    jp3.add(ja2);
    jp3.add(ja3);
    jp3.add(ja4);
    jp3.add(b1);
    jp3.add(b2);
    jp3.add(b3);
    jp3.add(c1);
	
	jtbp=new JTabbedPane(JTabbedPane.LEFT); //����ѡ���ʹѡ���ֱ����
	jtbp.add("������Ϣ",jp1);		
	jtbp.add("ѡ��",jp2);
	jtbp.add("�ɼ���ѯ",jp3);
	jtbp.setFont(new Font("����",Font.PLAIN,30)); 
	this.add(jtbp);    //���ѡ���������	
	}
	
	public  void closeThis()//�رյ�ǰ����
	{
		this.dispose();
	}
}
