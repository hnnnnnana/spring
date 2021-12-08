package chap06;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class BoardController {
	
	@Autowired
	BoardService boardService; // BoardServiceimpl이 아닌 BoardService로 가능한 이유 -> BoardService가 상위폴더여서 BoardServiceimpl도 자동형변환해서 불러와진다.
	
	@GetMapping("/board/index.do") // 같은 url이 있으면 에러 발생 -> 단 GetMapping, PostMapping으로 구분되어 있으면 에러 발생 X
	public String index(Model model, HttpServletRequest req, BoardVo vo) {
		int totCount = boardService.count(vo);
		int totPage = totCount / 10;
		if (totCount % 10 > 0) totPage++;
		System.out.println("totPage: "+totPage);
		
		int startIdx = (vo.getPage()-1) * 10;
		vo.setStartIdx(startIdx);
		
		List<BoardVo> list = boardService.selectList(vo); // list : ArrayList에 담아져 있는 객체값 대입
		model.addAttribute("list", list);
		model.addAttribute("totPage", totPage);
		return "board/index";
	}
	
	@RequestMapping("/board/write.do")
	public String write() {
		return "board/write";
	}
	
	@PostMapping("/board/insert.do")
	public String insert(BoardVo vo, HttpServletRequest req, MultipartFile file) { // MultipartFile : 사용자가 업로드한 파일을 자동으로 업로드
		// 파일저장
		if (!file.isEmpty()) { // 사용자가 파일을 첨부했다면
			try {
				String path = req.getRealPath("/upload/"); // 경로
				String filename = file.getOriginalFilename(); // 사용가자 업로드한 파일 이름
				file.transferTo(new File(path+filename)); // 경로에 파일을 저장
				vo.setFilename(filename); // 저장한 파일을 filename에 set
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		int r = boardService.insert(vo);
		
		System.out.println("r:"+r);
		
		// 정상적으로 등록되었습니다. alert띄우고 
		// index.do로 이동
		if (r > 0) {
		req.setAttribute("msg", "정상적으로 등록되었습니다.");	
		req.setAttribute("url", "index.do");
		} else {
		req.setAttribute("msg", "등록 오류");	
		req.setAttribute("url", "write.do");
		} 
		return "include/result";
	}
	@GetMapping("/board/detail.do")
	public String detail(Model model, @RequestParam int boardno) {
		model.addAttribute("data", boardService.selectOne(boardno));
		return "board/detail";
	}
	
	@GetMapping("/board/detail2.do")
	public String detail2(Model model, @RequestParam int boardno) {
		model.addAttribute("data", boardService.selectOne2(boardno));
		return "board/detail2";
	}
	
	@GetMapping("/board/edit.do")
	public String edit(Model model, @RequestParam int boardno) {
		model.addAttribute("data", boardService.selectOne(boardno)); 	
		return "board/edit";
	}
	
	@PostMapping("/board/update.do")
	public String update(Model model, BoardVo vo) {
		int r = boardService.update(vo);
		if (r > 0) {
			model.addAttribute("msg", "정상적으로 수정되었습니다.");
			model.addAttribute("url", "detail.do?boardno="+vo.getBoardno()); // 성공했을때 상세페이지로 이동
		} else {
			model.addAttribute("msg", "수정 오류");
			model.addAttribute("url", "edit.do?boardno="+vo.getBoardno()); // 실패했을때 상세페이지로 이동
		} 
		return "include/result";
	}
	
	@GetMapping("/board/delete.do")
	public String delete(Model model, BoardVo vo) {
		int r = boardService.delete(vo);
		if (r > 0) {
			model.addAttribute("msg", "정상적으로 삭제되었습니다.");
			model.addAttribute("url", "index.do"); // 성공했을때 상세페이지로 이동
		} else {
			model.addAttribute("msg", "수정 오류");
			model.addAttribute("url", "detail.do?boardno="+vo.getBoardno()); // 실패했을때 상세페이지로 이동
		} 
		return "include/result";
	}
}
