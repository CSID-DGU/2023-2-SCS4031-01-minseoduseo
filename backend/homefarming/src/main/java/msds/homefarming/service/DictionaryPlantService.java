package msds.homefarming.service;

import lombok.RequiredArgsConstructor;
import msds.homefarming.domain.DictionaryPlant;
import msds.homefarming.domain.dto.PlantDictionaryDto.*;
import msds.homefarming.exception.AlreadyExistDictionaryException;
import msds.homefarming.exception.NoExistDictionaryException;
import msds.homefarming.repository.DictionaryPlantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class DictionaryPlantService
{
    private final DictionaryPlantRepository dictionaryPlantRepository;

    //==도감식물 등록==//
    @Transactional
    public SaveDictionaryResponseDto register(SaveDictionaryRequestDto requestDto)
    {
        validateDuplicationPlantName(requestDto.getName());

        DictionaryPlant createPlant = DictionaryPlant.create(
                requestDto.getImage(),
                requestDto.getName(),
                requestDto.getStudyName(),
                requestDto.getFeature());
        DictionaryPlant plant = dictionaryPlantRepository.save(createPlant);
        return new SaveDictionaryResponseDto(
                plant.getId(),
                plant.getImage(),
                plant.getName(),
                plant.getStudyName(),
                plant.getFeature());
    }

    //==도감식물 이름 중복 검사==//
    private void validateDuplicationPlantName(String plantName)
    {
        DictionaryPlant plant = dictionaryPlantRepository.findByName(plantName);
        if (plant != null)
        {
            throw new AlreadyExistDictionaryException("이미 존재하는 식물입니다. 다른 식물을 등록해 주세요.");
        }
    }

    //==도감식물 모두 조회==//
    public FindAllDictionaryResponseDto findAll()
    {
        List<DictionaryPlant> plants = dictionaryPlantRepository.findAll();
        List<FindOneDictionaryResponseDto> list = new ArrayList<>();
        int count = 0;
        for (DictionaryPlant plant : plants)
        {
            list.add(new FindOneDictionaryResponseDto(
                    plant.getId(),
                    plant.getImage(),
                    plant.getName(),
                    plant.getStudyName(),
                    plant.getFeature()));
            count++;
        }
        return new FindAllDictionaryResponseDto(count, list);
    }

    //==도감식물 조회(아이디)==//
    public FindOneDictionaryResponseDto findById(Long dictionaryId)
    {
        DictionaryPlant plant = dictionaryPlantRepository.findById(dictionaryId);
        if (plant == null)
        {
            throw new NoExistDictionaryException("존재하지 않는 식물입니다.");
        }
        return new FindOneDictionaryResponseDto(
                plant.getId(),
                plant.getImage(),
                plant.getName(),
                plant.getStudyName(),
                plant.getFeature());
    }

    //==도감식물 조회(식물명)==//
    public FindOneDictionaryResponseDto findByName(String plantName)
    {
        DictionaryPlant plant = dictionaryPlantRepository.findByName(plantName);
        if (plant == null)
        {
            throw new NoExistDictionaryException("존재하지 않는 식물입니다.");
        }
        return new FindOneDictionaryResponseDto(
                plant.getId(),
                plant.getImage(),
                plant.getName(),
                plant.getStudyName(),
                plant.getFeature());
    }

    //==도감식물 삭제(아이디)==//
    @Transactional
    public DeleteDictionaryResponseDto delete(Long plantId)
    {
        DictionaryPlant plant = dictionaryPlantRepository.findById(plantId);
        if (plant == null)
        {
            throw new NoExistDictionaryException("존재하지 않는 식물입니다.");
        }
        Boolean status = dictionaryPlantRepository.deleteById(plantId);
        return new DeleteDictionaryResponseDto(status, "삭제가 완료되었습니다.");
    }
}
