package libsvm;

/**
 * The LIBSVM parameters structure.
 * <p>
 * </p>
 * 
 * @author <a href="mailto:jiangfan0576@gmail.com">Frank Jiang</a>
 * @version 1.0.0
 */
public class svm_parameter implements Cloneable, java.io.Serializable
{
	/**
	 * serialVersionUID.
	 */
	private static final long	serialVersionUID	= -2733609912517132812L;
	/**
	 * <strong>svm_type<strong>:
	 * <p>
	 * C-SVC: cost support vector machine classification.
	 * </p>
	 */
	public static final int		C_SVC				= 0;
	/**
	 * <strong>svm_type<strong>:
	 * <p>
	 * &nu;-SVC: support vector limited classification.
	 * </p>
	 */
	public static final int		NU_SVC				= 1;
	/**
	 * <strong>svm_type<strong>:
	 * <p>
	 * one-class SVM: support vector distribution evaluation.
	 * </p>
	 */
	public static final int		ONE_CLASS			= 2;
	/**
	 * <strong>svm_type<strong>:
	 * <p>
	 * &epsilon;-SVR: &epsilon parameter support vector regression.
	 * </p>
	 */
	public static final int		EPSILON_SVR			= 3;
	/**
	 * <strong>svm_type<strong>:
	 * <p>
	 * &nu;-SVC: support vector limited regression.
	 * </p>
	 */
	public static final int		NU_SVR				= 4;
	/**
	 * <strong>kernel_type</strong>: linear kernel
	 * <p>
	 * <strong>function:</strong> f(<strong>u</strong>,<strong>v</strong>) =
	 * <strong>u</strong><sup>T</sup>&sdot;<strong>v</strong>
	 * </p>
	 */
	public static final int		LINEAR				= 0;
	/**
	 * <strong>kernel_type</strong>: polynomial kernel
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
	 */
	public static final int		POLY				= 1;
	/**
	 * <strong>kernel_type</strong>: pre-computed kernel
	 * <p>
	 * A pre-computed kernel contains the mapping of input and output.<br>
	 * The kernel values are in the training_set_file.
	 * </p>
	 */
	public static final int		PRECOMPUTED			= 4;
	/**
	 * <strong>kernel_type</strong>: radial basis function kernel (Gauss kernel)
	 * <p>
	 * <strong>function:</strong><br>
	 * f(<strong>u</strong>,<strong>v</strong>) = <code>e</code>
	 * <sup>-&gamma;&times
	 * ;|<strong>u</strong>&minus;<strong>v</strong>|<sup>2</sup></sup>
	 * </p>
	 * <p>
	 * <strong>parameters:</strong><br>
	 * &gamma;: the coefficient of the kernel
	 * </p>
	 */
	public static final int		RBF					= 2;
	/**
	 * <strong>kernel_type</strong>: sigmoid kernel
	 * <p>
	 * <strong>function:</strong><br>
	 * f(<strong>u</strong>,<strong>v</strong>) =
	 * tanh(&gamma;&times;<strong>u</strong><sup>T</sup>&sdot;<strong>v</strong>
	 * + <code>b</code>)
	 * </p>
	 * <p>
	 * <strong>parameters:</strong><br>
	 * &gamma;: the coefficient of the inner polynomial<br>
	 * b: the coefficient of the offset for the polynomial<br>
	 * </p>
	 */
	public static final int		SIGMOID				= 3;
	/**
	 * The cost coefficient (default 1).
	 * <p>
	 * This parameter is used in C-SVC, &epsilon;-SVR and &nu;-SVR.
	 * </p>
	 */
	public double				C;
	/**
	 * Training parameter:
	 * <p>
	 * The cache memory size in MB (default 100).
	 * </p>
	 */
	public double				cache_size;
	/**
	 * The offset coefficient <code>b</code> in polynomial kernel and sigmoid
	 * kernel (default 0).
	 */
	public double				coef0;
	/**
	 * The power coefficient <code>d</code> in polynomial kernel.
	 */
	public int					degree;
	/**
	 * Training parameter:
	 * <p>
	 * The tolerance of termination criterion (default 0.001).
	 * </p>
	 */
	public double				eps;
	/**
	 * The &gamma; parameter in polynomial kernel, radial basis function kernel
	 * and sigmoid kernel (default 1/features_count, set 0 for use this value).
	 */
	public double				gamma;
	/**
	 * The flag of kernel type.
	 * 
	 * @see #LINEAR
	 * @see #POLY
	 * @see #RBF
	 * @see #SIGMOID
	 * @see #PRECOMPUTED
	 */
	public int					kernel_type;
	/**
	 * The length of the weight vector in C-SVC.
	 * <p>
	 * The weights set the parameter C of class i to weight*C, for C-SVC.
	 * </p>
	 */
	public int					nr_weight;
	/**
	 * The &nu; parameter in &nu-SVC, one-class SVM and &nu-SVR.
	 */
	public double				nu;
	/**
	 * The &epsilon; parameter in &epsilon;-SVR.
	 */
	public double				p;
	/**
	 * The flag for whether doing probability estimates. 1 for doing; 0 for not.
	 */
	public int					probability;
	/**
	 * The flag for whether using the shrinking heuristics.
	 */
	public int					shrinking;
	/**
	 * The support vector machine type.
	 * 
	 * @see #C_SVC
	 * @see #NU_SVC
	 * @see #ONE_CLASS
	 * @see #EPSILON_SVR
	 * @see #NU_SVR
	 */
	public int					svm_type;
	/**
	 * The custom weights vector of costs in C-SVC.
	 * <p>
	 * The weights set the parameter C of class i to weight*C, for C-SVC.
	 * </p>
	 */
	public double[]				weight;
	/**
	 * The labels for custom weights vector in C-SVC.
	 * <p>
	 * The weights set the parameter C of class i to weight*C, for C-SVC.
	 * </p>
	 */
	public int[]				weight_label;

	/**
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Object clone()
	{
		try
		{
			return super.clone();
		}
		catch (CloneNotSupportedException e)
		{
			return null;
		}
	}
}
