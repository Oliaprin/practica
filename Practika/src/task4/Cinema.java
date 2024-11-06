package task4;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
public class Cinema {
    private String address;
    private List<Session> sessions;

    public Cinema(String address) {
        this.address = address;
        this.sessions = new ArrayList<>();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void addSession(String movieTitle, LocalTime startTime) {
        sessions.add(new Session(movieTitle, startTime));
    }

    public List<Session> getSessions() {
        return sessions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cinema cinema = (Cinema) o;
        return Objects.equals(address, cinema.address) &&
                Objects.equals(sessions, cinema.sessions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, sessions);
    }

    @Override
    public String toString() {
        return "Кинотеатр{" +
                "адрес='" + address + '\'' +
                ", сеансы=" + sessions +
                '}';
    }

    public static class Session {
        private String movieTitle;
        private LocalTime startTime;

        public Session(String movieTitle, LocalTime startTime) {
            this.movieTitle = movieTitle;
            this.startTime = startTime;
        }

        public String getMovieTitle() {
            return movieTitle;
        }

        public void setMovieTitle(String movieTitle) {
            this.movieTitle = movieTitle;
        }

        public LocalTime getStartTime() {
            return startTime;
        }

        public void setStartTime(LocalTime startTime) {
            this.startTime = startTime;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Session session = (Session) o;
            return Objects.equals(movieTitle, session.movieTitle) &&
                    Objects.equals(startTime, session.startTime);
        }

        @Override
        public int hashCode() {
            return Objects.hash(movieTitle, startTime);
        }

        @Override
        public String toString() {
            return "Сеанс{" +
                    "фильм='" + movieTitle + '\'' +
                    ", время начала=" + startTime +
                    '}';
        }
    }

    public static void main(String[] args) {
        Cinema cinema = new Cinema("123 Главная ул, Город");

        cinema.addSession("Матрица", LocalTime.of(19, 0));
        cinema.addSession("Начало", LocalTime.of(21, 30));

        System.out.println(cinema);

        for (Session session : cinema.getSessions()) {
            System.out.println("Фильм: " + session.getMovieTitle() + ", Время начала: " + session.getStartTime());
        }
    }
}
