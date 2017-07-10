/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.econetwireless.testsolution.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Map;

/**
 *
 * @author thembelani
 */
@Data
@XmlRootElement
public class Config {
    private Map<String,Integer> config;
    private int sinkRate;
}
