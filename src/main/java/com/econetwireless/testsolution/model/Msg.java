/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.econetwireless.testsolution.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.econetwireless.testsolution.utils.MessageStatus;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author thembelani
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
@Entity
public class Msg implements Serializable {
    private static final Long serialVersionUID = 1L;
    @Id
    private String id;
    @Column(nullable = false)
    private long version;
    private String source;
    private String payload;
    private String ref;
    @Enumerated(EnumType.STRING)
    private MessageStatus status;

}
