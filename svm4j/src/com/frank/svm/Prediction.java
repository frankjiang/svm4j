/*
 * Copyright (c) 2011, 2020, Frank Jiang and/or its affiliates. All rights
 * reserved. Prediction.java is PROPRIETARY/CONFIDENTIAL built in 2013. Use is
 * subject to license terms.
 */
package com.frank.svm;

/**
 * The prediction result of SVM.
 * <p>
 * In this prediction result contains the classification results or regression
 * results and may contains the probability estimates if the model supports.
 * </p>
 * <p>
 * The probability estimates for LIBSVM divides into two types, classification
 * and regression. The generic type for classification probability estimates is
 * {@linkplain java.util.TreeMap}with key of class labels and its probability;
 * the generic type for regression is a <tt>double</tt> value of &sigma; in
 * Laplace distribution.<br>
 * </p>
 * <p>
 * <strong>Probability estimates model for test data(classification)</strong>:<br>
 * It maps the class label to the probability estimate value.
 * </p>
 * <p>
 * <strong>Probability estimates model for test data(regression)</strong>:<br>
 * target_value = predicted_value + z <br>
 * <strong>Laplace Distribution</strong>: <br>
 * p(z) = exp(-|z|/&sigma;) / (2&times;&sigma;)<br>
 * <strong>Laplace's Cumulative distribution:</strong><br>
 * P(z) = &int;(-&infin;,z)p(u)du = 0.5&times;(1+sgn(z)(1-exp(-|z|/&sigma;)))<br>
 * <strong>Inverse Laplace's Cumulative distribution:</strong><br>
 * P<sup>-1</sup>(P(z)) = <br>
 * &sigma;&times;log(2P(z)), z&lt;0<br>
 * &minus;&sigma;&times;log(2 - 2P(z)), z&ge;0
 * </p>
 * 
 * @author <a href="mailto:jiangfan0576@gmail.com">Frank Jiang</a>
 * @param <R>
 *            the type of prediction result
 * @param <PE>
 *            the type of probability estimates
 * @version 1.0.0
 */
public class Prediction<R, PE>
{
	/**
	 * The result of prediction.
	 */
	protected R		prediction;
	/**
	 * The result of probability estimates.
	 */
	protected PE	probabilityEstimates;

	/**
	 * Construct a empty instance of <tt>Prediction</tt>.
	 */
	public Prediction()
	{
	}

	/**
	 * Construct an instance of <tt>Prediction</tt>.
	 * 
	 * @param prediction
	 *            the result of prediction
	 * @param probabilityEstimates
	 *            the result of probability estimates
	 */
	public Prediction(R prediction, PE probabilityEstimates)
	{
		this.prediction = prediction;
		this.probabilityEstimates = probabilityEstimates;
	}

	/**
	 * Returns the result of prediction.
	 * 
	 * @return the prediction
	 */
	public R getPrediction()
	{
		return prediction;
	}

	/**
	 * Set the result prediction.
	 * 
	 * @param prediction
	 *            the value of prediction
	 */
	public void setPrediction(R prediction)
	{
		this.prediction = prediction;
	}

	/**
	 * Returns the result of probability estimates.
	 * 
	 * @return the result of probability estimates
	 */
	public PE getProbabilityEstimates()
	{
		if (probabilityEstimates == null)
			throw new UnsupportedOperationException(
					"This model does not support probability or it is not created.");
		return probabilityEstimates;
	}

	/**
	 * Set the result of probability estimates.
	 * 
	 * @param probabilityEstimates
	 *            the result of probability estimates
	 */
	public void setProbabilityEstimates(PE probabilityEstimates)
	{
		this.probabilityEstimates = probabilityEstimates;
	}
}
