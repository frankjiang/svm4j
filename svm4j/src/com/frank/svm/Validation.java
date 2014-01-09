/*
 * Copyright (c) 2011, 2020, Frank Jiang and/or its affiliates. All rights
 * reserved. Validation.java is PROPRIETARY/CONFIDENTIAL built in 2013. Use is
 * subject to license terms.
 */
package com.frank.svm;

/**
 * The result construction of cross validation.
 * <p>
 * </p>
 * 
 * @author <a href="mailto:jiangfan0576@gmail.com">Frank Jiang</a>
 * @version 1.0.0
 */
public class Validation
{
	/**
	 * The flag for classification or one-class cross validation.
	 */
	public static final int	CLASSIFICATION_OR_ONE_CLASS	= 0;
	/**
	 * The flag for regression cross validation.
	 */
	public static final int	REGRESSION					= 1;
	/**
	 * The fold amount.
	 */
	protected int			folds;
	/**
	 * The flag for cross validation type.
	 * 
	 * @see #CLASSIFICATION_OR_ONE_CLASS
	 * @see #REGRESSION
	 */
	protected int			type;
	/**
	 * The accuracy of classification cross validation.
	 */
	protected double		accuracy;
	/**
	 * The mean squared error of classification cross validation.
	 */
	protected double		mse;
	/**
	 * The squared correlation coefficient of classification cross validation.
	 */
	protected double		scc;

	/**
	 * Construct an instance of cross validation result for classification or
	 * one-class.
	 * 
	 * @param accuracy
	 *            the accuracy of classification cross validation
	 * @param folds
	 *            the fold amount
	 */
	public Validation(double accuracy, int folds)
	{
		type = CLASSIFICATION_OR_ONE_CLASS;
		this.accuracy = accuracy;
		this.folds = folds;
		mse = Double.NaN;
		scc = Double.NaN;
	}

	/**
	 * Construct an instance of cross validation result for regression.
	 * 
	 * @param mse
	 *            the mean squared error
	 * @param scc
	 *            the squared correlation coefficient
	 * @param folds
	 *            the fold amount
	 */
	public Validation(double mse, double scc, int folds)
	{
		type = REGRESSION;
		accuracy = Double.NaN;
		this.folds = folds;
		this.mse = mse;
		this.scc = scc;
	}

	/**
	 * Returns the fold amount.
	 * 
	 * @return the fold amount
	 */
	public int getFolds()
	{
		return folds;
	}

	/**
	 * Returns the accuracy of classification cross validation.
	 * 
	 * @return the accuracy
	 */
	public double getAccuracy()
	{
		return accuracy;
	}

	/**
	 * Returns the mean squared error of classification cross validation.
	 * 
	 * @return the mean squared error
	 */
	public double getMse()
	{
		return mse;
	}

	/**
	 * Returns the squared correlation coefficient of classification cross
	 * validation.
	 * 
	 * @return the squared correlation coefficient
	 */
	public double getScc()
	{
		return scc;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		switch (type)
		{
			case CLASSIFICATION_OR_ONE_CLASS:
				return String.format(
						"%d-folds cross validation: accuracy = %g%%", folds,
						accuracy * 100.0);
			case REGRESSION:
				return String
						.format("%d-folds cross validation:\r\nMean squared error = %g\r\nSquared correlation coefficient = %g\r\n",
								folds, mse, scc);
			default:
				return "unkown type of cross validation";
		}
	}
}
