package org.xyqiang.netflow_classifier;
/*
 * Demo界面，使用C4.5决策树预测异常流量
 * */
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import java.util.Iterator;
import java.awt.SystemColor;

public class MainContainer extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private MyPredict PredictData;
	private JTextArea textArea_0;
	private JTextArea textArea_1;
	private JTextArea textArea_2;
	private JButton btnBuild;
	private JButton btnEvaluate;
	private JButton btnPredict;
	private JButton btnExit;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainContainer frame = new MainContainer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainContainer() {
		setTitle("Version1.0");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 769, 474);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u5F02\u5E38\u6D41\u91CF\u8BC6\u522B\u7CFB\u7EDF");
		label.setFont(new Font("楷体", Font.PLAIN, 26));
		label.setBounds(69, 10, 217, 46);
		contentPane.add(label);
		
		JLabel lblCumtcsxyqiang = new JLabel("IndexFziQ");
		lblCumtcsxyqiang.setFont(new Font("Candara", Font.PLAIN, 14));
		lblCumtcsxyqiang.setBounds(296, 30, 105, 18);
		contentPane.add(lblCumtcsxyqiang);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 61, 616, 204);
		contentPane.add(scrollPane);
		
		textArea_0 = new JTextArea();
		textArea_0.setFont(new Font("楷体", Font.PLAIN, 13));
		String str0="决策树模型";		
		textArea_0.setText(str0);
		scrollPane.setViewportView(textArea_0);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(24, 275, 616, 76);
		contentPane.add(scrollPane_1);
		
		textArea_1 = new JTextArea();
		textArea_1.setFont(new Font("楷体", Font.PLAIN, 13));
		String str1="预测值";		
		textArea_1.setText(str1);
		scrollPane_1.setViewportView(textArea_1);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(24, 361, 616, 46);
		contentPane.add(scrollPane_2);
		
		textArea_2 = new JTextArea();
		textArea_2.setFont(new Font("楷体", Font.PLAIN, 13));
		String str2="警报";	
		textArea_2.setText(str2);
		scrollPane_2.setViewportView(textArea_2);
		
		btnBuild = new JButton("BuildTree");
		btnBuild.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoaderTree loadermodel=new LoaderTree();
			    try {
			    	textArea_0.append(loadermodel.loadTree().toString());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btnBuild.setBounds(650, 61, 93, 23);
		contentPane.add(btnBuild);
		
		btnEvaluate = new JButton("Evaluate");
		btnEvaluate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleEvaluate Result=new SimpleEvaluate();
				try {
					textArea_0.append(Result.simpleEvalute());
				} catch (Exception e1) {					
					e1.printStackTrace();
				}
				/*
				 * TrainResult=new MyEvaluation();	
			    try {
					TrainResult.TrainLoader();
					TrainResult.TestLoader();
					TrainResult.model();
					TrainResult.evaluate();
				} catch (Exception e1) {				
					e1.printStackTrace();
				}
				textArea_0.append(TrainResult.getEvaluateData());
				*/
			}
				
		});
		btnEvaluate.setBounds(650, 94, 93, 23);
		contentPane.add(btnEvaluate);
		
		btnPredict = new JButton("Predict");
		btnPredict.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				PredictData=new MyPredict();	
				
				String str5 ="\n";				
				try {
					PredictData.predict();					
					for(Iterator<String> it =PredictData.getPredictdata().iterator(); it.hasNext();){
			            str5 += it.next();
			            str5 += "\n";
			        }
					textArea_1.append(str5);
					
					String[] tmp = str5.split("\n");
					
					for(int i=1; i<tmp.length; i++){
						textArea_2.append("\n"+"该类型为："+tmp[i]);					
						if(tmp[i].equals("teardrop.")){							
							textArea_2.append("\n"+"该流量属于攻击类型DOS——teardrop");
						}
						else if(tmp[i].equals("back.")){							
							textArea_2.append("\n"+"该流量属于攻击类型DOS——back");
						}
						else if(tmp[i].equals("loadmodule.")){
							textArea_2.append("\n"+"该流量属于攻击类型U2R——loadmodule");
						}
						else if(tmp[i].equals("neptune.")){
							textArea_2.append("\n"+"该流量属于攻击类型DOS——neptune");
						}
						else if(tmp[i].equals("rootkit.")){
							textArea_2.append("\n"+"该流量属于攻击类型U2R——rootkit");
						}
						else if(tmp[i].equals("phf.")){
							textArea_2.append("\n"+"该流量属于攻击类型R2L——phf");
						}
						else if(tmp[i].equals("satan.")){
							textArea_2.append("\n"+"该流量属于攻击类型PROBE——satan");
						}
						else if(tmp[i].equals("buffer_overflow.")){
							textArea_2.append("\n"+"该流量属于攻击类型U2R——buffer_overflow");
						}
						else if(tmp[i].equals("ftp_write.")){
							textArea_2.append("\n"+"该流量属于攻击类型R2L——ftp_write");
						}
						else if(tmp[i].equals("land.")){
							textArea_2.append("\n"+"该流量属于攻击类型DOS——land");
						}
						else if(tmp[i].equals("spy.")){
							textArea_2.append("\n"+"该流量属于攻击类型R2L——spy");
						}
						else if(tmp[i].equals("ipsweep.")){
							textArea_2.append("\n"+"该流量属于攻击类型PROBE——ipsweep");
						}
						else if(tmp[i].equals("multihop.")){
							textArea_2.append("\n"+"该流量属于攻击类型R2L——multihop");
						}
						else if(tmp[i].equals("smurf.")){
							textArea_2.append("\n"+"该流量属于攻击类型DOS——smurf");
						}
						else if(tmp[i].equals("pod.")){
							textArea_2.append("\n"+"该流量属于攻击类型DOS——pod");
						}
						else if(tmp[i].equals("perl.")){
							textArea_2.append("\n"+"该流量属于攻击类型U2R——perl");
						}
						else if(tmp[i].equals("warezclient.")){
							textArea_2.append("\n"+"该流量属于攻击类型R2L——warezclient");
						}
						else if(tmp[i].equals("nmap.")){
							textArea_2.append("\n"+"该流量属于攻击类型PROBE——nmap");
						}
						else if(tmp[i].equals("imap.")){
							textArea_2.append("\n"+"该流量属于攻击类型R2L——imap");
						}
						else if(tmp[i].equals("warezmaster.")){
							textArea_2.append("\n"+"该流量属于攻击类型R2L——warezmaster");
						}
						else if(tmp[i].equals("portsweep.")){
							textArea_2.append("\n"+"该流量属于攻击类型PROBE——portsweep");
						}
						else if(tmp[i].equals("guess_passwd.")){
							textArea_2.append("\n"+"该流量属于攻击类型R2L——guess_passwd");
						}
						else if(tmp[i].equals("mscan.")){
							textArea_2.append("\n"+"该流量属于攻击类型PROBE——mscan");
						}
						else if(tmp[i].equals("apache2.")){
							textArea_2.append("\n"+"该流量属于攻击类型DOS——apache2");
						}
						else if(tmp[i].equals("mailbomb.")){
							textArea_2.append("\n"+"该流量属于攻击类型DOS——mailbomb");
						}
						else if(tmp[i].equals("processtable.")){
							textArea_2.append("\n"+"该流量属于攻击类型DOS——processtable");
						}
						else if(tmp[i].equals("udpstorm.")){
							textArea_2.append("\n"+"该流量属于攻击类型DOS——udpstorm");
						}
						else if(tmp[i].equals("httptunnel.")){
							textArea_2.append("\n"+"该流量属于攻击类型U2R——httptunnel");
						}
						else if(tmp[i].equals("ps.")){
							textArea_2.append("\n"+"该流量属于攻击类型U2R——ps");
						}
						else if(tmp[i].equals("sqlattack.")){
							textArea_2.append("\n"+"该流量属于攻击类型U2R——sqlattack");
						}
						else if(tmp[i].equals("xterm.")){
							textArea_2.append("\n"+"该流量属于攻击类型U2R——xterm");
						}
						else if(tmp[i].equals("named.")){
							textArea_2.append("\n"+"该流量属于攻击类型R2L——named");
						}
						else if(tmp[i].equals("sendmail.")){
							textArea_2.append("\n"+"该流量属于攻击类型R2L——sendmail");
						}
						else if(tmp[i].equals("snmpgetattack.")){
							textArea_2.append("\n"+"该流量属于攻击类型R2L——snmpgetattack");
						}
						else if(tmp[i].equals("snmpguess.")){
							textArea_2.append("\n"+"该流量属于攻击类型R2L——snmpguess");
						}
						else if(tmp[i].equals("worm.")){
							textArea_2.append("\n"+"该流量属于攻击类型R2L——worm");
						}
						else if(tmp[i].equals("xlock.")){
							textArea_2.append("\n"+"该流量属于攻击类型R2L——xlock");
						}
						else if(tmp[i].equals("xsnoop.")){
							textArea_2.append("\n"+"该流量属于攻击类型R2L——xsnoop");
						}
						else if(tmp[i].equals("saint.")){
							textArea_2.append("\n"+"该流量属于攻击类型PROBE——saint");
						}
						else{
							textArea_2.append("\n"+"该流量为正常流量——normal");
						}
					}					
				} catch (Exception e2) {					
					e2.printStackTrace();
				}
				
			}
		});
		btnPredict.setBounds(650, 128, 93, 23);
		contentPane.add(btnPredict);
			
		
		JTextPane textPane = new JTextPane();
		textPane.setFont(new Font("楷体", Font.PLAIN, 12));
		textPane.setForeground(SystemColor.controlDkShadow);
		textPane.setText("      \u7B80\u4ECB \r\n\u6A21\u62DF4\u5927\u7C7B\u7684\u7F51\r\n\u7EDC\u653B\u51FB\u65B9\u6CD5\uFF0C\u901A\r\n\u8FC7\u51B3\u7B56\u6811\u7B97\u6CD5\u5BF9\r\n\u6570\u636E\u8FDB\u884C\u9884\u6D4B\uFF0C\r\n\u5F97\u5230\u5F53\u524D\u6D41\u91CF\u7684\r\n\u9884\u6D4B\u503C\u3002      \r\n      \u529F\u80FD \r\n 1.\u8F93\u51FA\u51B3\u7B56\u6811\r\n 2.\u8F93\u51FA\u9884\u6D4B\u503C\r\n 3.\u8B66\u62A5\u548C\u63D0\u793A");
		textPane.setBounds(650, 216, 93, 191);
		contentPane.add(textPane);
		
		btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(650, 164, 93, 23);
		contentPane.add(btnExit);
	}
	public JTextArea getTextArea_0() {
		return textArea_0;
	}

	public void setTextArea_0(JTextArea textArea_0) {
		this.textArea_0 = textArea_0;
	}

	public JTextArea getTextArea_1() {
		return textArea_1;
	}

	public void setTextArea_1(JTextArea textArea_1) {
		this.textArea_1 = textArea_1;
	}

	public JTextArea getTextArea_2() {
		return textArea_2;
	}

	public void setTextArea_2(JTextArea textArea_2) {
		this.textArea_2 = textArea_2;
	}
}
