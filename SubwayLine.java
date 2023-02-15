package Subway;

import java.util.ArrayList;
import java.util.Arrays;

public class SubwayLine {
	
	public String lineName[];   //��·���� 
	public String lineStation[][];    //��·�ϵ�վ��
	int fare[];     //��·Ʊ��
	double Length[];       //��·����
	
	SubwayLine(){    //���췽��
	
	lineName = new String[] {"1����","2����","3����","4����"};
	fare = new int[] {4,6,4,3};
	Length = new double[] {38.54,60.8,30,50};
	lineStation = new String[4][];
	lineStation[0] = new String[] {"���ڱ�","�����´�","����·","ѭ����","̫ƽ��","����һ·","��Ҷ��","������","����","����"};
	lineStation[1] = new String[] {"��ӻ���","����̶","���ڻ�վ","ѭ����","����·","�ֵ���","��ȹ㳡","���пƼ���ѧ","��ȴ��","�人��վ","������"};
	lineStation[2] = new String[] {"��ͼ���","�޼�ׯ","�Ʒ�·","������","���Ǻ�","��������","���繫˾","�������"};
	lineStation[3] = new String[] {"�³�ʮһ·","������","ѭ����","�Ṥ��ѧ","���·","����·","������","���繫˾"};

	}
	
	
	public boolean LineQuery(String message1) {   //��·��ѯ�������ж��������·�Ƿ����
		boolean isfind = false;
		for(int i=0;i<lineName.length;i++) {
			if(lineName[i].equals(message1)) {
				isfind = true;
				break;
			}						
		}
		return isfind;	
	}
	
	public boolean stationQuery(String message2) {    //վ���ѯ�������ж������վ���Ƿ����
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
	
	
	public int getLineIndex(String line) {    //������·���Ƶõ����Ӧ������ֵ
		int number = 0;
		for(int i=0;i<lineName.length;i++) {
			if(lineName[i].equals(line)) {
				number = i;			
			}
		}
		return number;
	}
	
		
	public ArrayList<Integer> getLineIndexArray(String station) {     //����վ�����Ƶõ���Ӧ������·������ֵ��������������
		
		ArrayList<Integer> numberArray = new ArrayList<>();   //ArrayListֻ�����ں����У�������������,����һ��������
		for(int i=0;i<lineStation.length;i++) {
			for(int j=0;j<lineStation[i].length;j++) {
				if(lineStation[i][j].equals(station)) {
					numberArray.add(i);     //��վ������Ӧ��������·�����洢������numberArray��
				}
			}				
		}
		return numberArray;
	}
	

	public String getlineName(int number) {    //������·����
		return lineName[number];
	}
	
	public String getStation(int number) {     //�õ���·�ϵ�վ��
		return Arrays.toString(lineStation[number]);	
	}
	
	public String getHead(int number) {    //������վ����
		return lineStation[number][0];
	}
	
	public String getEnd(int number) {     //����ĩվ����
		return lineStation[number][lineStation[number].length-1];
	}
	
	public double getLength(int number) {    //������·����
		return Length[number];
	}
	
	public int getFare(int number) {     //������·Ʊ��
		return fare[number];
		
	}
}

