package dhbw.domain;

import jakarta.persistence.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Items")
public class GroceryItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String itemName;
    @Column
    private float preis;
    @Column
    private Integer anzahl;
    @Column
    private boolean isChecked;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public float getPreis() {
        return preis;
    }

    public void setPreis(float preis) {
        this.preis = preis;
    }

    public Integer getAnzahl() {
        return anzahl;
    }

    public void setAnzahl(Integer anzahl) {
        this.anzahl = anzahl;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public Long getId() {
        return id;
    }

    public GroceryItemEntity(String itemName, float preis, Integer anzahl, boolean isChecked) {
        this.itemName = itemName;
        this.preis = preis;
        this.anzahl = anzahl;
        this.isChecked = false;
    }
    public GroceryItemEntity() {

    }

}