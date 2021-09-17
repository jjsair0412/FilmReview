package movieReview.review.repository.reviewFunction.FindMovie;

import movieReview.review.dto.ReviewInfo.JpaRevieTab;
import movieReview.review.dto.ReviewInfo.ReviewInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;


@Component
public class ReviewFunctionRepositoryImpl implements ReviewFunctionRepository {

    private final JdbcTemplate template;
    private final EntityManager em;

    @Autowired
    public ReviewFunctionRepositoryImpl(JdbcTemplate template, EntityManager em) {
        this.template = template;
        this.em = em;
    }

    @Override
    public int insertReview(ReviewInfo reviewinfo) {
        String sql = "Update reviewTab set moviePoint = ? , movieReview =?, reviewUser=? where photoOriName = ?";
        return template.update(sql, reviewinfo.getMoviePoint(), reviewinfo.getMovieReivew(), reviewinfo.getReviewUser(), reviewinfo.getPhotoOriName());
    }

    @Override
    public int deleteReview(ReviewInfo reviewInfo) {
        String sql = "delete from reviewTab where reviewUser = ?";
        return template.update(sql,reviewInfo.getReviewUser());
    }

    @Override
    public List<JpaRevieTab> selectReview(ReviewInfo reviewInfo) {
        return em.createQuery("select m from JpaRevieTab m where m.photoOriName= :photoOriName", JpaRevieTab.class)
                .setParameter("photoOriName", reviewInfo.getPhotoOriName())
                .getResultList();
    }
}
