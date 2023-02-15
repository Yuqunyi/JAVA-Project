# Java 设计地铁信息查询系统  
## 1. 任务描述  
结合现实应用场景，设计一个图形界面的简易地铁信息查询系统，系统可根据用户输入查询地铁相关信息，包括地铁线路、线路长度、沿线站点、运行时间等。且系统可实现多维度的查询，比如输入线路、包含站点均可查询到相应信息。

总模块设计如下:

<img src="https://github.com/Yuqunyi/JAVA-Project/blob/main/image/picture1.png" width=300px>

## 2. 概要设计  
该程序设计可分为三个模块，分别是地铁信息查询界面、管理员登录界面以及存储线路信息的类。对系统文件结构列表说明：

<img src="https://github.com/Yuqunyi/JAVA-Project/blob/main/image/picture2.png" width=500px>

## 3. 详细设计   
### 3.1 界面设计  
**（1）地铁信息查询界面**

设计该查询界面窗口类，声明其含有的变量，窗口的基本组件包括菜单，标签，文本框，按钮，文本面板等。

```java
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
```
查询界面如图：

<img src="https://github.com/Yuqunyi/JAVA-Project/blob/main/image/picture3.png" width=400px>

**（2）管理员登录界面**

利用JDialog的子类创建管理员登录对话框，分别给登录按钮和退出按钮添加监视器，若点击退出按钮，调用System.exit(0)实现退出系统;若点击登录按钮，首先通过getText()方法获取输入内容，然后判断用户名和密码是否正确，若正确，弹出消息框并显示“登录成功！”，否则显示“用户名或密码错误!”。登录界面如图：

<img src="https://github.com/Yuqunyi/JAVA-Project/blob/main/image/picture4.png" width=250px>

在这个类中，对话框所含组件如下，在init()方法中创建组件后调用add()可将所有组件添加至容器中。
```java
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
  ```
### 3.2 线路对象设计  
  设计类SubwayLine，其成员变量包括：线路名称、站点名称、线路票价及线路长度，均为数组类型，在构造方法中给所有成员变量赋值。  
  ```java
  public class SubwayLine {
	
	public String lineName[];   //线路名称 
	public String lineStation[][];    //线路上的站点
	int fare[];     //线路票价
	double Length[];       //线路长度
	
	SubwayLine(){    //构造方法
	
	lineName = new String[] {"1号线","2号线","3号线","4号线"};
	fare = new int[] {4,6,4,3};
	Length = new double[] {38.54,60.8,30,50};
	lineStation = new String[4][];
	lineStation[0] = new String[] {……};
	lineStation[1] = new String[] {……};
	lineStation[2] = new String[] {……};
	lineStation[3] = new String[] {……};
	}
```
**（注：此处站点名称省略，具体的站点名称在源代码文件中体现。）**

在该类中，定义判断线路名称、站点名称是否存在和返回线路具体信息的方法：  
```java
public boolean LineQuery(String message1) {   //判断输入线路是否存在,判断站点是否存在方法类似
		boolean isfind = false;
		for(int i=0;i<lineName.length;i++) {
			if(lineName[i].equals(message1)) {
				isfind = true;
				break;
			}						
		}
		return isfind;	
	}
```
定义得到线路具体信息的方法，首先要根据线路名称或站点名称得到其线路对应的索引值，再由该索引值对应得到线路票价、长度、首站等信息。  
```java
public int getLineIndex(String line) {    //根据线路名称得到其对应的索引值
		int number = 0;
		for(int i=0;i<lineName.length;i++) {
			if(lineName[i].equals(line)) {
				number = i;			
			}
		}
		return number;
	}
```
```java
public String getStation(int number) {     //得到线路上的站点
  return Arrays.toString(lineStation[number]);	
}

public double getLength(int number) {    //返回线路长度
  return Length[number];
}

public int getFare(int number) {     //返回线路票价
  return fare[number];		
}		
```
### 3.3 具体功能设计  
**(1)管理员登录功能**

首先新建登录对话框对象manager，运行管理员菜单后，调用setVisible(false)方法将原窗口设为不可见，通过语句manager.setVisible(true)弹出该对话框。在该对话框中，设置用户名和密码输入文本框，并为登录和退出按钮添加**监视器**。
```java
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
```
```java
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
```
**(2)根据线路名称查询信息**

利用getText()方法获取输入文本框内的文本，若存在该线路，则通过定义的方法getLineIndex(String line)得到线路对应的索引值，再根据SubwayLine类中返回线路信息的方法获取线路信息，在文本面板的文档模型内调用insertString()方法输出该线路的名称、票价、线路长度、首末班时间和所有途径站点，并分别设置相应属性。
```java
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
```
**(3)根据站点名称查询信息**

与根据线路名称查询不同的是，一个站点可能位于多条线路上，即其对应的线路索引值可能有多个，判断站点存在后，调用getLineIndexArray(String station)方法得到该站点对应线路索引值的数组，利用for-each循环输出该站点所在的所有线路信息 。

## 4. 项目测试  
(1)运行“**管理员**”菜单，弹出登录对话框，输入用户名和密码进行登录，根据登录结果弹出消息对话框：

<img src="https://github.com/Yuqunyi/JAVA-Project/blob/main/image/picture5.png" width=250px>

<img src="https://github.com/Yuqunyi/JAVA-Project/blob/main/image/picture6.png" width=250px>    <img src="https://github.com/Yuqunyi/JAVA-Project/blob/main/image/picture7.png" width=250px>

(2)在主界面输入【**线路名称**】进行查询，如输入“3号线”，查询结果如下：

<img src="https://github.com/Yuqunyi/JAVA-Project/blob/main/image/picture8.png" width=400px>

(3)点击“清空”按钮清空检索词，输入【**站点名称**】进行查询，如输入“循礼门”，由于此站点所在线路为1、2、4号线，因此将显示三条线路的信息，通过文本面板上的滚动条可查看所有信息：

<img src="https://github.com/Yuqunyi/JAVA-Project/blob/main/image/picture9.png" width=400px>     <img src="https://github.com/Yuqunyi/JAVA-Project/blob/main/image/picture10.png" width=400px>

(4)若未查询到相关信息，则在结果显示文本区输出提示信息：

<img src="https://github.com/Yuqunyi/JAVA-Project/blob/main/image/picture11.png" width=400px>

