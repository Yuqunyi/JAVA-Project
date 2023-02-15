package Subway;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Manager extends JDialog {
	
	JLabel UserName;  //用户名标签
	JLabel UserPwd;   //密码标签
	JLabel Title;      //标题
	JTextField txName;    //用户名输入文本框
	JPasswordField txPwd;   //密码框
	JButton btnLogin;   //登录按钮
	JButton btnCancel;   //取消登录按钮
	JPanel jp[];   //元素为JPanel面板的数组
	
	Manager(JFrame owner){
		init();
	}
	
	void init(){
		jp = new JPanel[4];
		for(int i=0;i<=3;i++) {
			jp[i] = new JPanel();   //利用循环创建面板
		}	
		
		//在各个面板中添加相应的组件，并将面板添加至容器里
		Title = new JLabel("管理员登陆系统");
		jp[0].add(Title);
		
		UserName = new JLabel("用户名");
		txName = new JTextField(20);
		jp[1].add(UserName);
		jp[1].add(txName);
		
		UserPwd = new JLabel("密    码");
		txPwd = new JPasswordField(20);
		jp[2].add(UserPwd);
		jp[2].add(txPwd);
		
		btnLogin = new JButton("登录");
		btnCancel = new JButton("退出");
		jp[3].add(btnLogin);
		jp[3].add(btnCancel);
		
		
		for(int j=0;j<=3;j++) {
			this.add(jp[j]);     
		}

		this.setLayout(new GridLayout(4,1));    //设置布局
		this.setSize(300,200);   //设置对话框大小
		this.setLocationRelativeTo(null); 
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		btnLogin.addActionListener(new ActionListener() {    //给登录按钮注册监听器
			@Override
			public void actionPerformed(ActionEvent e) {
				if(txName.getText().equals("manager") && txPwd.getText().equals("123456")) { 
					//判断用户名和密码是否正确
					JOptionPane.showMessageDialog(null, "登录成功!");   //根据登录结果弹出消息框
				}else {
					JOptionPane.showMessageDialog(null, "用户名或密码错误!");
				}
			}
		});
		btnCancel.addActionListener(new ActionListener() {   //给退出按钮注册监听器
			   
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);    //点击按钮则退出系统
			}
		});
		
		
	}

}
