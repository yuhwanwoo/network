package kr.multi.bigdataShop.product.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
@Service
public class ProductCommentServiceImpl implements ProductCommentService {
	@Autowired
	@Qualifier("productCommentDao")
	ProductCommentDAO dao;

	@Override
	public List<ProductCommentDTO> getCommentList(String prd_no) {
		List<ProductCommentDTO> commentList = dao.getCommentList(prd_no);
		return commentList;
	}

	@Override
	public int addComment(ProductCommentDTO comment) {
		int res = dao.addComment(comment);
		return res;
	}

	@Override
	public List<CommentResultDTO> getCommentResult() {
		List<CommentResultDTO> cResultList = dao.getCommentResult();
		return cResultList;
	}

}











