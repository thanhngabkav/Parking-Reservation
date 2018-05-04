package com.atp.webservice.parking_reservation_10.entities;

import com.atp.webservice.parking_reservation_10.entities.uitls.DefaultValue;
import com.atp.webservice.parking_reservation_10.entities.uitls.TableName;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Table(name = TableName.OWNER)
@Entity
@AttributeOverrides({
        @AttributeOverride(name="id", column=@Column(name="id"))
})
public class Owner extends User implements Serializable{

//    @Id
//    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private String ID;

    @Column(name = "name", length = 250, nullable = false)
    private String name;

    @Column(name = "address", length = 250, nullable = false)
    private String address;

    @Column(name = "user_name", length = 150)
    private String userName;

    @Column(name = "bank_account_number", length = 200)
    private String bankAccountNumber;

    @Column(name = "bank", length = 150)
    private String bankName;

    @Column(name = "secret_key")
    @Type(type =  "text")
    private String secretKey;

    @Column(name = "created_time")
    private Timestamp createdTime;


    @OneToMany(mappedBy = "owner", cascade = CascadeType.PERSIST)
    private List<Station> stations;

    public Owner(String name, String address, String userName, String bankAccountNumber, String bankName) {
        this.name = name;
        this.address = address;
        this.userName = userName;
        this.bankAccountNumber = bankAccountNumber;
        this.bankName = bankName;
    }

    public Owner() {
        this(DefaultValue.STRING, DefaultValue.STRING, DefaultValue.STRING, DefaultValue.STRING, DefaultValue.STRING);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public Owner setSecretKey(String secretKey) {
        this.secretKey = secretKey;
        return this;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public Owner setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Owner that = (Owner) o;
        return this.getUserID() == that.getUserID();
    }

    @Override
    public int hashCode() {

        return Objects.hash(this.getUserID());
    }
}

