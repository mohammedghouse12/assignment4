package com.bootcamp.assignment1.service.implementations

import org.springframework.core.env.Environment
import org.springframework.stereotype.Service

@Service
class VariableTimeService {

    fun vairableTime(environment: Environment, timeService : TimeService) : String {
        val activeProfile = environment.getActiveProfiles()[0]
        if(activeProfile=="qa")
            return "Bonjour, l'heure actuelle est " + timeService.getCurrentTime()
        else if(activeProfile=="prod")
            return "Hallo, die aktuelle Zeit ist " + timeService.getCurrentTime()
        else
            return "Hello, Current Time is " +timeService.getCurrentTime()
    }
}

