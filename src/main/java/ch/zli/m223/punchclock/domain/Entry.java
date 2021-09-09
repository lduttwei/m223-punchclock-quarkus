package ch.zli.m223.punchclock.domain;

import javax.persistence.*;
import javax.ws.rs.BadRequestException;
import java.time.LocalDateTime;

@Entity
public class Entry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private User user;

    @ManyToOne
    private Place place;

    @ManyToOne
    private Project project;

    @Column(nullable = false)
    private LocalDateTime checkIn;

    @Column(nullable = false)
    private LocalDateTime checkOut;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) throws BadRequestException {
        if ( user == null ){
            throw new BadRequestException();
        }
        this.user = user;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) throws BadRequestException {
        this.place = place;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) throws BadRequestException{
        this.project = project;
    }

    public LocalDateTime getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDateTime checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDateTime getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDateTime checkOut) {
        this.checkOut = checkOut;
    }
}
