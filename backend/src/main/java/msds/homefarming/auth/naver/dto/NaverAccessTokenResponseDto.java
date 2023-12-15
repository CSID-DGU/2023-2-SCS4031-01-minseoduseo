package msds.homefarming.auth.naver.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NaverAccessTokenResponseDto
{
    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("refresh_token")
    private String refreshToken;

    @JsonProperty("token_type")
    private String tokenType;

    @JsonProperty("expires_in")
    private Integer expiresIn;

    @JsonProperty("error")
    private String error;

    @JsonProperty("error_description")
    private String errorDescription;

    @Override
    public String toString()
    {
        return "NaverAccessTokenResponseDto{" +
                "accessToken='" + accessToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", tokenType='" + tokenType + '\'' +
                ", expiresIn=" + expiresIn +
                ", error='" + error + '\'' +
                ", errorDescription='" + errorDescription + '\'' +
                '}';
    }
}
