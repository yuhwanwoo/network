package kr.multi.bigdataShop.product.comment;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("productCommentDao")
public class ProductCommentDAOImpl implements ProductCommentDAO {
	@Autowired
	SqlSession sqlSession;

	@Override
	public List<ProductCommentDTO> getCommentList(String prd_no) {
		List<ProductCommentDTO> commentList
			= sqlSession.selectList("kr.multi.bigdataShop.product.comment.selectComment", prd_no);
		return commentList;
	}

	@Override
	public int addComment(ProductCommentDTO comment) {
		int res = sqlSession.insert("kr.multi.bigdataShop.product.comment.insertComment", comment);
		return res;
	}

	@Override
	public List<CommentResultDTO> getCommentResult() {
		List<CommentResultDTO> cResultList = sqlSession.selectList("kr.multi.bigdataShop.product.comment.selectResult");
		return cResultList;
	}

}













