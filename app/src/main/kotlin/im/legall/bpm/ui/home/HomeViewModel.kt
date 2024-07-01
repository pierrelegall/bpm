package im.legall.bpm.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply { value = "Tap" }
    private val _buttonText = MutableLiveData<String>().apply { value = "This is a button" }

    val text: LiveData<String> = _text
    val buttonText: LiveData<String> = _buttonText
}
