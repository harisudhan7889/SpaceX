package com.spacex.launch.list.endpoint

import com.spacex.launch.common.model.LaunchData
import io.reactivex.Single
import retrofit2.http.GET

/**
 * Api end point interface which contains api for spacex launches.
 * In future can be included with more api methods.
 *
 * @author Hari Hara Sudhan.N
 */
interface LauncheListApi {
    /**
     * Get all spacex past launches.
     */
    @GET("launches/past")
    fun getPastLaunches(): Single<List<LaunchData>>
}