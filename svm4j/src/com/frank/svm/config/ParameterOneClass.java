/*
 * Copyright (c) 2011, 2020, Frank Jiang and/or its affiliates. All rights
 * reserved. ParameterOneClass.java is PROPRIETARY/CONFIDENTIAL built in 2013.
 * Use is subject to license terms.
 */
package com.frank.svm.config;

import libsvm.svm_parameter;

/**
 * The configuration of one-class SVM: distribution evaluation support vector
 * machine.
 * 
 * @author <a href="mailto:jiangfan0576@gmail.com">Frank Jiang</a>
 * @version 1.0.0
 */
public class ParameterOneClass extends AbstractParameter
{
	/**
	 * The support vector limit parameter &nu;. &nu;&isin;(0,1] (default 0.5).
	 * <p>
	 * &nu; parameter limits the amount of support vectors to
	 * &nu;&times;old_amount
	 * </p>
	 */
	protected double	nu;

	/**
	 * Construct a default <tt>ParameterOneClass</tt>.
	 */
	public ParameterOneClass()
	{
		this(0.5);
	}

	/**
	 * Construct an instance of <tt>ParameterOneClass</tt> with specified
	 * <code>&nu;</code> parameter.
	 * 
	 * @param nu
	 *            the support vector limit parameter &nu;. &nu;&isin;(0,1]
	 *            (default 0.5)
	 */
	public ParameterOneClass(double nu)
	{
		if (nu <= 0 || nu > 1)
			throw new IllegalArgumentException(String.format(
					"ν(%g) must be the in the region of (0,1].", nu));
		setNu(nu);
		svmType = svm_parameter.ONE_CLASS;
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
}
