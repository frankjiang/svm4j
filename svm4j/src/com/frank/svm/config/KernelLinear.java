/*
 * Copyright (c) 2011, 2020, Frank Jiang and/or its affiliates. All rights
 * reserved. KernelLinear.java is PROPRIETARY/CONFIDENTIAL built in 2013. Use is
 * subject to license terms.
 */
package com.frank.svm.config;

import libsvm.svm_parameter;

/**
 * The linear kernel.
 * <p>
 * <strong>function:</strong> f(<strong>u</strong>,<strong>v</strong>) =
 * <strong>u</strong><sup>T</sup>&sdot;<strong>v</strong>
 * </p>
 * 
 * @author <a href="mailto:jiangfan0576@gmail.com">Frank Jiang</a>
 * @version 1.0.0
 */
public class KernelLinear implements Kernel
{
	/**
	 * @see com.frank.svm.config.Kernel#configure(libsvm.svm_parameter)
	 */
	@Override
	public void configure(svm_parameter param)
	{
		param.kernel_type = svm_parameter.LINEAR;
	}
}
