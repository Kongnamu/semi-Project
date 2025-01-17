package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board.Community;
import board.CommunityDAO;
import member.Member;
import member.MemberDAO;
import reply.Reply;
import reply.ReplyDAO;

@WebServlet("*.do") // '/'이하의 경로에서 do로 끝나는 확장자는 모두 허용
public class MainController extends HttpServlet {
	
	private static final long serialVersionUID = 10L;
	MemberDAO mDAO;
	CommunityDAO cDAO;
	ReplyDAO rDAO;
	
    public MainController() { //생성자
	//필드
    mDAO = new MemberDAO();
    cDAO = new CommunityDAO();
    rDAO = new ReplyDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//한글 인코딩
		request.setCharacterEncoding("utf-8");
		//응답할 컨텐츠 유형
		response.setContentType("text/html; charset=utf-8");
		
		//경로 설정
		//uri - 컨텍스트(/) + 파일(.do)
		String uri = request.getRequestURI();
		System.out.println(uri);
		//command 패턴
		String command = uri.substring(uri.lastIndexOf("/"));
		System.out.println(command);
		//이동 페이지
		String nextPage = "";
		//세션 객체 생성
		HttpSession session = request.getSession();
		
		//view에 출력 객체 생성
		PrintWriter out = response.getWriter();
		
		if(command.equals("/userlist.do")) {
			//회원정보를 db에서 가져옴
			List<Member> userList = mDAO.getMemberList();
			//모델 생성
			request.setAttribute("userList", userList);
			//이동할 페이지
			nextPage = "/users/userlist.jsp";
		}else if(command.equals("/joinform.do")) {
			//get 방식
			nextPage = "/users/joinform.jsp";
		}else if(command.equals("/insertmember.do")) {
			//빈 회원 객체를 생성해서 데이터를 받아서 세팅
			//폼 데이터 받기
			String id = request.getParameter("id");
			String passwd = request.getParameter("passwd");
			String name = request.getParameter("name");
			String phone = request.getParameter("phone");
			String addr = request.getParameter("addr");
			//객체에 데이터 세팅
			Member m = new Member();
			m.setUserId(id);
			m.setUserPasswd(passwd);
			m.setUserName(name); // 회원 이름
			m.setUserPhone(phone);
			m.setUserAddr(addr);
			//db에 저장함
			mDAO.insertMember(m);
			//가입 후 자동 로그인
			session.setAttribute("sessionId", m.getUserId());	  //아이디를 가져와서 sessionId(세션이름) 발급
			session.setAttribute("sessionName", m.getUserName()); //성명을 가져와서 sessionName 발급
			//회원 가입후 이동
			nextPage = "/main.jsp";
		}else if(command.equals("/userview.do")) {
		   String id = request.getParameter("id");
			
			Member member = mDAO.getMember(id);
			
			//모델 생성
			request.setAttribute("member", member);
			
			nextPage = "/users/userview.jsp";
		}else if(command.equals("/updateuserform.do")) {
	         // 회원 수정
	            //수정을 위해서 게시글 가져오기
	         String id = request.getParameter("id");
	            //게시글 1건 가져오기
	            Member member = mDAO.getMember(id);
	            //모델 생성
	            request.setAttribute("member", member);
	            
	            nextPage = "/users/updateuserform.jsp";
	         }else if(command.equals("/updateuser.do")) {
	            // 회원 수정
	            //게시글 제목, 내용을 파라미터로 받음
	            String id =  request.getParameter("id");
	            String passwd = request.getParameter("passwd");
	            String phone = request.getParameter("phone");
	            String addr = request.getParameter("addr");
	            
	            
	            //수정 처리 메서드
	            Member m = new Member();
	            m.setUserId(id);
	            m.setUserPasswd(passwd);
	            m.setUserPhone(phone);
	            m.setUserAddr(addr);
	            
	            
	            mDAO.updateUsers(m);
	            
	            nextPage = "/userview.do";
		}else if(command.equals("/deletemember.do")) {   
	         //회원탈퇴
	         String id = request.getParameter("id");
	         //삭제 처리 메서드 호출
	         mDAO.deletemember(id);
	         nextPage = "/logout.do";
		}else if(command.equals("/loginform.do")) { //로그인 폼페이지 이동
			 nextPage = "/users/loginform.jsp";
		}else if(command.equals("/login.do")) { //로그인 처리
			//아이디와 비번 파라미터 받기
			String id = request.getParameter("id");
			String passwd = request.getParameter("passwd");
			//아이디와 비번 세팅해줌
			Member m = new Member();
			m.setUserId(id);
			m.setUserPasswd(passwd);
			//로그인 인증
			Member member = mDAO.checkLogin(m);
			String name = member.getUserName();
			if(name != null) { //result가 true이면 세션 발급
				session.setAttribute("sessionId", id);		//아이디 세션 발급
				session.setAttribute("sessionName", name);	//이름 세션 발급
				//로그인 후 페이지 이동
				nextPage = "/main.jsp";
			}else {
				//에러를 모델로 보내기
				String error = "아이디나 비밀번호를 다시 확인해주세요.";
				request.setAttribute("error", error);
				//에러 발생후 페이지 이동
				nextPage ="/users/loginform.jsp";
			}	
		}else if(command.equals("/logout.do")) {
			session.invalidate(); //모든 세션 삭제
			nextPage = "/main.jsp";
		}
		
		//게시판
	      if(command.equals("/communitylist.do")) {
	         //페이지 처리
	         String pageNum = request.getParameter("pageNum");
	         if(pageNum == null) { //페이지 번호를 클릭하지 않았을떄 기본값
	            pageNum = "1";
	         }
	         
	         //현재 페이지
	         int currentPage = Integer.parseInt(pageNum);
	         //페이지, 게시글 수 : 10(pageSize)
	         int pageSize = 10;
	         //1페이지 첫번째행(startRow) : 1번, 2페이지 : 11번
	         //산술을 사용하여 구현
	         int startRow = (currentPage - 1) * pageSize + 1;
	         System.out.print("페이지의 첫행: " + startRow);
	         
	         //시작페이지(startPage) : 12행은 2페이지, 22행은 3페이지
	         int startPage = startRow / pageSize + 1;
	         
	         //종료(끝) 페이지 : 전체 게시글 총 개수 ÷ 페이지당 개수
	         int totalRow = cDAO.getBoardCount();
	         int endPage = totalRow / pageSize;
	         
	         //페이지당 개수(10)로 나눠 떨어지지 않는 경우 코딩
	         endPage = (totalRow / pageSize == 0) ? endPage : endPage + 1;
	         //System.out.println("총 게시글 수: " + totalRow);
	         //System.out.println("마지막 페이지: " + endPage);
	         
	         //게시글 검색 처리
	         String _field = request.getParameter("field"); //임시로 저장 (null처리가 안되어서)
	         String _kw = request.getParameter("kw");
	         
	         String field = "";
	         String kw = "";
	         
	         //null 처리
	         if(_field != null) { //필드값이 있는 경우
	            field = _field;
	         }else { //쿼리값이 없는 경우 (검색 안했을 때) : 기본값
	            field = "title";
	         }
	         
	         if(_kw != null) { //검색어가 있는 경우
	            kw = _kw;
	         }else {
	            kw = ""; //검색창을 공백상태로 놓음
	         }
	         
	         List<Community> boardList = cDAO.getBoardList(field, kw, currentPage);
	         //모델 생성
	         request.setAttribute("boardList", boardList);
	         request.setAttribute("page", currentPage);       //현재 페이지
	         request.setAttribute("startPage", startPage);   //시작 페이지
	         request.setAttribute("endPage", endPage);      //마지막(종료) 페이지
	         request.setAttribute("field", field);         
	         request.setAttribute("kw", kw);               //검색어
	         
	         nextPage = "/community/communitylist.jsp";
	      }else if(command.equals("/writeform.do")) {
	         nextPage = "/community/writeform.jsp";
	         
	         //글쓰기 부분
	      }else if(command.equals("/write.do")) {
	         
	        String realFolder = "C:\\final\\final\\src\\main\\webapp\\upload";
	         int maxSize = 20*1024*1024;   //10MB
	         String encType = "utf-8";   //파일명 한글 인코딩
	         DefaultFileRenamePolicy policy = new DefaultFileRenamePolicy();
	         
	         //5가지 연자
	         MultipartRequest multi = new MultipartRequest (request, realFolder,
	                           maxSize, encType, policy);
	         
	         //폼 일반 속성 데이터 받기
	         String title = multi.getParameter("title");
	         String review_content = multi.getParameter("reviewContent");
	         String local_name = multi.getParameter("localName");
	         
	         //회원 가입 한사람만 글쓰기 때문에 세션 id를 받아야함
	         //세션 가져오기 (형변환 필요)
	         
	         String user_id = (String)session.getAttribute("sessionId");
	         
	         //file 객체 생성 
	         
	         Enumeration<?> files = multi.getFileNames();
	          String filename = "";
	          while(files.hasMoreElements()) { //파일명이 있는 동안 반복
	             String userFilename = (String)files.nextElement();
	               
	             //실제 저장될 이름
	             filename = multi.getFilesystemName(userFilename);
	         }
	         
	         //db에 저장
	         Community c = new Community();
	         c.setTitle(title);
	         c.setReviewContent(review_content);
	         c.setUserId(user_id);
	         c.setLocalName(local_name);
	         c.setFileName(filename);
	         
			 //write 메서드 실행
			 cDAO.write(c);
			
		}else if(command.equals("/communityview.do")) {
			//글 제목에서 요청한 글 번호 받기
			int rno = Integer.parseInt(request.getParameter("review_no"));
			
			//글 상세보기 처리
			Community community = cDAO.getBoard(rno);
			
			//댓글 목록 보기
			/* List<Reply> replyList = rDAO.getReplyList(rno); */
			
			//모델 생성해서 뷰로 보내기
			request.setAttribute("community", community);
			/* request.setAttribute("replyList", replyList); */
			nextPage = "/community/communityview.jsp";
		}else if(command.equals("/deletecommunity.do")) {
			int rno = Integer.parseInt(request.getParameter("review_no"));
			//삭제 처리
			cDAO.deletecommunity(rno);
			
			nextPage = "/communitylist.do";
			
		}else if(command.equals("/updatewirteform.do")) {
			//수정을 위해 게시글 가져오기
			int reviewNo = Integer.parseInt(request.getParameter("review_no"));
			//게시글 1건 가져오기
			Community community = cDAO.getBoard(reviewNo);
			//모델 생성
			request.setAttribute("community", community);
			
			nextPage = "/community/updatewirteform.jsp";
		}else if(command.equals("/updatewirte.do")) {
			//게시글 제목, 내용을 받아야함 : parameter사용
			int reviewNo = Integer.parseInt(request.getParameter("review_no"));
			String title = request.getParameter("title");
			String reviewContent = request.getParameter("review_content");
			String localName = request.getParameter("local_name");
			String fileName = request.getParameter("filename");
			
			//수정 처리 메서드
			Community c = new Community();
			c.setTitle(title);
			c.setReviewContent(reviewContent);
			c.setReviewNo(reviewNo);
			c.setLocalName(localName);
			c.setFileName(fileName);
			
			cDAO.updateboard(c);
			
			
			//nextPage = "/boardlist.do";
		}
		
		/*
		 * //댓글 구현 if(command.equals("/insertreply.do")) { //댓글 폼 데이터 받기 int bno =
		 * Integer.parseInt(request.getParameter("bno")); String rcontent =
		 * request.getParameter("rcontent"); String id = request.getParameter("id");
		 * 
		 * //댓글 등록 처리 Reply r = new Reply(); r.setReviewNo(bno);
		 * r.setReplyContent(rcontent); r.setUserId(id);
		 * 
		 * rDAO.insertreply(r);
		 * 
		 * }if(command.equals("/deletereply.do")) { int rno =
		 * Integer.parseInt(request.getParameter("rno")); //삭제 처리 메서드 호출
		 * rDAO.deletereply(rno); }
		 */
		//redirect와 forward 구분하기
		//새로고침하면 게시글, 댓글 중복 생성 문제 해결
		if(command.equals("/write.do") || command.equals("/updatewirte.do")) {
			response.sendRedirect("/communitylist.do");
		}else if(command.equals("/insertreply.do") || command.equals("/deletereply.do")) {
			int rno = Integer.parseInt(request.getParameter("review_no"));
		    response.sendRedirect("/communityview.do?title=" + rno);
		}else {	
		RequestDispatcher dispatch = 
				request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
		}
	}
}