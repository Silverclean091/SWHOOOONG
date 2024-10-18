package haru_end.service;

import haru_end.dto.DiaryDTO;
import haru_end.entity.DiaryEntity;
import haru_end.repository.DiaryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiaryService {

    private final DiaryRepository diaryRepository;

    // 의존성 주입
    public DiaryService(DiaryRepository diaryRepository) {
        this.diaryRepository = diaryRepository;
    }

    // POST: 다이어리 저장
    public void saveDiary(DiaryDTO diaryDTO) {
        DiaryEntity diaryEntity = mapToEntity(diaryDTO);
        diaryRepository.save(diaryEntity);
    }

    // GET: ID로 다이어리 조회
    public DiaryDTO findDiaryById(Long id) {
        DiaryEntity diaryEntity = diaryRepository.findById(id);
        if (diaryEntity == null) {
            return null; // 또는 예외 처리 (예: throw new ResourceNotFoundException("Diary not found"));
        }
        return mapToDTO(diaryEntity);
    }

    // GET: 모든 다이어리 조회
    public List<DiaryDTO> findAllDiaries() {
        return diaryRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    // PATCH: 특정 ID로 다이어리 업데이트
    public void updateDiaryById(Long id, DiaryDTO diaryDTO) {
        DiaryEntity existingDiary = diaryRepository.findById(id);
        if (existingDiary != null) {
            // 필요한 필드만 업데이트
            if (diaryDTO.getDiary_image() != 0) {
                existingDiary.setDiary_image(diaryDTO.getDiary_image());
            }
            if (diaryDTO.getToday_feeling() != null) {
                existingDiary.setToday_feeling(diaryDTO.getToday_feeling());
            }
            if (diaryDTO.getIs_open() != 0) {
                existingDiary.setIs_open(diaryDTO.getIs_open());
            }
            if (diaryDTO.getDiary_date() != null) {
                existingDiary.setDiary_date(diaryDTO.getDiary_date());
            }
            if (diaryDTO.getContext() != null) {
                existingDiary.setContext(diaryDTO.getContext());
            }
            if (diaryDTO.getUser_email() != null) {
                existingDiary.setUser_email(diaryDTO.getUser_email());
            }
            diaryRepository.updateById(id, existingDiary); // 업데이트 메서드 호출
        }
    }

    // DELETE: 특정 ID로 다이어리 삭제
    public void deleteDiaryById(Long id) {
        diaryRepository.softDeleteById(id);
    }

    // DiaryDTO -> DiaryEntity로 변환
    private DiaryEntity mapToEntity(DiaryDTO diaryDTO) {
        DiaryEntity diaryEntity = new DiaryEntity();
        diaryEntity.setDiary_image(diaryDTO.getDiary_image());
        diaryEntity.setToday_feeling(diaryDTO.getToday_feeling());
        diaryEntity.setIs_open(diaryDTO.getIs_open());
        diaryEntity.setDiary_date(diaryDTO.getDiary_date());
        diaryEntity.setContext(diaryDTO.getContext());
        diaryEntity.setUser_email(diaryDTO.getUser_email());
        return diaryEntity;
    }

    // DiaryEntity -> DiaryDTO로 변환
    private DiaryDTO mapToDTO(DiaryEntity diaryEntity) {
        DiaryDTO diaryDTO = new DiaryDTO();
        diaryDTO.setId(diaryEntity.getId());
        diaryDTO.setDiary_image(diaryEntity.getDiary_image());
        diaryDTO.setToday_feeling(diaryEntity.getToday_feeling());
        diaryDTO.setIs_open(diaryEntity.getIs_open());
        diaryDTO.setDiary_date(diaryEntity.getDiary_date());
        diaryDTO.setContext(diaryEntity.getContext());
        diaryDTO.setUser_email(diaryEntity.getUser_email());
        return diaryDTO;
    }
}
