package testmvc;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import parent.Parent;
import parent.ParentDAO;
import student.Student;
import student.StudentDAO;
import teacher.Teacher;
import teacher.TeacherDAO;

import banji.BanjiDAO;

import exam.ExamDAO;
import examdetail.ExamdetailDAO;
import grade.GradeDAO;
import gradedetail.GradedetailDAO;

import account.Account;
import account.AccountDAO;





@Controller
public class HelloController {
	
	@Resource
	AccountDAO adao;
	@Resource
	BanjiDAO bdao;
	@Resource
	ExamDAO examdao;
	@Resource
	ExamdetailDAO examdetaildao;
	@Resource
	GradeDAO gradedao;
	@Resource
	GradedetailDAO gradedetaildao;
	@Resource
	ParentDAO parentdao;
	@Resource
	StudentDAO studentdao;
	@Resource
	TeacherDAO teacherdao;
	@RequestMapping("/home")
	public String Homepage(HttpServletRequest request,ModelMap map)
	{
		Account a = adao.findByid("S001");
		request.getSession().setAttribute("user", a);
		return "test";
	}
	@RequestMapping("/registe")
	public String regist(HttpServletRequest request,HttpSession session,ModelMap map){
		String username=request.getParameter("username");
		String pass=request.getParameter("pass");
		String rname=request.getParameter("rname");
		String schoolname=request.getParameter("schoolname");
		int identy= Integer.parseInt(request.getParameter("identy"));
		Account ac=new Account();
		ac.setIdentify(identy);
		ac.setPassword(pass);
		ac.setUid(username);
//		System.out.println(username);
//		System.out.println(pass);
//		System.out.println(identy);
//		try{
		adao.add(ac);
		if(identy==1){
			Student st=new Student();
			st.setSid(username);
			st.setBid(null);
			st.setPid(null);
			st.setSname(rname);
			studentdao.add(st);
		}
		if(identy==2){
			Parent pt=new Parent();
			pt.setPid(username);
			pt.setPname(rname);
			pt.setSid(null);
			parentdao.add(pt);
		}
		if(identy==3){
			Teacher tr=new Teacher();
			tr.setTid(username);
			tr.setTname(rname);
			teacherdao.add(tr);
		}
//		}catch(Exception e){
//			return"XXX";//¥ÌŒÛΩÁ√Ê
//		}
//		Account a = adao.findByid("S001");
//		System.out.println(a.getPassword());
		return"index";
	}
	@RequestMapping("/login")
	public String login(HttpServletRequest request,HttpSession session,ModelMap map){
		String code=(String) session.getValue("code");
		String username=request.getParameter("username");
		String pass=request.getParameter("pass");
		String checkma=request.getParameter("checkma");
		if(!checkma.equals(code)){
			session.setAttribute("message","—È÷§¬Î¥ÌŒÛ");
			return "index";
		}
		Account ac= adao.findByid(username);
		if(ac.getPassword().equals(pass)){
			if(ac.getIdentify()==1){
				return"SLogin";
			}
			if(ac.getIdentify()==2){
				return"PLogin";			
			}
			else if(ac.getIdentify()==3){
				return"TLgin";
			}
		}
		else
		{
			session.setAttribute("message","√‹¬Î¥ÌŒÛ");
			return"index";
		}
		return"index";
	}
}
