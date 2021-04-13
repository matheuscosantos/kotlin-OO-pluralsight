package com.rsk


open class Meeting(val meetingName: String, open val location: Location, val logger: Logger) {

    open val locationName = ""
    private val _participants = mutableListOf<Participant>()

    val participants : List<Participant>
        get() = _participants

    fun addParticipant(participant: Participant) {
        logger.info("Participant added")
        if(verifyParticipant(participant)) {
            _participants.add(participant)
            println("Added: ${participant.participantName}")
        }
    }

    private fun verifyParticipant(participant: Participant) : Boolean {
        println("Try to verify")
        return true
    }

    protected open fun verifyMeeting() {
        println("Meeting: verify meeting")
    }
}

class PersonalReview(meetingName: String, val employee: Participant, reviewers: List<Participant>, override val location: Room, logger: Logger)
    : Meeting(meetingName, location, logger) {

    override val locationName
        get() = location.roomName

    fun closeReview() {
        println("Review ended")
        verifyMeeting()
    }

    override fun verifyMeeting() {
        println("PersonalReview: verify meeting")
        super.verifyMeeting()
    }
}



















