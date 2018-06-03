package teacher;

public interface TeacherDAO {
	public Teacher findByTid(java.lang.String tid);
	public int add(teacher.Teacher teacher);
}
