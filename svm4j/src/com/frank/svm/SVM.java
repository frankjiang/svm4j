/*
 * Copyright (c) 2011, 2020, Frank Jiang and/or its affiliates. All rights
 * reserved. SVM.java is PROPRIETARY/CONFIDENTIAL built in 2013. Use is subject
 * to license terms.
 */
package com.frank.svm;

import java.util.Collection;
import java.util.TreeMap;
import java.util.Vector;

import libsvm.svm;
import libsvm.svm_model;
import libsvm.svm_node;
import libsvm.svm_parameter;
import libsvm.svm_problem;

import com.frank.svm.config.AbstractParameter;
import com.frank.svm.config.ParameterCSVC;

/**
 * The LIBSVM interface.
 * <p>
 * In this interface, the structure of LIBSVM using is defined.
 * </p>
 * 
 * @author <a href="mailto:jiangfan0576@gmail.com">Frank Jiang</a>
 * @version 1.0.0
 */
public class SVM
{
	/**
	 * The SVM parameter.
	 */
	protected AbstractParameter	param;

	/**
	 * Construct a default <tt>SVM</tt>, a C-SVC inside.
	 */
	public SVM()
	{
		param = new ParameterCSVC();
		svm.svm_set_print_string_function(param.getPrinter());
	}

	/**
	 * Construct an instance of <tt>SVM</tt>.
	 */
	public SVM(AbstractParameter param)
	{
		if (param == null)
			throw new NullPointerException("The SVM parameter cannot be null.");
		this.param = param;
		svm.svm_set_print_string_function(this.param.getPrinter());
	}

	/**
	 * Training a SVM model according the specified sample collection/training
	 * set.
	 * 
	 * @param samples
	 *            the sample collection
	 * @return the SVM model
	 */
	public svm_model train(Collection<Sample> samples)
	{
		svm_problem prob = toProblem(samples);
		svm_parameter param = this.param.getParameter();
		return svm.svm_train(prob, param);
	}

	/**
	 * Predict the result of the specified sample according to the specified SVM
	 * model.
	 * 
	 * @param model
	 *            the SVM model
	 * @param prediction
	 *            the sample to predict
	 * @return the result of prediction
	 */
	public double predict(svm_model model, Sample prediction)
	{
		return svm.svm_predict(model, prediction.x());
	}

	/**
	 * Predict the results of the specified sample collection according to the
	 * specified SVM model.
	 * 
	 * @param model
	 *            the SVM model
	 * @param predictionSet
	 *            the sample collection to predict
	 * @param doProbabilityEstimates
	 *            the flag whether to do probability estimates, if do
	 *            <tt>true</tt> do
	 * @return the result of prediction
	 */
	public Prediction<Vector<Double>, ProbabilityEstimates> predict(
			svm_model model, Collection<Sample> predictionSet,
			boolean doProbabilityEstimates)
	{
		Double sigma = null;
		if (doProbabilityEstimates)
		{
			if (param.isUseProbabilityEstimates())
			{
				Vector<Double> prediction = new Vector<Double>(
						predictionSet.size());
				int svm_type = param.getSvmType();
				if (svm_type == svm_parameter.EPSILON_SVR
						|| svm_type == svm_parameter.NU_SVR)
					sigma = svm.svm_get_svr_probability(model);
				else
				{
					Vector<TreeMap<Integer, Double>> probabilityEstimates = new Vector<TreeMap<Integer, Double>>(
							predictionSet.size());
					int[] labels = new int[model.nr_class];
					double[] prob_estimates = new double[model.nr_class];
					svm.svm_get_labels(model, labels);
					for (Sample s : predictionSet)
					{
						prediction.add(svm.svm_predict_probability(model,
								s.x(), prob_estimates));
						TreeMap<Integer, Double> map = new TreeMap<Integer, Double>();
						for (int i = 0; i < labels.length; i++)
							map.put(labels[i], prob_estimates[i]);
						probabilityEstimates.add(map);
					}
					// classification result and probability estimates
					return new Prediction<Vector<Double>, ProbabilityEstimates>(
							prediction,
							new ProbabilityEstimates<Vector<TreeMap<Integer, Double>>>(
									probabilityEstimates));
				}
			}
			else
				throw new UnsupportedOperationException(
						"Current model do not support probability estimates.");
		}
		Vector<Double> result = new Vector<Double>();
		for (Sample s : predictionSet)
			result.add(svm.svm_predict(model, s.x()));
		if (sigma == null)
			// classification or regression result without probability estimates
			return new Prediction<Vector<Double>, ProbabilityEstimates>(result,
					null);
		else
			// regression result and probability estimates
			return new Prediction<Vector<Double>, ProbabilityEstimates>(result,
					new ProbabilityEstimates<Double>(sigma));
	}

