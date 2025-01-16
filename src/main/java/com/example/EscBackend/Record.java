package com.example.EscBackend;



import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;
@Entity
@Table(name="records")
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String name;
    private String telegram;
    private String phone;
    private String option;
    private LocalDateTime date;
    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    //datetime оставления заявки

    public Record() {
    }

    public Record(String name, String telegram, String phone, String option,LocalDateTime date) {
        this.name = name;
        this.telegram = telegram;
        this.phone = phone;
        this.option = option;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelegram() {
        return telegram;
    }

    public void setTelegram(String telegram) {
        this.telegram = telegram;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Record record = (Record) o;
        return Objects.equals(name, record.name) && Objects.equals(telegram, record.telegram) && Objects.equals(phone, record.phone) && Objects.equals(option, record.option);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, telegram, phone, option);
    }

    @Override
    public String toString(){
        return "Client "+name+" requested "+option+".\nContacts:\n Telegram: "+telegram+"\n Phone: "+phone;
    }
}

