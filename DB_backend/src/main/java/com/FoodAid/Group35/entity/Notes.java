package com.FoodAid.Group35.entity;

import javax.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "notes")
@EntityListeners(AuditingEntityListener.class)
public class Notes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String systemNote;
    public void setId(Long id) {
        this.id = id;
    }
    public String getSystemNote() {
        return systemNote;
    }
    public void setSystemNote(String systemNote) {
        this.systemNote = systemNote;
    }
}
