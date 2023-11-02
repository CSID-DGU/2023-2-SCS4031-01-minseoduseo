package msds.homefarming.auth.naver.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NaverMemberDto
{
    @JsonProperty("response")
    private NaverProperties properties;

    public String getId()
    {
        return this.properties.getId();
    }

    public String getNickname()
    {
        return this.properties.getNickname();
    }

    public String getRealName()
    {
        return this.properties.getRealName();
    }

    public String getProfileImage()
    {
        return this.properties.getProfileImage();
    }
}

@Getter
@Setter
class NaverProperties
{
    @JsonProperty("id")
    private String id;

    @JsonProperty("nickname")
    private String nickname;

    @JsonProperty("name")
    private String realName;

    @JsonProperty("profile_image")
    private String profileImage;
}
