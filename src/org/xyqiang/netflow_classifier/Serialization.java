package org.xyqiang.netflow_classifier;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import weka.classifiers.trees.J48;

public class Serialization {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//Serialization a = new Serialization();		
		//a.persistModel(tree, "F:/KDDCUP1999/J48.model");
	}
	public void persistModel(J48 model, String ModelPath) {
		 ObjectOutputStream oos = null;
	        try {
	            oos = new ObjectOutputStream(
	            new FileOutputStream(ModelPath));
	            oos.writeObject(model);
	            oos.flush();
	            oos.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}
	
	@SuppressWarnings("resource")
	public J48 reloadPersistModel(String ModelPath) {
        ObjectInputStream ois = null;
        try {
        	ois = new ObjectInputStream(new FileInputStream(new File(ModelPath)));
            J48 model = (J48) ois.readObject();
            return model;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
