package student;


public interface StudentDAO {
	public Student findBySid(java.lang.String sid);
	public int add(student.Student student);
}
