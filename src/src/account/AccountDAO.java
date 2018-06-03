package account;


public interface AccountDAO {
	public Account findByid(java.lang.String id);
	public int add(account.Account account);
}
