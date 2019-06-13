package org.xyqiang.netflow_classifier;

import weka.classifiers.evaluation.Evaluation;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class SimpleEvaluate {

	public static void main(String[] args) {
		
	}
	
	public String simpleEvalute() throws Exception {
		Instances train = DataSource.read("F:/KDDCUP1999/Train.arff");
		train.setClassIndex(train.numAttributes() - 1);
		Instances test = DataSource.read("F:/KDDCUP1999/Test.arff");
		test.setClassIndex(test.numAttributes() - 1);
		LoaderTree tree=new LoaderTree();
		Evaluation eval = new Evaluation(train);
		eval.evaluateModel(tree.loadTree(), test);
		return (eval.toSummaryString()+"/n"+eval.toClassDetailsString());
	}

}
