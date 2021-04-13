package com.rsk

fun main() {
    val repository = MeetingFileSystemRepository(MeetingJsonSerializer())
    var participants = listOf(ParticipantEntity("Kevin"), ParticipantEntity("Andy"))

    val meeting = MeetingEntity(0,"Review", LocationEntity("Saphire"), participants )
    repository.create(meeting)

    participants = listOf(ParticipantEntity("Kevin"), ParticipantEntity("Simon"))
    repository.create(MeetingEntity(0,"Lunch", LocationEntity("Starbucks"), participants ))

    repository.get().forEach { println(it) }

    repository.delete(2)

    println()
    repository.get().forEach { println(it) }



}