package com.bootcamp.assignment1.service.implementations

import org.springframework.stereotype.Service

@Service
class PingService(val timeService: TimeService) {

    fun pongAndCurrentTime() : String{
        return "Pong, " + timeService.getCurrentTime();
    }

}