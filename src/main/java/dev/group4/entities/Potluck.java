package dev.group4.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

    @Entity
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Table(name = "item")
    public class Potluck {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "potluck_id")
        private String id;

        @Column(name = "date_time")
        private long dateTime;

        @Column(name = "creator_id")
        private String creatorId;

}
