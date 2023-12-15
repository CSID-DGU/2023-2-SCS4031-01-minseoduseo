package msds.homefarming.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class DictionaryPlant
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dictionary_id")
    private Long id;

    private String image;
    private String name;
    private String studyName;
    private String feature;

    protected DictionaryPlant()
    {
    }

    protected DictionaryPlant(String image, String name, String studyName, String feature)
    {
        this.image = image;
        this.name = name;
        this.studyName = studyName;
        this.feature = feature;
    }

    public static DictionaryPlant create(String image, String name, String studyName, String feature)
    {
        return new DictionaryPlant(image, name, studyName, feature);
    }
}
