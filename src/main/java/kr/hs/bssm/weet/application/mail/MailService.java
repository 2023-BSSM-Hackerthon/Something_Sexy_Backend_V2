package kr.hs.bssm.weet.application.mail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import kr.hs.bssm.weet.application.user.UserService;
import kr.hs.bssm.weet.domain.form.Form;
import kr.hs.bssm.weet.domain.user.User;
import kr.hs.bssm.weet.global.error.exception.ErrorCode;
import kr.hs.bssm.weet.global.error.exception.WeetException;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;
    private final UserService userService;

    // 학생이 상담 신청 했을 때
    public void toTeacher(User student, User teacher) {
        sender(
                teacher.getEmail(),
                student.getGrade() + "학년 " + student.getClassNo() + "반 " + student.getNumber() + "번 " + student.getName() + "님이 상담을 신청했습니다.",
                "http://localhost:3000/applist\n\n위 링크에서 상세 내용을 확인하세요!"
        );
    }

    // 선생님이 거절 했을 때
    public void toStudentWhenReject(Form form, String reason) {
        String studentEmail = userService.findById(form.getUserId()).getEmail();
        sender(
                studentEmail,
                "신청이 거절되었습니다.",
                "- 상담 내용\n" + form.getTitle() + "\n\n- 거절 사유\n" + reason
        );
    }

    // 선생님이 수락 했을 때
    public void toStudentWhenApprove(Form form, Date decidedDate) {
        String studentEmail = userService.findById(form.getUserId()).getEmail();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분");
        String formattedDate = formatter.format(decidedDate);
        sender(
                studentEmail,
                "상담 신청이 승인되었습니다.",
                "- 승인된 상담 내용\n" + form.getTitle() + "\n\n" +
                        "신청한 날짜인 " + formattedDate + "까지 위클래스에 방문해주세요!"
        );
    }

    private void sender(String email, String title, String content) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "utf-8");
            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setFrom("weet.team14@gmail.com");
            mimeMessageHelper.setSubject(title);
            mimeMessageHelper.setText(content);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new WeetException(ErrorCode.EMAIL_SEND_ERROR);
        }
    }
}
