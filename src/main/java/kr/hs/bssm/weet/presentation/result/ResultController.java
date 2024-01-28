package kr.hs.bssm.weet.presentation.result;

import kr.hs.bssm.weet.application.result.ResultService;
import kr.hs.bssm.weet.global.annotation.TeacherOnly;
import kr.hs.bssm.weet.presentation.result.dto.ResultCreateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/result")
@RequiredArgsConstructor
public class ResultController {

    private final ResultService resultService;

    @TeacherOnly
    @PostMapping
    public Long create(@RequestBody ResultCreateRequestDto dto) {
        return resultService.create(dto);
    }
}
