package libsvm;

/**
 * The structure of restoring support vector machine features.
 * <p>
 * </p>
 * 
 * @author <a href="mailto:jiangfan0576@gmail.com">Frank Jiang</a>
 * @version 1.0.0
 */
public class svm_node implements java.io.Serializable
{
	/**
	 * serialVersionUID.
	 */
	private static final long	serialVersionUID	= -3046511301730620312L;
	/**
	 * The index of the feature in the feature array.
	 */
	public int					index;
	/**
	 * The value of the feature.
	 */
	public double				value;

	/**
	 * Construct an instance of <tt>svm_node</tt>.
	 */
	public svm_node()
	{
	}

	/**
	 * Construct an instance of <tt>svm_node</tt> with specified feature index
	 * and value.
	 * 
	 * @param index
	 *            the feature index
	 * @param value
	 *            the feature value
	 */
	public svm_node(int index, double value)
	{
	}
}
