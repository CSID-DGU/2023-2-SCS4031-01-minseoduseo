package msds.homefarming.domain.dto.PlantDictionaryDto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class SaveDictionaryRequestDto
{
    private String image;
    private String name;
    private String studyName;
    private String feature;
}
