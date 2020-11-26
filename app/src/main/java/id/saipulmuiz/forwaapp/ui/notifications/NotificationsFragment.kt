package id.saipulmuiz.forwaapp.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import id.saipulmuiz.forwaapp.R
import id.saipulmuiz.forwaapp.databinding.FragmentNotificationsBinding
import id.saipulmuiz.forwaapp.ui.base.BaseFragment

class NotificationsFragment : BaseFragment<FragmentNotificationsBinding, NotificationsViewModel>() {

    override var getLayoutId: Int = R.layout.fragment_notifications
    override var getViewModel: Class<NotificationsViewModel> = NotificationsViewModel::class.java
    override var title: MutableLiveData<String> = MutableLiveData("Events")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}