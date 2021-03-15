package com.scb.rider.configserver.model.entity;

import java.io.Serializable;

import javax.persistence.*;

import lombok.*;

/**
 * @author TTDKOC
 *
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "properties")
public class Property implements Serializable {

    private static final long serialVersionUID = 6747531716778688077L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROPERTY_SEQ")
    @SequenceGenerator(name="PROPERTY_SEQ", sequenceName="PROPERTY_SEQ", allocationSize=1)
    private Long id;

    private String application;

    private String profile;

    private String label;

    @Column(name="prop_key")
    private String key;

    @Column(name="prop_value")
    private String value;
}

