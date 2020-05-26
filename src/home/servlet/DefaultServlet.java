package home.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import home.controller.Controller;
import home.controller.ControllerFactory;
import home.controller.IndexController;
import home.controller.admin.AdminDropController;
import home.controller.admin.AdminEditController;
import home.controller.admin.AdminHomeController;
import home.controller.admin.AdminSearchController;
import home.controller.admin.AdminStatisticController;
import home.controller.admin.AdminTempController;
import home.controller.admin.AdminViewController;
import home.controller.board.BoardContentController;
import home.controller.board.BoardDeleteController;
import home.controller.board.BoardEditController;
import home.controller.board.BoardListController;
import home.controller.board.BoardReply_DeleteController;
import home.controller.board.BoardReply_InsertController;
import home.controller.board.BoardWriteController;
import home.controller.event.EventHomeController;
import home.controller.member.MemberCheckController;
import home.controller.member.MemberCheck_FailController;
import home.controller.member.MemberDropController;
import home.controller.member.MemberEditController;
import home.controller.member.MemberGoodbyeController;
import home.controller.member.MemberInforController;
import home.controller.member.MemberLoginController;
import home.controller.member.MemberLogin_FailController;
import home.controller.member.MemberLogoutController;
import home.controller.member.MemberPwController;
import home.controller.member.MemberPw_FailController;
import home.controller.member.MemberPw_SuccessController;
import home.controller.member.MemberRegistController;
import home.controller.member.MemberRegist_SuccessController;

@WebServlet(urlPatterns="*.do")
public class DefaultServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//주소 분석
			String uri = request.getRequestURI();
			System.out.println("uri = "+uri);
			String context = request.getContextPath();
			System.out.println("context = "+context);
			
			String page = uri.substring(context.length());
			System.out.println("page = "+page);
			
			String next = null;
			
			//주소에 따른 처리 및 다음 페이지 지정
			Controller controller = ControllerFactory.create(page);
			if(controller != null) {
				next = controller.work(request, response);
			}
//---------------------------------------------------------------------------------------------------------------------------------------------------
			
			System.out.println("next = "+next);
			//next에 따라서 다르게 처리를 수행
			//next == null이면 이동할 페이지를 찾지 못했다는 뜻이므로 사용자에게 404 에러를 전송
			if(next == null) {
				response.sendError(404);
			}
			else if(next.startsWith("redirect:")){
//				[1]redirect:을 제거한다
				next = next.substring("redirect:".length());
//				[2]/로 시작하면 절대경로 처리(절대경로으 경우 최상위 폴더 해당)
				if(next.startsWith("/")) {
					next = request.getContextPath()+next;
				}
				response.sendRedirect(next);
				System.out.println("finish = "+next);
				System.out.println("------------------------");
			}
			else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(next);
				dispatcher.forward(request, response);
			}
		}catch(Exception e) {
			e.printStackTrace();
			response.sendError(500);
		}
		
	}
}











