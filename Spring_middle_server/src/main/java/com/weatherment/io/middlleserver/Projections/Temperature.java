package com.weatherment.io.middlleserver.Projections;


import javax.persistence.*;

@Entity
@Table(name="temperature")
public class Temperature {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "value")
    private float value;

    @Column(name = "emitted_at")
    private Long emitted_at;

    public Long getId() {
        return id;
    }


    public Long getEmitted_at() {
        return emitted_at;
    }

    public void setEmitted_at(Long emitted_at) {
        this.emitted_at = emitted_at;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getValue() {
        return (float) (Math.round(value * 10.0) / 10.0);
    }

    public void setValue(float value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ID : " + getId() + "\nValue : " + getValue() + "\nemitted_at : " + emitted_at;
    }
}
