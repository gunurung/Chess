package com.gunurung.chess.ai;

import org.deeplearning4j.nn.api.OptimizationAlgorithm;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.Updater;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.weights.WeightInit;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.learning.config.AdaGrad;
import org.nd4j.linalg.learning.config.RmsProp;
import org.nd4j.linalg.lossfunctions.LossFunctions;

import static org.junit.jupiter.api.Assertions.*;

class ChessTest {

    public void testAi(){

        int numColumns = 1;
        int numSamples = -1;
        int batchSize = -1;
        int epochs = 200;
        int splitTrainNum = (int)(batchSize*.9);
        int seed = 123;
        int listenerFreq = 1;
        MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
                .seed(12345)
                .weightInit(WeightInit.XAVIER)
                .updater(new AdaGrad(0.05))
                .activation(Activation.RELU)
                .l2(0.0001)
                .list()
                .layer(new DenseLayer.Builder().nIn(773).nOut(100)
                        .build())
                .layer(new DenseLayer.Builder().nIn(100).nOut(100)
                        .build())
                .layer(new DenseLayer.Builder().nIn(100).nOut(100)
                        .build())
                .layer(new OutputLayer.Builder().nIn(100).nOut(100)
                        .activation(Activation.LEAKYRELU)
                        .lossFunction(LossFunctions.LossFunction.MSE)
                        .build())
                .build();
    }
}