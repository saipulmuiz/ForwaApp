package id.saipulmuiz.forwaapp.ui.event.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.shashank.sony.fancytoastlib.FancyToast
import id.saipulmuiz.forwaapp.R
import id.saipulmuiz.forwaapp.data.model.Status
import id.saipulmuiz.forwaapp.databinding.FragmentEventDetailBinding
import id.saipulmuiz.forwaapp.ui.base.BaseFragment
import id.saipulmuiz.forwaapp.util.hide
import id.saipulmuiz.forwaapp.util.show
import id.saipulmuiz.forwaapp.util.showDialogWarning

class EventDetailFragment : BaseFragment<FragmentEventDetailBinding, EventDetailViewModel>() {

    override var getLayoutId: Int = R.layout.fragment_event_detail
    override var getViewModel: Class<EventDetailViewModel> = EventDetailViewModel::class.java
    override var title: MutableLiveData<String> = MutableLiveData("Detail Event")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setContentPlaceholder(1)

        mViewBinding.apply {
            arguments?.let {
                val event = EventDetailFragmentArgs.fromBundle(it).event
                val eventId = EventDetailFragmentArgs.fromBundle(it).eventId
                if (event != null) {
                    setContentPlaceholder(1)
                    this.event = event
                } else {
                    observeDetail(eventId)
                }
            }

            eventDetBtnBack.setOnClickListener {
                activity?.onBackPressed()
            }
            eventDetBtnShare.setOnClickListener {
                FancyToast.makeText(
                    activity,
                    "Button Share Clicked!",
                    FancyToast.LENGTH_LONG,
                    FancyToast.SUCCESS,
                    false
                ).show()
            }
        }
    }

    private fun observeDetail(eventId: String) {
        mViewBinding.apply {
            eventProgressBar.show()

            mViewModel.getDetail(eventId)
                .observe(viewLifecycleOwner, Observer {
                    it?.let { status ->
                        when (status.status) {
                            Status.StatusType.SUCCESS -> {
                                it.data?.let { data ->
                                    eventProgressBar.hide()
                                    setContentPlaceholder(1)
                                    event = data
                                }
                            }
                            Status.StatusType.ERROR -> {
                                eventProgressBar.hide()
                                showDialogWarning(mDialog, status.message ?: "Error", null)
                                setContentPlaceholder(2)
                            }
                        }
                    }
                })
        }
    }
}