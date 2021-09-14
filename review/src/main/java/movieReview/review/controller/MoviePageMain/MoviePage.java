package movieReview.review.controller.MoviePageMain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movieReview.review.Session.SessionConst;
import movieReview.review.dto.FileInfo.photoUriInfo;
import movieReview.review.dto.MovieInfo.JpaMovieInfo;
import movieReview.review.dto.MovieInfo.movieInfo;
import movieReview.review.dto.ReviewInfo.ReviewInfo;
import movieReview.review.service.GetMovieInfo.getMovieInfoService;
import movieReview.review.service.Upload.UploadService;
import movieReview.review.service.reviewFunction.reviewUploadServie.ReviewUploadService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/oneMovie")
public class MoviePage {
    private final UploadService uploadService;
    private final getMovieInfoService getMovieInfoService;
    private final photoUriInfo photoUriinfo;
    private final ReviewUploadService reviewUploadService;

    @GetMapping
    public String goMovie(Model model){
        model.addAttribute("movieInfo",new movieInfo());
        return "MoviePage/MoviePageMain";
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/viewOneMovie")
    @ResponseBody
    public List<JpaMovieInfo> MovieView(movieInfo movieinfo, HttpServletRequest request){
        List<JpaMovieInfo> jpaMovieInfos = new ArrayList<>();
        List<JpaMovieInfo> uri = new ArrayList<>();
        List<JpaMovieInfo> movie = getMovieInfoService.getMovie(movieinfo);
        for(int i = 0; i<movie.size(); i++){
            uri.add(makeUri(movie.get(i)));
            jpaMovieInfos.add(uri.get(i));
        }

        return jpaMovieInfos;
    }

    @GetMapping("/reviewUpload")
    @ResponseBody
    public int reviewUpload(@SessionAttribute(value = SessionConst.LoginId, required = false) String id,ReviewInfo reviewInfo,HttpServletRequest request){


        reviewInfo.setPhotoOriName(photoOriName.toString());
        reviewInfo.setMovieReivew(reviewInfo.getMovieReivew());
        reviewInfo.setMoviePoint(reviewInfo.getMoviePoint());
        reviewInfo.setReviewUser(id);

        int result = reviewUploadService.reviewUpload(reviewInfo);
        return result;
    }

    public JpaMovieInfo makeUri(JpaMovieInfo movie){
        photoUriinfo.setPhotoUri(movie.getPhotoUri());
        photoUriinfo.setPhotoOriName(movie.getPhotoOriName());

        photoUriInfo photoUriInfo = uploadService.showPhoto(photoUriinfo);
        ClassPathResource resource = new ClassPathResource("/moviePhoto/"+photoUriInfo.getPhotoOriName());

        Path path1 = Paths.get(resource.getPath());

        log.info("path ={}",path1);
        movie.setPhotoUri(path1.toString());
        return movie;
    }
}
