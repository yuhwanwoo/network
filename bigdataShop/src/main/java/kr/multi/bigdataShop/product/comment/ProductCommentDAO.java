package kr.multi.bigdataShop.product.comment;

import java.util.List;

public interface ProductCommentDAO {
	List<ProductCommentDTO> getCommentList (String prd_no);
	int addComment(ProductCommentDTO comment);
	List<CommentResultDTO> getCommentResult();
}