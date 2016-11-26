package ai;

import org.deeplearning4j.nn.api.OptimizationAlgorithm;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.Updater;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
import org.nd4j.linalg.lossfunctions.LossFunctions.LossFunction;

/**
 * A fast policy that can rapidly sample actions during rollout
 * @author LongYang
 *
 */
public class RolloutPolicy {
	private MultiLayerNetwork network;
	private int numRows = 28;
	private int numColumns = 28;
	private int outputNum = 10; // number of output classes
	private int batchSize = 64; // batch size for each epoch
	private int rngSeed = 123; // random number seed for reproducibility
	private int numEpochs = 15; // number of epochs to perform
	private double rate = 0.0015; // learning rate
	public RolloutPolicy(){
		
	}
	public void init(){
		MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
	            .seed(rngSeed) //include a random seed for reproducibility
	            .optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT) // use stochastic gradient descent as an optimization algorithm
	            .iterations(1)
	            .activation("relu")
	            .weightInit(WeightInit.XAVIER)
	            .learningRate(rate) //specify the learning rate
	            .updater(Updater.NESTEROVS).momentum(0.98) //specify the rate of change of the learning rate.
	            .regularization(true).l2(rate * 0.005) // regularize learning model
	            .list()
	            .layer(0, new DenseLayer.Builder() //create the first input layer.
	                    .nIn(numRows * numColumns)
	                    .nOut(500)
	                    .build())
	            .layer(1, new DenseLayer.Builder() //create the second input layer
	                    .nIn(500)
	                    .nOut(100)
	                    .build())
	            .layer(2, new OutputLayer.Builder(LossFunction.NEGATIVELOGLIKELIHOOD) //create hidden layer
	                    .activation("softmax")
	                    .nIn(100)
	                    .nOut(outputNum)
	                    .build())
	            .pretrain(false).backprop(true) //use backpropagation to adjust weights
	            .build();
		 this.network = new MultiLayerNetwork(conf);
	     this.network.init();
	     this.network.setListeners(new ScoreIterationListener(1));
	}
	public void train(){
		// TODO Train NN
	}
	public void save(){
		
	}
	public void save(String filename){
		
	}
	public void load(){
		
	}
	public void load(String filename){
		
	}
}
