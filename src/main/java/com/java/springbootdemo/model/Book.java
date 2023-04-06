package com.java.springbootdemo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name="BookTB")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="bid")
    int bookId;
    @NotBlank(message = "{book.name.blank}")
    String bookName;

    @Size(min = 3,message = "{author.name.size}")
    @Pattern(regexp="[A-Za-z]+( [A-Za-z]+)*", message="{author.name.content}")
            //(Male|Female|T)
    String authorName;
    @Enumerated(EnumType.STRING)
    Rating bookRating;

}
