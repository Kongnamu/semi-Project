package reply;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import njdbc.JDBCUtil;

//댓글을 추가, 수정, 삭제, 검색하는 클래스
public class ReplyDAO {
	Connection conn = null;         //db 연결 및 종료
	PreparedStatement pstmt = null; //sql 처리
	ResultSet rs = null;            //검색한 데이터셋
	
	//댓글 목록
	public List<Reply> getReplyList(int reply_no){
		List<Reply> replyList = new ArrayList<>();
		try {
			conn = JDBCUtil.getConnection();
			String sql = "SELECT * FROM reply WHERE reply_no = ? "
					+ "ORDER BY review_date";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reply_no);
			//sql 처리
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Reply r = new Reply();
				r.setReplyNo(rs.getInt("reply_no"));
				r.setReplyContent(rs.getString("reply_content"));
				r.setReplyDate(rs.getTimestamp("reply_date"));
				r.setReviewDate(rs.getTimestamp("review_date"));
				r.setUserId(rs.getString("user_id"));
				r.setReviewNo(rs.getInt("review_no"));
				
				replyList.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		return replyList;
	}
	
	//댓글 등록
	public void insertreply(Reply r) {
		try {
			conn = JDBCUtil.getConnection();
			String sql = "INSERT INTO reply(reply_no,reply_content, user_id) "
					+ "VALUES (seq_reply_no.NEXTVAL, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, r.getReplyNo());
			pstmt.setString(2, r.getReplyContent());
			pstmt.setString(3, r.getUserId());
			//sql 처리
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt);
		}
	}
	
	//댓글 삭제 - 댓글 번호로 검색
			public void deletereply(int replyNo) {
				try {
					conn = JDBCUtil.getConnection();
					String sql = "DELETE FROM reply WHERE reply_no = ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, replyNo);
					//sql 처리
					pstmt.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					JDBCUtil.close(conn, pstmt);
				}
			}
			
	
	
}//replyDAO 닫기