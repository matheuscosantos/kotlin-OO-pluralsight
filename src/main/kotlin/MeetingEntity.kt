package com.rsk

import kotlinx.serialization.Serializable

interface Entity {
    val id: Int
}

@Serializable
data class MeetingEntity(override val id: Int, val meetingName: String, val location: LocationEntity, val participants: List<ParticipantEntity>) : Entity

@Serializable
data class ParticipantEntity(val name: String)

@Serializable
data class LocationEntity(val roomName: String)