package problemDomain;
/**
 * A payment that has to be authorized by a third party
 */
public abstract class AuthorizedPayment extends Payment
{

	/**
	 * The code of authorization
	 */
	private String authorizationCode;

	/**
	 * Tell whether the authorization code is valid
	 * @return Is the authorization code valid?
	 */
	public Boolean isAuthorized()
	{
		return true;
	}

	/**
	 * Tell whether to count the payment as cash
	 * @return Is the payment to be counted as cash?
	 */
	public Boolean countAsCash()
	{
		return false;
	}

}