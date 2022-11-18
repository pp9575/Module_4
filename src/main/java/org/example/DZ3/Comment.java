package org.example.DZ3;

import lombok.*;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "\"comment\"")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Comment {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    @NonNull
    private String text;


    @ManyToOne()
    @JoinColumn(name = "post_id", nullable = false)
    @NonNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Post post;


    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
    @NonNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User user;

    @Column
    private Calendar created_at = Calendar.getInstance();
}
