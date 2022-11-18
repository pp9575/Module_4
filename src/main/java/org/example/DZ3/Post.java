package org.example.DZ3;

import lombok.*;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name = "\"post\"")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Post {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    @NonNull
    private String text;

    @Column
    private Calendar created_at = Calendar.getInstance();


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @NonNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User user;

    @OneToMany(mappedBy = "post")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Comment> comments;

}
