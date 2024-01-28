package kr.hs.bssm.weet.presentation.result;

import kr.hs.bssm.weet.application.result.ResultService;
import kr.hs.bssm.weet.domain.result.Result;
import kr.hs.bssm.weet.global.annotation.TeacherOnly;
import kr.hs.bssm.weet.presentation.result.dto.ResultCreateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/result")
@RequiredArgsConstructor
public class ResultController {

    private final ResultService resultService;

    @TeacherOnly
    @GetMapping
    public List<Result> findAll() {
        return resultService.findAll();
    }

    @TeacherOnly
    @PostMapping
    public Long create(@RequestBody ResultCreateRequestDto dto) {
        return resultService.create(dto);
    }
}
