package Subway;
import javax.swing.*;

import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import java.awt.*;
import java.awt.event.ActionEvent;     
import java.awt.event.ActionListener;
import java.util.ArrayList;


//MessageQuery������Ϊ������ѯϵͳ�ṩ��ͼ��GUI���棩

public class MessageQuery extends JFrame {
	

	JPanel jp0,jp1,jp2;    //���������������
	JMenuBar menuBar;    //�˵���
	JMenu menu;      //�˵�
	JTextField textInput;     //�����ı���
	JButton btnQuery,btnClear;     //��ѯ��ť����հ�ť
	JLabel reminder;  //��������Ϣ��ʾ��ǩ
	JTextPane textShow;      //��Ϣ��ʾ�ı����
	FlowLayout layout;   //���沼��
	Manager manager;      //����Ա��¼�Ի������
	SubwayLine subway;    //������·�����
	

	MessageQuery(){
	init();      
	setVisible(true);      //���ô��ڿɼ�
	setLocationRelativeTo(null);     //���ô���λ��
	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	void init() {
		
		menuBar = new JMenuBar();   //�����˵���   
		menu = new JMenu("����Ա");     //�����˵�
		manager = new Manager(this);    //��������Ա��¼�Ի���this��ָ���ô�������Ϊ�Ի��������Ĵ���
		jp0 = new JPanel();
		jp1 = new JPanel();
		jp2 = new JPanel();
		textInput = new JTextField(10);    //�û������ı���
		btnQuery = new JButton("��ѯ");     //��ѯ��ť
		btnClear = new JButton("���");   //��������ı���ť
		reminder = new JLabel("��������ʣ��硰1���ߡ�������ȹ㳡����.");    //��ʾ��Ϣ
		reminder.setForeground(Color.red);     //������ʾ��Ϣ������ɫ
		StyledDocument s = new DefaultStyledDocument();   //�ĵ�ģ��
		textShow = new JTextPane(s);   //��������ָ���ĵ�ģ�͵���Ϣ��ʾ�ı����
		textShow.setEditable(false);   //���ø��ı����ڲ��ɱ༭
		textShow.setPreferredSize(new Dimension(550,250));     //�����ı�����С   	
		layout = new FlowLayout();
		layout.setVgap(15);      //���������ֱ��϶
		subway = new SubwayLine();

		
		menuBar.add(Box.createHorizontalGlue());   //ʹ�˵�������ӵĲ˵�����ʾ���ұ�
		menuBar.add(menu);	  //��Ӳ˵�
		jp0.setLayout(new GridLayout(2,1));
		jp0.add(jp1);
		jp0.add(jp2);
		jp1.add(new JLabel("�û���  ������Ϣ��ѯ"));
		jp1.add(textInput);
		jp1.add(btnQuery);    //����������Ӧ���
		jp1.add(btnClear);
		jp2.add(reminder);
	
		//����������������������
		this.setLayout(layout);
		this.setJMenuBar(menuBar);
		this.add(jp0);
		this.add(new JScrollPane(textShow));    //���Ը��������textShow��ӹ���
		
		menu.addMenuListener(new MenuListener() {    //Ϊ������Ա���˵�����Ӽ�����
			public void menuSelected(MenuEvent e) {
				setVisible(false);
				manager.setVisible(true);	//��������Ա��¼�Ի���ͬʱ�ر�ԭ����		
			}
			
			@Override
			public void menuDeselected(MenuEvent e) {
				// TODO Auto-generated method stub	
			}
			
			@Override
			public void menuCanceled(MenuEvent e) {
				// TODO Auto-generated method stub	
			}
	
		});
		
		
		
		btnQuery.addActionListener(new ActionListener(){	//Ϊ��Ϣ��ѯ��ť��Ӽ�����
						
			public void actionPerformed(ActionEvent e) {
				
				String input = textInput.getText();    //��ȡ�û��������Ϣ
				
				SimpleAttributeSet set1,set2,set3;
				set1 = new SimpleAttributeSet();
				set2 = new SimpleAttributeSet(); 
				set3 = new SimpleAttributeSet();    //�½��������Լ�
			
				StyleConstants.setFontSize(set1,22); //Ϊ���Լ�set1���������С
				StyleConstants.setBold(set1,true);  // ����
				StyleConstants.setForeground(set1,Color.BLUE); // Ϊ���Լ�set1����������ɫ
				StyleConstants.setFontSize(set2,15);
				StyleConstants.setForeground(set2,Color.BLACK); 
				StyleConstants.setFontSize(set3,25); 
				StyleConstants.setForeground(set3,Color.RED);   //ͬ������Լ�set2��set3������Ӧ����
				
				if(subway.LineQuery(input)) {    //�ж�������Ƿ�Ϊ��·����
					
					int lineNumber = subway.getLineIndex(input);   //������·���Ƶõ�������·�������ж�Ӧ������ֵ
							
					//�ĵ�ģ�������·��Ϣ
					try {
						textShow.setText(null);//�����壬��ÿ����һ��������ǰ������Ľ���������
						
						s.insertString(s.getLength(),"�人����"+input+"    "+subway.getHead(lineNumber)+"����"+subway.getEnd(lineNumber)+"    "+"ȫ��"+subway.lineStation[lineNumber].length+"վ\n\n",set1);
						//������⣬������·���ơ���վ��ĩվ������վ��������set1���Լ�
						s.insertString(s.getLength(),"����ʱ��"+"����"+"������6:00����Ϣ��6:30-23:00\n",set2);		
						s.insertString(s.getLength(),"����վ��"+"����"+subway.getStation(lineNumber)+"\n",set2);
						s.insertString(s.getLength(),"��·����"+"����"+subway.getLength(lineNumber)+"ǧ��\n",set2);
						s.insertString(s.getLength(),"��·Ʊ��"+"����"+subway.getFare(lineNumber)+"Ԫ\n",set2);
						//���������Ϣ����������ʱ�䡢��·���ȡ�����վ�����·Ʊ�ۣ�����set2���Լ�
						
						} catch (BadLocationException e1) {
							e1.printStackTrace();
						}
				}
				else if(subway.stationQuery(input)){    //�ж�������Ƿ�Ϊ����վ��
					
					ArrayList<Integer> lineNumberArray = subway.getLineIndexArray(input);     //����վ�����Ƶõ����Ӧ��������·����ֵ��������������
					
					try {
						textShow.setText(null);    //������
						
						for(int n:lineNumberArray) {       //����forѭ�������洢����·����ֵ�����飬�����վ������Ӧ��������·����Ϣ
							s.insertString(s.getLength(),"�人����"+subway.getlineName(n)+"    "+subway.getHead(n)+"����"+subway.getEnd(n)+"    "+"ȫ��"+subway.lineStation[n].length+"վ\n\n",set1);
							s.insertString(s.getLength(),"����ʱ��"+"����"+"������6:00����Ϣ��6:30-23:00\n",set2);		
							s.insertString(s.getLength(),"����վ��"+"����"+subway.getStation(n)+"\n",set2);
							s.insertString(s.getLength(),"��·����"+"����"+subway.getLength(n)+"ǧ��\n",set2);
							s.insertString(s.getLength(),"��·Ʊ��"+"����"+subway.getFare(n)+"Ԫ\n",set2);
							
						//���������Ϣ
						}
						} catch (BadLocationException e1) {
							e1.printStackTrace();
						}
					
				}	
				else {
					try {
						textShow.setText(null);   //�����ش������������ʾ������set3���Լ�
						s.insertString(s.getLength(),"û���ҵ������Ϣ�����������Ƿ�����",set3);
						
					}catch (BadLocationException e1) {
						e1.printStackTrace();		
				}
					}
			}

		});
		
		btnClear.addActionListener(new ActionListener(){	//Ϊ��������ı���ť��Ӽ�����
			public void actionPerformed(ActionEvent e) {
				textInput.setText(null);
			}
		});
			
	}

	
	
	

}
