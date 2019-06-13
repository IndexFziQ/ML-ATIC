package org.xyqiang.netflow_classifier;

import weka.classifiers.Evaluation;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.core.converters.ConverterUtils.DataSource;

public class TestBP {

	public static void main(String[] args) throws Exception {

		Instances train = DataSource.read("./data/Model/Train.arff");
		train.setClassIndex(train.numAttributes() - 1);
		Instances test = DataSource.read("./data/Model/Test.arff");
		test.setClassIndex(test.numAttributes() - 1);

		MultilayerPerceptron mp=new MultilayerPerceptron();

		//ÉèÖÃÈë²Î
		mp.setGUI(false);//ÊÇ·ñ½øÐÐÍ¼ÐÎ½»»¥
		mp.setAutoBuild(true);//ÉèÖÃÍøÂçÖÐµÄÁ¬½ÓºÍÒþ²ã
		mp.setDebug(false);//¿ØÖÆ´òÓ¡ÐÅÏ¢
		mp.setDecay(false);//Èç¹ûÎªtrue»á½µµÍÑ§Ï°ËÙÂÊ
		mp.setHiddenLayers("a");//¶ÔÔ¤²â½á¹û¼¸ºõÃ»ÓÃÓ°Ïì
		mp.setLearningRate(0.3);//Weights±»¸üÐÂµÄÊýÁ¿,¶ÔÔ¤²â½á¹ûÓ°ÏìºÜ´ó
		mp.setMomentum(0.8);//µ±¸üÐÂweightsÊ±ÉèÖÃµÄ¶¯Á¿
		mp.setNormalizeAttributes(true);//¿ÉÒÔÓÅ»¯ÍøÂçÐÔÄÜ
		mp.setNormalizeNumericClass(true);//Èç¹ûÔ¤²âµÄÊÇÊýÖµÐÍ¿ÉÒÔÌá¸ßÍøÂçµÄÐÔÄÜ
		mp.setReset(false);//±ØÐëÒªÔÚAutoBuildÎªtrueµÄÌõ¼þÏÂ½øÐÐÉèÖÃ·ñÔòÄ¬ÈÏ¼´¿É
		mp.setSeed(0);//Ëæ»úÖÖ×ÓÊý£¬¶ÔÔ¤²â½á¹ûÓ°Ïì´ó
		mp.setTrainingTime(300);//µü´úµÄ´ÎÊý,ÓÐÒ»¶¨Ó°Ïì£¬µ«ÊÇ²»´ó
		mp.setValidationSetSize(20);//ÑéÖ¤°Ù·Ö±È£¬Ó°Ïì´ó
		mp.setValidationThreshold(50);//¼¸ºõÃ»ÓÃÓ°Ïì
		mp.setNominalToBinaryFilter(true);//¿ÉÒÔÌá¸ßÐÔÄÜ

		mp.buildClassifier(train);
		SerializationHelper.write("./data/Model/MultilayerPerceptron.model", mp);

		Evaluation eval=new Evaluation(train);
		eval.evaluateModel(mp, test);

		System.out.println("Æ½¾ù¾ø¶ÔÎó²î£º"+eval.meanAbsoluteError());//Ô½Ð¡Ô½ºÃ
		System.out.println("¾ù·½¸ùÎó²î£º"+eval.rootMeanSquaredError());//Ô½Ð¡Ô½ºÃ
		System.out.println(mp.toString());//¿´²»³öÄ£ÐÍµÄÒâË¼


	}

}
