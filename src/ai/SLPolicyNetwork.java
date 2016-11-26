package ai;

import org.deeplearning4j.nn.api.OptimizationAlgorithm;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.Updater;
import org.deeplearning4j.nn.conf.inputs.InputType;
import org.deeplearning4j.nn.conf.layers.ConvolutionLayer;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.conf.layers.SubsamplingLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
import org.nd4j.linalg.lossfunctions.LossFunctions;

/**
 * Supervised Learning Policy Network based on the human game data
 * @author LongYang
 *
 */
public class SLPolicyNetwork {
	private MultiLayerNetwork network;
	private int seed;
	private int iterations;
	private int nChannels;
	private int outputNum;
	public SLPolicyNetwork(){
		
	}
	public void init(){
		 MultiLayerConfiguration.Builder builder = new NeuralNetConfiguration.Builder()
				 	.seed(seed)
	                .iterations(iterations) // Training iterations as above
	                .regularization(true).l2(0.0005)
	                .learningRate(.01)//.biasLearningRate(0.02)
	                //.learningRateDecayPolicy(LearningRatePolicy.Inverse).lrPolicyDecayRate(0.001).lrPolicyPower(0.75)
	                .weightInit(WeightInit.XAVIER)
	                .optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT)
	                .updater(Updater.NESTEROVS).momentum(0.9)
	                .list()
	                .layer(0, new ConvolutionLayer.Builder(5, 5)
	                        //nIn and nOut specify depth. nIn here is the nChannels and nOut is the number of filters to be applied
	                        .nIn(nChannels)
	                        .stride(1, 1)
	                        .nOut(20)
	                        .activation("identity")
	                        .build())
	                .layer(1, new SubsamplingLayer.Builder(SubsamplingLayer.PoolingType.MAX)
	                        .kernelSize(2,2)
	                        .stride(2,2)
	                        .build())
	                .layer(2, new ConvolutionLayer.Builder(5, 5)
	                        //Note that nIn need not be specified in later layers
	                        .stride(1, 1)
	                        .nOut(50)
	                        .activation("identity")
	                        .build())
	                .layer(3, new SubsamplingLayer.Builder(SubsamplingLayer.PoolingType.MAX)
	                        .kernelSize(2,2)
	                        .stride(2,2)
	                        .build())
	                .layer(4, new DenseLayer.Builder().activation("relu")
	                        .nOut(500).build())
	                .layer(5, new OutputLayer.Builder(LossFunctions.LossFunction.NEGATIVELOGLIKELIHOOD)
	                        .nOut(outputNum)
	                        .activation("softmax")
	                        .build())
	                .setInputType(InputType.convolutionalFlat(28,28,1)) //See note below
	                .backprop(true).pretrain(false);
		 MultiLayerConfiguration conf = builder.build();
	     this.network = new MultiLayerNetwork(conf);
	     this.network.init();
	     this.network.setListeners(new ScoreIterationListener(1));
	}
	public void train(){
		// TODO Train NN
	}
	public MultiLayerNetwork getNetwork(){
		return this.network;
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
