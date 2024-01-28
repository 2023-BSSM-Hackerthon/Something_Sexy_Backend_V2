package kr.hs.bssm.weet.domain.result.repository;

import kr.hs.bssm.weet.domain.result.Result;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepository extends JpaRepository<Result, Long> {
    Result findByFormId(Long formId);
}
