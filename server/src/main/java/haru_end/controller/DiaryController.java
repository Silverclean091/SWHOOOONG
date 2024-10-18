package haru_end.controller;

import haru_end.dto.DiaryDTO;
import haru_end.service.DiaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/diary")
public class DiaryController {

    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @PostMapping
    public ResponseEntity<String> saveDiary(@RequestBody DiaryDTO diaryDTO) {
        diaryService.saveDiary(diaryDTO);
        return ResponseEntity.ok("Diary saved successfully.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiaryDTO> getDiaryById(@PathVariable Long id) {
        DiaryDTO diaryDTO = diaryService.findDiaryById(id);
        if (diaryDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(diaryDTO);
    }

    @GetMapping
    public ResponseEntity<List<DiaryDTO>> getAllDiaries() {
        List<DiaryDTO> diaryDTOList = diaryService.findAllDiaries();
        return ResponseEntity.ok(diaryDTOList);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateDiaryById(@PathVariable Long id, @RequestBody DiaryDTO diaryDTO) {
        diaryService.updateDiaryById(id, diaryDTO);
        return ResponseEntity.ok("Diary updated successfully.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDiaryById(@PathVariable Long id) {
        diaryService.deleteDiaryById(id);
        return ResponseEntity.ok("Diary deleted successfully.");
    }
}
