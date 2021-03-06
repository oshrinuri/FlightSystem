package com.flightsystem.flights.dtos;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@ToString
@Entity
@Table(name = "\"Messages\"")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"ID\"", nullable = false)
    private int id;
    @Column(name = "\"Sender_Email\"",nullable = false)
    private String senderEmail;
    @Column(name = "\"Message_Content\"",length = 1000)
    private String messageContent;
}
