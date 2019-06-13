package org.xyqiang.netflow_classifier;
/**
 * 模拟实时读取流量，对异常流量进行识别
 * 对每一条流量的记录进行预测
 * 返回每一次的预测值
 * */
import java.io.BufferedReader;
import java.io.FileReader;

import weka.classifiers.trees.J48;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class SystemDemo {
	
	private double add;//所需要的double型参数的容器
	
	private String li;
	private String lineData;
	private Instance inst;
	private MyEvaluation TrainTree;
	private J48 temp;
	private Instances some;

	
	private  Instances train1;
	private  Instances test1;	
	private  J48 tree1;
	
	public String getLineData() {
		return lineData;
	}
	public void setLineData(String lineData) {
		this.lineData = lineData;
	}
	
	public  String getLi() {
		return li;
	}
	public  void setLi(String li) {
		this.li = li;
	}
	public  Instance getInst() {
		return inst;
	}
	public  void setInst(Instance inst) {
		this.inst = inst;
	}
	public J48 getTemp() {
		return temp;
	}
	public void setTemp(J48 temp) {
		this.temp = temp;
	}

	public Instances getSome() {
		return some;
	}

	public void setSome(Instances some) {
		this.some = some;
	}	
	
	
	public J48 getTreeInfo() throws Exception {
		TrainTree=new MyEvaluation();	
		temp=TrainTree.getTree();
		return temp;
	}
	
	public  Instances getDataInfo() throws Exception {
		TrainTree=new MyEvaluation();	
		some=TrainTree.getTrain();
		return some;
	}
	public J48 model() throws Exception {						
		// 训练J48分类器
		String[] options = new String[1];
		options[0] = "-U"; // 不修剪
		tree1 = new J48(); // J48分类器对象
		tree1.setOptions(options);// 设置选项
		tree1.buildClassifier(train1);// 构建分类器	
		return tree1;
	}
	public Instances TrainLoader() throws Exception {
		train1 = DataSource.read("F:/KDDCUP1999/Train.arff");
		train1.setClassIndex(train1.numAttributes() - 1);
		return train1;
	}
	public Instances TestLoader() throws Exception {
		test1  = DataSource.read("F:/KDDCUP1999/Test.arff");
		test1.setClassIndex(test1.numAttributes() - 1);
		return test1;
	}
	
	public  String predict() throws Exception {	
		TrainTree=new MyEvaluation();	
		Instances demo = DataSource.read("F:/KDDCUP1999/Train.arff");
		demo.setClassIndex(demo.numAttributes() - 1);
		FileReader reader = new FileReader("./test.txt");
		BufferedReader br = new BufferedReader(reader);
		// 依附于train的空instances对象race，为了下面的instance的创建
		Instances race = new Instances(demo, 0);
		lineData= br.readLine();
		while (lineData != null) {
			String[] tmp = lineData.split(",");
			
			Instance inst = new DenseInstance(tmp.length);		
			
			for(int i=0; i<tmp.length; i++){
				//System.out.println(i);
				try {
					add = Double.parseDouble(tmp[i]);
					inst.setValue(race.attribute(i), add);
				} catch (Exception e) {
					inst.setValue(race.attribute(i), tmp[i]);
				}
			}
			inst.setDataset(race);
			try {
				double res = tree1.classifyInstance(inst);
				
				switch((int)res){
				
				case 0:setLi("back.");break;
				case 1:setLi("teardrop.");break;
				case 2:setLi("loadmodule.");break;
				case 3:setLi("neptune.");break;
				case 4:setLi("rootkit.");break;
				case 5:setLi("phf.");break;
				case 6:setLi("satan.");break;
				case 7:setLi("buffer_overflow.");break;
				case 8:setLi("ftp_write.");break;
				case 9:setLi("land.");break;
				case 10:setLi("spy.");break;
				case 11:setLi("ipsweep.");break;
				case 12:setLi("multihop.");break;
				case 13:setLi("smurf.");break;
				case 14:setLi("pod.");break;
				case 15:setLi("perl.");break;
				case 16:setLi("warezclient.");break;
				case 17:setLi("nmap.");break;
				case 18:setLi("imap.");break;
				case 19:setLi("warezmaster.");break;
				case 20:setLi("portsweep.");break;
				case 21:setLi("normal.");break;
				case 22:setLi("guess_passwd.");break;
				case 23:setLi("mscan.");break;
				case 24:setLi("apache2.");break;
				case 25:setLi("mailbomb.");break;
				case 26:setLi("processtable.");break;
				case 27:setLi("udpstorm.");break;
				case 28:setLi("httptunnel.");break;
				case 29:setLi("ps.");break;
				case 30:setLi("sqlattack.");break;
				case 31:setLi("xterm.");break;
				case 32:setLi("named.");break;
				case 33:setLi("sendmail.");break;
				case 34:setLi("snmpgetattack.");break;
				case 35:setLi("snmpguess.");break;
				case 36:setLi("worm.");break;
				case 37:setLi("xlock.");break;
				case 38:setLi("xsnoop.");break;
				case 39:setLi("saint.");break;
				
				}
				return getLi();
			} catch (Exception e) {
				e.printStackTrace();
			}
			//System.out.println(inst);
		}
		
		br.close();
		reader.close();	
		return lineData;
	}
	public static void main(String[] args) throws Exception{								
		SystemDemo a = new SystemDemo();
		a.TrainLoader();
		a.TestLoader();
		a.model();
		/*a.getTreeInfo();
		a.getDataInfo();*/
		a.predict();
		System.out.println(a.getLi());
		
	}
	
}

