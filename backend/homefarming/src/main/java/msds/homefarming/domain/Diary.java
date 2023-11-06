package msds.homefarming.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.Month;

@Getter
@Setter
@Entity
public class Diary
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diary_id")
    private Long id;

    private LocalDateTime createDate;

    @JsonIgnore
    private int createYear;
    @JsonIgnore
    private int createMonth;
    @JsonIgnore
    private int createDay;

    private LocalDateTime modifyDate;
    private String title;
    private String plantName;
    private String contents;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member author;

    protected Diary(String title, String plantName,LocalDateTime createDate, String contents)
    {
        this.title = title;
        this.plantName = plantName;

        this.createDate = createDate;
        this.createYear = createDate.getYear();
        this.createMonth = createDate.getMonthValue();
        this.createDay = createDate.getDayOfMonth();

        this.contents = contents;
    }

    protected Diary()
    {
    }

    public static Diary create(String title, String plantName, LocalDateTime createDate, String contents)
    {
        return new Diary(title,plantName, createDate, contents);
    }

    public Diary update(String title,String plantName, LocalDateTime modifyDate, String contents)
    {
        this.title = title;
        this.plantName = plantName;
        this.modifyDate = modifyDate;
        this.contents = contents;
        return this;
    }
}