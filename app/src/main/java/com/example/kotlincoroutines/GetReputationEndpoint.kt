package com.example.kotlincoroutines

import com.example.kotlincoroutines.common.ThreadInfoLogger
import kotlinx.coroutines.delay
import kotlin.random.Random

class GetReputationEndpoint
{
     suspend fun getReputation(userId: String): Int
    {

        ThreadInfoLogger.logThreadInfo("GetReputationEndpoint#getReputation(): called")
        delay(3000)
        ThreadInfoLogger.logThreadInfo("GetReputationEndpoint#getReputation(): return data")
        return Random.nextInt(0, 100)
    }
}