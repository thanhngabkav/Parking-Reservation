package com.atp.webservice.parking_reservation_10.entities;


import com.atp.webservice.parking_reservation_10.entities.uitls.TableName;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = TableName.SERVICE)
public class Service implements Serializable{

    @Id
    @Column(name = "service_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Column(name = "service_name")
    private String serviceName;

    @ManyToMany(mappedBy = "services")
    private List<Station> stations;

    @OneToMany(mappedBy = "service")
    @JsonIgnore
    private List<TicketType> ticketTypes;


    public int getServiceID() {
        return ID;
    }

    public void setServiceID(int serviceID) {
        this.ID = serviceID;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Service setStations(List<Station> stations) {
        this.stations = stations;
        return this;
    }

    public Service() {
    }

    public Service(String serviceName) {
        this.serviceName = serviceName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Service service = (Service) o;
        return Objects.equals(ID, service.ID);
    }

    @Override
    public int hashCode() {

        return Objects.hash(ID);
    }


    @Override
    public String toString() {
        return "Service{" +
                "serviceID='" + ID + '\'' +
                ", serviceName='" + serviceName + '\'' +
                '}';
    }
}
