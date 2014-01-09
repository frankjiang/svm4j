/*
 * Copyright (c) 2011, 2020, Frank Jiang and/or its affiliates. All rights
 * reserved. KernelRBF.java is PROPRIETARY/CONFIDENTIAL built in 2013. Use is
 * subject to license terms.
 */
package com.frank.svm.config;

import libsvm.svm_parameter;

/**
 * Radial basis function kernel (Gauss kernel)
 * <p>
 * <strong>function:</strong><br>
 * f(<strong>u</strong>,<strong>v</strong>) = <code>e</code> <sup>-&gamma;&times
 * ;|<strong>u</strong>&minus;<strong>v</strong>|<sup>2</sup></sup>
 * </p>
 * <p>
 * <strong>parameters:</strong><br>
 * &gamma;: the coefficient of the kernel
 * </p>
 * 
 * @author <a href="mailto:jiangfan0576@gmail.com">Frank Jiang</a>
 * @version 1.0.0
 */
public class KernelRBF extends KernelGamma
{
	/**
	 * Construct a default <tt>KernelRBF</tt>.
	 */
	public KernelRBF()
	{
		super(0.0);
	}

	/**
	 * Construct an instance of <tt>KernelRBF</tt> with specified &gamma; value.
	 * 
	 * @param gamma
	 *            the &gamma; value
	 */
	public KernelRBF(double gamma)
	{
		super(gamma);
	}

	/**
	 * @see com.frank.svm.config.Kernel#configure(libsvm.svm_parameter)
	 */
	@Override
	public void configure(svm_parameter param)
	{
		param.kernel_type = svm_parameter.RBF;
		param.gamma = gamma;
	}
}