	/**
	 * Predict the results of the specified sample array according to the
	 * specified SVM model.
	 * 
	 * @param model
	 *            the SVM model
	 * @param predictionArray
	 *            the sample array to predict
	 * @param doProbabilityEstimates
	 *            the flag whether to do probability estimates, if do
	 *            <tt>true</tt> do
	 * @return the result of prediction
	 */
	public Prediction<double[], ProbabilityEstimates> predict(svm_model model,
			Sample[] predictionArray, boolean doProbabilityEstimates)
	{
		Double sigma = null;
		if (doProbabilityEstimates)
		{
			if (param.isUseProbabilityEstimates())
			{
				double[] prediction = new double[predictionArray.length];
				int svm_type = param.getSvmType();
				if (svm_type == svm_parameter.EPSILON_SVR
						|| svm_type == svm_parameter.NU_SVR)
					sigma = svm.svm_get_svr_probability(model);
				else
				{
					TreeMap<Integer, Double>[] probabilityEstimates = new TreeMap[predictionArray.length];
					int[] labels = new int[model.nr_class];
					double[] prob_estimates = new double[model.nr_class];
					svm.svm_get_labels(model, labels);
					for (int i = 0; i < predictionArray.length; i++)
					{
						prediction[i] = (svm.svm_predict_probability(model,
								predictionArray[i].x(), prob_estimates));
						TreeMap<Integer, Double> map = new TreeMap<Integer, Double>();
						for (int classIndex = 0; classIndex < labels.length; classIndex++)
							map.put(labels[classIndex],
									prob_estimates[classIndex]);
						probabilityEstimates[i] = map;
					}
					// classification result and probability estimates
					return new Prediction<double[], ProbabilityEstimates>(
							prediction,
							new ProbabilityEstimates<TreeMap<Integer, Double>[]>(
									probabilityEstimates));
				}
			}
			else
				throw new UnsupportedOperationException(
						"Current model do not support probability estimates.");
		}
		double[] prediction = new double[predictionArray.length];
		for (int i = 0; i < predictionArray.length; i++)
			prediction[i] = svm.svm_predict(model, predictionArray[i].x());
		if (sigma == null)
			// classification or regression result without probability estimates
			return new Prediction<double[], ProbabilityEstimates>(prediction,
					null);
		else
			// regression result and probability estimates
			return new Prediction<double[], ProbabilityEstimates>(prediction,
					new ProbabilityEstimates<Double>(sigma));
	}

	/**
	 * Training a SVM model and predict the samples array according to the
	 * trained SVM model.
	 * 
	 * @param trainingSet
	 *            the training samples set
	 * @param predictionArray
	 *            the samples collection to predict
	 * @param doProbabilityEstimates
	 *            the flag whether to do probability estimates, if do
	 *            <tt>true</tt> do
	 * @return the result of prediction
	 */
	public Prediction<Vector<Double>, ProbabilityEstimates> trainAndPredict(
			Collection<Sample> trainingSet, Collection<Sample> predictionSet,
			boolean doProbabilityEstimates)
	{
		return predict(train(trainingSet), predictionSet,
				doProbabilityEstimates);
	}

	/**
	 * Training a SVM model and predict the samples array according to the
	 * trained SVM model.
	 * 
	 * @param trainingSet
	 *            the training samples set
	 * @param predictonSet
	 *            the sample collection to predict
	 * @param doProbabilityEstimates
	 *            the flag whether to do probability estimates, if do
	 *            <tt>true</tt> do
	 * @return the result of prediction
	 */
	public Prediction<double[], ProbabilityEstimates> trainAndPredict(
			Collection<Sample> trainingSet, Sample[] predictionArray,
			boolean doProbabilityEstimates)
	{
		return predict(train(trainingSet), predictionArray,
				doProbabilityEstimates);
	}

	/**
	 * Transform the sample collection/training set to a
	 * {@linkplain svm_problem}.
	 * 
	 * @param samples
	 *            the sample collection
	 * @return the SVM problem instance
	 */
	public static svm_problem toProblem(Collection<Sample> samples)
	{
		svm_problem prob = new svm_problem();
		prob.l = samples.size();
		prob.x = new svm_node[prob.l][];
		prob.y = new double[prob.l];
		int i = 0;
		for (Sample s : samples)
		{
			prob.x[i] = s.x();
			prob.y[i] = s.y();
			i++;
		}
		return prob;
	}

	public Validation crossValidate(Collection<Sample> samples, int folds)
	{
		if (folds < 2)
			throw new IllegalArgumentException(String.format(
					"The fold amount(%d) must be greater than 1.", folds));
		svm_parameter param = this.param.getParameter();
		svm_problem prob = toProblem(samples);
		int i;
		int total_correct = 0;
		double total_error = 0;
		double sumv = 0, sumy = 0, sumvv = 0, sumyy = 0, sumvy = 0;
		double[] target = new double[prob.l];
		svm.svm_cross_validation(prob, param, folds, target);
		if (param.svm_type == svm_parameter.EPSILON_SVR
				|| param.svm_type == svm_parameter.NU_SVR)
		{
			for (i = 0; i < prob.l; i++)
			{
				double y = prob.y[i];
				double v = target[i];
				total_error += (v - y) * (v - y);
				sumv += v;
				sumy += y;
				sumvv += v * v;
				sumyy += y * y;
				sumvy += v * y;
			}
			double mse = total_error / prob.l;
			double scc = ((prob.l * sumvy - sumv * sumy) * (prob.l * sumvy - sumv
					* sumy))
					/ ((prob.l * sumvv - sumv * sumv) * (prob.l * sumyy - sumy
							* sumy));
			return new Validation(mse, scc, folds);
		}
		else
		{
			for (i = 0; i < prob.l; i++)
				if (target[i] == prob.y[i])
					++total_correct;
			return new Validation(total_correct / (double) prob.l, folds);
		}
	}
}
