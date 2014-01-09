/*
 * Copyright (c) 2011, 2020, Frank Jiang and/or its affiliates. All rights
 * reserved. KernelPolynomial.java is PROPRIETARY/CONFIDENTIAL built in 2013.
 * Use is subject to license terms.
 */
package com.frank.svm.config;

import libsvm.svm_parameter;

/**
 * Polynomial kernel
 * <p>
 * <strong>function:</strong><br>
 * f(<strong>u</strong>,<strong>v</strong>) =
 * (&gamma;&times;<strong>u</strong><sup>T</sup>&sdot;<strong>v</strong> +
 * <code>b</code>)<sup><code>d</code></sup>
 * </p>
 * <p>
 * <strong>parameters:</strong><br>
 * &gamma;: the coefficient of the inner polynomial<br>
 * b: the coefficient of the offset for the polynomial<br>
 * d: the power of the polynomial
 * </p>
 * 
 * @author <a href="mailto:jiangfan0576@gmail.com">Frank Jiang</a>
 * @version 1.0.0
 */
public class KernelPolynomial extends KernelSigmoid
{
	/**
	 * The power coefficient {@code d} in polynomial kernel, {@code d}
	 * &isin;[0,+&infin;) (default 3).
	 */
	protected int	degree;

	/**
	 * Construct an instance of <tt>KernelPolynomial</tt>.
	 */
	public KernelPolynomial()
	{
		this(0, 0, 3);
	}

	/**
	 * Construct an instance of <tt>KernelPolynomial</tt> with specified &gamma;
	 * value, offset value {@code b} and power coefficient {@code d}.
	 * 
	 * @param gamma
	 *            the &gamma; value (default 1/features_count, set 0 for use
	 *            this value)
	 * @param b
	 *            the offset value {@code b} (default 0)
	 * @param degree
	 *            the power coefficient {@code d}, {@code d}&isin;[0,+&infin;)
	 *            (default 3)
	 */
	public KernelPolynomial(double gamma, double b, int degree)
	{
		super(gamma, b);
		setDegree(degree);
	}

	/**
	 * Returns the power coefficient <code>d</code> (default 3).
	 * 
	 * @return the power coefficient <code>d</code>
	 */
	public int getDegree()
	{
		return degree;
	}

	/**
	 * Set the power coefficient {@code d}, {@code d}&isin;[0,+&infin;) (default
	 * 3).
	 * 
	 * @param degree
	 *            the power coefficient {@code d}, {@code d}&isin;[0,+&infin;)
	 *            (default 3)
	 */
	public void setDegree(int degree)
	{
		if (degree < 0)
			throw new IllegalArgumentException(String.format(
					"degree(%d) must be nonnegative.", degree));
		this.degree = degree;
	}

	/**
	 * @see com.frank.svm.config.KernelSigmoid#configure(libsvm.svm_parameter)
	 */
	public void configure(svm_parameter param)
	{
		param.kernel_type = svm_parameter.POLY;
		param.gamma = gamma;
		param.coef0 = b;
		param.degree = degree;
	}
}
