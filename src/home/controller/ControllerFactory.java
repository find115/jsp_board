package home.controller;

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

/**
 *	컨트롤러 생성 공장. 주소에 따라 컨트롤러를 생성하여 반환 
 */
public class ControllerFactory {
	
	public static Controller create(String url) {
		
		switch(url) {
		case "/index.do": 					return new IndexController();
		
		case "/test.do" :						return new TestController();
		
		case "/event/home.do": 		return new EventHomeController();
		
		case "/member/regist.do":	return new MemberRegistController();
		case "/member/regist_success.do": return new MemberRegist_SuccessController();
		case "/member/login.do":		return new MemberLoginController();
		case "/member/login_fail.do": return new MemberLogin_FailController();
		case "/member/logout.do": 	return new MemberLogoutController();
		case "/member/information.do": return new MemberInforController();
		case "/member/pw.do": return new MemberPwController();
		case "/member/pw_success.do": return new MemberPw_SuccessController();
		case "/member/pw_fail.do": return new MemberPw_FailController();
		case "/member/check.do": return new MemberCheckController();
		case "/member/check_fail.do": return new MemberCheck_FailController();
		case "/member/edit.do": return new MemberEditController();
		case "/member/drop.do": return new MemberDropController();
		case "/member/goodbye.do": return new MemberGoodbyeController();

		case "/admin/home.do": return new AdminHomeController();
		case "/admin/search.do": return new AdminSearchController();
		case "/admin/statistic.do": return new AdminStatisticController();
		case "/admin/view.do":	return new AdminViewController();
		case "/admin/edit.do": return new AdminEditController();
		case "/admin/drop.do":	return new AdminDropController();
		case "/admin/temp.do": return new AdminTempController();
			
		case "/board/list.do": return new BoardListController();
		case "/board/write.do": return new BoardWriteController();
		case "/board/content.do": return new BoardContentController();
		case "/board/delete.do": return new BoardDeleteController();
		case "/board/edit.do": return new BoardEditController();
		
		case "/board/reply_insert.do": return new BoardReply_InsertController();
		case "/board/reply_delete.do": return new BoardReply_DeleteController();
		default:	return null;
		}
		
	}
	
}
