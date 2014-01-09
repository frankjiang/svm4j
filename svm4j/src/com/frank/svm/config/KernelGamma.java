/*
 * Copyright (c) 2011, 2020, Frank Jiang and/or its affiliates. All rights
 * reserved. KernelGamma.java is PROPRIETARY/CONFIDENTIAL built in 2013. Use is
 * subject to license terms.
 */
package com.frank.svm.config;

/**
 * A gamma kernel is a middle kernel, which declares that the current kernel
 * contains parameter &gamma;.
 * 
 * @author <a href="mailto:jiangfan0576@gmail.com">Frank Jiang</a>
 * @version 1.0.0
 */
public abstract class KernelGamma implements Kernel
{
	/**
	 * The parameter value &gamma; of the kernel (default 1/features_count, set
	 * 0 for use this value).
	 */
	protected double	gamma;

	/**
	 * Construct an instance of <tt>KernelGamma</tt> with specified &gamma;
	 * value.
	 * 
	 * @param gamma
	 *            the &gamma; value (default 1/features_count, set 0 for use
	 *            this value)
	 */
	public KernelGamma(double gamma)
	{
		setGamma(gamma);
	}

	/**
	 * Returns the parameter value &gamma; of the kernel (default
	 * 1/features_count, set 0 for use this value).
	 * 
	 * @return the &gamma; value
	 */
	public double getGamma()
	{
		return gamma;
	}

	/**
	 * Set the parameter value &gamma; of the kernel (default 1/features_count,
	 * set 0 for use this value).
	 * 
	 * @param gamma
	 *            the value of &gamma;
	 */
	public void setGamma(double gamma)
	{
		if (gamma < 0)
			throw new IllegalArgumentException(String.format(
					"γ(%g) must be nonnegative.", gamma));
		this.gamma = gamma;
	}
}
