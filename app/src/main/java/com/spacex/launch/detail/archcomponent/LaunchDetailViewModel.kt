package com.spacex.launch.detail.archcomponent

import androidx.lifecycle.MutableLiveData
import com.spacex.launch.common.archcomponent.BaseViewModel
import com.spacex.launch.common.model.*
import com.spacex.launch.detail.model.Detail
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import javax.inject.Inject

/**
 * @author Hari Hara Sudhan.N
 */
class LaunchDetailViewModel @Inject
constructor(private val launchDetailUseCase: LaunchDetailUseCase)
    : BaseViewModel(), SingleObserver<List<Detail>>{

    private var launchDetailLiveData: MutableLiveData<ViewState<Detail>> = MutableLiveData()

    init {
        launchDetailUseCase.setObserver(this)
    }

    override fun onSuccess(details: List<Detail>) {
        launchDetailLiveData.postValue(DataState(details))
    }

    override fun onSubscribe(diposable: Disposable) {
        launchDetailLiveData.postValue(LoadingState(emptyList()))
    }

    override fun onError(error: Throwable) {
        launchDetailLiveData.postValue(ErrorState(error, emptyList()))
    }

    fun getViewStateObserver(): MutableLiveData<ViewState<Detail>> = launchDetailLiveData

    fun invoke(launchData: LaunchData?) {
        launchDetailUseCase.invoke(launchData)
    }

}