package com.capstone.pantauharga.ui.provinsi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.pantauharga.data.response.DataItemDaerah
import com.capstone.pantauharga.data.retrofit.ApiConfig
import kotlinx.coroutines.launch

class ProvinceViewModel : ViewModel() {
    private val _provinsi = MutableLiveData<List<DataItemDaerah>>()
    val provinsi: LiveData<List<DataItemDaerah>> get() = _provinsi

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> get() = _error

    fun getProvinces(commodityId: String) {
        _loading.value = true

        viewModelScope.launch {
            try {
                val responseProvinsi = ApiConfig.getApiService().getProvincesByCommodity(commodityId)
                _loading.value = false
                _provinsi.postValue(responseProvinsi.data)
            } catch (e: Exception) {
                _loading.value = false
                setError(true)
            }
        }
    }

    fun setError(value: Boolean) {
        _error.value = value
    }
}