package org.xyqiang.netflow_classifier;
/**
 * 得出基于決策樹的分类器模型可视化效果和评估结果
 * */
import weka.classifiers.evaluation.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class MyEvaluation {
		
	/** 读取训练集的对象 */	
	private  Instances train;
	
	/** 读取测试集的对象 */	
	private  Instances test;	
	
	/** 生成的决策树模型 */	
	private  J48 tree;
	
	/** 决策树的字符串输出模型  */	
	private  String trees;
	
	/** 评估结果的字符串输出 */	
	private  String evaluateData;
	
	/**
	 * 决策树的字符串输出模型的获取方法
	 * 
	 * @return 运行后生成的String型决策树模型
	 */		
	public String getTrees() {
		return trees;
	}	
	
	public void setTrees(String trees) {
		this.trees = trees;
	}
	
	/**
	 * 评估结果的字符串输出的获取方法
	 * 
	 * @return 运行后生成的String型评估结果
	 */	
	public String getEvaluateData() {
		return evaluateData;
	}
	
	public void setEvaluateData(String evaluateData) {
		this.evaluateData = evaluateData;
	}	
	
	/**
	 * 训练集对象的获取
	 * 
	 * @return 读取的训练集，用于决策树的生成
	 */	
	public Instances getTrain() {
		return train;
	}
	
	public void setTrain(Instances train) {
		this.train = train;
	}
	
	/**
	 * 测试集对象的获取
	 * 
	 * @return 读取的测试集，用于评估结果的生成
	 */	
	public Instances getTest() {
		return test;
	}
	
	public void setTest(Instances test) {
		this.test = test;
	}
	
	/**
	 * 决策树模型的获取方式
	 * 
	 * @return 运行生成的J48类对象，创建的决策树模型，可用于预测和评估
	 */	
	public J48 getTree() {
		return tree;
	}
	
	public void setTree(J48 tree) {
		this.tree = tree;
	}
			
	/**
	 * J48类创建决策树对象
	 * 决策树的选项见代码中注释，关键是buildClassifier方法
	 * 
	 * @return 决策树模型——tree
	 */	
	public J48 model() throws Exception {						
		// 训练J48分类器
		String[] options = new String[1];
		options[0] = "-U"; 			// 不修剪
		tree = new J48(); 			// J48分类器对象
		tree.setOptions(options);	// 设置选项
		tree.buildClassifier(train);// 构建分类器	
		return tree;
	}
	
	/**
	 * 加载训练集
	 * 
	 * @return 训练集——train
	 */	
	public Instances TrainLoader() throws Exception {
		train = DataSource.read("F:/KDDCUP1999/Train.arff");
		train.setClassIndex(train.numAttributes() - 1);
		return train;
	}
	
	/**
	 * 加载训练集
	 * 
	 * @return 训练集——test
	 */	
	public Instances TestLoader() throws Exception {
		test  = DataSource.read("F:/KDDCUP1999/Test.arff");
		test.setClassIndex(test.numAttributes() - 1);
		return test;
	}
	
	/**
	 * 在决策树生成之后，以字符串的形式展现出来
	 * toString就是转换成字符串的一个方法，相当于给trees赋值
	 * 
	 * @return 决策树的字符串形式——trees
	 */	
	public void buildtree() throws Exception {
		setTrees(tree.toString());
	}
	
	/**
	 * 对建立好的模型进行评估测试
	 * 加载好测试集，增加识别的一般性
	 * 
	 * @return 评估结果——evaluateData
	 */	
	public void evaluate() throws Exception {
		Evaluation eval = new Evaluation(train);
		eval.evaluateModel(tree, test);
		setEvaluateData(eval.toSummaryString()+"/n"+eval.toClassDetailsString());
		
	}
	
	/**
	 * 主函数，用于测试
	 * 
	 */	
	public static void main(String[] args) throws Exception {
		try {
			MyEvaluation a = new MyEvaluation();
			a.TrainLoader();
			a.TestLoader();
			a.model();
			a.evaluate();	
			System.out.print(a.getTree());
			System.out.print(a.getEvaluateData());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
}
