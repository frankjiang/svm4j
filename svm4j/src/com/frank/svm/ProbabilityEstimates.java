/*
 * Copyright (c) 2011, 2020, Frank Jiang and/or its affiliates. All rights
 * reserved. ProbabilityEstimates.java is PROPRIETARY/CONFIDENTIAL built in
 * 2013. Use is subject to license terms.
 */
package com.frank.svm;

/**
 * The structure of storing probability estimates.
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
 * @version 1.0.0
 */
public class ProbabilityEstimates<T>
{
	/**
	 * The result of probability estimates.
	 */
	protected T	probabilityEstimates;

	/**
	 * Construct an instance of <tt>ProbabilityEstimates</tt>.
	 * 
	 * @param probabilityEstimates
	 *            the result of probability estimates
	 */
	public ProbabilityEstimates(T probabilityEstimates)
	{
		this.probabilityEstimates = probabilityEstimates;
	}

	/**
	 * Returns the result of probability estimates.
	 * 
	 * @return the probability estimates
	 */
	public T getProbabilityEstimates()
	{
		return probabilityEstimates;
	}

	/**
	 * Set the result of probability estimates.
	 * 
	 * @param probabilityEstimates
	 *            the value of probability estimates
	 */
	public void setProbabilityEstimates(T probabilityEstimates)
	{
		this.probabilityEstimates = probabilityEstimates;
	}

	/**
	 * Returns the value of Laplace distribution:<br>
	 * <strong>Laplace Distribution</strong>: <br>
	 * p(z) = exp(-|z|/&sigma;) / (2&times;&sigma;)<br>
	 * 
	 * @param z
	 *            the random noise z
	 * @param sigma
	 *            the &sigma; value
	 * @return the probability
	 */
	public static double laplaceDistibution(double z, double sigma)
	{
		return Math.exp(-Math.abs(z) / sigma) / (2 * sigma);
	}

	/**
	 * Returns the cumulative distribution of Laplace distribution.
	 * <p>
	 * <strong>Laplace's Cumulative distribution:</strong><br>
	 * P(z) = &int;(-&infin;,z)p(u)du =
	 * 0.5&times;(1+sgn(z)(1-exp(-|z|/&sigma;)))
	 * </p>
	 * 
	 * @param z
	 *            the maximum random noise z
	 * @param sigma
	 *            the &sigma; value
	 * @return the cumulative probability
	 */
	public static double cumulativeLaplaceDistribution(double z, double sigma)
	{
		return 0.5 * (1 + Math.signum(z) * (1 - Math.exp(Math.abs(z) / -sigma)));
	}

	/**
	 * Returns the cumulative distribution for Laplace distribution.
	 * <p>
	 * <strong>Laplace's Cumulative distribution:</strong><br>
	 * P(z) = &int;(a,b)p(u)du = 0.5&times;[1+sgn(z)(1-exp(-|z|/&sigma;))
	 * </p>
	 * 
	 * @param sigma
	 *            the &sigma; value
	 * @param a
	 *            the maximum random noise a
	 * @param b
	 *            the maximum random noise b
	 * @return the cumulative probability
	 */
	public static double cumulativeLaplaceDistribution(double sigma, double a,
			double b)
	{
		return cumulativeLaplaceDistribution(b, sigma)
				- cumulativeLaplaceDistribution(a, sigma);
	}

	/**
	 * Returns the inverse value of cumulative distribution for Laplace
	 * distribution.
	 * <p>
	 * <strong>Inverse Laplace's Cumulative distribution:</strong><br>
	 * P<sup>-1</sup>(P(z)) = <br>
	 * &sigma;&times;log(2P(z)), z&lt;0<br>
	 * &minus;&sigma;&times;log(2 - 2P(z)), z&ge;0
	 * </p>
	 * 
	 * @param sigma
	 *            the value of &sigma;
	 * @param pz
	 *            the value of P(z)
	 * @param flag
	 *            the flag for whether z is nonnegative, <tt>true</tt> if
	 *            nonnegative
	 * @return
	 */
	public static double inverseCumulativeLaplaceDistribution(double sigma,
			double pz, boolean flag)
	{
		return flag ? (-sigma * Math.log(2 - 2 * pz)) : (sigma * Math
				.log(2 * pz));
	}

	/**
	 * Returns the radius of the accuracy region.
	 * <p>
	 * The radius means, the noise z&isin(-radius,+radius) match accuracy
	 * percent of the whole region.
	 * </p>
	 * 
	 * @param sigma
	 *            the &sigma; value
	 * @param accuracy
	 *            the accuracy value in (0,1)
	 * @return the radius
	 */
	public static double laplaceRadius(double sigma, double accuracy)
	{
		if (accuracy <= 0 || accuracy >= 1)
			throw new IllegalArgumentException(String.format(
					"The accuracy (%g) must be in the region (0,1).", accuracy));
		return inverseCumulativeLaplaceDistribution(sigma, 1 - accuracy / 2,
				true);
	}
}
