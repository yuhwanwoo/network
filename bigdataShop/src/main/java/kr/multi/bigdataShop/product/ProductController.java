package kr.multi.bigdataShop.product;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.multi.bigdataShop.product.comment.ProductCommentDTO;
import kr.multi.bigdataShop.product.comment.ProductCommentService;

@Controller
public class ProductController {
	@Autowired
	ProductService service;
	@Autowired
	ProductCommentService cService;

	// 전체상품보기,카테고리별 보기
	@RequestMapping("/product/list.do")
	public ModelAndView list(String category) {
		System.out.println("category => " + category);
		ModelAndView mav = new ModelAndView();

		List<ProductDTO> list = service.productlist(category);

		mav.addObject("prdlist", list);// db에서 조회한 결과 - 서블릿에서 request.setAttribute
		mav.setViewName("product/list");

		return mav;
	}

	// 일반 메소드 리턴하는 것처럼 List<BoardDTO>를 리턴하면서
	// @ResponseBody로 설정하면 jackson라이브러리가 자동으로 json객체로 변환
	@RequestMapping(value = "/product/show_json", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public @ResponseBody List<ProductDTO> categoryboardlist(String category) {
		String result = "";
		List<ProductDTO> prdlist = service.productlist(category);
		System.out.println("ajax통신" + prdlist);
		return prdlist;
	}

	// 상품상세보기
	@RequestMapping("/product/read.do")
	public String showProduct(String prd_no, String category, Model model, HttpServletRequest req) {
		ProductDTO product = service.read(prd_no);
		model.addAttribute("product", product);

		List<ProductCommentDTO> commentlist = cService.getCommentList(prd_no);
		model.addAttribute("commentlist", commentlist);

		return "product/read";
	}
}
