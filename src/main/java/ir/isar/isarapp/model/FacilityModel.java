package ir.isar.isarapp.model;

import java.io.Serializable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author User
 */
public class FacilityModel implements Serializable {

    private final StringProperty hadieVorood = new SimpleStringProperty();

    public String getHadieVorood() {
        return hadieVorood.get();
    }

    public void setHadieVorood(String value) {
        hadieVorood.set(value);
    }

    public StringProperty hadieVoroodProperty() {
        return hadieVorood;
    }
    private final StringProperty komakHazine = new SimpleStringProperty();

    public String getKomakHazine() {
        return komakHazine.get();
    }

    public void setKomakHazine(String value) {
        komakHazine.set(value);
    }

    public StringProperty komakHazineProperty() {
        return komakHazine;
    }
    private final StringProperty komakShahrie = new SimpleStringProperty();

    public String getKomakShahrie() {
        return komakShahrie.get();
    }

    public void setKomakShahrie(String value) {
        komakShahrie.set(value);
    }

    public StringProperty komakShahrieProperty() {
        return komakShahrie;
    }
    private final StringProperty tashvighErtegha = new SimpleStringProperty();

    public String getTashvighErtegha() {
        return tashvighErtegha.get();
    }

    public void setTashvighErtegha(String value) {
        tashvighErtegha.set(value);
    }

    public StringProperty tashvighErteghaProperty() {
        return tashvighErtegha;
    }
    private final StringProperty hadieMomtazin = new SimpleStringProperty();

    public String getHadieMomtazin() {
        return hadieMomtazin.get();
    }

    public void setHadieMomtazin(String value) {
        hadieMomtazin.set(value);
    }

    public StringProperty hadieMomtazinProperty() {
        return hadieMomtazin;
    }
    private final StringProperty hazineEskan = new SimpleStringProperty();

    public String getHazineEskan() {
        return hazineEskan.get();
    }

    public void setHazineEskan(String value) {
        hazineEskan.set(value);
    }

    public StringProperty hazineEskanProperty() {
        return hazineEskan;
    }
    private final StringProperty hazinePayanName = new SimpleStringProperty();

    public String getHazinePayanName() {
        return hazinePayanName.get();
    }

    public void setHazinePayanName(String value) {
        hazinePayanName.set(value);
    }

    public StringProperty hazinePayanNameProperty() {
        return hazinePayanName;
    }
    private final StringProperty hadieFaregholTahsilan = new SimpleStringProperty();

    public String getHadieFaregholTahsilan() {
        return hadieFaregholTahsilan.get();
    }

    public void setHadieFaregholTahsilan(String value) {
        hadieFaregholTahsilan.set(value);
    }

    public StringProperty hadieFaregholTahsilanProperty() {
        return hadieFaregholTahsilan;
    }

}
