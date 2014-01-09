/*
 * Copyright (c) 2011, 2020, Frank Jiang and/or its affiliates. All rights
 * reserved. AbstractParameter.java is PROPRIETARY/CONFIDENTIAL built in 2013.
 * Use is subject to license terms.
 */
package com.frank.svm.config;

import java.io.PrintStream;

import libsvm.svm_parameter;
import libsvm.svm_print_interface;

/**
 * The abstract LIBSVM parameter configuration.
 * <p>
 * </p>
 * 
 * @author <a href="mailto:jiangfan0576@gmail.com">Frank Jiang</a>
 * @version 1.0.0
 */
public abstract class AbstractParameter
{
	/**
	 * The support vector machine type. This value should be initialized in the
	 * constructors of the sub-classes.
	 * 
	 * @see svm_parameter#LINEAR
	 * @see svm_parameter#POLY
	 * @see svm_parameter#RBF
	 * @see svm_parameter#SIGMOID
	 * @see svm_parameter#PRECOMPUTED
	 */
	protected int					svmType;
	/**
	 * The cache size of SVM in MB (default 100).
	 */
	protected double				cacheSize				= 100.0;
	/**
	 * The tolerance of termination criterion (default 0.001) for training.
	 */
	protected double				tolerance				= 0.001;
	/**
	 * The flag for whether shrinking technology is used (default <tt>true</tt>
	 * ).
	 */
	protected boolean				useShrinking			= true;
	/**
	 * The flag for whether the model supports probability estimates (default
	 * <tt>false</tt> ).
	 */
	protected boolean				useProbabilityEstimates	= false;
	/**
	 * The flag for whether the training and others action is do in quiet mode
	 * (default <tt>true</tt>). A quiet mode means the SVM will run without any
	 * console output; in opposite, some information will be print in the
	 * console.
	 */
	protected boolean				isQuietMode;
	/**
	 * The SVM printer. It is mapped with the quiet mode.
	 */
	protected svm_print_interface	printer;
	/**
	 * The kernel type of the SVM.
	 */
	protected Kernel				kernel					= new KernelRBF();

	/**
	 * Configure the specified LIBSVM parameter with current parameter settings.
	 * 
	 * @param param
	 *            the LIBSVM parameter to set
	 */
	protected void configParameter(svm_parameter param)
	{
		param.svm_type = svmType;
		param.cache_size = cacheSize;
		param.eps = tolerance;
		param.shrinking = useShrinking ? 1 : 0;
		param.probability = useProbabilityEstimates ? 1 : 0;
		printer = new svm_print_interface()
		{
			@Override
			public void print(String s)
			{
				// quiet printer, do nothing
			}
		};
		kernel.configure(param);
		param.nr_weight = 0;
		param.weight = new double[0];
		param.weight_label = new int[0];
	}

	/**
	 * Returns the LIBSVM parameter instance with the configuration of current
	 * parameters.
	 * 
	 * @return the parameters
	 */
	public svm_parameter getParameter()
	{
		svm_parameter param = new svm_parameter();
		configParameter(param);
		return param;
	}

	/**
	 * Getter for cache size in MB of the current SVM.
	 * 
	 * @return the cache size
	 */
	public double getCacheSize()
	{
		return cacheSize;
	}

	/**
	 * Set the cache size in MB of the current SVM (default 100).
	 * 
	 * @param cacheSize
	 *            the value of cache size
	 */
	public void setCacheSize(double cacheSize)
	{
		if (cacheSize <= 0)
			throw new IllegalArgumentException(String.format(
					"The cache size %.4f must be positive.", cacheSize));
		this.cacheSize = cacheSize;
	}

	/**
	 * Get the tolerance of termination criterion.
	 * 
	 * @return the tolerance
	 */
	public double getTolerance()
	{
		return tolerance;
	}

	/**
	 * Set the tolerance of termination criterion (default 0.001).
	 * 
	 * @param tolerance
	 *            the value of tolerance
	 */
	public void setTolerance(double tolerance)
	{
		if (tolerance <= 0)
			throw new IllegalArgumentException(String.format(
					"The tolerance %g must be positive.", tolerance));
		this.tolerance = tolerance;
	}

	/**
	 * Returns <tt>true</tt> if shrinking technology is used, otherwise
	 * <tt>false</tt>.
	 * 
	 * @return <tt>true</tt> if shrinking technology is used, otherwise
	 *         <tt>false</tt>
	 */
	public boolean isUseShrinking()
	{
		return useShrinking;
	}

	/**
	 * Set whether the shrinking technology is used.
	 * 
	 * @param useShrinking
	 *            <tt>true</tt> for using shrinking
	 */
	public void setUseShrinking(boolean useShrinking)
	{
		this.useShrinking = useShrinking;
	}

	/**
	 * Returns <tt>true</tt> if the current SVM support probability estimates
	 * 
	 * @return <tt>true</tt> if the current SVM support probability estimates
	 */
	public boolean isUseProbabilityEstimates()
	{
		return useProbabilityEstimates;
	}

	/**
	 * Set whether the current SVM support probability estimates.
	 * 
	 * @param useProbabilityEstimates
	 *            <tt>true</tt> for using probability estimates
	 */
	public void setUseProbabilityEstimates(boolean useProbabilityEstimates)
	{
		if (useProbabilityEstimates && svmType == svm_parameter.ONE_CLASS)
			throw new IllegalArgumentException(
					"One-class SVM does not support probability estimates.");
		this.useProbabilityEstimates = useProbabilityEstimates;
	}

	/**
	 * Returns the kernel type.
	 * 
	 * @return the kernel type
	 */
	public Kernel getKernel()
	{
		return kernel;
	}

	/**
	 * Set the kernel type.
	 * 
	 * @param kernel
	 *            the value of kernel
	 */
	public void setKernel(Kernel kernel)
	{
		this.kernel = kernel;
	}

	/**
	 * Getter for printer.
	 * 
	 * @return the printer
	 */
	public svm_print_interface getPrinter()
	{
		return printer;
	}

	/**
	 * Setter for printer. If need quiet mode, just use <tt>null</tt> for the
	 * parameter {@code printer}.
	 * 
	 * @param printer
	 *            the value of printer
	 */
	public void setPrinter(final PrintStream printer)
	{
		if (printer != null)
		{
			isQuietMode = false;
			this.printer = new svm_print_interface()
			{
				@Override
				public void print(String s)
				{
					printer.print(s);
					printer.flush();
				}
			};
		}
		else
		{
			isQuietMode = true;
			this.printer = new svm_print_interface()
			{
				@Override
				public void print(String s)
				{
					// quiet printer, do nothing
				}
			};
		}
	}

	/**
	 * Returns the support vector machine type.
	 * 
	 * @return the support vector machine type
	 */
	public int getSvmType()
	{
		return svmType;
	}

	/**
	 * Set the support vector machine type.
	 * 
	 * @param svmType
	 *            the support vector machine type
	 */
	public void setSvmType(int svmType)
	{
		this.svmType = svmType;
	}
}
