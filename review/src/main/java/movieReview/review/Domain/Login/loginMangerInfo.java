package movieReview.review.Domain.Login;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class loginMangerInfo {
    private String id;

    private String password;
}
