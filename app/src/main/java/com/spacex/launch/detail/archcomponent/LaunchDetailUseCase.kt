package com.spacex.launch.detail.archcomponent

import com.spacex.launch.common.archcomponent.UseCase
import com.spacex.launch.common.extension.basic
import com.spacex.launch.common.extension.media
import com.spacex.launch.common.extension.other
import com.spacex.launch.common.model.LaunchData
import com.spacex.launch.detail.model.BasicDetail
import com.spacex.launch.detail.model.Detail
import com.spacex.launch.detail.model.MediaDetail
import com.spacex.launch.detail.model.OtherDetail
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * @author Hari Hara Sudhan.N
 */
class LaunchDetailUseCase @Inject constructor(): UseCase<LaunchData, Detail>() {

    private var singleObserver: SingleObserver<List<Detail>>? = null

    /**
     * Splits the details of the selected
     * launch into basic, media and other.
     * And add these details in a list.
     *
     * @param data Selected launch detail.
     *
     * @see BasicDetail
     * @see MediaDetail
     * @see OtherDetail
     */
    override fun invoke(data: LaunchData?) {
        singleObserver?.run {
            data?.let {
                Single.just(data)
                        .map { launchData ->
                            val details = ArrayList<Detail>()
                            details addItem launchData.basic()
                            details addItem launchData.media()
                            details addItem launchData.other()
                            details
                        }.subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this)
            }
        }
    }

    /**
     * Observer that listens
     * the result from the processed usecase.
     */
    override fun setObserver(singleObserver: SingleObserver<List<Detail>>?) {
        this.singleObserver = singleObserver
    }

    /**
     * infix function to add detail to the array.
     * This is used to check null safety at one place
     * before adding the detail to the array.
     *
     * @param detail can be any type of detail scoped to generic.
     */
    private infix fun ArrayList<Detail>.addItem(detail: Detail?) {
        detail?.let {
            add(it)
        }
    }

}