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


//MessageQuery对象负责为地铁查询系统提供视图（GUI界面）

public class MessageQuery extends JFrame {
	

	JPanel jp0,jp1,jp2;    //用于添加组件的面板
	JMenuBar menuBar;    //菜单栏
	JMenu menu;      //菜单
	JTextField textInput;     //输入文本框
	JButton btnQuery,btnClear;     //查询按钮、清空按钮
	JLabel reminder;  //检索词信息提示标签
	JTextPane textShow;      //信息显示文本面板
	FlowLayout layout;   //界面布局
	Manager manager;      //管理员登录对话框对象
	SubwayLine subway;    //地铁线路类对象
	

	MessageQuery(){
	init();      
	setVisible(true);      //设置窗口可见
	setLocationRelativeTo(null);     //设置窗口位置
	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	void init() {
		
		menuBar = new JMenuBar();   //创建菜单条   
		menu = new JMenu("管理员");     //创建菜单
		manager = new Manager(this);    //创建管理员登录对话框，this是指将该窗口设置为对话框依赖的窗口
		jp0 = new JPanel();
		jp1 = new JPanel();
		jp2 = new JPanel();
		textInput = new JTextField(10);    //用户输入文本框
		btnQuery = new JButton("查询");     //查询按钮
		btnClear = new JButton("清空");   //清空输入文本按钮
		reminder = new JLabel("输入检索词，如“1号线”，“光谷广场”等.");    //提示信息
		reminder.setForeground(Color.red);     //设置提示信息字体颜色
		StyledDocument s = new DefaultStyledDocument();   //文档模型
		textShow = new JTextPane(s);   //创建具有指定文档模型的信息显示文本面板
		textShow.setEditable(false);   //设置该文本窗口不可编辑
		textShow.setPreferredSize(new Dimension(550,250));     //设置文本面板大小   	
		layout = new FlowLayout();
		layout.setVgap(15);      //设置组件垂直间隙
		subway = new SubwayLine();

		
		menuBar.add(Box.createHorizontalGlue());   //使菜单条上添加的菜单项显示在右边
		menuBar.add(menu);	  //添加菜单
		jp0.setLayout(new GridLayout(2,1));
		jp0.add(jp1);
		jp0.add(jp2);
		jp1.add(new JLabel("用户：  地铁信息查询"));
		jp1.add(textInput);
		jp1.add(btnQuery);    //面板上添加相应组件
		jp1.add(btnClear);
		jp2.add(reminder);
	
		//添加组件，并设置容器布局
		this.setLayout(layout);
		this.setJMenuBar(menuBar);
		this.add(jp0);
		this.add(new JScrollPane(textShow));    //可以给文字面板textShow添加滚条
		
		menu.addMenuListener(new MenuListener() {    //为“管理员”菜单项添加监视器
			public void menuSelected(MenuEvent e) {
				setVisible(false);
				manager.setVisible(true);	//弹出管理员登录对话框，同时关闭原窗口		
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
		
		
		
		btnQuery.addActionListener(new ActionListener(){	//为信息查询按钮添加监视器
						
			public void actionPerformed(ActionEvent e) {
				
				String input = textInput.getText();    //获取用户输入的信息
				
				SimpleAttributeSet set1,set2,set3;
				set1 = new SimpleAttributeSet();
				set2 = new SimpleAttributeSet(); 
				set3 = new SimpleAttributeSet();    //新建三种属性集
			
				StyleConstants.setFontSize(set1,22); //为属性集set1设置字体大小
				StyleConstants.setBold(set1,true);  // 粗体
				StyleConstants.setForeground(set1,Color.BLUE); // 为属性集set1设置字体颜色
				StyleConstants.setFontSize(set2,15);
				StyleConstants.setForeground(set2,Color.BLACK); 
				StyleConstants.setFontSize(set3,25); 
				StyleConstants.setForeground(set3,Color.RED);   //同理对属性集set2、set3进行相应设置
				
				if(subway.LineQuery(input)) {    //判断输入词是否为线路名称
					
					int lineNumber = subway.getLineIndex(input);   //根据线路名称得到其在线路名数组中对应的索引值
							
					//文档模型输出线路信息
					try {
						textShow.setText(null);//清空面板，即每进行一次搜索，前面输出的结果都会清空
						
						s.insertString(s.getLength(),"武汉地铁"+input+"    "+subway.getHead(lineNumber)+"——"+subway.getEnd(lineNumber)+"    "+"全程"+subway.lineStation[lineNumber].length+"站\n\n",set1);
						//输出标题，包括线路名称、首站、末站、及总站数，采用set1属性集
						s.insertString(s.getLength(),"运行时间"+"——"+"工作日6:00，休息日6:30-23:00\n",set2);		
						s.insertString(s.getLength(),"沿线站点"+"——"+subway.getStation(lineNumber)+"\n",set2);
						s.insertString(s.getLength(),"线路长度"+"——"+subway.getLength(lineNumber)+"千米\n",set2);
						s.insertString(s.getLength(),"线路票价"+"——"+subway.getFare(lineNumber)+"元\n",set2);
						//输出地铁信息，包括运行时间、线路长度、沿线站点和线路票价，采用set2属性集
						
						} catch (BadLocationException e1) {
							e1.printStackTrace();
						}
				}
				else if(subway.stationQuery(input)){    //判断输入词是否为包含站点
					
					ArrayList<Integer> lineNumberArray = subway.getLineIndexArray(input);     //根据站点名称得到其对应的所有线路索引值，并存入数组中
					
					try {
						textShow.setText(null);    //清空面板
						
						for(int n:lineNumberArray) {       //利用for循环遍历存储了线路索引值的数组，输出该站点所对应的所有线路的信息
							s.insertString(s.getLength(),"武汉地铁"+subway.getlineName(n)+"    "+subway.getHead(n)+"——"+subway.getEnd(n)+"    "+"全程"+subway.lineStation[n].length+"站\n\n",set1);
							s.insertString(s.getLength(),"运行时间"+"——"+"工作日6:00，休息日6:30-23:00\n",set2);		
							s.insertString(s.getLength(),"沿线站点"+"——"+subway.getStation(n)+"\n",set2);
							s.insertString(s.getLength(),"线路长度"+"——"+subway.getLength(n)+"千米\n",set2);
							s.insertString(s.getLength(),"线路票价"+"——"+subway.getFare(n)+"元\n",set2);
							
						//输出地铁信息
						}
						} catch (BadLocationException e1) {
							e1.printStackTrace();
						}
					
				}	
				else {
					try {
						textShow.setText(null);   //若搜素词有误则输出提示，采用set3属性集
						s.insertString(s.getLength(),"没有找到相关信息，检查输入词是否有误！",set3);
						
					}catch (BadLocationException e1) {
						e1.printStackTrace();		
				}
					}
			}

		});
		
		btnClear.addActionListener(new ActionListener(){	//为清空输入文本按钮添加监听器
			public void actionPerformed(ActionEvent e) {
				textInput.setText(null);
			}
		});
			
	}

	
	
	

}
