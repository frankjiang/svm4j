/*
 * Copyright (c) 2011, 2020, Frank Jiang and/or its affiliates. All rights
 * reserved. AbstractKernel.java is PROPRIETARY/CONFIDENTIAL built in 2013. Use
 * is subject to license terms.
 */
package com.frank.svm.config;

import libsvm.svm_parameter;

/**
 * The LIBSVM kernel configuration interface.
 * 
 * @author <a href="mailto:jiangfan0576@gmail.com">Frank Jiang</a>
 * @version 1.0.0
 * @see svm_parameter#LINEAR
 * @see svm_parameter#POLY
 * @see svm_parameter#RBF
 * @see svm_parameter#SIGMOID
 * @see svm_parameter#PRECOMPUTED
 */
public interface Kernel
{
	/**
	 * Configure the kernel information for the specified LIBSVM parameter.
	 * 
	 * @param param
	 *            the specified LIBSVM parameter
	 */
	public void configure(svm_parameter param);
}
