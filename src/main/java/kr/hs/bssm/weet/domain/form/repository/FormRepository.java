package kr.hs.bssm.weet.domain.form.repository;

import kr.hs.bssm.weet.domain.form.Form;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FormRepository extends JpaRepository<Form, Long> {
    List<Form> findByUserId(Long userId);

    List<Form> findAllOrderByIdDesc();

    List<Form> findByIsAccepted(boolean isAccepted);
}
