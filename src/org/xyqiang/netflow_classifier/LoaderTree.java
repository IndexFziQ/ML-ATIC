package org.xyqiang.netflow_classifier;

import weka.classifiers.trees.J48;

public class LoaderTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LoaderTree mal=new LoaderTree();
		System.out.println(mal.loadTree());
		System.gc();
	}
	
	public J48 loadTree() {
		Serialization test=new Serialization();
		J48 model=(J48) test.reloadPersistModel("./data/Model/test.model");
		return model;
	}
	
}
