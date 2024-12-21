package org.example.ESCBackend.model;

import java.util.Objects;

public class Record {
    private String name;
    private String telegram;
    private String phone;
    private String option;

    public Record(String name, String telegram, String phone, String option) {
        this.name = name;
        this.telegram = telegram;
        this.phone = phone;
        this.option = option;
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
}
