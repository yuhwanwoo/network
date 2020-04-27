package kr.multi.bigdataShop.product.comment;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class ProductCommentController {
	@Autowired
	ProductCommentService service;
	
	//댓글 작성
	@RequestMapping("/comment/write.do")
	public String addComment(ProductCommentDTO comment) {
		int res = service.addComment(comment);
		
		return "redirect:/product/read.do?prd_no=" + comment.getPrd_no();
	}
	
	//wordcount 결과 출력
	@RequestMapping("/comment/result")
	public String getCommentResult(Model model,HttpServletRequest req) {
		List<CommentResultDTO> commentResult = service.getCommentResult();
		model.addAttribute("commentResult", commentResult);
		
		return "comment/result";
	}
}
