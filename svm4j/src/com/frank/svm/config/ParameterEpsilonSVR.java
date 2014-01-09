/*
 * Copyright (c) 2011, 2020, Frank Jiang and/or its affiliates. All rights
 * reserved. ParameterEpsilonSVR.java is PROPRIETARY/CONFIDENTIAL built in 2013.
 * Use is subject to license terms.
 */
package com.frank.svm.config;

import libsvm.svm_parameter;

/**
 * &epsilon;-SVR: The support vector regression in &epsilon; parameter.
 * 
 * @author <a href="mailto:jiangfan0576@gmail.com">Frank Jiang</a>
 * @version 1.0.0
 */
public class ParameterEpsilonSVR extends AbstractParameter
{
	/**
	 * The regression parameter <code>&epsilon;</code>, <code>&epsilon;</code>
	 * &isin;[0,+&infin;) (default 0.1).
	 */
	protected double	epsilon;

	/**
	 * Construct a default <tt>ParameterEpsilonSVR</tt>.
	 */
	public ParameterEpsilonSVR()
	{
		this(0.1);
	}

	/**
	 * Construct an instance of <tt>ParameterEpsilonSVR</tt> with specified
	 * epsilon.
	 * 
	 * @param epsilon
	 *            the regression parameter <code>&epsilon;</code>,
	 *            <code>&epsilon;</code> &isin;[0,+&infin;) (default 0.1).
	 */
	public ParameterEpsilonSVR(double epsilon)
	{
		setEpsilon(epsilon);
		svmType = svm_parameter.EPSILON_SVR;
	}

	/**
	 * Returns the regression parameter <code>&epsilon;</code>,
	 * <code>&epsilon;</code> &isin;[0,+&infin;) (default 0.1).
	 * 
	 * @return the <code>&epsilon;</code> parameter
	 */
	public double getEpsilon()
	{
		return epsilon;
	}

	/**
	 * Set the regression parameter <code>&epsilon;</code>,
	 * <code>&epsilon;</code> &isin;[0,+&infin;) (default 0.1).
	 * 
	 * @param epsilon
	 *            the <code>&epsilon;</code> parameter
	 */
	public void setEpsilon(double epsilon)
	{
		if (epsilon < 0)
			throw new IllegalArgumentException(String.format(
					"ε(%g) must be nonnegative.", epsilon));
		this.epsilon = epsilon;
	}

	/**
	 * @see com.frank.svm.config.AbstractParameter#configParameter(libsvm.svm_parameter)
	 */
	protected void configParameter(svm_parameter param)
	{
		super.configParameter(param);
		param.p = epsilon;
	}
}
