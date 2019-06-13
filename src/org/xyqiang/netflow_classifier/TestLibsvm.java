package org.xyqiang.netflow_classifier;

import java.io.File;

import weka.classifiers.Classifier;
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.core.converters.ArffLoader;

public class TestLibsvm {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Classifier classifier1;
        Classifier classifier2;
        Classifier classifier3;
    //    Classifier classifier4;

       
        File inputFile = new File(
               "./data/TrainAndTest/Train.arff");// 训练语料文件
        ArffLoader atf = new ArffLoader();
        atf.setFile(inputFile);
        Instances instancesTrain = atf.getDataSet(); // 读入训练文件
       
        inputFile = new File(
               "./data/TrainAndTest/Test.arff");// 测试语料文件
        atf.setFile(inputFile);
        Instances instancesTest = atf.getDataSet(); // 读入测试文件

       
        instancesTest.setClassIndex(instancesTest.numAttributes() - 1);
        instancesTrain.setClassIndex(instancesTrain.numAttributes() - 1);

       
       // 朴素贝叶斯算法
        classifier1 = (Classifier) Class.forName(
               "weka.classifiers.bayes.NaiveBayes").newInstance();
        // 决策树
        classifier2 = (Classifier) Class.forName(
               "weka.classifiers.trees.J48").newInstance();
        // Zero
        classifier3 = (Classifier) Class.forName(
               "weka.classifiers.rules.ZeroR").newInstance();
        // LibSVM
    //    classifier4 = (Classifier) Class.forName("weka.classifiers.functions.LibSVM").newInstance();
        
    // classifier4.buildClassifier(instancesTrain);
        classifier1.buildClassifier(instancesTrain);
        classifier2.buildClassifier(instancesTrain);
        classifier3.buildClassifier(instancesTrain);
        
        SerializationHelper.write("./data/Model/NaiveBayes.model", classifier1);
        SerializationHelper.write("./data/Model/J48_test.model", classifier2);
        SerializationHelper.write("./data/Model/ZeroR.model", classifier3);     
       
        
//        Evaluation eval = new Evaluation(instancesTrain);
//       
////        eval.evaluateModel(classifier4, instancesTest);
////      	System.out.println("LibSVM算法评估结果"+"\n"+eval.toSummaryString()+"\n"+eval.toClassDetailsString());
//        eval.evaluateModel(classifier1, instancesTest);
//        System.out.println("朴素贝叶斯算法评估结果"+"\n"+eval.toSummaryString()+"\n"+eval.toClassDetailsString());
//        eval.evaluateModel(classifier2, instancesTest);
//        System.out.println("决策树算法评估结果"+"\n"+eval.toSummaryString()+"\n"+eval.toClassDetailsString());
//        eval.evaluateModel(classifier3, instancesTest);
//        System.out.println("Zero算法评估结果"+"\n"+eval.toSummaryString()+"\n"+eval.toClassDetailsString());
       


    }

/*如果只有训练集，采用十交叉验证的方法，将上面的第5步和第6步更改为如下代码：
       
        Evaluation eval = new Evaluation(instancesTrain);
        eval.crossValidateModel(classifier4, instancesTrain, 10, new Random(1));
        System.out.println(eval.errorRate());
        eval.crossValidateModel(classifier1, instancesTrain, 10, new Random(1));
        System.out.println(eval.errorRate());
        eval.crossValidateModel(classifier2, instancesTrain, 10, new Random(1));
        System.out.println(eval.errorRate());
        eval.crossValidateModel(classifier3, instancesTrain, 10, new Random(1));
        System.out.println(eval.errorRate());
如果需要保存和加载分类器模型参数，在第5步和第6步之间加入如下代码：
       
        SerializationHelper.write("LibSVM.model", classifier4);
       
       
       
        Classifier classifier8 = (Classifier) weka.core.SerializationHelper.read("LibSVM.model");
        Classifier classifier5 = (Classifier) weka.core.SerializationHelper.read("NaiveBayes.model");
        Classifier classifier6 = (Classifier) weka.core.SerializationHelper.read("J48.model");
        Classifier classifier7 = (Classifier) weka.core.SerializationHelper.read("ZeroR.model");*/
}

