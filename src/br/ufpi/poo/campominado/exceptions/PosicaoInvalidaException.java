/**
 * 
 */
package br.ufpi.poo.campominado.exceptions;

/**
 * @author alcemirsantos
 *
 */
@SuppressWarnings("serial")
public class PosicaoInvalidaException extends Exception {

	/**
	 * 
	 */
	public PosicaoInvalidaException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public PosicaoInvalidaException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public PosicaoInvalidaException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public PosicaoInvalidaException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public PosicaoInvalidaException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
