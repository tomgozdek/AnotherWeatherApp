package tgozdek.com.anotherweatherapp.domain.commands

public interface Command<out T> {
    fun execute(): T
}