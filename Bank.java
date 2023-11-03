import java.io.*;
import java.sql.*;	
import javax.swing.*;
import java.awt.Font;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
class Bank implements ActionListener
{
	JTable table;
	JScrollPane scroll;
	JPasswordField tt2,tc3,t3;
	JFrame f,f1,f2,f3,f4,f5,f6,fsa,fcds;
	JTextField t1,t2,tc1,tc2,tc4,tcd1,tt1,twd,tdp;
	JButton smt,ca,sa,cd,cac,cds ,csmt,cab,wd,dp,da,de;
	JLabel l,l1,l2,l3,ln,lc,lc1,lc2,lc3,lc4,lcd1,lcd,ll,ll1,ll2,lad,lb,lbn,lwd,ldp,lda,le;

	Bank()
	{
		f=new JFrame("Login Form");
		f.setSize(500,400);
		f.setVisible(true);
		f.setLayout(null);
		f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
		ll=new JLabel("Login Account");
        ll.setFont(new Font("Arial",Font.BOLD,20));

		ll1=new JLabel("Name");
		tt1=new JTextField();
		ll2=new JLabel("Password");
		tt2=new JPasswordField();
		csmt=new JButton("Submit");//login

		cab=new JButton("Create Account");
		cab.setBorder(BorderFactory.createEmptyBorder());

		f.add(csmt);
		f.add(ll1);f.add(ll2);f.add(ll);
		f.add(cab);f.add(tt1);f.add(tt2);

		ll.setBounds(20,10,300,100);
		ll1.setBounds(10,100,50,25);
		ll2.setBounds(10,140,100,25);

		tt1.setBounds(100,100,100,25);
		tt2.setBounds(100,140,100,25);

		csmt.setBounds(20,210,100,25);
		cab.setBounds(100,260,122,25);

		csmt.addActionListener(this);
		cab.addActionListener(this);

		smt=new JButton("Submit");
		smt.addActionListener(this);

		ca=new JButton("Create Account");
		sa=new JButton("Show Account");
		cd=new JButton("Customer Details");

		ca.addActionListener(this);
		sa.addActionListener(this);
		cd.addActionListener(this);	

		cac=new JButton("Submit");
		cac.addActionListener(this);

		cds=new JButton("Submit");
		cds.addActionListener(this);

		wd=new JButton("Withdrawal");
		dp=new JButton("Deposit");
		da=new JButton("Delete Account");
		de=new JButton("Details");

		wd.addActionListener(this);
		dp.addActionListener(this);
		da.addActionListener(this);
		de.addActionListener(this);

	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==cab)
		{
		f1=new JFrame("Registation Form");
		f1.setSize(500,400);
		f1.setVisible(true);
		f1.setLayout(null);
		f.setDefaultCloseOperation(f1.EXIT_ON_CLOSE);
		l=new JLabel("Create Ragistation");
        l.setFont(new Font("Arial",Font.BOLD,20));
		l1=new JLabel("Admin");
		t1=new JTextField();
		l2=new JLabel("Branch");
		t2=new JTextField();
		l3=new JLabel("Password");
		t3=new JPasswordField();

		f1.add(smt);
		f1.add(l1);f1.add(l2);f1.add(l3);f1.add(l);
		f1.add(t1);f1.add(t2);f1.add(t3);

		l.setBounds(20,10,300,100);
		l1.setBounds(10,100,50,25);
		l2.setBounds(10,130,50,25);
		l3.setBounds(10,170,100,25);

		t1.setBounds(100,100,100,25);
		t2.setBounds(100,130,100,25);
		t3.setBounds(100,170,100,25);

		smt.setBounds(20,210,100,25);


		return;
		}
		//create account
		if(e.getSource()==smt)
		{
			if(t1.getText().equals("") || t2.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null,"Fill the form");
				return;
			}else{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/","root","");
			Statement st=con.createStatement();
			if(con.isClosed())
			{
				System.out.println("connection created");
			}
			else
			{
			st.executeUpdate("create database if not exists bank");
			}
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","");
			Statement sta=conn.createStatement();
			sta.executeUpdate("create table if not exists user(id int not null auto_increment primary key,admin varchar(25),branch varchar(40) not null,password varchar(25) not null)");
			ResultSet rsta= sta.executeQuery("select * from user where admin='"+t1.getText()+"' && '"+t3.getText()+"' ");
			int i=0;
			while(rsta.next())
			{
				System.out.println(rsta.getString(1)+" "+ rsta.getString(2));
				i++;
			}
			if(i>=1){
				JOptionPane.showMessageDialog(null,"Account is all ready exit","Error",JOptionPane.ERROR_MESSAGE);
				t1.setText("");
				t2.setText("");
				t3.setText("");
				return;
			}

			String q="insert into user(admin,branch,password) values('"+t1.getText()+"','"+t2.getText()+"','"+t3.getText()+"')";
			sta.executeUpdate(q);
			JOptionPane.showMessageDialog(null,t1.getText()+" Ragistation success fully");

			t1.setText("");
			t2.setText("");
			t3.setText("");
			
			con.close();
		}
		catch(Exception ep)
		{
			System.out.println(ep.getMessage());
		}
	}
		}
		//end create account
		
		//login
		if(e.getSource()==csmt)
		{
			if(tt1.getText().equals("") || tt2.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null,"Fill the form");
				return;
			}else{
		
		try
		{ 	
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","");
			Statement sta=con.createStatement();
			String q="select admin,password from user where admin='"+tt1.getText()+"' && password= '"+tt2.getText()+"' ";
			ResultSet rs=sta.executeQuery(q);
			int i=0;
			while(rs.next())
			{
				System.out.println(rs.getString(1)+" Login");
				JOptionPane.showMessageDialog(null,rs.getString(1)+ " Login Success Fully");
				i++;
			}
			if(i < 1){
				JOptionPane.showMessageDialog(null,"No Record Found","Error",JOptionPane.ERROR_MESSAGE);
				tt1.setText("");
				tt2.setText("");
				return;
			}

			tt1.setText("");
			tt2.setText("");

			f2=new JFrame("Bank");
			f2.setSize(500,400);
			f2.setVisible(true);
			f2.setLayout(null);
			f2.setDefaultCloseOperation(f1.EXIT_ON_CLOSE);
			ln=new JLabel("Select Option");
        	ln.setFont(new Font("Arial",Font.BOLD,20));
			f2.add(ln);
			f2.add(ca);f2.add(sa);f2.add(cd);

			ln.setBounds(20,10,200,40);
			ca.setBounds(20,80,200,30);
			sa.setBounds(20,126,200,30);
			cd.setBounds(20,170,200,30);

			con.close();
		}
		catch(Exception ep)
		{
			JOptionPane.showMessageDialog(null,"No Account Found","Error",JOptionPane.ERROR_MESSAGE);
			// System.out.println(ep.getMessage());
		}
	}
	}
	//end login	

	if(e.getSource()==ca)
			{
				f3=new JFrame("Create Account");
				f3.setSize(500,400);
				f3.setVisible(true);
				f3.setLayout(null);
				f2.setDefaultCloseOperation(f3.EXIT_ON_CLOSE);
				lc=new JLabel("Create Account");
        		lc.setFont(new Font("Arial",Font.BOLD,20));
				lc1=new JLabel("Name");
				tc1=new JTextField();
				lc2=new JLabel("Age");
				tc2=new JTextField();
				lc3=new JLabel("Password");
				tc3=new JPasswordField();
				lc4=new JLabel("Balance");
				tc4=new JTextField();

				f3.add(cac);
				f3.add(lc1);f3.add(lc2);f3.add(lc3);f3.add(lc4);f3.add(lc);
				f3.add(tc1);f3.add(tc2);f3.add(tc3);f3.add(tc4);

				lc.setBounds(20,10,300,100);
				lc1.setBounds(10,110,50,25);
				lc2.setBounds(10,140,50,25);
				lc3.setBounds(10,170,100,25);
				lc4.setBounds(10,200,100,25);

				tc1.setBounds(100,110,100,25);
				tc2.setBounds(100,140,100,25);
				tc3.setBounds(100,170,100,25);
				tc4.setBounds(100,200,100,25);

				cac.setBounds(20,230,100,25);
			}

