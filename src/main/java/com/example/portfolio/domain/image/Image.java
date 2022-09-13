package com.example.portfolio.domain.image;

import com.example.portfolio.domain.user.User;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder(builderMethodName = "hiddenBuilder")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;

    private String originalFileName;

    private String extension;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user.id")
    private User user;

    public static ImageBuilder builder(
        String fileName, String originalFileName, String extension
    ){
        return hiddenBuilder()
            .fileName(fileName)
            .originalFileName(originalFileName)
            .extension(extension);
    }
}
