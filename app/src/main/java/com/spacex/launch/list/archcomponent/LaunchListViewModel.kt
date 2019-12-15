package com.spacex.launch.list.archcomponent

import androidx.lifecycle.MutableLiveData
import com.spacex.launch.common.archcomponent.BaseViewModel
import com.spacex.launch.common.model.DataState
import com.spacex.launch.common.model.LaunchData
import com.spacex.launch.common.model.LoadingState
import com.spacex.launch.common.model.ViewState
import com.spacex.launch.common.model.ErrorState

import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import javax.inject.Inject

/**
 * @author Hari Hara Sudhan.N
 */
class LaunchListViewModel @Inject
constructor(private val launchListUseCase: LaunchListUseCase)
    : BaseViewModel(), SingleObserver<List<LaunchData>> {

    private var launchListLiveData: MutableLiveData<ViewState<LaunchData>> = MutableLiveData()

    init {
        launchListUseCase.setObserver(this)
    }

    fun invoke() {
        launchListUseCase.invoke()
    }

    /**
     * These observer methods will be called from the LaunchListUseCase
     *
     * @see LaunchListUseCase
     */
    override fun onSubscribe(d: Disposable) {
        launchListLiveData.postValue(LoadingState(emptyList()))
    }

    override fun onSuccess(launches: List<LaunchData>) {
        launchListLiveData.postValue(DataState(launches))
        launchListUseCase.cacheInDatabase(launches)
    }

    override fun onError(error: Throwable) {
        launchListLiveData.postValue(ErrorState(error, emptyList()))
    }

    /**
     * Method to get the LiveData Observer for the screen states
     *
     * @see LoadingState
     * @see DataState
     * @see ErrorState
     */
    fun getViewStateObserver(): MutableLiveData<ViewState<LaunchData>> = launchListLiveData
}