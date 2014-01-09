/*
 * Copyright (c) 2011, 2020, Frank Jiang and/or its affiliates. All rights
 * reserved. Sample.java is PROPRIETARY/CONFIDENTIAL built in 2013. Use is
 * subject to license terms.
 */
package com.frank.svm;

import java.util.Map.Entry;

import libsvm.svm_node;

import com.frank.math.SparseVector;

/**
 * The structure for the support vector machine training samples.
 * 
 * @author <a href="mailto:jiangfan0576@gmail.com">Frank Jiang</a>
 * @version 1.0.0
 */
public class Sample
{
	/**
	 * The features vector.
	 */
	protected SparseVector.Double	x;
	/**
	 * The destination value.
	 */
	protected double				y;

	/**
	 * Construct an instance of <tt>Sample</tt>.
	 * 
	 * @param y
	 *            the destination value
	 */
	public Sample(double y)
	{
		this.y = y;
		x = new SparseVector.Double();
	}

	/**
	 * Construct an instance of <tt>Sample</tt>.
	 */
	public Sample()
	{
		this(0.0);
	}

	/**
	 * Set the target value {@code y}.
	 * 
	 * @param y
	 *            the target value
	 */
	public void setTarget(double y)
	{
		this.y = y;
	}

	/**
	 * Add one element to the feature vector.
	 * 
	 * @param index
	 *            the index of the feature
	 * @param value
	 *            the value of the feature
	 */
	public void insert(int index, double value)
	{
		if (index >= 0 && value != 0)
			x.insert(index, value);
	}

	/**
	 * Returns the {@link svm_node} type of the feature vector {@code x}.
	 * 
	 * @return the features vector {@code x}
	 */
	public svm_node[] x()
	{
		svm_node[] nodes = new svm_node[x.size()];
		int i = 0;
		for (Entry<Integer, Double> e : x.entrySet())
			nodes[i++] = new svm_node(e.getKey(), e.getValue());
		return nodes;
	}

	/**
	 * Returns the destination value {@code y}.
	 * 
	 * @return the destination value {@code y}
	 */
	public double y()
	{
		return y;
	}
}
