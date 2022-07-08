package com.microservices.entities;

import com.microservices.entities.Country;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

//@Entity
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;//generate
    private String name;
    private String phoneno;
    private String departmentit;
    private String status;
    @Column(updatable = false)
    private Date createddtm;//one time generate
    private String createdby;
    private Date updateddtm;//generate
    private String updatedby;
    //data loading on spot means eager
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cid")
    private Country country;//get from country class
}
