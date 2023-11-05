package msds.homefarming.domain.dto.memberPlantDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GetPlantsResponseDto
{
    private Integer count;
    private String username;
    private List<RegisterPlantResponseDto> plantList = new ArrayList<>();
}
