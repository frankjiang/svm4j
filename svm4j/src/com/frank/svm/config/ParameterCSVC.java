/*
 * Copyright (c) 2011, 2020, Frank Jiang and/or its affiliates. All rights
 * reserved. ParameterCSVC.java is PROPRIETARY/CONFIDENTIAL built in 2013. Use
 * is subject to license terms.
 */
package com.frank.svm.config;

import java.util.Map.Entry;

import libsvm.svm_parameter;

import com.frank.math.SparseVector;

/**
 * C-SVC: The configuration for standard support vector classification in cost
 * parameter.
 * 
 * @author <a href="mailto:jiangfan0576@gmail.com">Frank Jiang</a>
 * @version 1.0.0
 */
public class ParameterCSVC extends AbstractParameter
{
	/**
	 * The cost parameter {@code C}, {@code C}&isin;(0,+&infin;) (default 1.0).
	 */
	protected double				cost;
	/**
	 * The weights vector for cost.
	 * <p>
	 * The weights set the parameter C of class i to weight*C, for C-SVC.
	 * </p>
	 */
	protected SparseVector.Double	weights;

	/**
	 * Construct a default <tt>ParameterCSVC</tt>.
	 */
	public ParameterCSVC()
	{
		this(1.0, null);
	}

	/**
	 * Construct an instance of <tt>ParameterCSVC</tt> with specified cost
	 * parameter.
	 * 
	 * @param cost
	 *            the cost parameter {@code C}, {@code C}&isin;(0,+&infin;)
	 *            (default 1.0)
	 */
	public ParameterCSVC(double cost)
	{
		this(cost, null);
	}

	/**
	 * Construct an instance of <tt>ParameterCSVC</tt> with weights vector.
	 * 
	 * @param weights
	 *            the weights vector
	 */
	public ParameterCSVC(SparseVector.Double weights)
	{
		this(1.0, weights);
	}

	/**
	 * Construct an instance of <tt>ParameterCSVC</tt> with specified cost
	 * parameter and weights vector.
	 * 
	 * @param cost
	 *            the cost parameter
	 * @param weights
	 *            the weights vector
	 */
	public ParameterCSVC(double cost, SparseVector.Double weights)
	{
		setCost(cost);
		svmType = svm_parameter.C_SVC;
		this.weights = weights;
	}

	/**
	 * @see com.frank.svm.config.AbstractParameter#configParameter(libsvm.svm_parameter)
	 */
	protected void configParameter(svm_parameter param)
	{
		super.configParameter(param);
		param.C = cost;
		if (weights != null && !weights.isEmpty())
		{
			int size = weights.size();
			param.weight = new double[size];
			param.weight_label = new int[size];
			int i = 0;
			for (Entry<Integer, Double> e : weights.entrySet())
			{
				param.weight[i] = e.getValue();
				param.weight_label[i] = e.getKey();
				i++;
			}
		}
	}

	/**
	 * Returns the cost parameter {@code C}, {@code C}&isin;(0,+&infin;)
	 * (default 1.0).
	 * 
	 * @return the cost parameter
	 */
	public double getCost()
	{
		return cost;
	}

	/**
	 * Set the cost parameter {@code C}, {@code C}&isin;(0,+&infin;) (default
	 * 1.0).
	 * 
	 * @param cost
	 *            the value of parameter {@code C}, {@code C}&isin;(0,+&infin;)
	 *            (default 1.0)
	 */
	public void setCost(double cost)
	{
		if (cost <= 0)
			throw new IllegalArgumentException(String.format(
					"C(%g) must be positive.", cost));
		this.cost = cost;
	}

	/**
	 * Returns the weights vector.
	 * 
	 * @return the weights vector
	 */
	public SparseVector.Double getWeights()
	{
		return weights;
	}

	/**
	 * Set the weights vector.
	 * <p>
	 * The weights set the parameter C of class i to weight*C, for C-SVC. It can
	 * be null or empty, if need all 1.
	 * </p>
	 * 
	 * @param weights
	 *            the weights vector
	 */
	public void setWeights(SparseVector.Double weights)
	{
		this.weights = weights;
	}
}
