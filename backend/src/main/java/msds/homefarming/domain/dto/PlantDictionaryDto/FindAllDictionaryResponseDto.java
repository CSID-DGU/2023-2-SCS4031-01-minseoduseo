package msds.homefarming.domain.dto.PlantDictionaryDto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class FindAllDictionaryResponseDto
{
    private int count;
    private List<FindOneDictionaryResponseDto> list;
}
