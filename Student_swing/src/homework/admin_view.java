package homework;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class admin_view extends JFrame  {

	/**
	 * 
	 */
private static final long serialVersionUID = 1L;	

	public admin_view()
	{				
		super("����Ա��¼");

		JPanel A=new JPanel();		
		A.setBackground(Color.WHITE);
		JPanel B=new JPanel();
		B.setBackground(Color.WHITE);	
	    JSplitPane jSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,A,B);//�����ķ�ʽ������HORIZONTAL_SPLIT������VERTICAL_SPLIT
	    jSplitPane.setDividerLocation(150);
	    this.add(jSplitPane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800,650);	
		this.setLocationRelativeTo(null);
		setVisible(true);
		
		//����A�����еĸ������
		JButton component1=new JButton("���");
		component1.setBackground(Color.PINK);		
		JButton component2=new JButton("�޸�");
		component2.setBackground(Color.PINK);		
		JButton component3=new JButton("ɾ��");
		component3.setBackground(Color.PINK);			
		JButton component4=new JButton("�˳�");//��ɾ���˰�ť
		component4.setBackground(Color.PINK);	
		
		JLabel component5=new JLabel("������ѧ��:");
		component5.setFont(new Font("����",Font.PLAIN,17));//������������壬���ӣ���С	
		JTextField component6=new JTextField(20);//����������
		
		JButton component7=new JButton("��ѯ");//��ѯ��ť
		component7.setBackground(Color.PINK);		
		JButton component8=new JButton("ȫ �� �� Ϣ չ ʾ");
		component8.setBackground(Color.PINK);		
		component8.setFont(new Font("����",Font.BOLD,17));
		
		ImageIcon im1 =new ImageIcon("C:\\\\Users\\\\С��ͬѧ\\\\Pictures\\\\a.png");
	    JLabel componenta=new JLabel(im1);
	    ImageIcon im2 =new ImageIcon("C:\\\\Users\\\\С��ͬѧ\\\\Pictures\\\\b.png");
	    JLabel componentb=new JLabel(im2);
	    JLabel componentc=new JLabel(im2);
	    JLabel componentd=new JLabel(im1);
	    
	    ImageIcon im3 =new ImageIcon("C:\\\\Users\\\\С��ͬѧ\\\\Pictures\\\\d.gif");
	    JLabel componente=new JLabel(im3);
		
	    A.setLayout(null);//���ɲ���
	    component1.setBounds(20, 30, 70, 30);
	    component2.setBounds(105, 30, 70, 30);
	    component3.setBounds(20, 100, 70, 30);
	    component4.setBounds(105, 100, 70, 30);
	    componenta.setBounds(220, 10, 60, 60);
	    componentb.setBounds(290, 10, 60, 60);
	    componentc.setBounds(220, 80, 60, 60);
	    componentd.setBounds(290, 80, 60, 60);
	    component5.setBounds(395, 30, 100, 25);
	    component6.setBounds(500, 30, 200, 25);
	    component7.setBounds(715, 27, 60, 30);
	    component8.setBounds(460, 85, 210, 30);
	    
	    componente.setBounds(0, 230, 160, 200);
        
        //������������봰�������ȥ
        A.add(component1);
        A.add(component2);
        A.add(component3);
        A.add(component4);
        A.add(component5);
        A.add(component6);
        A.add(component7);
        A.add(component8);
        A.add(componenta);
        A.add(componentb);
        A.add(componentc);
        A.add(componentd);     
        
        B.add(componente);     
       
        //�ĸ����
        String[][] datas = {};
        String[] titles = { "ѧ��", "����","�Ա�","����","רҵ" };        
        String[][] datas1 = {};
        String[] titles1 = { "�γ̺�", "�γ���","ѧ��"};      
        String[][] datas2 = {};
        String[] titles2 = { "ѧ��", "�γ̺�","�ɼ�","�ȼ�"};
        String[][] datas3 = {};
        String[] titles3 = { "�˺�", "����","ѧ��","�绰����"};
        
        DefaultTableModel myModel  = new DefaultTableModel(datas, titles);// myModel��ű�������
        DefaultTableModel myModel1 = new DefaultTableModel(datas1, titles1);
        DefaultTableModel myModel2 = new DefaultTableModel(datas2, titles2);
        DefaultTableModel myModel3 = new DefaultTableModel(datas3, titles3);
        
        JTable table  = new JTable(myModel);// ������table��������Դ��myModel����   
        JTable table1 = new JTable(myModel1);
        JTable table2 = new JTable(myModel2);
        JTable table3 = new JTable(myModel3);
        
        table.setPreferredScrollableViewportSize(new Dimension(600, 100));// ������ʾ�ߴ�
        table1.setPreferredScrollableViewportSize(new Dimension(600, 100));
        table2.setPreferredScrollableViewportSize(new Dimension(600, 100));
        table3.setPreferredScrollableViewportSize(new Dimension(600, 100));

        // ����һ���������������
        JScrollPane scrollPane = new JScrollPane(table);
        JScrollPane scrollPane1 = new JScrollPane(table1);
        JScrollPane scrollPane2 = new JScrollPane(table2);
        JScrollPane scrollPane3 = new JScrollPane(table3);
        
        //����λ��
        scrollPane3.setBounds(170, 0, 600, 100);
        scrollPane.setBounds(170, 110, 600, 100);
        scrollPane1.setBounds(170, 220, 600, 100);
        scrollPane2.setBounds(170, 330, 600, 100);  
              
        // ��������������������B������
        B.setLayout(null);//���ɲ���
        B.add(scrollPane);
        B.add(scrollPane1);
        B.add(scrollPane2);
        B.add(scrollPane3);
       
        component1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
            	new add();
            }
        });       
        
        component2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
            	new update();
            }
        });
                
        component3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
            	new delete();
            }
        });
               
        component4.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
            	new admin_login();
            	closeThis();
            }
        });
        
        
        component7.addActionListener(new ActionListener(){
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
    				
    				while(myModel.getRowCount()>0)
    				{
    					myModel.removeRow(myModel.getRowCount()-1);
    					}

    				while(myModel1.getRowCount()>0)
    				{
    					myModel1.removeRow(myModel1.getRowCount()-1);
    					}

    				while(myModel2.getRowCount()>0)
    				{
    					myModel2.removeRow(myModel2.getRowCount()-1);
    					}
    				while(myModel3.getRowCount()>0)
    				{
    					myModel3.removeRow(myModel3.getRowCount()-1);
    					}//��ձ��ϵĶ���
    				
    				String ID=component6.getText().trim();
    			    String  s="(Select * from dbo.Student,dbo.PY where Student.Sno='"+ID+"' And Student.Sno=PY.Sno)";
    				ResultSet r=st.executeQuery(s);
    				while(r.next())
    				{
    					    Vector<String> ve = new Vector<String>();
    						ve.addElement(r.getString(1));
    						ve.addElement(r.getString(2));
    						ve.addElement(r.getString(3));
    						ve.addElement(r.getString(4));
    						ve.addElement(r.getString(5));
    						myModel.addRow(ve);		
    						
    						Vector<String> ve1 = new Vector<String>();
    						ve1.addElement(r.getString(7));
    						ve1.addElement(r.getString(8));
    						ve1.addElement(r.getString(9));
    						ve1.addElement(r.getString(10));
    						myModel3.addRow(ve1);	
    				}
    				
    				String  s1="(Select * from dbo.SC,dbo.Course where Sno='"+ID+"' And SC.Cno=Course.Cno)";
     				ResultSet r1=st.executeQuery(s1);
    				while(r1.next())
    				{
    					    Vector<String> ve1 = new Vector<String>();
    						ve1.addElement(r1.getString(1));
    						ve1.addElement(r1.getString(2));
    						ve1.addElement(r1.getString(3));
    						ve1.addElement(r1.getString(4));
    						myModel2.addRow(ve1);	
    						
    						Vector<String> ve2 = new Vector<String>();
    						ve2.addElement(r1.getString(5));
    						ve2.addElement(r1.getString(6));
    						ve2.addElement(r1.getString(8));
    						myModel1.addRow(ve2);
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
            
        
        component8.addActionListener(new ActionListener(){
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
				
				while(myModel.getRowCount()>0)
				{
					myModel.removeRow(myModel.getRowCount()-1);
					}

				while(myModel1.getRowCount()>0)
				{
					myModel1.removeRow(myModel1.getRowCount()-1);
					}

				while(myModel2.getRowCount()>0)
				{
					myModel2.removeRow(myModel2.getRowCount()-1);
					}
				while(myModel3.getRowCount()>0)
				{
					myModel3.removeRow(myModel3.getRowCount()-1);
					}
				

			    String  strSQL="(Select * from  dbo.student)";
				ResultSet rs=st.executeQuery(strSQL);
				while(rs.next())
				{		
			        Vector<String> v = new Vector<String>();
					v.addElement(rs.getString(1));
					v.addElement(rs.getString(2));
					v.addElement(rs.getString(3));
					v.addElement(rs.getString(4));
					v.addElement(rs.getString(5));
					myModel.addRow(v);					
				}
				String  strSQL1="(Select * from  dbo.Course)";
				ResultSet rs1=st.executeQuery(strSQL1);
				while(rs1.next())
				{					
					Vector<String> v1 = new Vector<String>();
					v1.addElement(rs1.getString(1));
					v1.addElement(rs1.getString(2));
					v1.addElement(rs1.getString(4));
					myModel1.addRow(v1);
				}	
					String  strSQL2="(Select * from  dbo.SC)";
					ResultSet rs2=st.executeQuery(strSQL2);
				while(rs2.next())
				{
					Vector<String> v2 = new Vector<String>();
					v2.addElement(rs2.getString(1));
					v2.addElement(rs2.getString(2));
					v2.addElement(rs2.getString(3));
					v2.addElement(rs2.getString(4));
					myModel2.addRow(v2);
				}
				 String  strSQL3="(Select * from  dbo.PY)";
				 ResultSet rs3=st.executeQuery(strSQL3);
				 while(rs3.next())
					{
						Vector<String> v3 = new Vector<String>();
						v3.addElement(rs3.getString(1));
						v3.addElement(rs3.getString(2));
						v3.addElement(rs3.getString(3));
						v3.addElement(rs3.getString(4));
						myModel3.addRow(v3);
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
            
            }

	public  void closeThis()//�رյ�ǰ����
	{
		this.dispose();
	}
	
	}  