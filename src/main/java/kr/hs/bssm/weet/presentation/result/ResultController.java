package kr.hs.bssm.weet.presentation.result;

import kr.hs.bssm.weet.application.result.ResultService;
import kr.hs.bssm.weet.domain.result.Result;
import kr.hs.bssm.weet.global.annotation.LoginRequired;
import kr.hs.bssm.weet.global.annotation.TeacherOnly;
import kr.hs.bssm.weet.presentation.result.dto.ResultCreateRequestDto;
import kr.hs.bssm.weet.presentation.result.dto.ResultUpdateRequestDto;
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

    @LoginRequired
    @GetMapping("/form/{formId}")
    public Result findByForm(@PathVariable Long formId) {
        return resultService.findByForm(formId);
    }

    @TeacherOnly
    @PostMapping
    public Long create(@RequestBody ResultCreateRequestDto dto) {
        return resultService.create(dto);
    }

    @TeacherOnly
    @PutMapping("/{id}")
    public Long update(@PathVariable Long id,
                       @RequestBody ResultUpdateRequestDto dto) {
        return resultService.update(id, dto);
    }
}
