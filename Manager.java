package Subway;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Manager extends JDialog {
	
	JLabel UserName;  //�û�����ǩ
	JLabel UserPwd;   //�����ǩ
	JLabel Title;      //����
	JTextField txName;    //�û��������ı���
	JPasswordField txPwd;   //�����
	JButton btnLogin;   //��¼��ť
	JButton btnCancel;   //ȡ����¼��ť
	JPanel jp[];   //Ԫ��ΪJPanel��������
	
	Manager(JFrame owner){
		init();
	}
	
	void init(){
		jp = new JPanel[4];
		for(int i=0;i<=3;i++) {
			jp[i] = new JPanel();   //����ѭ���������
		}	
		
		//�ڸ�������������Ӧ�������������������������
		Title = new JLabel("����Ա��½ϵͳ");
		jp[0].add(Title);
		
		UserName = new JLabel("�û���");
		txName = new JTextField(20);
		jp[1].add(UserName);
		jp[1].add(txName);
		
		UserPwd = new JLabel("��    ��");
		txPwd = new JPasswordField(20);
		jp[2].add(UserPwd);
		jp[2].add(txPwd);
		
		btnLogin = new JButton("��¼");
		btnCancel = new JButton("�˳�");
		jp[3].add(btnLogin);
		jp[3].add(btnCancel);
		
		
		for(int j=0;j<=3;j++) {
			this.add(jp[j]);     
		}

		this.setLayout(new GridLayout(4,1));    //���ò���
		this.setSize(300,200);   //���öԻ����С
		this.setLocationRelativeTo(null); 
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		btnLogin.addActionListener(new ActionListener() {    //����¼��ťע�������
			@Override
			public void actionPerformed(ActionEvent e) {
				if(txName.getText().equals("manager") && txPwd.getText().equals("123456")) { 
					//�ж��û����������Ƿ���ȷ
					JOptionPane.showMessageDialog(null, "��¼�ɹ�!");   //���ݵ�¼���������Ϣ��
				}else {
					JOptionPane.showMessageDialog(null, "�û������������!");
				}
			}
		});
		btnCancel.addActionListener(new ActionListener() {   //���˳���ťע�������
			   
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);    //�����ť���˳�ϵͳ
			}
		});
		
		
	}

}
