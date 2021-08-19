package com.example.mvvm_architecture_android

import android.os.Parcel
import android.os.Parcelable
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.mvvm_architecture_android.data.api.ApiHelper
import com.example.mvvm_architecture_android.data.api.ApiService
import com.example.mvvm_architecture_android.data.api.ApiServiceImpl
import com.example.mvvm_architecture_android.data.model.User
import com.example.mvvm_architecture_android.data.repository.MainRepository
import com.example.mvvm_architecture_android.ui.view.main.viewmodel.MainViewModel
import com.example.mvvm_architecture_android.utils.Resource
import io.reactivex.Single
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SingleNetworkCallViewModelTest {

    /*needed to test code with LiveData-if we don't use this, we'll get a RuntimeException related to Looper in Android*/
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var single: Single<List<User>>

    @Mock
    private lateinit var list: List<User>

    @Mock
    private lateinit var mainRepository: MainRepository

    @Mock
    private lateinit var usersObserver: Observer<Resource<List<User>>>

    @Before
    fun setUp() {
        // do something if required
    }

    /*When the server gives 200, it should return success to the UI layer.
    We've mocked the ApiHelper to return the success with an empty list. Then we fetch and verify.*/
    @Test
    fun givenServerResponse200_whenFetch_shouldReturnSuccess() {
        testCoroutineRule.runBlockingTest {
            doReturn(single)
                .`when`(mainRepository)
                .getUsers()
            val viewModel = MainViewModel(mainRepository)
            viewModel.getUsers().observeForever(usersObserver)
            verify(mainRepository).getUsers()
            verify(usersObserver).onChanged(Resource.success(list))
            viewModel.getUsers().removeObserver(usersObserver)
        }
    }

    @After
    fun tearDown() {
        // do something if required
    }
}