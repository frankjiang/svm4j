/*
 * Copyright (c) 2011, 2020, Frank Jiang and/or its affiliates. All rights
 * reserved. ParameterNuSVR.java is PROPRIETARY/CONFIDENTIAL built in 2013. Use
 * is subject to license terms.
 */
package com.frank.svm.config;

import libsvm.svm_parameter;

/**
 * &nu;-SVC: The configuration for support vector limited classification.
 * 
 * @author <a href="mailto:jiangfan0576@gmail.com">Frank Jiang</a>
 * @version 1.0.0
 */
public class ParameterNuSVC extends AbstractParameter
{
	/**
	 * The cost parameter {@code C}, {@code C}&isin;(0,+&infin;) (default 1.0).
	 */
	protected double	cost;
	/**
	 * The support vector limit parameter &nu;. &nu;&isin;(0,1] (default 0.5).
	 * <p>
	 * &nu; parameter limits the amount of support vectors to
	 * &nu;&times;old_amount
	 * </p>
	 */
	protected double	nu;

	/**
	 * Construct a default <tt>ParameterNuSVC</tt>.
	 */
	public ParameterNuSVC()
	{
		this(1.0, 0.5);
	}

	/**
	 * Construct an instance of <tt>ParameterNuSVC</tt> with specified cost
	 * parameter {@code C} and support vector limit paramter <code>&nu;</code>.
	 * 
	 * @param cost
	 *            the cost parameter {@code C} (default 1.0)
	 * @param nu
	 *            the support vector limit paramter <code>&nu;</code>,
	 *            <code>&nu;</code>&isin;(0,1] (default 0.5)
	 */
	public ParameterNuSVC(double cost, double nu)
	{
		setCost(cost);
		setNu(nu);
		svmType = svm_parameter.NU_SVC;
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
	 *            the value of cost {@code C}, {@code C}&isin;(0,+&infin;)
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
	 * Returns the support vector limit paramter <code>&nu;</code>,
	 * <code>&nu;</code>&isin;(0,1] (default 0.5)
	 * 
	 * @return the <code>&nu;</code>
	 */
	public double getNu()
	{
		return nu;
	}

	/**
	 * Set the support vector limit paramter <code>&nu;</code>,
	 * <code>&nu;</code>&isin;(0,1] (default 0.5).
	 * 
	 * @param nu
	 *            the value of <code>&nu;</code>
	 */
	public void setNu(double nu)
	{
		if (nu <= 0 || nu > 1)
			throw new IllegalArgumentException(String.format(
					"ν(%g) must be the in the region of (0,1].", nu));
		this.nu = nu;
	}

	/**
	 * @see com.frank.svm.config.AbstractParameter#configParameter(libsvm.svm_parameter)
	 */
	protected void configParameter(svm_parameter param)
	{
		super.configParameter(param);
		param.C = cost;
		param.nu = nu;
	}
}
