package org.example.DZ3;

import lombok.*;


import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name = "\"user\"")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class User {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    @NonNull
    private String name;

    @Column(nullable = false)
    @NonNull
    private String password;

    @Column
    private Calendar created_at = Calendar.getInstance();

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Post> posts;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Comment> comments;
}
