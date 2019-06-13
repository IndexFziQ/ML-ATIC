package org.xyqiang.netflow_classifier;

import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class BuildTree {

	public static void main(String[] args) throws Exception {
		/*BuildTree a = new BuildTree();
		a.buildTree();*/

	}
	public void buildTree() throws Exception {
		Serialization test=new Serialization();		
		Instances train = DataSource.read("./data/TrainAndTest/Train.arff");		
		train.setClassIndex(train.numAttributes() - 1);
		String[] options = new String[1];
		options[0] = "-U"; 
		J48 tree = new J48(); 
		tree.setOptions(options);
		tree.buildClassifier(train);		
		test.persistModel(tree, "./data/Model/test.model");
	}
	
}
