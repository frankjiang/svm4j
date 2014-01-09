package libsvm;

/**
 * The structure which stores the support vector machine training/sample set.
 * <p>
 * </p>
 * 
 * @author <a href="mailto:jiangfan0576@gmail.com">Frank Jiang</a>
 * @version 1.0.0
 */
public class svm_problem implements java.io.Serializable
{
	/**
	 * serialVersionUID.
	 */
	private static final long	serialVersionUID	= -4451389443706847272L;
	/**
	 * The size of sample set.
	 */
	public int					l;
	/**
	 * The sparse vectors of features.
	 */
	public svm_node[][]			x;
	/**
	 * The vector of destination values.
	 */
	public double[]				y;
}
