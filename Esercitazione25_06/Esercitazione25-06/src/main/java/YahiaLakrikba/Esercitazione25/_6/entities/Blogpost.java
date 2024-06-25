package YahiaLakrikba.Esercitazione25._6.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Blogpost {
    private int id;
    private String category;
    private String title;
    private String cover;
    private String content;
    private double readingTime;
}