if(e.getSource()==cac)
	{
		if(tc1.getText().equals("") || tc2.getText().equals("") || tc3.getText().equals("") || tc4.getText().equals(""))
		{
	 		JOptionPane.showMessageDialog(null,"Fill the form");
	 		return;
			}
			int bai=Integer.parseInt(tc4.getText());
			if(bai < 1000)
			{
				JOptionPane.showMessageDialog(null,"Minimum Deposit Rs. 1000","Error",JOptionPane.ERROR_MESSAGE);
				return;
			}
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection cone=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","");
			Statement stat=cone.createStatement();
			stat.executeUpdate("create table if not exists account(account_no int not null auto_increment primary key,name varchar(25),age int not null,password varchar(25) not null,balance int not null)");
			stat.executeUpdate("alter table account auto_increment=1001");

			String qu="insert into account(name,age,password,balance) values('"+tc1.getText()+"','"+tc2.getText()+"','"+tc3.getText()+"','"+tc4.getText()+"')";
			stat.executeUpdate(qu);
			// System.out.println(tc1.getText()+" Account Create");

			JOptionPane.showMessageDialog(null,tc1.getText()+" Account Created Success Fully");
			tc1.setText("");
			tc2.setText("");
			tc3.setText("");
			tc4.setText("");

			cone.close();
		}
		catch(Exception ep)
		{
			// System.out.println(ep.getMessage());
               JOptionPane.showMessageDialog(null, "Fill Correct Form", "Error", JOptionPane.ERROR_MESSAGE);
		}
		}

		if(e.getSource()==sa)
		{
 			fsa=new JFrame("Accounts");
            f2.setDefaultCloseOperation(fsa.EXIT_ON_CLOSE);
            // fsa.setLayout(new BorderLayout());
            String[] coln = {"Account ","name", "age", "Password", "balance"};
            DefaultTableModel model = new DefaultTableModel();
            model.setColumnIdentifiers(coln);
            table=new JTable();
            table.setModel(model);
            table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            table.setFillsViewportHeight(true);
            JScrollPane scroll=new JScrollPane(table);
            scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

            String account_no="";
            String name="";
            String age="";
            String password="";
            String balance="";

            fsa.add(scroll);
            fsa.setVisible(true);
            fsa.setSize(400, 300);

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "");
        PreparedStatement ps = con.prepareStatement("select * from account");
        ResultSet rs = ps.executeQuery();
            int i = 0;
            while (rs.next()) {
                account_no = rs.getString("account_no");
                name = rs.getString("name");
                age = rs.getString("age");
                password = rs.getString("password");
                balance = rs.getString("balance");
                model.addRow(new Object[]{account_no,name,age, password, balance});
                i++;
            }
        if (i < 1) {
                JOptionPane.showMessageDialog(null, "No Record Found", "Error", JOptionPane.ERROR_MESSAGE);
                fsa.dispose();
            }
            con.close();
        } catch (Exception esa) {
            // System.out.println(esa);
			JOptionPane.showMessageDialog(null,"No Account","Error",JOptionPane.ERROR_MESSAGE);
        }
		}

		if(e.getSource()==cd)
		{
			f5=new JFrame("Account");
			f5.setSize(500,400);
			f5.setVisible(true);
			f5.setLayout(null);
			f5.setDefaultCloseOperation(f4.EXIT_ON_CLOSE);
			lcd=new JLabel("Customer Details");
			lcd1=new JLabel("Account No");
			tcd1=new JTextField();
			lcd.setFont(new Font("Arial", Font.BOLD,20));
			f5.add(cds);
			f5.add(lcd1);f5.add(tcd1);f5.add(lcd);

			lcd.setBounds(20,10,300,40);
			lcd1.setBounds(25,90,80,25);
			tcd1.setBounds(105,90,100,25);
			cds.setBounds(40,140,100,25);
		}
		if(e.getSource()==cds)
		{
			if(tcd1.getText().equals(""))
			{	
	 		JOptionPane.showMessageDialog(null,"Fill the form");
	 		return;
			}
			try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
       	 	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "");
        	PreparedStatement ps = con.prepareStatement("select * from account where account_no='"+tcd1.getText()+"' ");
        	ResultSet rs = ps.executeQuery();
        	if(rs.next() == false)
        	{
        		JOptionPane.showMessageDialog(null,"No Account Found","Error",JOptionPane.ERROR_MESSAGE);
        		tcd1.setText("");
        		return;
        	}else
        	{
        	JOptionPane.showMessageDialog(null,"Account Login Success Fully");
        	}

			f6=new JFrame("User Account Details");
            f6.setVisible(true);
            f6.setLayout(null);
            f6.setSize(500,400);
            f6.setDefaultCloseOperation(f6.EXIT_ON_CLOSE);
            lad=new JLabel("Account Details");
            lad.setFont(new Font("Arial",Font.BOLD,20));
            f6.add(lad);
            lad.setBounds(20,10,300,40);
           	lb=new JLabel("Balance Rs. "+ rs.getString(5));
           	lb.setFont(new Font("Arial",Font.BOLD,12));
           	lbn=new JLabel("Customer Name: "+rs.getString(2));

           	le=new JLabel("Customer Details");
            lwd=new JLabel("Withdrawal Amount");
            ldp=new JLabel("Deposit Amount");
            lda=new JLabel("Delete account");
            twd=new JTextField();
            tdp=new JTextField();

            f6.add(twd);f6.add(tdp);
           	f6.add(lb);f6.add(lbn);f6.add(le);
            f6.add(lwd);f6.add(ldp);f6.add(lda);
           	f6.add(wd);f6.add(da);f6.add(dp);f6.add(de);

           	lb.setBounds(280,50,250,25);
           	lbn.setBounds(10,50,250,25);
           	de.setBounds(20,110,100,25);
           	twd.setBounds(10,167,100,25);
           	wd.setBounds(125,167,100,25);
           	tdp.setBounds(10,225,100,25);
           	dp.setBounds(125,225,100,25);
           	da.setBounds(20,284,130,25);

           	le.setBounds(10,85,150,25);
            lwd.setBounds(10,145,150,25);
            ldp.setBounds(10,200,150,25);
            lda.setBounds(10,260,150,25);

    		}catch (Exception ecds) {
			JOptionPane.showMessageDialog(null,"No Account Found","Error",JOptionPane.ERROR_MESSAGE);
			return;
       		}
		}

		if(e.getSource()==wd)
		{
			if(twd.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null,"Fill Withdrawal Amount","Error",JOptionPane.ERROR_MESSAGE);
				return;
			}
		try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "");
        PreparedStatement ps = con.prepareStatement("select * from account where account_no='"+tcd1.getText()+"' ");
        ResultSet rsw = ps.executeQuery();
        int iaw=0;
            if(rsw.next())
            {
				 iaw=rsw.getInt(5);
            }
			int ia=Integer.parseInt(twd.getText());
			if(ia <= iaw)
			{
			int py=iaw-ia;
			String qre="update account set balance= ? where account_no='"+tcd1.getText()+"'  ";
			PreparedStatement stt=con.prepareStatement(qre);
			stt.setInt(1,py);
			stt.executeUpdate();
			JOptionPane.showMessageDialog(null,"Withdrawal Success Fully");
			}else{
				JOptionPane.showMessageDialog(null,"Low Balance","Error",JOptionPane.ERROR_MESSAGE);
				return;
			}

			twd.setText("");
			f6.dispose();

        } catch (Exception ecds) {
            // System.out.println(ecds);
			JOptionPane.showMessageDialog(null,"No Account Found","Error",JOptionPane.ERROR_MESSAGE);
        }
		}

		if(e.getSource()==dp)
		{
			if(tdp.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null,"Fill Deposit Amount","Error",JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "");
        PreparedStatement ps = con.prepareStatement("select * from account where account_no='"+tcd1.getText()+"' ");
        ResultSet rswa = ps.executeQuery();
        int iawe=0;
            if(rswa.next())
            {
				 iawe=rswa.getInt(5);
            }
			int iae=Integer.parseInt(tdp.getText());
			
			int pya=iawe+iae;
			String qreu="update account set balance= ? where account_no='"+tcd1.getText()+"'  ";
			PreparedStatement stta=con.prepareStatement(qreu);
			stta.setInt(1,pya);
			stta.executeUpdate();
			JOptionPane.showMessageDialog(null,"Deposit Success Fully");
			tdp.setText("");
			f6.dispose();

        } catch (Exception ecds) {
            // System.out.println(ecds);
			JOptionPane.showMessageDialog(null,"No Account Found","Error",JOptionPane.ERROR_MESSAGE);
        }
		}
		//customer details show
		if(e.getSource()==de)
		{
			fcds=new JFrame("Accounts");
            f6.setDefaultCloseOperation(fcds.EXIT_ON_CLOSE);
            String[] coln = {"Account ","Name", "Age", "Password", "Balance"};
            DefaultTableModel model = new DefaultTableModel();
            model.setColumnIdentifiers(coln);
            table=new JTable();
            table.setModel(model);
            table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            table.setFillsViewportHeight(true);
            JScrollPane scroll=new JScrollPane(table);
            scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

            String account_no="";
            String name="";
            String age="";
            String password="";
            String balance="";

            fcds.setVisible(true);
            fcds.setSize(400, 400);
            fcds.add(scroll);
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "");
        PreparedStatement ps = con.prepareStatement("select * from account where account_no='"+tcd1.getText()+"' ");
        ResultSet rscd = ps.executeQuery();
            if(rscd.next()) {
                account_no = rscd.getString("account_no");
                name = rscd.getString("name");
                age = rscd.getString("age");
                password = rscd.getString("password");
                balance = rscd.getString("balance");
                model.addRow(new Object[]{account_no,name,age, password, balance});
            }

            con.close();
        } catch (Exception ecds) {
            // System.out.println(ecds);
			JOptionPane.showMessageDialog(null,"No Account Found","Error",JOptionPane.ERROR_MESSAGE);
        }
		}

		if(e.getSource()==da)
		{
		try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "");
        Statement psde = con.createStatement();
        String qda="delete from account where account_no='"+tcd1.getText()+"' ";
        psde.executeUpdate(qda);
        JOptionPane.showMessageDialog(null,"Account Deleted Success Fully");
        f6.dispose();
		
          con.close();
        } catch (Exception ecds) {
            // System.out.println(ecds);
			JOptionPane.showMessageDialog(null,"No Account Found","Error",JOptionPane.ERROR_MESSAGE);
        }
		}
	}
	public static void main(String args[])
	{
		Bank sq=new Bank();
	}
}