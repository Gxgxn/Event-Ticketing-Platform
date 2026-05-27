package com.gxgxn.tickets.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class User {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    //TODO: Organized Events
    @OneToMany(mappedBy = "organizer", cascade = CascadeType.ALL)
    private List<Event> organizedEvents = new ArrayList<>();

    //TODO: Attending Events
    @ManyToMany
    @JoinTable(
        name = "user_attending_events",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private List<Event> attendingEvents = new ArrayList<>();

    //TODO: Staffing Events
    @ManyToMany
    @JoinTable(
            name="user_staffing_event",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private List<Event> staffingEvents = new ArrayList<>();

    @CreatedDate
    @Column(name="created_at", updatable = false, nullable = false)
    private LocalDate creationDate;

    @LastModifiedDate
    @Column(name="updated_at", nullable = false)
    private LocalDate lastModifiedDate;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(creationDate, user.creationDate) && Objects.equals(lastModifiedDate, user.lastModifiedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, creationDate, lastModifiedDate);
    }
}
