package parent;


public interface ParentDAO {
	public Parent findByPid(java.lang.String pid);
	public int add(parent.Parent parent);
}
