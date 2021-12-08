package chap06;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDao {
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate; // @Autowired가 없으면 sqlSessionTemplate에 자동주입이 안되서 null이 된다.
	
	public int count(BoardVo vo) {
		return sqlSessionTemplate.selectOne("board.count", vo);
	}
	
	public List<BoardVo> selectList(BoardVo vo) {
		return sqlSessionTemplate.selectList("board.selectList", vo);  //selectList("board.selectList") -> board. -> board.xml에 있는 namespace 이름이다.
	}
	
	public int insert(BoardVo vo) {
//		return sqlSessionTemplate.insert("board.insert", vo);
		int r = 0;
		try {
			r = sqlSessionTemplate.insert("board.insert", vo);
		} catch (Exception e) {
			r = 0;
			System.out.println(e.getMessage());
		}
		return r;
	}
	
	/*
	 * 메서드 생성
	 * sqlSessionTemplate.selectOne("board.selectOne", PK)
	 */
	
	public BoardVo selectOne(int boardno) {
		return sqlSessionTemplate.selectOne("board.selectOne", boardno);
	}
	
	public BoardVo2 selectOne2(int boardno) {
		return sqlSessionTemplate.selectOne("board.selectOne2", boardno);
	}
	
	public int update(BoardVo vo) {
		return sqlSessionTemplate.update("board.update", vo);
	}
	
	public int delete(BoardVo vo) {
		return sqlSessionTemplate.delete("board.delete", vo.getBoardno());
	}
}
