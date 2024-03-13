package dhbw.ase.listitcrudmaven.domain;


public class GroceryItem {
    private String itemName;
    private int preis;
    private Integer anzahl; // Kann null sein, um Optionalität auszudrücken
    private boolean isChecked; // Kann nachträglich geändert werden

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getPreis() {
        return preis;
    }

    public void setPreis(int preis) {
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

    public GroceryItem(String itemName, int preis, Integer anzahl, boolean isChecked) {
        this.itemName = itemName;
        this.preis = preis;
        this.anzahl = anzahl;
        this.isChecked = false;
    }

}
