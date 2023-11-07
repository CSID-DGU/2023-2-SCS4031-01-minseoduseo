package msds.homefarming.api;

import lombok.RequiredArgsConstructor;
import msds.homefarming.domain.dto.PlantDictionaryDto.*;
import msds.homefarming.service.DictionaryPlantService;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class DictionaryController
{
    private final DictionaryPlantService dictionaryPlantService;

    //==도감식물 등록==//
    @PostMapping("/api/dictionary")
    public SaveDictionaryResponseDto register(@RequestBody SaveDictionaryRequestDto requestDto)
    {
        return dictionaryPlantService.register(requestDto);
    }

    //==도감식물 모두 조회==//
    @GetMapping("/api/dictionary")
    public FindAllDictionaryResponseDto findAll()
    {
        return dictionaryPlantService.findAll();
    }

    //==도감식물 조회(아이디)==//
    @GetMapping("/api/dictionary/id")
    public FindOneDictionaryResponseDto findOneById(@RequestParam("id") Long dictionaryId)
    {
        return dictionaryPlantService.findById(dictionaryId);
    }

    //==도감식물 조회(식물명)==//
    @GetMapping("/api/dictionary/name")
    public FindOneDictionaryResponseDto findOneByName(@RequestParam("name") String plantName)
    {
        return dictionaryPlantService.findByName(plantName);
    }

    //==도감식물 삭제(아이디)==//
    @DeleteMapping("/api/dictionary/id")
    public DeleteDictionaryResponseDto deleteById(@RequestParam("id") Long dictionaryId)
    {
        return dictionaryPlantService.delete(dictionaryId);
    }
}
