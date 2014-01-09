//
// svm_model
//
package libsvm;

/**
 * The original definition for a support vector machine model.
 * <p>
 * </p>
 * 
 * @author <a href="mailto:jiangfan0576@gmail.com">Frank Jiang</a>
 * @version 1.0.0
 */
public class svm_model implements java.io.Serializable
{
	/**
	 * serialVersionUID.
	 */
	private static final long	serialVersionUID	= 278220065587415279L;
	/**
	 * total #SV
	 */
	public int					l;
	/**
	 * label of each class (label[k])
	 */
	public int[]				label;
	/**
	 * number of classes, = 2 in regression/one class svm
	 */
	public int					nr_class;
	/**
	 * number of SVs for each class (nSV[k]) nSV[0] + nSV[1] + ... + nSV[k-1] =
	 * l
	 */
	public int[]				nSV;
	/**
	 * parameters
	 */
	public svm_parameter		param;
	/**
	 * pariwise probability information
	 */
	public double[]				probA, probB;
	/**
	 * constants in decision functions (rho[k*(k-1)/2])
	 */
	public double[]				rho;
	/**
	 * SVs (SV[l])
	 */
	public svm_node[][]			SV;
	/**
	 * for classification only: <br>
	 * coefficients for SVs in decision functions (sv_coef[k-1][l])
	 */
	public double[][]			sv_coef;
	/**
	 * for classification only: <br>
	 * sv_indices[0,...,nSV-1] are values in [1,...,num_traning_data] to
	 * indicate SVs in the training set
	 */
	public int[]				sv_indices;
};
