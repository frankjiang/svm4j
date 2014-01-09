/*
 * Copyright (c) 2011, 2020, Frank Jiang and/or its affiliates. All rights
 * reserved. KernelSigmoid.java is PROPRIETARY/CONFIDENTIAL built in 2013. Use
 * is subject to license terms.
 */
package com.frank.svm.config;

import libsvm.svm_parameter;

/**
 * Sigmoid kernel
 * <p>
 * <strong>function:</strong><br>
 * f(<strong>u</strong>,<strong>v</strong>) =
 * tanh(&gamma;&times;<strong>u</strong><sup>T</sup>&sdot;<strong>v</strong> +
 * <code>b</code>)
 * </p>
 * <p>
 * <strong>parameters:</strong><br>
 * &gamma;: the coefficient of the inner polynomial<br>
 * b: the coefficient of the offset for the polynomial<br>
 * </p>
 * 
 * @author <a href="mailto:jiangfan0576@gmail.com">Frank Jiang</a>
 * @version 1.0.0
 */
public class KernelSigmoid extends KernelGamma
{
	/**
	 * The offset coefficient {@code b} (default 0).
	 */
	protected double	b;

	/**
	 * Construct a default of <tt>KernelSigmoid</tt>.
	 * <p>
	 * &gamma; = 1 / features_count<br>
	 * {@code b} = 0
	 * </p>
	 */
	public KernelSigmoid()
	{
		this(0.0, 0.0);
	}

	/**
	 * Construct an instance of <tt>KernelSigmoid</tt> with specified &gamma;
	 * value and offset value {@code b}.
	 * 
	 * @param gamma
	 *            the &gamma; value (default 1/features_count, set 0 for use
	 *            this value)
	 * @param b
	 *            the offset coefficient (default 0)
	 */
	public KernelSigmoid(double gamma, double b)
	{
		super(gamma);
		this.b = b;
	}

	/**
	 * @see com.frank.svm.config.Kernel#configure(libsvm.svm_parameter)
	 */
	@Override
	public void configure(svm_parameter param)
	{
		param.kernel_type = svm_parameter.SIGMOID;
		param.gamma = gamma;
		param.coef0 = b;
	}

	/**
	 * Returns the offset coefficient {@code b} (default 0).
	 * 
	 * @return the offset value {@code b}
	 */
	public double getB()
	{
		return b;
	}

	/**
	 * Set the offset coefficient {@code b} (default 0).
	 * 
	 * @param b
	 *            the offset value {@code b}
	 */
	public void setB(double b)
	{
		this.b = b;
	}
}
