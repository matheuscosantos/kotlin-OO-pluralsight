package com.rsk

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import java.io.File

//interface IMeetingRepository {
//    fun get() : List<Meeting>
//    fun get(id: Int) : Meeting
//    fun create(meeting: Meeting) : Meeting
//    fun update(meeting: Meeting): Boolean
//    fun delete(id: Int): Boolean
//}

interface IRepository<T> {
    fun get() : List<T>
    fun get(id: Int) : T
    fun create(meeting: T) : T
    fun update(meeting: T): Boolean
    fun delete(id: Int): Boolean
}

interface IMeetingRepository : IRepository<MeetingEntity>

abstract class FileSystemRepository<T: Entity>(val serializer: IJsonSerializer<T>, val filename: String = "database.json") : IRepository<T> {
    init {
        val file = File(filename)
        if(!file.isFile) file.createNewFile()
        println(file.absolutePath)
    }
    private fun loadEntities(): List<T> {

        // open file
        val data = File(filename).readText()
        // deserialize meetings

        return serializer.read(data)
    }


    override fun get(): List<T> {
        return loadEntities()
    }


    override fun get(id: Int): T {
        val entity = loadEntities()
        return entity.first { it.id == id }
    }

    override fun create(entity: T): T {
        val entities = loadEntities()
        val mutableMeetings = entities.toMutableList()
        val newEntity = copyEntity(entity, entities.size + 1)
        mutableMeetings.add(newEntity)

        // serialize and save
        saveEntities(mutableMeetings)

        // add a new one
        return entity
    }

    private fun saveEntities(mutableMeetings: MutableList<T>) {
        val jsonData = serializer.write(mutableMeetings)

        File(filename).writeText(jsonData)
    }

    override fun update(entity: T): Boolean {
        val entities = loadEntities()
        val filteredMeetings = entities.filter { it.id != entity.id }.toMutableList()
        filteredMeetings.add(entity)

        saveEntities(filteredMeetings)

        return true
    }

    override fun delete(id: Int): Boolean {
        val entities = loadEntities()
        val filteredMeetings = entities.filter { it.id != id }.toMutableList()

        saveEntities(filteredMeetings)

        return true
    }

    abstract fun copyEntity(entity: T, newId: Int) : T


}

class MeetingFileSystemRepository(serializer: IJsonSerializer<MeetingEntity>) : FileSystemRepository<MeetingEntity>(serializer), IMeetingRepository {
    private val json = Json(JsonConfiguration.Default)

    override fun copyEntity(entity: MeetingEntity, newId: Int): MeetingEntity {
        return entity.copy(id = newId)
    }

}



















