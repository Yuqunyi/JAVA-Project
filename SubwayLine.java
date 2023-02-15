package Subway;

import java.util.ArrayList;
import java.util.Arrays;

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
	lineStation[0] = new String[] {"汉口北","徐州新村","黄浦路","循礼门","太平洋","汉西一路","竹叶海","东吴大道","三店","泾河"};
	lineStation[1] = new String[] {"天河机场","金银潭","汉口火车站","循礼门","江汉路","街道口","光谷广场","华中科技大学","光谷大道","武汉东站","佛祖岭"};
	lineStation[2] = new String[] {"宏图大道","罗家庄","云飞路","王家湾","三角湖","体育中心","东风公司","沌阳大道"};
	lineStation[3] = new String[] {"新城十一路","金银湖","循礼门","轻工大学","香港路","江汉路","汉正街","东风公司"};

	}
	
	
	public boolean LineQuery(String message1) {   //线路查询方法，判断输入的线路是否存在
		boolean isfind = false;
		for(int i=0;i<lineName.length;i++) {
			if(lineName[i].equals(message1)) {
				isfind = true;
				break;
			}						
		}
		return isfind;	
	}
	
	public boolean stationQuery(String message2) {    //站点查询方法，判断输入的站点是否存在
		boolean isfind = false;
		for(int i=0;i<lineStation.length;i++) {
			for(int j=0;j<lineStation[i].length;j++) {
				if(lineStation[i][j].equals(message2)) {
					isfind = true;
					break;
				}
			}				
		}
		return isfind;	
	}
	
	
	public int getLineIndex(String line) {    //根据线路名称得到其对应的索引值
		int number = 0;
		for(int i=0;i<lineName.length;i++) {
			if(lineName[i].equals(line)) {
				number = i;			
			}
		}
		return number;
	}
	
		
	public ArrayList<Integer> getLineIndexArray(String station) {     //根据站点名称得到对应所有线路的索引值，并放入数组中
		
		ArrayList<Integer> numberArray = new ArrayList<>();   //ArrayList只能用于函数中，不能用于类里,创建一个空数组
		for(int i=0;i<lineStation.length;i++) {
			for(int j=0;j<lineStation[i].length;j++) {
				if(lineStation[i][j].equals(station)) {
					numberArray.add(i);     //将站点所对应的所有线路索引存储到数组numberArray中
				}
			}				
		}
		return numberArray;
	}
	

	public String getlineName(int number) {    //返回线路名称
		return lineName[number];
	}
	
	public String getStation(int number) {     //得到线路上的站点
		return Arrays.toString(lineStation[number]);	
	}
	
	public String getHead(int number) {    //返回首站名称
		return lineStation[number][0];
	}
	
	public String getEnd(int number) {     //返回末站名称
		return lineStation[number][lineStation[number].length-1];
	}
	
	public double getLength(int number) {    //返回线路长度
		return Length[number];
	}
	
	public int getFare(int number) {     //返回线路票价
		return fare[number];
		
	}
}

