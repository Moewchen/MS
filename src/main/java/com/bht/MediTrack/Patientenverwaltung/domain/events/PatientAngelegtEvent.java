package com.bht.MediTrack.Patientenverwaltung.domain.events;

public class PatientAngelegtEvent {

        private final String vorname;
        private final String nachname;
        private final String geburtsdatum;
        private final String geschlecht;
        private final String adresse;
        private final String telefonnummer;
        private final String email;

        public PatientAngelegtEvent(String vorname, String nachname, String geburtsdatum, String geschlecht, String adresse, String telefonnummer, String email) {
            this.vorname = vorname;
            this.nachname = nachname;
            this.geburtsdatum = geburtsdatum;
            this.geschlecht = geschlecht;
            this.adresse = adresse;
            this.telefonnummer = telefonnummer;
            this.email = email;
        }

        public String getVorname() {
            return vorname;
        }

        public String getNachname() {
            return nachname;
        }

        public String getGeburtsdatum() {
            return geburtsdatum;
        }

        public String getGeschlecht() {
            return geschlecht;
        }

        public String getAdresse() {
            return adresse;
        }

        public String getTelefonnummer() {
            return telefonnummer;
        }

        public String getEmail() {
            return email;
        }
}
