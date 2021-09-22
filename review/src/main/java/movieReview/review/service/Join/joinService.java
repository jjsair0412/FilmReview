package movieReview.review.service.Join;

import movieReview.review.Domain.mangerInfo;
import movieReview.review.Domain.userInfo;

public interface joinService {
    int join(String email, String id, int password, Integer mangerNum); //회원가입
    userInfo myInfo(String id); //내정보 조회
    mangerInfo mangerInfo(String id); // 관리자정보 조회
    int update(String id, int password); //사용자 비밀번호 수정
    int mangerUpdate(String id, int password);
    int delete(String id); //유저 회원탈퇴
    int deleteManger(String id); // 관리자 회원탈퇴
}