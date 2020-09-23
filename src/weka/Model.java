package weka;

import java.util.ArrayList;

import weka.classifiers.misc.InputMappedClassifier;
import weka.classifiers.rules.DecisionTable;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.RandomForest;
import weka.classifiers.trees.RandomTree;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class Model {
	
	private RandomForest tree;
	private Instances dataset;
	private Instance inst;
	
	public Model(){		
		try {
			tree = (RandomForest) weka.core.SerializationHelper.read("C:/Users/Francesco/Desktop/compiti/MachineLearning/Progetto/models/RandomForest/7x7/RF1.2.model");
			//tree = (J48) weka.core.SerializationHelper.read("C:/Users/Francesco/Desktop/compiti/MachineLearning/Progetto/models/RandomForest/J48_1.0.model");
			//tree = (RandomTree) weka.core.SerializationHelper.read("C:/Users/Francesco/Desktop/compiti/MachineLearning/Progetto/models/RandomTree/RT1.0.model");
			//tree = (DecisionTable) weka.core.SerializationHelper.read("C:/Users/Francesco/Desktop/compiti/MachineLearning/Progetto/models/decisionTable/DT1.0.model");
			if (tree != null) {
				System.out.println("The Model was loaded.");
			}else{
				System.out.println("Error: The Model was not loaded !!!!");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		//dataset.setClass(new Attribute("backState"));
		
		try {
			DataSource source = new DataSource("C:/Users/Francesco/Desktop/compiti/MachineLearning/Progetto/trainSets/balanced/7x7/0_200IntMuroBal7x7.arff");
			dataset = source.getDataSet();
			dataset.setClassIndex(dataset.numAttributes()-1);
		}catch(Exception e) {
	    	System.out.println(e.toString());
	    }
		
        inst = dataset.firstInstance();
	}

	public double predict(double[] input) throws Exception{
		
		//System.out.println("Start prediction");
		
		//setup instance to predict
		//dataRaw.setClassIndex(dataRaw.numAttributes() - 1);
		
		for(int i=0; i < inst.numAttributes()-1; i++){
			inst.setValue(i, input[i]);
		}
		
		//Classifying stage
		//inst = new DenseInstance(24);
	    double actualValue = inst.classValue();
	    double predValue = tree.classifyInstance(inst);
	    //System.out.println("value: "+actualValue+"  pred:"+predValue);
	    //System.out.println("End prediction");
		
		return predValue;
	}
	
	
	
}
