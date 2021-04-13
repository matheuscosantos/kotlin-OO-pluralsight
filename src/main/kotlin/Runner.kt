package com.rsk

import java.io.File
import java.nio.file.Paths

fun main() {

    val postCode = USZipCode("12345")

    val logger: Logger = FileLogger(Paths.get("/some/file.log"))

    val meeting = Meeting("Review", UkAddress("a house", "a street", "a town", "a county", UKPostCode("")), logger)
    val review = PersonalReview("Review Meeting", Participant(Name("Alice"), ""), listOf(), Room("Room 1"), logger )

    println("Created: $review with name ${review.meetingName} at ${review.locationName}")

    review.closeReview()

    var name = Name("Kevin Jones")
    var participant = Participant(name, "Kevin@Rocksolidknowledge.com")
    meeting.addParticipant(participant)

    name = Name("Alice Smith")
    participant = Participant(name, "alice@Rocksolidknowledge.com")
    meeting.addParticipant(participant)

    println("-------- Participants -------------")
    meeting.participants.forEach {
        println(it.name)
    }
    println("-------- Participants -------------")

}