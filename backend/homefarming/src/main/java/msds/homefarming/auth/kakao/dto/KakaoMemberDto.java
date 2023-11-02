package msds.homefarming.auth.kakao.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KakaoMemberDto
{
    @JsonProperty("id")
    private Long id;

    @JsonProperty("connected_at")
    private String connectedAt;

    @JsonProperty("properties")
    private KakaoProperties properties;

    public String getNickname()
    {
        return this.properties.getNickname();
    }

    public String getProfileImage()
    {
        return this.properties.getProfileImage();
    }

    public String getThumbnailImage()
    {
        return this.properties.getThumbnailImage();
    }
}

@Getter
@Setter
class KakaoProperties
{
    @JsonProperty("nickname")
    private String nickname;

    @JsonProperty("profile_image")
    private String profileImage;

    @JsonProperty("thumbnail_image")
    private String thumbnailImage;
}

