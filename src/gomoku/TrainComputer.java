package gomoku;

import org.deeplearning4j.eval.Evaluation;

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TrainComputer {
	public TrainComputer(){	
	}
	
	public Computer Train(){
		Logger log = LoggerFactory.getLogger(TrainComputer.class);
			
		final int numRows = 28;
        final int numColumns = 28;
        int outputNum = 10; // number of output classes
        int batchSize = 64; // batch size for each epoch
        int rngSeed = 123; // random number seed for reproducibility
        int numEpochs = 15; // number of epochs to perform
        double rate = 0.0015; // learning rate

        log.info("Build model....");
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

        MultiLayerNetwork model = new MultiLayerNetwork(conf);
        model.init();
        model.setListeners(new ScoreIterationListener(5));  //print the score with every iteration

        log.info("Train model....");
        for( int i=0; i<numEpochs; i++ ){
        	log.info("Epoch " + i);
            model.fit(/*Train Set Here*/);
        }


        Evaluation eval = new Evaluation(outputNum); //create an evaluation object with 10 possible classes

        log.info(eval.stats());
        log.info("****************Example finished********************");
		return new Computer();
	}
}
