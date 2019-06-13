package org.xyqiang.netflow_classifier;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Vector;

import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class MyPredict {
	
	private double add;
	private String pdd;
	private String temp;
	private Instance inst;
	
	private Vector<String> predictdata;
	
	public Vector<String> getPredictdata() {
		return predictdata;
	}

	public void setPredictdata(Vector<String> predictdata) {
		this.predictdata = predictdata;
	}
	public MyPredict(){
		try {
			predict();
		} catch (Exception e) {
			
		}
	}
	public void predict() throws Exception{	
		predictdata=new Vector<String>();
		
		Instances train = DataSource.read("./data/TrainandTest/Train.arff");		
		train.setClassIndex(train.numAttributes() - 1);
		
		Instances race = new Instances(train, 0);		
		LoaderTree tree=new LoaderTree();
		
		
		FileReader reader = new FileReader("./test.txt");
		BufferedReader br = new BufferedReader(reader);
		
		String string = null;
		while ((string = br.readLine()) != null) {
			String[] tmp = string.split(",");
			
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
				double res = tree.loadTree().classifyInstance(inst);
				
				switch((int)res){
				
				case 0:setPdd("back.");break;
				case 1:setPdd("teardrop.");break;
				case 2:setPdd("loadmodule.");break;
				case 3:setPdd("neptune.");break;
				case 4:setPdd("rootkit.");break;
				case 5:setPdd("phf.");break;
				case 6:setPdd("satan.");break;
				case 7:setPdd("buffer_overflow.");break;
				case 8:setPdd("ftp_write.");break;
				case 9:setPdd("land.");break;
				case 10:setPdd("spy.");break;
				case 11:setPdd("ipsweep.");break;
				case 12:setPdd("multihop.");break;
				case 13:setPdd("smurf.");break;
				case 14:setPdd("pod.");break;
				case 15:setPdd("perl.");break;
				case 16:setPdd("warezclient.");break;
				case 17:setPdd("nmap.");break;
				case 18:setPdd("imap.");break;
				case 19:setPdd("warezmaster.");break;
				case 20:setPdd("portsweep.");break;
				case 21:setPdd("normal.");break;
				case 22:setPdd("guess_passwd.");break;
				case 23:setPdd("mscan.");break;
				case 24:setPdd("apache2.");break;
				case 25:setPdd("mailbomb.");break;
				case 26:setPdd("processtable.");break;
				case 27:setPdd("udpstorm.");break;
				case 28:setPdd("httptunnel.");break;
				case 29:setPdd("ps.");break;
				case 30:setPdd("sqlattack.");break;
				case 31:setPdd("xterm.");break;
				case 32:setPdd("named.");break;
				case 33:setPdd("sendmail.");break;
				case 34:setPdd("snmpgetattack.");break;
				case 35:setPdd("snmpguess.");break;
				case 36:setPdd("worm.");break;
				case 37:setPdd("xlock.");break;
				case 38:setPdd("xsnoop.");break;
				case 39:setPdd("saint.");break;
				
				}
				temp=getPdd();
				predictdata.add(temp);				//System.out.println("预测值"+getPdd());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		br.close();
		reader.close();
		
	}
	
	public static void main(String[] args) throws Exception{
		/*xa a=new xa();		
		a.predict();
		//for(int i=0;i<predictdata.size();i++){
		String str2 ="";
		for(Iterator<String> it = predictdata.iterator(); it.hasNext();){
            str2 += it.next();
            str2 += "\n";
        }
		System.out.println(str2);
		//}
*/				
	}
	public String getPdd() {
		return pdd;
	}
	public void setPdd(String pdd) {
		this.pdd = pdd;
	}
	public Instance getInst() {
		return inst;
	}
	public void setInst(Instance inst) {
		this.inst = inst;
	}

	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}		
}


